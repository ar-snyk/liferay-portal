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

package com.liferay.headless.foundation.internal.resource;

import com.liferay.headless.foundation.dto.Category;
import com.liferay.headless.foundation.resource.CategoryResource;
import com.liferay.portal.kernel.model.Company;
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
public abstract class BaseCategoryResourceImpl implements CategoryResource {

	@Override
	public Category getCategories(Long categoriesId, Company company)
		throws Exception {

		return new Category();
	}

	@Override
	public Page<Category> getCategoriesCategoriesPage(
			Long categoriesId, Company company, Pagination pagination)
		throws Exception {

		return new Page<>(Collections.emptyList(), 0);
	}

	@Override
	public Page<Category> getVocabulariesCategoriesPage(
			Long vocabulariesId, Company company, Pagination pagination)
		throws Exception {

		return new Page<>(Collections.emptyList(), 0);
	}

	@Override
	public Category postCategoriesCategories(Long categoriesId, Company company)
		throws Exception {

		return new Category();
	}

	@Override
	public Category postCategoriesCategoriesBatchCreate(
			Long categoriesId, Company company)
		throws Exception {

		return new Category();
	}

	@Override
	public Category postVocabulariesCategories(
			Long vocabulariesId, Company company)
		throws Exception {

		return new Category();
	}

	@Override
	public Category postVocabulariesCategoriesBatchCreate(
			Long vocabulariesId, Company company)
		throws Exception {

		return new Category();
	}

	@Override
	public Category putCategories(Long categoriesId, Company company)
		throws Exception {

		return new Category();
	}

	protected <T, R> List<R> transform(
		List<T> list, Function<T, R> transformFunction) {

		return TransformUtil.transform(list, transformFunction);
	}

}