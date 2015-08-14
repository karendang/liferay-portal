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

import com.thoughtworks.selenium.Selenium;

/**
 * This is an interface class for custom Selenium methods.
 *
 * @author Brian Wing Shun Chan
 */
@SuppressWarnings("deprecation")
public interface LiferaySelenium extends Selenium {

	/**
	 * Executes an ant command with specified file and target names
	 *
	 * @param	fileName name of the file where the target lives
	 * @param	target target to call
	 */
	public void antCommand(String fileName, String target) throws Exception;

	/**
	 * Asserts the prompted alert is the specified pattern
	 *
	 * @param	pattern	message of the javaScript alert
	 */
	public void assertAlert(String pattern) throws Exception;

	/**
	 * Asserts no alert is prompted
	 */
	public void assertAlertNotPresent() throws Exception;

	/**
	 * Asserts the element is checked
	 *
	 * @param	locator location of the element
	 */
	public void assertChecked(String locator) throws Exception;

	/**
	 * Asserts the given message matches the JavaScript dialog generated during
	 * the previous action
	 *
	 * @param	pattern pattern to search for in the dialog
	 */
	public void assertConfirmation(String pattern) throws Exception;

	/**
	 * Asserts the specified text is not in the console
	 *
	 * @param	text	text to search for in the console
	 */
	public void assertConsoleTextNotPresent(String text) throws Exception;

	/**
	 * Asserts the specified text is in the console
	 *
	 * @param	text	text to search for in the console
	 */
	public void assertConsoleTextPresent(String text) throws Exception;

	/**
	 * Asserts the element is editable
	 *
	 * @param	locator location of the element
	 */
	public void assertEditable(String locator) throws Exception;

	/**
	 * Asserts the element is not present
	 *
	 * @param	locator location of the element
	 */
	public void assertElementNotPresent(String locator) throws Exception;

	/**
	 * Asserts the element is present
	 *
	 * @param	locator location of the element
	 */
	public void assertElementPresent(String locator) throws Exception;

	/**
	 * Asserts the email body is the same as the specified body
	 *
	 * @param	body text to match with the email body
	 * @param	index	email message number
	 */
	public void assertEmailBody(String index, String body) throws Exception;

	/**
	 * Asserts the email subject is the same the specified subject
	 *
	 * @param	index	email message number
	 * @param	subject text to match with the email subject
	 */
	public void assertEmailSubject(String index, String subject)
		throws Exception;

	/**
	 * Asserts a specified value is not present in the HTML source code
	 *
	 * @param	value text to search for in the HTML source code
	 */
	public void assertHTMLSourceTextNotPresent(String value) throws Exception;

	/**
	 * Asserts a specified value is present in the HTML source code
	 *
	 * @param	value text to search for the HTML source code
	 */
	public void assertHTMLSourceTextPresent(String value) throws Exception;

	/**
	 * Asserts the JavaScript error matches the given allowable error
	 *
	 * @param	ignoreJavaScriptError	ignorable JavaScript error
	 */
	public void assertJavaScriptErrors(String ignoreJavaScriptError)
		throws Exception;

	/**
	 * Asserts the Liferay errors caught are ignorable
	 */
	public void assertLiferayErrors() throws Exception;

	/**
	 * Asserts the location matches the specified pattern
	 *
	 * @param	pattern	location/url
	 */
	public void assertLocation(String pattern);

	/**
	 * Asserts no JavaScript exceptions are thrown
	 */
	public void assertNoJavaScriptExceptions() throws Exception;

	/**
	 * Asserts no Liferay exceptions are thrown
	 */
	public void assertNoLiferayExceptions() throws Exception;

	/**
	 * Asserts text in the prompted alert is not the same as the specified
	 * pattern
	 *
	 * @param	pattern	the pattern to match with the alert
	 */
	public void assertNotAlert(String pattern);

