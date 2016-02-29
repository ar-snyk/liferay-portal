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

package com.liferay.portal.upgrade.v6_2_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.util.ParallelSchemaUpgraderExecutor;

/**
 * @author Raymond Augé
 */
public class UpgradeSchema extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		ParallelSchemaUpgraderExecutor parallelSchemaUpgraderExecutor =
			new ParallelSchemaUpgraderExecutor(
				"update-6.1.1-6.2.0.sql", "update-6.1.1-6.2.0_dl.sql",
				"update-6.1.1-6.2.0_expando.sql",
				"update-6.1.1-6.2.0_group.sql", "update-6.1.1-6.2.0_user.sql",
				"update-6.1.1-6.2.0_wiki.sql");

		parallelSchemaUpgraderExecutor.execute();

		upgrade(UpgradeMVCCVersion.class);
	}

}