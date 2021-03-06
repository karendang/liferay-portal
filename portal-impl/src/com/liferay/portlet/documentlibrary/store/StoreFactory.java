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

package com.liferay.portlet.documentlibrary.store;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 * @author Manuel de la Peña
 */
public class StoreFactory {

	public static StoreFactory getInstance() {
		if (_storeFactory == null) {
			_storeFactory = new StoreFactory();
		}

		return _storeFactory;
	}

	public void checkProperties() {
		if (_warned) {
			return;
		}

		String dlHookImpl = PropsUtil.get("dl.hook.impl");

		if (Validator.isNull(dlHookImpl)) {
			_warned = true;

			return;
		}

		boolean found = false;

		for (String key : _storeServiceTrackerMap.keySet()) {
			Store store = getStore(key);

			Class<?> clazz = store.getClass();

			String className = clazz.getName();

			if (dlHookImpl.equals(className)) {
				PropsValues.DL_STORE_IMPL = className;

				found = true;

				break;
			}
		}

		if (!found) {
			PropsValues.DL_STORE_IMPL = dlHookImpl;
		}

		if (_log.isWarnEnabled()) {
			StringBundler sb = new StringBundler(13);

			sb.append("Liferay is configured with the legacy ");
			sb.append("property \"dl.hook.impl=");
			sb.append(dlHookImpl);
			sb.append("\" ");
			sb.append("in portal-ext.properties. Please reconfigure ");
			sb.append("to use the new property \"");
			sb.append(PropsKeys.DL_STORE_IMPL);
			sb.append("\". Liferay will ");
			sb.append("attempt to temporarily set \"");
			sb.append(PropsKeys.DL_STORE_IMPL);
			sb.append("=");
			sb.append(PropsValues.DL_STORE_IMPL);
			sb.append("\".");

			_log.warn(sb.toString());
		}

		_warned = true;
	}

	public void destroy() {
		_storeServiceTrackerMap.close();

		_storeWrapperServiceTrackerMap.close();
	}

	public Store getStore() {
		if (_store == null) {
			setStore(PropsValues.DL_STORE_IMPL);
		}

		if (_store == null) {
			throw new IllegalStateException("Store is not available");
		}

		return _store;
	}

	public Store getStore(String key) {
		Store store = _storeServiceTrackerMap.getService(key);

		StoreWrapper storeWrapper = _storeWrapperServiceTrackerMap.getService(
			key);

		if (storeWrapper != null) {
			return storeWrapper.wrap(store);
		}

		return store;
	}

	public String[] getStoreTypes() {
		Set<String> storeTypes = _storeServiceTrackerMap.keySet();

		return storeTypes.toArray(new String[storeTypes.size()]);
	}

	public void setStore(String key) {
		if (key == null) {
			_store = null;

			return;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Set " + key);
		}

		_store = getStore(key);
	}

	private StoreFactory() {
		_storeServiceTrackerMap.open();

		_storeWrapperServiceTrackerMap.open();
	}

	private static final Log _log = LogFactoryUtil.getLog(StoreFactory.class);

	private static StoreFactory _storeFactory;
	private static boolean _warned;

	private volatile Store _store = null;
	private final ServiceTrackerMap<String, Store> _storeServiceTrackerMap =
		ServiceTrackerCollections.singleValueMap(Store.class, "store.type");
	private final ServiceTrackerMap<String, StoreWrapper>
		_storeWrapperServiceTrackerMap =
			ServiceTrackerCollections.singleValueMap(
				StoreWrapper.class, "store.type");

}