	/**
	 * Asserts the element is not checked
	 *
	 * @param	locator location of the check box element
	 */
	public void assertNotChecked(String locator) throws Exception;

	/**
	 * Asserts the element is not editable
	 *
	 * @param	locator location of the element
	 */
	public void assertNotEditable(String locator) throws Exception;

	/**
	 * Asserts the current location does not match the given location
	 *
	 * @param	pattern	location/url
	 */
	public void assertNotLocation(String pattern) throws Exception;

	/**
	 * Asserts the element text does not contain the specified pattern
	 *
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 */
	public void assertNotPartialText(String locator, String pattern)
		throws Exception;

	/**
	 * Asserts a selected element is not the same as the pattern
	 *
	 * @param	pattern pattern to search for in the selection items
	 * @param	selectLocator location of the element
	 */
	public void assertNotSelectedLabel(String selectLocator, String pattern)
		throws Exception;

	/**
	 * Asserts the element does not contain the specified pattern
	 *
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 */
	public void assertNotText(String locator, String pattern) throws Exception;

	/**
	 * Asserts the element does not contain the specified value
	 *
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element value
	 */
	public void assertNotValue(String locator, String pattern) throws Exception;

	/**
	 * Asserts the element is not visible
	 *
	 * @param	locator location of the element
	 */
	public void assertNotVisible(String locator) throws Exception;

	/**
	 * Retrieves the message of a JavaScript confirmation dialog generated
	 * during the previous action and asserts the confirmation contains the
	 * specified string
	 *
	 * @param	pattern pattern to search for in the confirmation dialog
	 */
	public void assertPartialConfirmation(String pattern) throws Exception;

	/**
	 * Retrieves the element text and asserts the text contains the specified
	 * pattern
	 *
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 */
	public void assertPartialText(String locator, String pattern)
		throws Exception;

	/**
	 * Asserts the selected element is the same as the pattern
	 *
	 * @param	pattern pattern to search for in the selected element
	 * @param	selectLocator location of the element
	 */
	public void assertSelectedLabel(String selectLocator, String pattern)
		throws Exception;

	/**
	 * Asserts the element text does contain the specified pattern
	 *
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 */
	public void assertText(String locator, String pattern) throws Exception;

	/**
	 * Asserts the specified pattern is not present
	 *
	 * @param	pattern pattern to search for
	 */
	public void assertTextNotPresent(String pattern) throws Exception;

	/**
	 * Asserts the specified pattern is present
	 *
	 * @param	pattern pattern to search for
	 */
	public void assertTextPresent(String pattern) throws Exception;

	/**
	 * Asserts the element value is the same as the specified pattern
	 *
	 * @param	locator location of the element
	 * @param	pattern pattern to search for
	 */
	public void assertValue(String locator, String pattern) throws Exception;

	/**
	 * Asserts the element is present and visible
	 *
	 * @param	locator location of the element
	 */
	public void assertVisible(String locator) throws Exception;

	/**
	 * Clicks the element and waits 30 seconds
	 *
	 * @param	locator location of the element
	 */
	public void clickAndWait(String locator);

	/**
	 * Clicks the element and waits 30 seconds
	 *
	 * @param	coordString	specifies the x,y position (i.e. - 10,20) of the
	 * 			mouse event relative to the element returned by the locator
	 * @param	locator location of the element
	 */
	public void clickAtAndWait(String locator, String coordString);

	/**
	 * Connects to gmail via imap with given email address and password
	 *
	 * @param	emailAddress email address
	 * @param	emailPassword email password
	 */
	public void connectToEmailAccount(String emailAddress, String emailPassword)
		throws Exception;

	/**
	 * Retrieves the element text and copies it
	 *
	 * @param	locator location of the element
	 */
	public void copyText(String locator);

	/**
	 * Retrieves the element value and copies it
	 *
	 * @param	locator location of the element
	 */
	public void copyValue(String locator);

	/**
	 * Deletes all the emails
	 */
	public void deleteAllEmails() throws Exception;

