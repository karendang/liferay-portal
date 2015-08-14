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

import com.liferay.poshi.runner.util.PropsValues;
import com.liferay.poshi.runner.util.StringUtil;

import com.thoughtworks.selenium.Selenium;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;

import java.io.IOException;

import java.util.List;

import org.openqa.selenium.WebElement;

import org.w3c.dom.Node;

/**
 * @author Kenji Heigel
 */
@SuppressWarnings("deprecation")
public class MobileDriverToSeleniumBridge
	extends MobileDriverWrapper implements Selenium {

	public MobileDriverToSeleniumBridge(MobileDriver mobileDriver) {
		super(mobileDriver);

		WebDriverHelper.setDefaultWindowHandle(mobileDriver.getWindowHandle());
	}

	@Deprecated
	@Override
	public void addCustomRequestHeader(String key, String value) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void addLocationStrategy(
		String strategyName, String functionDefinition) {

		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void addScript(String script, String scriptTagId) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void addSelection(String locator, String optionLocator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void allowNativeXpath(String allow) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void altKeyDown() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void altKeyUp() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void answerOnNextPrompt(String answer) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void assignId(String locator, String identifier) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void attachFile(String fieldLocator, String fileLocator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void captureEntirePageScreenshot(String fileName, String kwargs) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String captureEntirePageScreenshotToString(String kwargs) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String captureNetworkTraffic(String type) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void captureScreenshot(String fileName) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String captureScreenshotToString() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void check(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void chooseCancelOnNextConfirmation() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void chooseOkOnNextConfirmation() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Clicks at the current mouse location
	 *
	 * @param	locator location of the element
	 */
	@Override
	public void click(String locator) {
		try {
			tap(locator);
		}
		catch (Exception e) {
			if (!isInViewport(locator)) {
				swipeWebElementIntoView(locator);
			}

			tap(locator);
		}
	}

	/**
	 * Clicks at the current mouse location
	 *
	 * @param	coordString specifies the x,y position (i.e. - 10,20) of the
	 * 			mouse event relative to the element returned by the locator
	 * @param	locator location of the element
	 */
	@Override
	public void clickAt(String locator, String coordString) {
		clickAt(locator, coordString, true);
	}

	/**
	 * Clicks at the current mouse location
	 *
	 * @param	coordString specifies the x,y position (i.e. - 10,20) of the
	 * 			mouse event relative to the element returned by the locator
	 * @param	locator location of the element
	 * @param	scrollIntoView <code>true<code> if scroll into view is needed;
	 * 			<code>false<code> if scroll into view is not needed
	 */
	public void clickAt(
		String locator, String coordString, boolean scrollIntoView) {

		click(locator);
	}

	/**
	 * Closes the current window, quitting the browser if it's the last window
	 * currently open
	 */
	@Override
	public void close() {
		super.close();
	}

	@Deprecated
	@Override
	public void contextMenu(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void contextMenuAt(String locator, String coordString) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void controlKeyDown() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void controlKeyUp() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void createCookie(String nameValuePair, String optionsString) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void deleteAllVisibleCookies() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void deleteCookie(String name, String optionsString) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void deselectPopUp() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void doubleClick(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void doubleClickAt(String locator, String coordString) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void dragAndDrop(String locator, String coordString) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void dragAndDropToObject(
		String locatorOfObjectToBeDragged,
		String locatorOfDragDestinationObject) {

		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void dragdrop(String locator, String movementsString) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void fireEvent(String locator, String eventName) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void focus(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String getAlert() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String[] getAllButtons() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String[] getAllFields() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String[] getAllLinks() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String[] getAllWindowIds() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String[] getAllWindowNames() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String[] getAllWindowTitles() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the attribute of the element
	 *
	 * @param	attributeLocator location of the attribute
	 * @return	returns the attribute of the element
	 */
	@Override
	public String getAttribute(String attributeLocator) {
		return WebDriverHelper.getAttribute(this, attributeLocator);
	}

	@Deprecated
	@Override
	public String[] getAttributeFromAllWindows(String attributeName) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String getBodyText() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String getConfirmation() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String getCookie() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String getCookieByName(String name) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public Number getCssCount(String css) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public Number getCursorPosition(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public Number getElementHeight(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public Number getElementIndex(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public Number getElementPositionLeft(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public Number getElementPositionTop(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public Number getElementWidth(String locator) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Evaluates the JavaScript executed with the specified script.
	 * Returns a JavaScript response.
	 *
	 * @param 	script JavaScript to execute
	 */
	@Override
	public String getEval(String script) {
		return WebDriverHelper.getEval(this, script);
	}

	@Deprecated
	@Override
	public String getExpression(String expression) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	public Node getHtmlNode(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	public String getHtmlNodeHref(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	public String getHtmlNodeText(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String getHtmlSource() {
		throw new UnsupportedOperationException();
	}


	@Override
	public String getLocation() {
		return WebDriverHelper.getLocation(this);
	}

	@Deprecated
	@Override
	public String getLog() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public Number getMouseSpeed() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String getPrompt() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String getSelectedId(String selectLocator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String[] getSelectedIds(String selectLocator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String getSelectedIndex(String selectLocator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String[] getSelectedIndexes(String selectLocator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String getSelectedLabel(String selectLocator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	public String getSelectedLabel(String selectLocator, String timeout) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String[] getSelectedLabels(String selectLocator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String getSelectedValue(String selectLocator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String[] getSelectedValues(String selectLocator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String[] getSelectOptions(String selectLocator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String getSpeed() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String getTable(String tableCellAddress) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the element text
	 *
	 * @param	locator location of the element
	 * @return	returns the element text
	 */
	@Override
	public String getText(String locator) {
		return getText(locator, null);
	}

	/**
	 * Returns the element text
	 *
	 * @param	locator location of the element
	 * @param	timeout	implicit time to wait for element
	 * @return	returns the element text
	 */
	public String getText(String locator, String timeout) {
		WebElement webElement = getWebElement(locator, timeout);

		if (!isInViewport(locator)) {
			swipeWebElementIntoView(locator);
		}

		String text = webElement.getText();

		text = text.trim();

		return text.replace("\n", " ");
	}

	/**
	 * Returns the title of the current page
	 *
	 * @return	returns the title of the current page
	 */
	@Override
	public String getTitle() {
		return super.getTitle();
	}

	/**
	 * Returns the element value
	 *
	 * @param	locator location of the element
	 * @return	returns the element value
	 */
	@Override
	public String getValue(String locator) {
		return getValue(locator, null);
	}

	/**
	 * Returns the element value
	 *
	 * @param	locator location of the element
	 * @param	timeout	implicit time to wait for element
	 * @return	returns the element value
	 */
	public String getValue(String locator, String timeout) {
		WebElement webElement = getWebElement(locator, timeout);

		if (!isInViewport(locator)) {
			swipeWebElementIntoView(locator);
		}

		return webElement.getAttribute("value");
	}

	@Deprecated
	@Override
	public boolean getWhetherThisFrameMatchFrameExpression(
		String currentFrameString, String target) {

		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean getWhetherThisWindowMatchWindowExpression(
		String currentWindowString, String target) {

		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public Number getXpathCount(String xPath) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void goBack() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void highlight(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void ignoreAttributesWithoutValue(String ignore) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean isAlertPresent() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns <code>true<code> if the element is checked
	 *
	 * @param	locator location of the element
	 * @return	returns <code>true<code> if the element is checked
	 */
	@Override
	public boolean isChecked(String locator) {
		WebElement webElement = getWebElement(locator, "1");

		if (!webElement.isDisplayed()) {
			return webElement.isDisplayed();
		}

		if (!isInViewport(locator)) {
			swipeWebElementIntoView(locator);
		}

		return webElement.isSelected();
	}

	@Deprecated
	@Override
	public boolean isConfirmationPresent() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean isCookiePresent(String name) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean isEditable(String locator) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns <code>true<code> if the element is present
	 *
	 * @param	locator location of the element
	 * @return	returns <code>true<code> if the element is present
	 */
	@Override
	public boolean isElementPresent(String locator) {
		return WebDriverHelper.isElementPresent(this, locator);
	}

	/**
	 * Returns <code>true<code> if the element is in viewport
	 *
	 * @param	locator location of the element
	 * @return	returns <code>true<code> if the element is in viewport
	 */
	public boolean isInViewport(String locator) {
		int elementPositionCenterY = WebDriverHelper.getElementPositionCenterY(
			this, locator);

		int viewportPositionBottom = WebDriverHelper.getViewportPositionBottom(
			this);

		int viewportPositionTop = WebDriverHelper.getScrollOffsetY(this);

		if ((elementPositionCenterY >= viewportPositionBottom) ||
			(elementPositionCenterY <= viewportPositionTop)) {

			return false;
		}
		else {
			return true;
		}
	}

	@Deprecated
	@Override
	public boolean isOrdered(String locator1, String locator2) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean isPromptPresent() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean isSomethingSelected(String selectLocator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean isTextPresent(String pattern) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns <code>true<code> if the element is visible
	 *
	 * @param	locator location of the element
	 * @return	returns <code>true<code> if the element is visible
	 */
	@Override
	public boolean isVisible(String locator) {
		WebElement webElement = getWebElement(locator, "1");

		if (PropsValues.MOBILE_DEVICE_TYPE.equals("android")) {
			if (!isInViewport(locator)) {
				swipeWebElementIntoView(locator);
			}

			return isInViewport(locator);
		}
		else {
			if (!webElement.isDisplayed()) {
				WebDriverHelper.scrollWebElementIntoView(this, webElement);
			}

			return webElement.isDisplayed();
		}
	}

	@Deprecated
	@Override
	public void keyDown(String locator, String keySequence) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void keyDownNative(String keycode) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void keyPress(String locator, String keySequence) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void keyPressNative(String keycode) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void keyUp(String locator, String keySequence) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void keyUpNative(String keycode) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void metaKeyDown() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void metaKeyUp() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void mouseDown(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void mouseDownAt(String locator, String coordString) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void mouseDownRight(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void mouseDownRightAt(String locator, String coordString) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void mouseMove(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void mouseMoveAt(String locator, String coordString) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void mouseOut(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void mouseOver(String locator) {
	}

	@Deprecated
	@Override
	public void mouseUp(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void mouseUpAt(String locator, String coordString) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void mouseUpRight(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void mouseUpRightAt(String locator, String coordString) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Navigates to the specified URL
	 *
	 * @param	url	site to navigate to
	 */
	@Override
	public void open(String url) {
		WebDriverHelper.open(this, url);
	}

	@Deprecated
	@Override
	public void open(String url, String ignoreResponseCode) {
		throw new UnsupportedOperationException();
	}

	/**
	 * TODO This is exactly like the method above -- open(String url).
	 */
	@Override
	public void openWindow(String url, String windowID) {
		open(url);
	}

	/**
	 * Refreshes the current page
	 */
	@Override
	public void refresh() {
		WebDriverHelper.refresh(this);
	}

	@Deprecated
	@Override
	public void removeAllSelections(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void removeScript(String scriptTagId) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void removeSelection(String locator, String optionLocator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public String retrieveLastRemoteControlLogs() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void rollup(String rollupName, String kwargs) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Executes the specified JavaScript script
	 *
	 * @param	script JavaScript to run
	 */
	@Override
	public void runScript(String script) {
		getEval(script);
	}

	@Deprecated
	@Override
	public void select(String selectLocator, String optionLocator) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Selects the frame specified by the locator
	 *
	 * @param	locator location of the element
	 */
	@Override
	public void selectFrame(String locator) {
		WebDriverHelper.selectFrame(this, locator);
	}

	@Deprecated
	@Override
	public void selectPopUp(String windowID) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Selects the window specified by the windowID
	 *
	 * @param	windowID title or number of the browser window
	 */
	@Override
	public void selectWindow(String windowID) {
		WebDriverHelper.selectWindow(this, windowID);
	}

	@Deprecated
	@Override
	public void setBrowserLogLevel(String logLevel) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void setContext(String context) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void setCursorPosition(String locator, String position) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Sets the implicit timeout to be what was defined in property
	 * timeout.implicit.wait
	 */
	public void setDefaultTimeoutImplicit() {
		WebDriverHelper.setDefaultTimeoutImplicit(this);
	}

	@Deprecated
	@Override
	public void setExtensionJs(String extensionJs) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void setMouseSpeed(String pixels) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void setSpeed(String value) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void setTimeout(String timeout) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Sets the implicit timeout time with the specified time
	 *
	 * @param	timeout time to set as the implicit timeout
	 */
	public void setTimeoutImplicit(String timeout) {
		WebDriverHelper.setTimeoutImplicit(this, timeout);
	}

	@Deprecated
	@Override
	public void shiftKeyDown() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void shiftKeyUp() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void showContextualBanner() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void showContextualBanner(String className, String methodName) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void shutDownSeleniumServer() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void start() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void start(Object optionsObject) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void start(String optionsString) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Quits this driver, closing every associated window
	 */
	@Override
	public void stop() {
		quit();
	}

	@Deprecated
	@Override
	public void submit(String formLocator) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Types the <code>value<code> at the element specified by the <code>
	 * locator<code>
	 *
	 * @param	locator location of the element
	 * @param	value text to type
	 */
	@Override
	public void type(String locator, String value) {
		if (PropsValues.MOBILE_DEVICE_TYPE.equals("android")) {
			WebElement webElement = getWebElement(locator);

			if (!webElement.isEnabled()) {
				return;
			}

			webElement.clear();

			Runtime runtime = Runtime.getRuntime();

			StringBuilder sb = new StringBuilder(6);

			sb.append(PropsValues.MOBILE_ANDROID_HOME);
			sb.append("/platform-tools/");
			sb.append("adb -s emulator-5554 shell input text ");

			value = StringUtil.replace(value, " ", "%s");

			sb.append(value);

			try {
				runtime.exec(sb.toString());
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		else if (PropsValues.MOBILE_DEVICE_TYPE.equals("ios")) {
			WebDriverHelper.type(this, locator, value);
		}
	}

	@Deprecated
	@Override
	public void typeKeys(String locator, String value) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	public void typeKeys(String locator, String value, boolean typeAceEditor) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void uncheck(String locator) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void useXpathLibrary(String libraryName) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void waitForCondition(String script, String timeout) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void waitForFrameToLoad(String frameAddress, String timeout) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void waitForPageToLoad(String timeout) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void waitForPopUp(String windowID, String timeout) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void windowFocus() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void windowMaximize() {
		throw new UnsupportedOperationException();
	}

	protected WebElement getWebElement(String locator) {
		return WebDriverHelper.getWebElement(this, locator);
	}

	protected WebElement getWebElement(String locator, String timeout) {
		return WebDriverHelper.getWebElement(this, locator, timeout);
	}

	protected List<WebElement> getWebElements(String locator) {
		return WebDriverHelper.getWebElements(this, locator);
	}

	protected List<WebElement> getWebElements(String locator, String timeout) {
		return WebDriverHelper.getWebElements(this, locator, timeout);
	}

	protected void swipeWebElementIntoView(String locator) {
		if (PropsValues.MOBILE_DEVICE_TYPE.equals("android")) {
			int elementPositionCenterY =
				WebDriverHelper.getElementPositionCenterY(this, locator);

			for (int i = 0; i < 25; i++) {
				int viewportPositionBottom =
					WebDriverHelper.getViewportPositionBottom(this);

				int viewportPositionTop = WebDriverHelper.getScrollOffsetY(
					this);

				StringBuilder sb = new StringBuilder(4);

				sb.append(PropsValues.MOBILE_ANDROID_HOME);
				sb.append("/platform-tools/");

				if (elementPositionCenterY >= viewportPositionBottom) {
					try {
						sb.append("adb -s emulator-5554 shell ");
						sb.append("/data/local/swipe_up.sh");

						Runtime runtime = Runtime.getRuntime();

						runtime.exec(sb.toString());
					}
					catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
				else if (elementPositionCenterY <= viewportPositionTop ) {
					try {
						sb.append("adb -s emulator-5554 shell ");
						sb.append("/data/local/swipe_down.sh");

						Runtime runtime = Runtime.getRuntime();

						runtime.exec(sb.toString());
					}
					catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
				else {
					break;
				}

				try {
					LiferaySeleniumHelper.pause("1000");
				}
				catch (Exception e) {
				}
			}
		}
		else if (PropsValues.MOBILE_DEVICE_TYPE.equals("ios")) {
			WebElement webElement = getWebElement(locator, "1");

			WebDriverHelper.scrollWebElementIntoView(this, webElement);
		}
	}

	protected void tap(String locator) {
		if (PropsValues.MOBILE_DEVICE_TYPE.equals("android")) {
			try {
				Runtime runtime = Runtime.getRuntime();

				StringBuilder sb = new StringBuilder(6);

				sb.append(PropsValues.MOBILE_ANDROID_HOME);
				sb.append("/platform-tools/");
				sb.append("adb -s emulator-5554 shell /data/local/tap.sh ");

				int elementPositionCenterX =
					WebDriverHelper.getElementPositionCenterX(this, locator);

				int screenPositionX = elementPositionCenterX * 3 / 2;

				sb.append(screenPositionX);

				sb.append(" ");

				int elementPositionCenterY =
					WebDriverHelper.getElementPositionCenterY(this, locator);
				int navigationBarHeight = 116;
				int viewportPositionTop = WebDriverHelper.getScrollOffsetY(
					this);

				int screenPositionY =
					(((elementPositionCenterY - viewportPositionTop) * 3) / 2) +
						navigationBarHeight;

				sb.append(screenPositionY);

				runtime.exec(sb.toString());
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		else if (PropsValues.MOBILE_DEVICE_TYPE.equals("ios")) {
			TouchAction touchAction = new TouchAction(this);

			int screenPositionX = WebDriverHelper.getElementPositionCenterX(
				this, locator);

			int navigationBarHeight = 50;

			int screenPositionY =
				WebDriverHelper.getElementPositionCenterY(this, locator) +
					navigationBarHeight;

			context("NATIVE_APP");

			touchAction.tap(screenPositionX, screenPositionY);

			touchAction.perform();

			context("WEBVIEW_1");
		}
	}

}