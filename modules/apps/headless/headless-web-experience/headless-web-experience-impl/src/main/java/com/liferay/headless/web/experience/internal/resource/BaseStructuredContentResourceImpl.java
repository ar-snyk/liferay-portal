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

package com.liferay.headless.web.experience.internal.resource;

import com.liferay.headless.web.experience.dto.StructuredContent;
import com.liferay.headless.web.experience.resource.StructuredContentResource;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.context.AcceptLanguage;
import com.liferay.portal.vulcan.context.Pagination;
import com.liferay.portal.vulcan.dto.Page;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public abstract class BaseStructuredContentResourceImpl
	implements StructuredContentResource {

	@Override
	public Page<StructuredContent> getContentSpaceStructuredContentsPage(
			Long contentSpaceId, String filter, String sort,
			AcceptLanguage acceptLanguage, Company company,
			Pagination pagination)
		throws Exception {

		return new Page<>(Collections.emptyList(), 0);
	}

	@Override
	public StructuredContent getStructuredContent(
			Long structuredContentsId, AcceptLanguage acceptLanguage,
			Company company)
		throws Exception {

		return new StructuredContent();
	}

	@Override
	public StructuredContent postContentSpaceStructuredContent(
			Long contentSpaceId, AcceptLanguage acceptLanguage, Company company)
		throws Exception {

		return new StructuredContent();
	}

	@Override
	public StructuredContent postContentSpaceStructuredContentsBatchCreate(
			Long contentSpaceId, AcceptLanguage acceptLanguage, Company company)
		throws Exception {

		return new StructuredContent();
	}

	@Override
	public StructuredContent putStructuredContent(
			Long structuredContentsId, AcceptLanguage acceptLanguage,
			Company company)
		throws Exception {

		return new StructuredContent();
	}

	protected <T, R> List<R> transform(
		List<T> list, Function<T, R> transformFunction) {

		return TransformUtil.transform(list, transformFunction);
	}

}