	/**
	 * Prints the specified text on to the console
	 *
	 * @param	message	text to be printed
	 */
	public void echo(String message);

	/**
	 * Terminates the test and throws an exception with a fail message
	 *
	 * @param	message	fail message
	 */
	public void fail(String message);

	/**
	 * Returns the current day of the month
	 *
	 * @return	returns the current day of the month
	 */
	public String getCurrentDay();

	/**
	 * Returns the current month
	 *
	 * @return	returns the current month
	 */
	public String getCurrentMonth();

	/**
	 * Returns the current year
	 *
	 * @return	returns the current year
	 */
	public String getCurrentYear();

	/**
	 * Returns the entire body message of an email
	 *
	 * @param	index email number
	 * @return	returns the body message of an email
	 */
	public String getEmailBody(String index) throws Exception;

	/**
	 * Returns the subject of an email
	 *
	 * @param	index email number
	 * @return	returns the subject of an email
	 */
	public String getEmailSubject(String index) throws Exception;

	/**
	 * Returns the first number in an element
	 *
	 * @param	locator location of the element
	 * @return	returns the first number in an element
	 */
	public String getFirstNumber(String locator);

	/**
	 * Retrieves the first number in an element and increments it by one
	 *
	 * @param	locator location of the element
	 * @return	returns the first number in an element and increments it by one
	 */
	public String getFirstNumberIncrement(String locator);

	/**
	 * Returns the value decremented by one
	 *
	 * @param	value number
	 * @return	returns the value decremented by one
	 */
	public String getNumberDecrement(String value);

	/**
	 * Returns the value incremented by one
	 *
	 * @param	value number
	 * @return	returns the value incremented by one
	 */
	public String getNumberIncrement(String value);

	/**
	 * Returns the property output.dir.name
	 *
	 * @return	returns the property output.dir.name
	 */
	public String getOutputDirName();

	/**
	 * Returns the primary test suite name
	 *
	 * @return	returns the primary test suite name
	 */
	public String getPrimaryTestSuiteName();

	/**
	 * Returns the property project.dir.name
	 *
	 * @return	returns the property project.dir.name
	 */
	public String getProjectDirName();

	/**
	 * Returns the property project.dir.name
	 *
	 * @return	returns the property project.dir.name
	 */
	public String getSikuliImagesDirName();

	/**
	 * Returns the property test.dependencies.dir.name
	 *
	 * @return	returns the property test.dependencies.dir.name
	 */
	public String getTestDependenciesDirName();

	/**
	 * Goes back one back and waits 30 seconds
	 */
	public void goBackAndWait();

	/**
	 * Returns <code>true<code> if the pattern matches the confirmation message
	 *
	 * @param	pattern pattern to search for in the confirmation message
	 */
	public boolean isConfirmation(String pattern);

	/**
	 * Returns <code>true<code> if an element is present at the specified
	 * location
	 *
	 * @param	locator location of the element
	 * @return	returns <code>true<code> if an element is present at the
	 * 			specified location
	 */
	public boolean isElementNotPresent(String locator);

	/**
	 * Returns <code>true<code> if an element is present at the specified
	 * location after a wait of up to the time specified in the property
	 * timeout.explicit.wait
	 *
	 * @param	locator location of the element
	 * @return	returns <code>true<code> if an element is present after wait
	 */
	public boolean isElementPresentAfterWait(String locator) throws Exception;

	/**
	 * Returns <code>true<code> if the specific value is present in the HTML
	 * source code
	 *
	 * @param	value text to find in the HTML source code
	 * @return	returns <code>true<code> if the specific value is present in the
	 * 			HTML source code
	 */
	public boolean isHTMLSourceTextPresent(String value) throws Exception;

	/**
	 * Returns <code>true<code> if the mobile device is enabled
	 *
	 * @return	returns <code>true<code> if the mobile device is enabled
	 */
	public boolean isMobileDeviceEnabled();

