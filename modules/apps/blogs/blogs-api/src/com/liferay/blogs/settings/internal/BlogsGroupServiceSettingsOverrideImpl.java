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

package com.liferay.blogs.settings.internal;

import com.liferay.blogs.settings.BlogsGroupServiceSettingsOverride;
import com.liferay.portal.kernel.settings.TypedSettings;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Sergio González
 */
public class BlogsGroupServiceSettingsOverrideImpl
	implements BlogsGroupServiceSettingsOverride {

	public BlogsGroupServiceSettingsOverrideImpl(TypedSettings typedSettings) {
		_typedSettings = typedSettings;
	}

	@Override
	public boolean enableRss() {
		if (!PortalUtil.isRSSFeedsEnabled()) {
			return false;
		}

		return _typedSettings.getBooleanValue("enableRss");
	}

	private final TypedSettings _typedSettings;

}