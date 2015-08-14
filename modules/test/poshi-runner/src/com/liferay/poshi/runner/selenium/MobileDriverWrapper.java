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

package com.liferay.poshi.runner.selenium;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;

import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Response;

/**
 * @author Kenji Heigel
 */
public class MobileDriverWrapper
	extends WebDriverWrapper implements MobileDriver {

	public MobileDriverWrapper(MobileDriver mobileDriver) {
		super(mobileDriver);

		_mobileDriver = mobileDriver;
	}

	/**
	 * Switch the focus of future commands for this driver to the context with
	 * the given name
	 *
	 * @param	name name of the context
	 * @return	returns the WebDriver focused on the given window
	 */
	@Override
	public WebDriver context(String name) {
		return _mobileDriver.context(name);
	}

	@Override
	public Response execute(String driverCommand, Map<String, ?> parameters) {
		return _mobileDriver.execute(driverCommand, parameters);
	}

	/**
	 * Return an opaque handle to this context that uniquely identifies it
	 * within this driver instance
	 *
	 * @return	Return an opaque handle to this context that uniquely identifies
	 * 			it within this driver instance
	 */
	@Override
	public String getContext() {
		return _mobileDriver.getContext();
	}

	/**
	 * Return a set of context handles which can be used to iterate over all
	 * contexts of this WebDriver instance
	 *
	 * @return	returns a set of context handles which can be used to iterate
	 * over available contexts
	 */
	@Override
	public Set<String> getContextHandles() {
		return _mobileDriver.getContextHandles();
	}

	/**
	 * Performs multiple TouchAction gestures at the same time, to simulate
	 * multiple fingers/touch inputs.
	 *
	 * @param	multiAction the MultiTouchAction object to perform
	 */
	@Override
	public void performMultiTouchAction(MultiTouchAction multiAction) {
		_mobileDriver.performMultiTouchAction(multiAction);
	}

	/**
	 * Performs a chain of touch actions, which together can be considered an
	 * entire gesture.
	 *
	 * @param 	touchAction TouchAction object, which contains a list of
	 * 			individual touch actions to perform
	 * @return 	returns the same touch action object
	 */
	@Override
	public TouchAction performTouchAction(TouchAction touchAction) {
		return _mobileDriver.performTouchAction(touchAction);
	}

	private final MobileDriver _mobileDriver;

}