	/**
	 * Returns <code>true<code> if the element is not checked
	 *
	 * @param	locator location of the element
	 * @return	returns <code>true<code> if the element is not checked
	 */
	public boolean isNotChecked(String locator);

	/**
	 * Returns <code>true<code> if the element is not editable
	 *
	 * @param	locator location of the element
	 * @return	returns <code>true<code> if the element is not editable
	 */
	public boolean isNotEditable(String locator);

	/**
	 * Returns <code>true<code> if the element does not contain the specified
	 * pattern
	 *
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 * @return	returns <code>true<code> if the element does not contain the
	 * 			specified pattern
	 */
	public boolean isNotPartialText(String locator, String value);

	/**
	 * Returns <code>true<code> if the selected element is not the same as the
	 * pattern
	 *
	 * @param	pattern pattern to search for in the element text
	 * @param	selectLocator location of the element
	 * @return	returns <code>true<code> if the selected element is not the same
	 * 			as the pattern
	 */
	public boolean isNotSelectedLabel(String selectLocator, String pattern);

	/**
	 * Returns <code>true<code> if the element text does not match the specified
	 * value
	 *
	 * @param	locator location of the element
	 * @param	value text to search for in the element text
	 * @return	returns <code>true<code> if the element text does not match the
	 * 			specified value
	 */
	public boolean isNotText(String locator, String value);

	/**
	 * Returns <code>true<code> if the element value does not contain the
	 * specified value
	 *
	 * @param	locator location of the element
	 * @param	value text to search for in the element value
	 * @return	returns <code>true<code> if the element value does not contain
	 * 			the specified value
	 */
	public boolean isNotValue(String locator, String value);

	/**
	 * Returns <code>true<code> if the element is not visible
	 *
	 * @param	locator location of the element
	 * @return	returns <code>true<code> if the element is not visible
	 */
	public boolean isNotVisible(String locator);

	/**
	 * Returns <code>true<code> if the element does not contain the specified
	 * pattern
	 *
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 * @return	returns <code>true<code> if the element does not contain the
	 * 			specified pattern
	 */
	public boolean isPartialText(String locator, String value);

	/**
	 * Returns <code>true<code> if the selected element is the same as the
	 * pattern
	 *
	 * @param	pattern pattern to search for in the element text
	 * @param	selectLocator location of the element
	 * @return	returns <code>true<code> if the selected element is the same as
	 * 			the pattern
	 */
	public boolean isSelectedLabel(String selectLocator, String pattern);

	/**
	 * Returns <code>true<code> if the sikuli image is present
	 *
	 * @param	image location of the image file
	 * @return	returns <code>true<code> if the sikuli image is present
	 */
	public boolean isSikuliImagePresent(String image) throws Exception;

	/**
	 * Returns <code>true<code> if the TCat is enabled
	 *
	 * @return	returns <code>true<code> if the TCat is enabled
	 */
	public boolean isTCatEnabled();

	/**
	 * Returns <code>true<code> if the element text matches the specified value
	 *
	 * @param	locator location of the element
	 * @param	value text to search for in the element text
	 * @return	returns <code>true<code> if the element text matches the
	 * 			specified value
	 */
	public boolean isText(String locator, String value);

	/**
	 * Returns <code>true<code> if the element text does not contain specified
	 * pattern
	 *
	 * @param	pattern pattern to search for in the element text
	 * @return	returns <code>true<code> if the element text does not contain
	 * 			specified pattern
	 */
	public boolean isTextNotPresent(String pattern);

	/**
	 * Returns <code>true<code> if the element value matches the specified value
	 *
	 * @param	locator location of the element
	 * @param	value text to search for in the element value
	 * @return	returns <code>true<code> if the element value matches the
	 * 			specified value
	 */
	public boolean isValue(String locator, String value);

	/**
	 * Presses and holds the sequence of keys and waits 30 seconds
	 *
	 * @param	keySequence sequence of keys to press
	 * @param	locator location of the element
	 */
	public void keyDownAndWait(String locator, String keySequence);

