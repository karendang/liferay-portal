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

package com.liferay.dynamic.data.mapping.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Marcellus Tavares
 */
public class DDMFormLayout {

	public DDMFormLayout() {
	}

	public DDMFormLayout(DDMFormLayout ddmFormLayout) {
		_defaultLocale = ddmFormLayout._defaultLocale;

		for (DDMFormLayoutPage ddmFormLayoutPage :
				ddmFormLayout._ddmFormLayoutPages) {

			addDDMFormLayoutPage(new DDMFormLayoutPage(ddmFormLayoutPage));
		}
	}

	public void addDDMFormLayoutPage(DDMFormLayoutPage ddmFormLayoutPage) {
		_ddmFormLayoutPages.add(ddmFormLayoutPage);
	}

	public DDMFormLayoutPage getDDMFormLayoutPage(int index) {
		return _ddmFormLayoutPages.get(index);
	}

	public List<DDMFormLayoutPage> getDDMFormLayoutPages() {
		return _ddmFormLayoutPages;
	}

	public Locale getDefaultLocale() {
		return _defaultLocale;
	}

	public void setDDMFormLayoutPages(
		List<DDMFormLayoutPage> ddmFormLayoutPages) {

		_ddmFormLayoutPages = ddmFormLayoutPages;
	}

	public void setDefaultLocale(Locale defaultLocale) {
		_defaultLocale = defaultLocale;
	}

	private List<DDMFormLayoutPage> _ddmFormLayoutPages = new ArrayList<>();
	private Locale _defaultLocale;

}