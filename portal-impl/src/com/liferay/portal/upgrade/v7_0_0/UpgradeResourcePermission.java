/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.dao.orm.common.SQLTransformer;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sampsa Sohlman
 */
public class UpgradeResourcePermission extends UpgradeProcess {

	protected void createIndex() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			runSQLTemplateString(
				"create index IX_D5F1E2A2 on ResourcePermission " +
					"(name[$COLUMN_LENGTH:255$])",
				false);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		createIndex();

		upgradeResourcePermissions();
	}

	protected void upgradeResourcePermissions() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			runSQL(
				"update ResourcePermission set viewActionId = [$FALSE$] " +
					"where MOD(actionIds, 2) = 0");
			runSQL(
				"update ResourcePermission set viewActionId = [$TRUE$] where " +
					"MOD(actionIds, 2) = 1");

			try (PreparedStatement ps1 = connection.prepareStatement(
					"select distinct name from ResourcePermission");
				ResultSet rs1 = ps1.executeQuery();
				PreparedStatement ps2 = connection.prepareStatement(
					"select distinct primKey from ResourcePermission where " +
						"name = ?")) {

				while (rs1.next()) {
					List<String> stringPrimKeys = new ArrayList<>();

					String name = rs1.getString("name");

					ps2.setString(1, name);

					try (ResultSet rs2 = ps2.executeQuery()) {
						while (rs2.next()) {
							String primKey = rs2.getString("primKey");

							if ((GetterUtil.getLong(primKey) <= 0) &&
								!primKey.contains(
									PortletConstants.LAYOUT_SEPARATOR)) {

								stringPrimKeys.add(primKey);
							}
						}
					}

					String[][] stringPrimKeysArray;

					DB db = DBManagerUtil.getDB();

					if ((db.getDBType() == DBType.ORACLE) &&
						(stringPrimKeys.size() > 1000)) {

						stringPrimKeysArray = (String[][])ArrayUtil.split(
							stringPrimKeys.toArray(), 1000);
					}
					else {
						stringPrimKeysArray = (String[][])ArrayUtil.split(
							stringPrimKeys.toArray(), stringPrimKeys.size());
					}

					_updatePrimKeyIdsByName(name, stringPrimKeysArray);
				}
			}
		}
	}

	private String _createInClause(String[] primKeys) {
		StringBundler sb = new StringBundler(primKeys.length + 1);

		sb.append("in (?");

		for (int i = 1; i < primKeys.length; i++) {
			sb.append(", ?");
		}

		sb.append(")");

		return sb.toString();
	}

	private void _updatePrimKeyIds(String sql, String name, String[] primKeys)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				SQLTransformer.transform(sql))) {

			ps.setString(1, name);

			for (int i = 0; i < primKeys.length; i++) {
				String primKey = primKeys[i];

				ps.setString(i + 2, primKey);
			}

			ps.executeUpdate();
		}
	}

	private void _updatePrimKeyIdsByName(String name, String[][] primKeyBatches)
		throws Exception {

		if (ArrayUtil.isEmpty(primKeyBatches)) {
			_updatePrimKeyIds(
				"update ResourcePermission set primKeyId = CAST_LONG(" +
					"primKey) where name = ? and primKey not like '%_LAYOUT_%'",
				name, new String[0]);

			_updatePrimKeyIds(
				"update ResourcePermission set primKeyId = 0 where name = ? " +
					"and primKey like '%_LAYOUT_%'",
				name, new String[0]);

			return;
		}

		for (String[] primKeyBatch : primKeyBatches) {
			String inClause = _createInClause(primKeyBatch);

			_updatePrimKeyIds(
				StringBundler.concat(
					"update ResourcePermission set primKeyId = 0 where name = ",
					"? and (primKey like '%_LAYOUT_%' or primKey ", inClause,
					")"),
				name, primKeyBatch);
		}

		runSQL(
			StringBundler.concat(
				"update ResourcePermission set primKeyId = CAST_LONG(primKey",
				") where name = '", name,
				"' and (primKey not like '%_LAYOUT_%' and primKeyId != 0)"));
	}

}