	/**
	 * Presses sequence of keys and waits 30 seconds
	 *
	 * @param	keySequence sequence of keys to press
	 * @param	locator location of the element
	 */
	public void keyPressAndWait(String locator, String keySequence);

	/**
	 * Releases the sequence of keys and waits 30 seconds
	 *
	 * @param	keySequence sequence of keys to press
	 * @param	locator location of the element
	 */
	public void keyUpAndWait(String locator, String keySequence);

	/**
	 * Injects JavaScript to the page and makes it visible
	 *
	 * @param	locator location of the element
	 */
	public void makeVisible(String locator);

	/**
	 * Releases the depressed left mouse button at the current mouse location
	 */
	public void mouseRelease();

	/**
	 * Pastes the previously copied text to the specified element
	 *
	 * @param	locator location of the element
	 */
	public void paste(String locator);

	/**
	 * Temporarily stops actions for a specified amount of time
	 *
	 * @param	waitTime time in milliseconds to wait
	 */
	public void pause(String waitTime) throws Exception;

	@Deprecated
	public void pauseLoggerCheck() throws Exception;

	/**
	 * Refreshes the page and waits for 30 seconds
	 */
	public void refreshAndWait();

	/**
	 * Sends a reply to a specified recipient with a specified body text
	 *
	 * @param	body text in the body of the email
	 * @param	to email recipient
	 */
	public void replyToEmail(String to, String body) throws Exception;

	/**
	 * Takes a screenshot of the current screen and saves the image
	 */
	public void saveScreenshot() throws Exception;

	@Deprecated
	public void saveScreenshotAndSource() throws Exception;

	/**
	 * Takes a screenshot of the current screen.
	 *
	 * When actionFailed is <code>false<code>, the previously saved image gets
	 * overwritten by the current image. When actionFailed is <code>true<code>,
	 * the image name to be saved gets incremented by one resulting in a new
	 * file name.
	 *
	 * @param	actionFailed <code>true<code> if the current action caused a
	 * failure
	 */
	public void saveScreenshotBeforeAction(boolean actionFailed)
		throws Exception;

	/**
	 * Scroll to where the element is located
	 *
	 * @param	locator location of the element
	 */
	public void scrollWebElementIntoView(String locator) throws Exception;

	/**
	 * Selects the option and waits 30 seconds
	 *
	 * @param	selectLocator location of the selection element
	 * @param	optionLocator location of the option to select
	 */
	public void selectAndWait(String selectLocator, String optionLocator);

	@Deprecated
	public void sendActionDescriptionLogger(String description);

	@Deprecated
	public boolean sendActionLogger(String command, String[] params);

	/**
	 * Sends an email to a specified recipient with a specified subject header
	 * and body text
	 *
	 * @param	body text in the body of the email
	 * @param	to email recipient
	 * @param	subject text in the subject of the email
	 */
	public void sendEmail(String to, String subject, String body)
		throws Exception;

	/**
	 * Sends the value to the specified element
	 *
	 * @param	locator location of the element
	 * @param	value text to send to the element
	 */
	public void sendKeys(String locator, String value);

	/**
	 * Sends the value to the specified Ace Editor element
	 *
	 * @param	locator location of the element
	 * @param	value text to send to the element
	 */
	public void sendKeysAceEditor(String locator, String value);

	@Deprecated
	public void sendLogger(String id, String status);

	@Deprecated
	public void sendMacroDescriptionLogger(String description);

	@Deprecated
	public void sendTestCaseCommandLogger(String command);

	@Deprecated
	public void sendTestCaseHeaderLogger(String command);

	@Deprecated
	public void setDefaultTimeout();

	@Deprecated
	public void setDefaultTimeoutImplicit();

	/**
	 * Sets the primary test suite name
	 *
	 * @param	 primaryTestSuiteName name of the test suite to be set
	 */
	public void setPrimaryTestSuiteName(String primaryTestSuiteName);

	@Deprecated
	public void setTimeoutImplicit(String timeout);

	/**
	 * Sets window size to be the specified size
	 *
	 * @param	 coordString size of the window
	 */
	public void setWindowSize(String coordString);

	/**
	 * Asserts the element is not present using Sikuli to match the element
	 * image
	 *
	 * @param	image location of the image file
	 */
	public void sikuliAssertElementNotPresent(String image) throws Exception;

	/**
	 * Asserts the element is present using Sikuli to match the element image
	 *
	 * @param	image location of the image file
	 */
	public void sikuliAssertElementPresent(String image) throws Exception;

	/**
	 * Clicks on an element using Sikuli to match the element image
	 *
	 * @param	image location of the image file
	 */
	public void sikuliClick(String image) throws Exception;

	/**
	 * Clicks on the element at index using Sikuli to match the element image
	 *
	 * @param	image location of the image file
	 * @param	index zero-based index of the element to match
	 */
	public void sikuliClickByIndex(String image, String index) throws Exception;

	/**
	 * Clicks and holds at the location of the element (found by Sikuli image
	 * search), moves to the specified coordinate, then releases the mouse.
	 *
	 * @param	coordString location of where to release the element
	 * @param	image location of the image file
	 */
	public void sikuliDragAndDrop(String image, String coordString)
		throws Exception;

	/**
	 * Left clicks and holds at the current mouse location using Sikuli mouse
	 */
	public void sikuliLeftMouseDown() throws Exception;

	/**
	 * Releases the previously pressed left click button using Sikuli mouse
	 */
	public void sikuliLeftMouseUp() throws Exception;

	/**
	 * Moves the mouse to the location of the element found by Sikuli search
	 *
	 * @param	image location of the image file
	 */
	public void sikuliMouseMove(String image) throws Exception;

	/**
	 * Right clicks and holds at the current mouse location using Sikuli mouse
	 */
	public void sikuliRightMouseDown() throws Exception;

	/**
	 * Releases the previously pressed right click button using Sikuli mouse
	 */
	public void sikuliRightMouseUp() throws Exception;

	/**
	 * Uses Sikuli to find the location of the element and types into it
	 *
	 * @param	image location of the image file
	 * @param	value text to type
	 */
	public void sikuliType(String image, String value) throws Exception;

	/**
	 * Clicks on the element using Sikuli image search and types the name of the
	 * file to upload.
	 *
	 * NOTE: File must be in the dependencies folder. The dependencies folder is
	 * defined in property test.dependencies.dir.name
	 *
	 * @param	image location of the image file
	 * @param	value file name
	 */
	public void sikuliUploadCommonFile(String image, String value)
		throws Exception;

	/**
	 * Clicks on the element using Sikuli image search and types the name of the
	 * file to upload.
	 *
	 * NOTE: File must be in the TCat folder. The TCat folder is defined in
	 * property tcat.admin.repository
	 *
	 * @param	image location of the image file
	 * @param	value file name
	 */
	public void sikuliUploadTCatFile(String image, String value)
		throws Exception;

	/**
	 * Clicks on the element using Sikuli image search and types the name of the
	 * file to upload.
	 *
	 * NOTE: The file location must be in the directory defined in property
	 * output.dir.name.
	 *
	 * @param	image location of the image file
	 * @param	value file name
	 */
	public void sikuliUploadTempFile(String image, String value)
		throws Exception;

	@Deprecated
	public void startLogger();

	@Deprecated
	public void stopLogger();

	/**
	 * Types the value to the specified Ace Editor element
	 *
	 * @param	locator location of the element
	 * @param	value text to send to the element
	 */
	public void typeAceEditor(String locator, String value);

	/**
	 * Types the value to the specified Alloy Editor element
	 *
	 * @param	locator location of the element
	 * @param	value text to send to the element
	 */
	public void typeAlloyEditor(String locator, String value);

	/**
	 * Types the value to the specified CK Editor element
	 *
	 * @param	locator location of the element
	 * @param	value text to send to the element
	 */
	public void typeCKEditor(String locator, String value);

	@Deprecated
	public void typeScreen(String value);

	/**
	 * Clicks on the element and types the name of the file to upload.
	 *
	 * NOTE: File must be in the dependencies folder. The dependencies folder is
	 * defined in property test.dependencies.dir.name
	 *
	 * @param	image location of the image file
	 * @param	value file name
	 */
	public void uploadCommonFile(String locator, String value);

	/**
	 * Clicks on the element and types path of the file to upload.
	 *
	 * @param	image location of the image file
	 * @param	value canonical path of the file
	 */
	public void uploadFile(String locator, String value);

	/**
	 * Clicks on the element and types the name of the file to upload.
	 *
	 * NOTE: The file location must be in the directory defined in property
	 * output.dir.name.
	 *
	 * @param	image location of the image file
	 * @param	value file name
	 */
	public void uploadTempFile(String locator, String value);

	/**
	 * Waits for the given message in the JavaScript dialog generated during
	 * the previous action
	 *
	 * @param	pattern pattern to search for in the dialog
	 */
	public void waitForConfirmation(String pattern) throws Exception;

	/**
	 * Waits for the element to not be present
	 *
	 * @param	locator location of the element
	 */
	public void waitForElementNotPresent(String locator) throws Exception;

	/**
	 * Waits for the element to be present
	 *
	 * @param	locator location of the element
	 */
	public void waitForElementPresent(String locator) throws Exception;

	/**
	 * Waits the element text to not contain the specified pattern
	 *
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 */
	public void waitForNotPartialText(String locator, String value)
		throws Exception;

	/**
	 * Wait for selection element to not be present
	 *
	 * @param	pattern pattern to search for in the element text
	 * @param	selectLocator location of the selection element
	 */
	public void waitForNotSelectedLabel(String selectLocator, String pattern)
		throws Exception;

	/**
	 * Waits the element text to not equal the specified pattern
	 *
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 */
	public void waitForNotText(String locator, String value) throws Exception;

	/**
	 * Waits the element value to not equal the specified pattern
	 *
	 * @param	locator location of the element
	 * @param	value text
	 */
	public void waitForNotValue(String locator, String value) throws Exception;

	/**
	 * Waits the element to not be visible
	 *
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 */
	public void waitForNotVisible(String locator) throws Exception;

	/**
	 * Waits the element text to contain the specified value
	 *
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element
	 */
	public void waitForPartialText(String locator, String value)
		throws Exception;

	/**
	 * Waits the selection element to be present
	 *
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element
	 */
	public void waitForSelectedLabel(String selectLocator, String pattern)
		throws Exception;

	/**
	 * Waits the element text to equal the specified value
	 *
	 * @param	locator location of the element
	 * @param	value text to search for in the element text
	 */
	public void waitForText(String locator, String value) throws Exception;

	/**
	 * Waits the element text to not be present
	 *
	 * @param	locator location of the element
	 * @param	value text to search for in the element text
	 */
	public void waitForTextNotPresent(String value) throws Exception;

	/**
	 * Waits the element text to be present
	 *
	 * @param	locator location of the element
	 * @param	value text to search for in the element text
	 */
	public void waitForTextPresent(String value) throws Exception;

	/**
	 * Waits the element value to equal the specified value
	 *
	 * @param	locator location of the element
	 * @param	value text to search for in the element value
	 */
	public void waitForValue(String locator, String value) throws Exception;

	/**
	 * Waits the element text to not be visble
	 *
	 * @param	locator location of the element
	 */
	public void waitForVisible(String locator) throws Exception;

	/**
	 * Maximizes the window and waits 30 seconds
	 */
	public void windowMaximizeAndWait();

}