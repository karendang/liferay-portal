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

import com.liferay.poshi.runner.PoshiRunnerGetterUtil;
import com.liferay.poshi.runner.util.AntCommands;
import com.liferay.poshi.runner.util.EmailCommands;
import com.liferay.poshi.runner.util.FileUtil;
import com.liferay.poshi.runner.util.GetterUtil;
import com.liferay.poshi.runner.util.HtmlUtil;
import com.liferay.poshi.runner.util.OSDetector;
import com.liferay.poshi.runner.util.PropsValues;
import com.liferay.poshi.runner.util.RuntimeVariables;
import com.liferay.poshi.runner.util.StringUtil;
import com.liferay.poshi.runner.util.Validator;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import junit.framework.TestCase;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.Location;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Key;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.api.visual.Canvas;
import org.sikuli.api.visual.CanvasBuilder.ElementAdder;
import org.sikuli.api.visual.CanvasBuilder.ElementAreaSetter;
import org.sikuli.api.visual.DesktopCanvas;

/**
 * @author Brian Wing Shun Chan
 */
@SuppressWarnings("deprecation")
public class LiferaySeleniumHelper {

	/**
	 * Appends the exception to the list of JavaScript exceptions
	 *
	 * @param	exception exception thrown
	 */
	public static void addToJavaScriptExceptions(Exception exception) {
		_javaScriptExceptions.add(exception);
	}

	/**
	 * Appends the exception to the list of Liferay exceptions
	 *
	 * @param	exception exception thrown
	 */
	public static void addToLiferayExceptions(Exception exception) {
		_liferayExceptions.add(exception);
	}

	/**
	 * Executes an ant command with specified file and target names
	 *
	 * @exception	throws an exception when the ant execution fails
	 * @param	fileName name of the file where the target lives
	 * @param 	liferaySelenium liferaySelenium object
	 * @param	target target to call
	 */
	public static void antCommand(
			LiferaySelenium liferaySelenium, String fileName, String target)
		throws Exception {

		AntCommands antCommands = new AntCommands(
			liferaySelenium, fileName, target);

		ExecutorService executorService = Executors.newCachedThreadPool();

		Future<Void> future = executorService.submit(antCommands);

		try {
			future.get(150, TimeUnit.SECONDS);
		}
		catch (ExecutionException ee) {
			throw ee;
		}
		catch (TimeoutException te) {
		}
	}

	/**
	 * Asserts the prompted alert is the specified pattern
	 *
	 * @exception	throws an exception when the alert does not match the
	 * 				pattern
	 * @param	liferaySelenium liferaySelenium object
	 * @param	pattern pattern to search for in the alert
	 */
	public static void assertAlert(
			LiferaySelenium liferaySelenium, String pattern)
		throws Exception {

		TestCase.assertEquals(pattern, liferaySelenium.getAlert());
	}

	/**
	 * Asserts the no alert is prompted
	 *
	 * @exception  throws an exception when the alert is not present
	 * @param	liferaySelenium liferaySelenium object
	 */
	public static void assertAlertNotPresent(LiferaySelenium liferaySelenium)
		throws Exception {

		if (liferaySelenium.isAlertPresent()) {
			throw new Exception("Alert is present");
		}
	}

	/**
	 * Asserts the element is checked
	 *
	 * @exception  throws an exception when element is not checked
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 */
	public static void assertChecked(
			LiferaySelenium liferaySelenium, String locator)
		throws Exception {

		liferaySelenium.assertElementPresent(locator);

		if (liferaySelenium.isNotChecked(locator)) {
			throw new Exception(
				"Element is not checked at \"" + locator + "\"");
		}
	}

	/**
	 * Asserts the given message matches the JavaScript dialog generated during
	 * the previous action
	 *
	 * @exception  	throws an exception the confirmation message does not match
	 * 				the pattern
	 * @param	liferaySelenium liferaySelenium object
	 * @param	pattern pattern to search for in the dialog
	 */
	public static void assertConfirmation(
			LiferaySelenium liferaySelenium, String pattern)
		throws Exception {

		String confirmation = liferaySelenium.getConfirmation();

		if (!pattern.equals(confirmation)) {
			throw new Exception(
				"Pattern \"" + pattern + "\" does not match \"" + confirmation +
					"\"");
		}
	}

	/**
	 * Asserts the specified text is not in the console
	 *
	 * @exception	throws an exception when text is present in the console
	 * @param	text text to search for in the console
	 */
	public static void assertConsoleTextNotPresent(String text)
		throws Exception {

		if (isConsoleTextPresent(text)) {
			throw new Exception("\"" + text + "\" is present in console");
		}
	}

	/**
	 * Asserts the specified text is in the console
	 *
	 * @exception	throws an exception when text is not present in the console
	 * @param	text text to search for in the console
	 */
	public static void assertConsoleTextPresent(String text) throws Exception {
		if (!isConsoleTextPresent(text)) {
			throw new Exception("\"" + text + "\" is not present in console");
		}
	}

	/**
	 * Asserts the specified text is not in the console
	 *
	 * @exception	throws an exception when element is not editable
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of element
	 */
	public static void assertEditable(
			LiferaySelenium liferaySelenium, String locator)
		throws Exception {

		if (liferaySelenium.isNotEditable(locator)) {
			throw new Exception(
				"Element is not editable at \"" + locator + "\"");
		}
	}

	/**
	 * Asserts the element is not present
	 *
	 * @exception	throws an exception when element is present
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of element
	 */
	public static void assertElementNotPresent(
			LiferaySelenium liferaySelenium, String locator)
		throws Exception {

		if (liferaySelenium.isElementPresent(locator)) {
			throw new Exception("Element is present at \"" + locator + "\"");
		}
	}

	/**
	 * Asserts the element is present
	 *
	 * @exception	throws an exception when element is not present
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of element
	 */
	public static void assertElementPresent(
			LiferaySelenium liferaySelenium, String locator)
		throws Exception {

		if (liferaySelenium.isElementNotPresent(locator)) {
			throw new Exception(
				"Element is not present at \"" + locator + "\"");
		}
	}

	/**
	 * Asserts the email body is the same as the specified body
	 *
	 * @exception	throws an exception when email body is the same as the body
	 * @param	index email message number
	 * @param 	liferaySelenium liferaySelenium object
	 * @param	body text to match with the email body
	 */
	public static void assertEmailBody(
			LiferaySelenium liferaySelenium, String index, String body)
		throws Exception {

		TestCase.assertEquals(body, liferaySelenium.getEmailBody(index));
	}

	/**
	 * Asserts the email subject is the same as the specified subject
	 *
	 * @exception	throws an exception when email subject is the same as the
	 * 				subject
	 * @param	index email message number
	 * @param 	liferaySelenium liferaySelenium object
	 * @param	subject text to match with the email subject
	 */
	public static void assertEmailSubject(
			LiferaySelenium liferaySelenium, String index, String subject)
		throws Exception {

		TestCase.assertEquals(subject, liferaySelenium.getEmailSubject(index));
	}

	/**
	 * Asserts the specified value is not present in the HTML source code
	 *
	 * @exception	throws an exception when pattern does exist in the HTML
	 * 				source
	 * @param	liferaySelenium liferaySelenium object
	 * @param	value text to search for in the HTML source code
	 */
	public static void assertHTMLSourceTextNotPresent(
			LiferaySelenium liferaySelenium, String value)
		throws Exception {

		if (isHTMLSourceTextPresent(liferaySelenium, value)) {
			throw new Exception(
				"Pattern \"" + value + "\" does exists in the HTML source");
		}
	}

	/**
	 * Asserts the specified value is present in the HTML source code
	 *
	 * @exception	throws an exception when pattern does not exist in the HTML
	 * 				source
	 * @param	liferaySelenium liferaySelenium object
	 * @param	value text to search for in the HTML source code
	 */
	public static void assertHTMLSourceTextPresent(
			LiferaySelenium liferaySelenium, String value)
		throws Exception {

		if (!isHTMLSourceTextPresent(liferaySelenium, value)) {
			throw new Exception(
				"Pattern \"" + value + "\" does not exists in the HTML source");
		}
	}

	/**
	 * Asserts the Liferay errors caught are ignorable
	 *
	 * @exception	throws an exception when errors are not included in the list
	 * 				of ignored errors
	 * @param	liferaySelenium liferaySelenium object
	 * @param	value text to search for in the HTML source code
	 */
	public static void assertLiferayErrors() throws Exception {
		String fileName = PropsValues.TEST_CONSOLE_LOG_FILE_NAME;

		if (!FileUtil.exists(fileName)) {
			return;
		}

		String content = FileUtil.read(fileName);

		if (content.equals("")) {
			return;
		}

		SAXReader saxReader = new SAXReader();

		content = "<log4j>" + content + "</log4j>";
		content = content.replaceAll("log4j:", "");

		InputStream inputStream = new ByteArrayInputStream(
			content.getBytes("UTF-8"));

		Document document = saxReader.read(inputStream);

		Element rootElement = document.getRootElement();

		List<Element> eventElements = rootElement.elements("event");

		for (Element eventElement : eventElements) {
			String level = eventElement.attributeValue("level");

			if (level.equals("ERROR")) {
				String fileContent = FileUtil.read(fileName);

				fileContent = fileContent.replaceFirst(
					"level=\"ERROR\"", "level=\"ERROR_FOUND\"");

				FileUtil.write(fileName, fileContent);

				Element messageElement = eventElement.element("message");

				String messageText = messageElement.getText();

				if (isIgnorableErrorLine(messageText)) {
					continue;
				}

				Element throwableElement = eventElement.element("throwable");

				Exception exception = null;

				if (throwableElement != null) {
					exception = new Exception(
						messageText + throwableElement.getText());

					addToLiferayExceptions(exception);

					throw exception;
				}

				exception = new Exception(messageText);

				addToLiferayExceptions(exception);

				throw exception;
			}
		}
	}

	/**
	 * Asserts the location matches the specified pattern
	 *
	 * @exception	throws an exception when the location differs from the
	 * 				pattern
	 * @param	liferaySelenium liferaySelenium object
	 * @param	pattern location/url
	 */
	public static void assertLocation(
		LiferaySelenium liferaySelenium, String pattern) {

		TestCase.assertEquals(pattern, liferaySelenium.getLocation());
	}

	/**
	 * Asserts no JavaScript exceptions are thrown
	 *
	 * @exception	throws an exception when the JavaScript exceptions are
	 * 				thrown
	 */
	public static void assertNoJavaScriptExceptions() throws Exception {
		if (!_javaScriptExceptions.isEmpty()) {
			StringBuilder sb = new StringBuilder();

			sb.append(_javaScriptExceptions.size());
			sb.append(" Javascript Exception");

			if (_javaScriptExceptions.size() > 1) {
				sb.append("s were");
			}
			else {
				sb.append(" was");
			}

			sb.append(" thrown");

			System.out.println();
			System.out.println("##");
			System.out.println("## " + sb.toString());
			System.out.println("##");

			for (int i = 0; i < _javaScriptExceptions.size(); i++) {
				Exception liferayException = _javaScriptExceptions.get(i);

				System.out.println();
				System.out.println("##");
				System.out.println("## Javascript Exception #" + (i + 1));
				System.out.println("##");
				System.out.println();
				System.out.println(liferayException.getMessage());
				System.out.println();
			}

			throw new Exception(sb.toString());
		}
	}

	/**
	 * Asserts no Liferay errors exist
	 *
	 * @exception	throws an exception when the Liferay exceptions are thrown
	 */
	public static void assertNoLiferayExceptions() throws Exception {
		if (!_liferayExceptions.isEmpty()) {
			StringBuilder sb = new StringBuilder();

			sb.append(_liferayExceptions.size());
			sb.append(" Liferay Exception");

			if (_liferayExceptions.size() > 1) {
				sb.append("s were");
			}
			else {
				sb.append(" was");
			}

			sb.append(" thrown");

			System.out.println();
			System.out.println("##");
			System.out.println("## " + sb.toString());
			System.out.println("##");

			for (int i = 0; i < _liferayExceptions.size(); i++) {
				Exception liferayException = _liferayExceptions.get(i);

				System.out.println();
				System.out.println("##");
				System.out.println("## Liferay Exception #" + (i + 1));
				System.out.println("##");
				System.out.println();
				System.out.println(liferayException.getMessage());
				System.out.println();
			}

			throw new Exception(sb.toString());
		}
	}

	/**
	 * Asserts text in the prompted alert is not the same as the specified
	 * pattern
	 *
	 * @exception	throws an exception when the alert is the same as the
	 * 				pattern
	 * @param	liferaySelenium liferaySelenium object
	 * @param	pattern the pattern to match with the alert
	 */
	public static void assertNotAlert(
		LiferaySelenium liferaySelenium, String pattern) {

		TestCase.assertTrue(
			!Validator.equals(pattern, liferaySelenium.getAlert()));
	}

	/**
	 * Asserts element is not checked
	 *
	 * @exception	throws an exception when the element is checked
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 */
	public static void assertNotChecked(
			LiferaySelenium liferaySelenium, String locator)
		throws Exception {

		liferaySelenium.assertElementPresent(locator);

		if (liferaySelenium.isChecked(locator)) {
			throw new Exception("Element is checked at \"" + locator + "\"");
		}
	}

	/**
	 * Asserts element is not editable
	 *
	 * @exception	throws an exception when the element is editable
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 */
	public static void assertNotEditable(
			LiferaySelenium liferaySelenium, String locator)
		throws Exception {

		if (liferaySelenium.isEditable(locator)) {
			throw new Exception("Element is editable at \"" + locator + "\"");
		}
	}

	/**
	 * Asserts location is not the same as the pattern
	 *
	 * @exception	throws an exception when the location is the same as the
	 * 				pattern
	 * @param	liferaySelenium liferaySelenium object
	 * @param	pattern location/url
	 */
	public static void assertNotLocation(
		LiferaySelenium liferaySelenium, String pattern) {

		TestCase.assertTrue(
			Validator.equals(pattern, liferaySelenium.getLocation()));
	}

	/**
	 * Asserts element text does not contain the pattern
	 *
	 * @exception	throws an exception when the element text contains the
	 * 				pattern
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 */
	public static void assertNotPartialText(
			LiferaySelenium liferaySelenium, String locator, String pattern)
		throws Exception {

		liferaySelenium.assertElementPresent(locator);

		if (liferaySelenium.isPartialText(locator, pattern)) {
			String text = liferaySelenium.getText(locator);

			throw new Exception(
				"\"" + text + "\" contains \"" + pattern + "\" at \"" +
					locator + "\"");
		}
	}

	/**
	 * Asserts the selected element is not the same as the pattern
	 *
	 * @exception	throws an exception when the element selected is the same as
	 * 				the pattern
	 * @param	liferaySelenium liferaySelenium object
	 * @param	selectLocator location of the element
	 * @param	pattern pattern to search for in the selected element
	 */
	public static void assertNotSelectedLabel(
			LiferaySelenium liferaySelenium, String selectLocator,
			String pattern)
		throws Exception {

		liferaySelenium.assertElementPresent(selectLocator);

		if (liferaySelenium.isSelectedLabel(selectLocator, pattern)) {
			String text = liferaySelenium.getSelectedLabel(selectLocator);

			throw new Exception(
				"Pattern \"" + pattern + "\" matches \"" + text + "\" at \"" +
					selectLocator + "\"");
		}
	}

	/**
	 * Asserts the element text is not the same as the pattern
	 *
	 * @exception	throws an exception when the element text is the same as the
	 * 				pattern
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 */
	public static void assertNotText(
			LiferaySelenium liferaySelenium, String locator, String pattern)
		throws Exception {

		liferaySelenium.assertElementPresent(locator);

		if (liferaySelenium.isText(locator, pattern)) {
			String text = liferaySelenium.getText(locator);

			throw new Exception(
				"Pattern \"" + pattern + "\" matches \"" + text + "\" at \"" +
					locator + "\"");
		}
	}

	/**
	 * Asserts the element value is not the same as the pattern
	 *
	 * @exception	throws an exception when the element value is the same as
	 * 				the pattern
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element value
	 */
	public static void assertNotValue(
			LiferaySelenium liferaySelenium, String locator, String pattern)
		throws Exception {

		liferaySelenium.assertElementPresent(locator);

		if (liferaySelenium.isValue(locator, pattern)) {
			String value = liferaySelenium.getValue(locator);

			throw new Exception(
				"Pattern \"" + pattern + "\" matches \"" + value + "\" at \"" +
					locator + "\"");
		}
	}

	/**
	 * Asserts the element value is not visible
	 *
	 * @exception	throws an exception when the element value is visible
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 */
	public static void assertNotVisible(
			LiferaySelenium liferaySelenium, String locator)
		throws Exception {

		liferaySelenium.assertElementPresent(locator);

		if (liferaySelenium.isVisible(locator)) {
			throw new Exception("Element is visible at \"" + locator + "\"");
		}
	}

	/**
	 * Retrieves the message of a JavaScript confirmation dialog generated
	 * during the previous action and asserts the confirmation contains the
	 * specified string
	 *
	 * @exception	throws an exception when the confirmation does not contain
	 * 				the pattern
	 * @param	liferaySelenium liferaySelenium object
	 * @param	pattern	pattern to search for in the confirmation dialog
	 */
	public static void assertPartialConfirmation(
			LiferaySelenium liferaySelenium, String pattern)
		throws Exception {

		String confirmation = liferaySelenium.getConfirmation();

		if (!confirmation.contains(pattern)) {
			throw new Exception(
				"\"" + confirmation + "\" does not contain \"" + pattern +
					"\"");
		}
	}

	/**
	 * Retrieves the element text and asserts the text contains the specified
	 * pattern
	 *
	 * @exception	throws an exception when the element text does not contain
	 * 				the pattern
	 * @param	locator	location of the element
	 * @param	pattern	pattern to search for in the element text
	 */
	public static void assertPartialText(
			LiferaySelenium liferaySelenium, String locator, String pattern)
		throws Exception {

		liferaySelenium.assertElementPresent(locator);

		if (liferaySelenium.isNotPartialText(locator, pattern)) {
			String text = liferaySelenium.getText(locator);

			throw new Exception(
				"\"" + text + "\" does not contain \"" + pattern + "\" at \"" +
					locator + "\"");
		}
	}

	/**
	 * Asserts the selected element is the same as the pattern
	 *
	 * @exception	throws an exception when the element selected is not the
	 * 				same as the pattern
	 * @param	liferaySelenium liferaySelenium object
	 * @param	pattern pattern to search for in the selected element
	 * @param	selectLocator location of the element
	 */
	public static void assertSelectedLabel(
			LiferaySelenium liferaySelenium, String selectLocator,
			String pattern)
		throws Exception {

		liferaySelenium.assertElementPresent(selectLocator);

		if (liferaySelenium.isNotSelectedLabel(selectLocator, pattern)) {
			String text = liferaySelenium.getSelectedLabel(selectLocator);

			throw new Exception(
				"Pattern \"" + pattern + "\" does not match \"" + text +
					"\" at \"" + selectLocator + "\"");
		}
	}

	/**
	 * Asserts the element text equals the specified pattern
	 *
	 * @exception	throws an exception when the element text does not equal the
	 * 				pattern
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator	location of the element
	 * @param	pattern	pattern to search for in the element text
	 */
	public static void assertText(
			LiferaySelenium liferaySelenium, String locator, String pattern)
		throws Exception {

		liferaySelenium.assertElementPresent(locator);

		if (liferaySelenium.isNotText(locator, pattern)) {
			String text = liferaySelenium.getText(locator);

			throw new Exception(
				"Pattern \"" + pattern + "\" does not match \"" + text +
					"\" at \"" + locator + "\"");
		}
	}

	/**
	 * Asserts the specified pattern is not present
	 *
	 * @exception	throws an exception when the specified pattern is present
	 * @param	liferaySelenium liferaySelenium object
	 * @param	pattern	pattern to search for
	 */
	public static void assertTextNotPresent(
			LiferaySelenium liferaySelenium, String pattern)
		throws Exception {

		if (liferaySelenium.isTextPresent(pattern)) {
			throw new Exception("\"" + pattern + "\" is present");
		}
	}

	/**
	 * Asserts the specified pattern is present
	 *
	 * @exception	throws an exception when the specified pattern is not
	 * 				present
	 * @param	liferaySelenium liferaySelenium object
	 * @param	pattern	pattern to search for
	 */
	public static void assertTextPresent(
			LiferaySelenium liferaySelenium, String pattern)
		throws Exception {

		if (liferaySelenium.isTextNotPresent(pattern)) {
			throw new Exception("\"" + pattern + "\" is not present");
		}
	}

	/**
	 * Asserts the element value does contain the specified pattern
	 *
	 * @exception	throws an exception when the element value does not equal
	 * 				the value
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator	location of the element
	 * @param	pattern	pattern to search for in the element value
	 */
	public static void assertValue(
			LiferaySelenium liferaySelenium, String locator, String pattern)
		throws Exception {

		liferaySelenium.assertElementPresent(locator);

		if (liferaySelenium.isNotValue(locator, pattern)) {
			String value = liferaySelenium.getValue(locator);

			throw new Exception(
				"Pattern \"" + pattern + "\" does not match \"" + value +
					"\" at \"" + locator + "\"");
		}
	}

	/**
	 * Asserts the element is visible
	 *
	 * @exception  throws an exception when the element is not visible
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator	location of the element
	 */
	public static void assertVisible(
			LiferaySelenium liferaySelenium, String locator)
		throws Exception {

		liferaySelenium.assertElementPresent(locator);

		if (liferaySelenium.isNotVisible(locator)) {
			throw new Exception(
				"Element is not visible at \"" + locator + "\"");
		}
	}

	/**
	 * Takes a screen-capture of the current screen
	 *
	 * @param	fileName name for the captured image
	 */
	public static void captureScreen(String fileName) throws Exception {
		if (!PropsValues.SAVE_SCREENSHOT) {
			return;
		}

		File file = new File(fileName);

		file.mkdirs();

		Robot robot = new Robot();

		Toolkit toolkit = Toolkit.getDefaultToolkit();

		Rectangle rectangle = new Rectangle(toolkit.getScreenSize());

		BufferedImage bufferedImage = robot.createScreenCapture(rectangle);

		ImageIO.write(bufferedImage, "jpg", file);
	}

	/**
	 * Connects to gmail via imap with given email address and password
	 *
	 * @param	emailAddress email address
	 * @param	emailPassword email password
	 */
	public static void connectToEmailAccount(
			String emailAddress, String emailPassword)
		throws Exception {

		EmailCommands.connectToEmailAccount(emailAddress, emailPassword);
	}

	/**
	 * Prints the specified text on to the console
	 *
	 * @param	message	text to be printed
	 */
	public static void deleteAllEmails() throws Exception {
		EmailCommands.deleteAllEmails();
	}

	/**
	 * Prints the specified text on to the console
	 *
	 * @param	message	text to be printed
	 */
	public static void echo(String message) {
		System.out.println(message);
	}

	/**
	 * Terminates the test and throws an exception with a fail message
	 *
	 * @param	message	fail message
	 */
	public static void fail(String message) {
		TestCase.fail(message);
	}

	/**
	 * Returns the entire body message of an email
	 *
	 * @param	index	email number
	 * @return	returns the body message of an email
	 */
	public static String getEmailBody(String index) throws Exception {
		return EmailCommands.getEmailBody(GetterUtil.getInteger(index));
	}

	/**
	 * Returns the subject of an email
	 *
	 * @param	index	email number
	 * @return	returns the subject of an email
	 */
	public static String getEmailSubject(String index) throws Exception {
		return EmailCommands.getEmailSubject(GetterUtil.getInteger(index));
	}

	/**
	 * Returns the ImageTarget object of the specified image
	 *
	 * @param	image location of the image
	 * @param	liferaySelenium liferaySelenium object
	 * @return	returns the ImageTarget object of the image
	 */
	public static ImageTarget getImageTarget(
			LiferaySelenium liferaySelenium, String image)
		throws Exception {

		File file = new File(
			_TEST_BASE_DIR_NAME + "/" +
				liferaySelenium.getSikuliImagesDirName() + image);

		return new ImageTarget(file);
	}

	/**
	 * Returns the value decremented by one
	 *
	 * @param	value	number
	 * @return	returns the value decremented by one
	 */
	public static String getNumberDecrement(String value) {
		return StringUtil.valueOf(GetterUtil.getInteger(value) - 1);
	}

	/**
	 * Returns the value incremented by one
	 *
	 * @param	value	number
	 * @return	returns the value incremented by one
	 */
	public static String getNumberIncrement(String value) {
		return StringUtil.valueOf(GetterUtil.getInteger(value) + 1);
	}

	/**
	 * Returns <code>true<code> if an element is present at the specified
	 * location
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @return	returns <code>true<code> if an element is present at the
	 * 			specified location
	 */
	public static boolean isConfirmation(
		LiferaySelenium liferaySelenium, String pattern) {

		String confirmation = liferaySelenium.getConfirmation();

		return pattern.equals(confirmation);
	}

	/**
	 * Returns <code>true<code> if the specified text is in the console
	 *
	 * @param	text text to search for in the console
	 * @return	returns <code>true<code> if the text is in the console
	 */
	public static boolean isConsoleTextPresent(String text) throws Exception {
		String fileName = PropsValues.TEST_CONSOLE_LOG_FILE_NAME;

		if (!FileUtil.exists(fileName)) {
			return false;
		}

		String content = FileUtil.read(fileName);

		if (content.equals("")) {
			return false;
		}

		SAXReader saxReader = new SAXReader();

		content = "<log4j>" + content + "</log4j>";
		content = content.replaceAll("log4j:", "");

		InputStream inputStream = new ByteArrayInputStream(
			content.getBytes("UTF-8"));

		Document document = saxReader.read(inputStream);

		Element rootElement = document.getRootElement();

		List<Element> eventElements = rootElement.elements("event");

		for (Element eventElement : eventElements) {
			Element messageElement = eventElement.element("message");

			String messageText = messageElement.getText();

			Pattern pattern = Pattern.compile(text);

			Matcher matcher = pattern.matcher(messageText);

			if (matcher.find()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns <code>true<code> if an element is present at the specified
	 * location after a wait of up to the time specified in the property
	 * timeout.explicit.wait
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @return	returns <code>true<code> if an element is present after wait
	 */
	public static boolean isElementPresentAfterWait(
			LiferaySelenium liferaySelenium, String locator)
		throws Exception {

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				return liferaySelenium.isElementPresent(locator);
			}

			if (liferaySelenium.isElementPresent(locator)) {
				break;
			}

			Thread.sleep(1000);
		}

		return liferaySelenium.isElementPresent(locator);
	}

	/**
	 * Returns <code>true<code> if the specific value is present in the HTML
	 * source code
	 *
	 * @param	value text to find in the HTML source code
	 * @return	returns <code>true<code> if the specific value is present in the
	 * 			HTML source code
	 */
	public static boolean isHTMLSourceTextPresent(
			LiferaySelenium liferaySelenium, String value)
		throws Exception {

		URL url = new URL(liferaySelenium.getLocation());

		InputStream inputStream = url.openStream();

		BufferedReader bufferedReader = new BufferedReader(
			new InputStreamReader(inputStream));

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			Pattern pattern = Pattern.compile(value);

			Matcher matcher = pattern.matcher(line);

			if (matcher.find()) {
				return true;
			}
		}

		inputStream.close();

		bufferedReader.close();

		return false;
	}

	/**
	 * Returns <code>true<code> if the line is an ignorable error defined in
	 * property ignore.errors
	 *
	 * @param	line	text to compare to property
	 * @return	returns <code>true<code> if the line is ignorable
	 */
	public static boolean isIgnorableErrorLine(String line) throws Exception {
		if (isInIgnoreErrorsFile(line, "log")) {
			return true;
		}

		if (Validator.equals(PropsValues.LIFERAY_PORTAL_BUNDLE, "6.2.10.1") ||
			Validator.equals(PropsValues.LIFERAY_PORTAL_BUNDLE, "6.2.10.2") ||
			Validator.equals(PropsValues.LIFERAY_PORTAL_BUNDLE, "6.2.10.3") ||
			Validator.equals(PropsValues.LIFERAY_PORTAL_BUNDLE, "6.2.10.4") ||
			Validator.equals(PropsValues.LIFERAY_PORTAL_BRANCH, "ee-6.2.10")) {

			if (line.contains(
					"com.liferay.portal.kernel.search.SearchException: " +
						"java.nio.channels.ClosedByInterruptException")) {

				return true;
			}

			if (line.contains(
					"org.apache.lucene.store.AlreadyClosedException")) {

				return true;
			}
		}

		if (Validator.isNotNull(PropsValues.IGNORE_ERRORS)) {
			if (Validator.isNotNull(PropsValues.IGNORE_ERRORS_DELIMITER)) {
				String ignoreErrorsDelimiter =
					PropsValues.IGNORE_ERRORS_DELIMITER;

				if (ignoreErrorsDelimiter.equals("|")) {
					ignoreErrorsDelimiter = "\\|";
				}

				String[] ignoreErrors = PropsValues.IGNORE_ERRORS.split(
					ignoreErrorsDelimiter);

				for (String ignoreError : ignoreErrors) {
					if (line.contains(ignoreError)) {
						return true;
					}
				}
			}
			else if (line.contains(PropsValues.IGNORE_ERRORS)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns <code>true<code> if the line is in the file defined in property
	 * ignore.errors.file.name
	 *
	 * @param	line	text to find in the file
	 * @return	returns <code>true<code> if the line is in the file
	 */
	public static boolean isInIgnoreErrorsFile(String line, String errorType)
		throws Exception {

		if (Validator.isNotNull(PropsValues.IGNORE_ERRORS_FILE_NAME)) {
			SAXReader saxReader = new SAXReader();

			String content = FileUtil.read(PropsValues.IGNORE_ERRORS_FILE_NAME);

			InputStream inputStream = new ByteArrayInputStream(
				content.getBytes("UTF-8"));

			Document document = saxReader.read(inputStream);

			Element rootElement = document.getRootElement();

			Element errorTypeElement = rootElement.element(errorType);

			if (errorTypeElement == null) {
				return false;
			}

			List<Element> ignoreErrorElements = errorTypeElement.elements(
				"ignore-error");

			for (Element ignoreErrorElement : ignoreErrorElements) {
				Element containsElement = ignoreErrorElement.element(
					"contains");
				Element matchesElement = ignoreErrorElement.element("matches");

				String containsText = containsElement.getText();
				String matchesText = matchesElement.getText();

				if (Validator.isNotNull(containsText) &&
					Validator.isNotNull(matchesText)) {

					if (line.contains(containsText) &&
						line.matches(matchesText)) {

						return true;
					}
				}
				else if (Validator.isNotNull(containsText)) {
					if (line.contains(containsText)) {
						return true;
					}
				}
				else if (Validator.isNotNull(matchesText)) {
					if (line.matches(matchesText)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Returns <code>true<code> if the mobile device is enabled
	 *
	 * @return	returns <code>true<code> if the mobile device is enabled
	 */
	public static boolean isMobileDeviceEnabled() {
		if (Validator.isNull(PropsValues.MOBILE_DEVICE_TYPE)) {
			return false;
		}

		return true;
	}

	/**
	 * Returns <code>true<code> if the element is not checked
	 *
	 * @param 	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @return	returns <code>true<code> if the element is not checked
	 */
	public static boolean isNotChecked(
		LiferaySelenium liferaySelenium, String locator) {

		return !liferaySelenium.isChecked(locator);
	}

	/**
	 * Returns <code>true<code> if the element does not contain the specified
	 * pattern
	 *
	 * @param 	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 * @return	returns <code>true<code> if the element does not contain the
	 * 			specified pattern
	 */
	public static boolean isNotPartialText(
		LiferaySelenium liferaySelenium, String locator, String value) {

		return !liferaySelenium.isPartialText(locator, value);
	}

	/**
	 * Returns <code>true<code> if the element text does not match the specified
	 * value
	 *
	 * @param 	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	value text to search for in the element text
	 * @return	returns <code>true<code> if the element text does not match the
	 * 			specified value
	 */
	public static boolean isNotText(
		LiferaySelenium liferaySelenium, String locator, String value) {

		return !liferaySelenium.isText(locator, value);
	}

	/**
	 * Returns <code>true<code> if the element value does not contain the
	 * specified value
	 *
	 * @param 	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	value text to search for in the element value
	 * @return	returns <code>true<code> if the element value does not contain
	 * 			the specified value
	 */
	public static boolean isNotValue(
		LiferaySelenium liferaySelenium, String locator, String value) {

		return !liferaySelenium.isValue(locator, value);
	}

	/**
	 * Returns <code>true<code> if the element is not visible
	 *
	 * @param 	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @return	returns <code>true<code> if the element is not visible
	 */
	public static boolean isNotVisible(
		LiferaySelenium liferaySelenium, String locator) {

		return !liferaySelenium.isVisible(locator);
	}

	/**
	 * Returns <code>true<code> if the sikuli image is present
	 *
	 * @param	image location of the image file
	 * @param 	liferaySelenium liferaySelenium object
	 * @return	returns <code>true<code> if the sikuli image is present
	 */
	public static boolean isSikuliImagePresent(
			LiferaySelenium liferaySelenium, String image)
		throws Exception {

		ScreenRegion screenRegion = new DesktopScreenRegion();

		ImageTarget imageTarget = getImageTarget(liferaySelenium, image);

		if (screenRegion.find(imageTarget) != null) {
			return true;
		}

		return false;
	}

	/**
	 * Returns <code>true<code> if the TCat is enabled
	 *
	 * @return	returns <code>true<code> if the TCat is enabled
	 */
	public static boolean isTCatEnabled() {
		return PropsValues.TCAT_ENABLED;
	}

	/**
	 * Returns <code>true<code> if the element text does not contain specified
	 * pattern
	 *
	 * @param 	liferaySelenium liferaySelenium object
	 * @param	pattern	pattern to search for in the element text
	 * @return	returns <code>true<code> if the element text does not contain
	 * 			specified pattern
	 */
	public static boolean isTextNotPresent(
		LiferaySelenium liferaySelenium, String pattern) {

		return !liferaySelenium.isTextPresent(pattern);
	}

	/**
	 * Temporarily stops actions for a specified amount of time
	 *
	 * @param	waitTime time in milliseconds to wait
	 */
	public static void pause(String waitTime) throws Exception {
		Thread.sleep(GetterUtil.getInteger(waitTime));
	}

	/**
	 * Sends a reply to a specified recipient with a specified body text
	 *
	 * @param	body text in the body of the email
	 * @param 	liferaySelenium liferaySelenium object
	 * @param	to email recipient
	 */
	public static void replyToEmail(
			LiferaySelenium liferaySelenium, String to, String body)
		throws Exception {

		EmailCommands.replyToEmail(to, body);

		liferaySelenium.pause("3000");
	}

	/**
	 * Takes a screenshot of the current screen and saves the image
	 *
	 * @param 	liferaySelenium liferaySelenium object
	 */
	public static void saveScreenshot(LiferaySelenium liferaySelenium)
		throws Exception {

		if (!PropsValues.SAVE_SCREENSHOT) {
			return;
		}

		_screenshotCount++;

		captureScreen(
			liferaySelenium.getProjectDirName() +
				"portal-web/test-results/functional/screenshots/" +
					_screenshotCount + ".jpg");
	}

	/**
	 * Takes a screenshot of the current screen.
	 *
	 * When actionFailed is <code>false<code>, the previously saved image gets
	 * overwritten by the current image. When actionFailed is <code>true<code>,
	 * the image name to be saved gets incremented by one resulting in a new
	 * file name.
	 *
	 * @param	actionFailed <code>true<code> if the current action caused a
	 * 			failure
	 * @param 	liferaySelenium liferaySelenium object
	 */
	public static void saveScreenshotBeforeAction(
			LiferaySelenium liferaySelenium, boolean actionFailed)
		throws Exception {

		if (!PropsValues.SAVE_SCREENSHOT) {
			return;
		}

		if (actionFailed) {
			_screenshotErrorCount++;
		}

		captureScreen(
			liferaySelenium.getProjectDirName() +
				"portal-web/test-results/functional/screenshots/" +
					"ScreenshotBeforeAction" + _screenshotErrorCount + ".jpg");
	}

	/**
	 * Sends an email to a specified recipient with a specified subject header
	 * and body text
	 *
	 * @param	body text in the body of the email
	 * @param 	liferaySelenium liferaySelenium object
	 * @param	to email recipient
	 * @param	subject text in the subject of the email
	 */
	public static void sendEmail(
			LiferaySelenium liferaySelenium, String to, String subject,
			String body)
		throws Exception {

		EmailCommands.sendEmail(to, subject, body);

		liferaySelenium.pause("3000");
	}

	/**
	 * Asserts the element is not present using Sikuli to match the element
	 * image
	 *
	 * @param	image location of the image file
	 * @param 	liferaySelenium liferaySelenium object
	 */
	public static void sikuliAssertElementNotPresent(
			LiferaySelenium liferaySelenium, String image)
		throws Exception {

		ScreenRegion screenRegion = new DesktopScreenRegion();

		ImageTarget imageTarget = getImageTarget(liferaySelenium, image);

		if (screenRegion.wait(imageTarget, 5000) != null) {
			throw new Exception("Element is present");
		}
	}

	/**
	 * Asserts the element is present using Sikuli to match the element image
	 *
	 * @param	image location of the image file
	 * @param 	liferaySelenium liferaySelenium object
	 */
	public static void sikuliAssertElementPresent(
			LiferaySelenium liferaySelenium, String image)
		throws Exception {

		ScreenRegion screenRegion = new DesktopScreenRegion();

		ImageTarget imageTarget = getImageTarget(liferaySelenium, image);

		screenRegion = screenRegion.wait(imageTarget, 5000);

		if (screenRegion == null) {
			throw new Exception("Element is not present");
		}

		Canvas canvas = new DesktopCanvas();

		ElementAdder elementAdder = canvas.add();

		ElementAreaSetter elementAreaSetter = elementAdder.box();

		elementAreaSetter.around(screenRegion);

		canvas.display(2);
	}

	/**
	 * Clicks on an element using Sikuli to match the element image
	 *
	 * @param	image location of the image file
	 * @param	liferaySelenium liferaySelenium object
	 */
	public static void sikuliClick(
			LiferaySelenium liferaySelenium, String image)
		throws Exception {

		Mouse mouse = new DesktopMouse();

		ScreenRegion screenRegion = new DesktopScreenRegion();

		ImageTarget imageTarget = getImageTarget(liferaySelenium, image);

		ScreenRegion imageTargetScreenRegion = screenRegion.find(imageTarget);

		if (imageTargetScreenRegion != null) {
			mouse.click(imageTargetScreenRegion.getCenter());
		}
	}

	/**
	 * Clicks on the element at index using Sikuli to match the element image
	 *
	 * @param	image location of the image file
	 * @param	liferaySelenium liferaySelenium object
	 * @param	index zero-based index of the element to match
	 */
	public static void sikuliClickByIndex(
			LiferaySelenium liferaySelenium, String image, String index)
		throws Exception {

		Mouse mouse = new DesktopMouse();

		ScreenRegion screenRegion = new DesktopScreenRegion();

		ImageTarget imageTarget = getImageTarget(liferaySelenium, image);

		List<ScreenRegion> imageTargetScreenRegions = screenRegion.findAll(
			imageTarget);

		ScreenRegion imageTargetScreenRegion = imageTargetScreenRegions.get(
			Integer.parseInt(index));

		if (imageTargetScreenRegion != null) {
			mouse.click(imageTargetScreenRegion.getCenter());
		}
	}

	/**
	 * Clicks and holds at the location of the element (found by Sikuli image
	 * search), moves to the specified coordinate, then releases the mouse.
	 *
	 * @param	image location of the image file
	 * @param	liferaySelenium liferaySelenium object
	 * @param	coordString location of where to release the element
	 */
	public static void sikuliDragAndDrop(
			LiferaySelenium liferaySelenium, String image, String coordString)
		throws Exception {

		ScreenRegion screenRegion = new DesktopScreenRegion();

		ImageTarget imageTarget = getImageTarget(liferaySelenium, image);

		screenRegion = screenRegion.find(imageTarget);

		Mouse mouse = new DesktopMouse();

		mouse.move(screenRegion.getCenter());

		Robot robot = new Robot();

		robot.delay(1000);

		mouse.press();

		robot.delay(2000);

		String[] coords = coordString.split(",");

		Location location = screenRegion.getCenter();

		int x = location.getX() + GetterUtil.getInteger(coords[0]);
		int y = location.getY() + GetterUtil.getInteger(coords[1]);

		robot.mouseMove(x, y);

		robot.delay(1000);

		mouse.release();
	}

	/**
	 * Left clicks and holds at the current mouse location using Sikuli mouse
	 *
	 * @param	liferaySelenium liferaySelenium object
	 */
	public static void sikuliLeftMouseDown(LiferaySelenium liferaySelenium)
		throws Exception {

		liferaySelenium.pause("1000");

		Mouse mouse = new DesktopMouse();

		mouse.press();
	}

	/**
	 * Releases the previously pressed left click button using Sikuli mouse
	 *
	 * @param	liferaySelenium liferaySelenium object
	 */
	public static void sikuliLeftMouseUp(LiferaySelenium liferaySelenium)
		throws Exception {

		liferaySelenium.pause("1000");

		Mouse mouse = new DesktopMouse();

		mouse.release();
	}

	/**
	 * Moves the mouse to the location of the element found by Sikuli search
	 *
	 * @param	image location of the image file
	 * @param	liferaySelenium liferaySelenium object
	 */
	public static void sikuliMouseMove(
			LiferaySelenium liferaySelenium, String image)
		throws Exception {

		ScreenRegion screenRegion = new DesktopScreenRegion();

		ImageTarget imageTarget = getImageTarget(liferaySelenium, image);

		screenRegion = screenRegion.find(imageTarget);

		Mouse mouse = new DesktopMouse();

		mouse.move(screenRegion.getCenter());
	}

	/**
	 * Right clicks and holds at the current mouse location using Sikuli mouse
	 *
	 * @param	liferaySelenium liferaySelenium object
	 */
	public static void sikuliRightMouseDown(LiferaySelenium liferaySelenium)
		throws Exception {

		liferaySelenium.pause("1000");

		Mouse mouse = new DesktopMouse();

		mouse.rightPress();
	}

	/**
	 * Releases the previously pressed right click button using Sikuli mouse
	 *
	 * @param	liferaySelenium liferaySelenium object
	 */
	public static void sikuliRightMouseUp(LiferaySelenium liferaySelenium)
		throws Exception {

		liferaySelenium.pause("1000");

		Mouse mouse = new DesktopMouse();

		mouse.rightRelease();
	}

	/**
	 * Uses Sikuli to find the location of the element and types into it
	 *
	 * @param	image location of the image file
	 * @param	liferaySelenium liferaySelenium object
	 * @param	value text to type
	 */
	public static void sikuliType(
			LiferaySelenium liferaySelenium, String image, String value)
		throws Exception {

		sikuliClick(liferaySelenium, image);

		liferaySelenium.pause("1000");

		Keyboard keyboard = new DesktopKeyboard();

		if (value.contains("${line.separator}")) {
			String[] tokens = StringUtil.split(value, "${line.separator}");

			for (int i = 0; i < tokens.length; i++) {
				keyboard.type(tokens[i]);

				if ((i + 1) < tokens.length) {
					keyboard.type(Key.ENTER);
				}
			}

			if (value.endsWith("${line.separator}")) {
				keyboard.type(Key.ENTER);
			}
		}
		else {
			keyboard.type(value);
		}
	}

	/**
	 * Clicks on the element using Sikuli image search and types the name of the
	 * file to upload.
	 *
	 * NOTE: File must be in the dependencies folder. The dependencies folder is
	 * defined in property test.dependencies.dir.name
	 *
	 * @param	image location of the image file
	 * @param	liferaySelenium liferaySelenium object
	 * @param	value file name
	 */
	public static void sikuliUploadCommonFile(
			LiferaySelenium liferaySelenium, String image, String value)
		throws Exception {

		sikuliClick(liferaySelenium, image);

		Keyboard keyboard = new DesktopKeyboard();

		keyboard.keyDown(Key.CTRL);

		keyboard.type("a");

		keyboard.keyUp(Key.CTRL);

		sikuliType(
			liferaySelenium, image,
			_TEST_BASE_DIR_NAME + "/" + _TEST_DEPENDENCIES_DIR_NAME + value);

		keyboard.type(Key.ENTER);
	}

	/**
	 * Clicks on the element using Sikuli image search and types the name of the
	 * file to upload.
	 *
	 * NOTE: File must be in the TCat folder. The TCat folder is defined in
	 * property tcat.admin.repository
	 *
	 * @param	image location of the image file
	 * @param	liferaySelenium liferaySelenium object
	 * @param	value file name
	 */
	public static void sikuliUploadTCatFile(
			LiferaySelenium liferaySelenium, String image, String value)
		throws Exception {

		String tCatAdminFileName =
			PropsValues.TCAT_ADMIN_REPOSITORY + "/" + value;

		if (OSDetector.isWindows()) {
			tCatAdminFileName = tCatAdminFileName.replace("/", "\\");
		}

		sikuliType(liferaySelenium, image, tCatAdminFileName);

		Keyboard keyboard = new DesktopKeyboard();

		keyboard.type(Key.ENTER);
	}

	/**
	 * Clicks on the element using Sikuli image search and types the name of the
	 * file to upload.
	 *
	 * NOTE: The file location must be in the directory defined in property
	 * output.dir.name.
	 *
	 * @param	image location of the image file
	 * @param	liferaySelenium liferaySelenium object
	 * @param	value file name
	 */
	public static void sikuliUploadTempFile(
			LiferaySelenium liferaySelenium, String image, String value)
		throws Exception {

		sikuliClick(liferaySelenium, image);

		Keyboard keyboard = new DesktopKeyboard();

		keyboard.keyDown(Key.CTRL);

		keyboard.type("a");

		keyboard.keyUp(Key.CTRL);

		String slash = "/";

		if (OSDetector.isWindows()) {
			slash = "\\";
		}

		sikuliType(
			liferaySelenium, image,
			liferaySelenium.getOutputDirName() + slash + value);

		keyboard.type(Key.ENTER);
	}

	/**
	 * Types the value to the specified Ace Editor element
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	value text to send to the element
	 */
	public static void typeAceEditor(
		LiferaySelenium liferaySelenium, String locator, String value) {

		liferaySelenium.typeKeys(locator, "");

		Keyboard keyboard = new DesktopKeyboard();

		Matcher matcher = _aceEditorPattern.matcher(value);

		int x = 0;

		while (matcher.find()) {
			int y = matcher.start();

			String line = value.substring(x, y);

			keyboard.type(line.trim());

			String specialCharacter = matcher.group();

			if (specialCharacter.equals("(")) {
				keyboard.type("(");
			}
			else if (specialCharacter.equals("${line.separator}")) {
				liferaySelenium.keyPress(locator, "\\SPACE");
				liferaySelenium.keyPress(locator, "\\RETURN");
			}

			x = y + specialCharacter.length();
		}

		String line = value.substring(x);

		keyboard.type(line.trim());
	}

	/**
	 * Types the value to the specified CK Editor element
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	value text to send to the element
	 */
	public static void typeCKEditor(
		LiferaySelenium liferaySelenium, String locator, String value) {

		StringBuilder sb = new StringBuilder();

		String idAttribute = liferaySelenium.getAttribute(locator + "@id");

		int x = idAttribute.indexOf("cke__");
		int y = idAttribute.indexOf("cke__", x + 1);

		if (y == -1) {
			y = idAttribute.length();
		}

		sb.append(idAttribute.substring(x + 4, y));

		sb.append(".setHTML(\"");
		sb.append(HtmlUtil.escapeJS(value.replace("\\", "\\\\")));
		sb.append("\")");

		liferaySelenium.runScript(sb.toString());
	}

	@Deprecated
	public static void typeScreen(String value) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Waits for the given message in the JavaScript dialog generated during
	 * the previous action
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	pattern pattern to search for in the dialog
	 */
	public static void waitForConfirmation(
			LiferaySelenium liferaySelenium, String pattern)
		throws Exception {

		int timeout =
			PropsValues.TIMEOUT_EXPLICIT_WAIT /
				PropsValues.TIMEOUT_IMPLICIT_WAIT;

		for (int second = 0;; second++) {
			if (second >= timeout) {
				assertConfirmation(liferaySelenium, pattern);
			}

			try {
				if (isConfirmation(liferaySelenium, pattern)) {
					break;
				}
			}
			catch (Exception e) {
			}
		}
	}

	/**
	 * Waits for the element to not be present
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 */
	public static void waitForElementNotPresent(
			LiferaySelenium liferaySelenium, String locator)
		throws Exception {

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertElementNotPresent(locator);
			}

			try {
				if (liferaySelenium.isElementNotPresent(locator)) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	/**
	 * Waits for the element to be present
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 */
	public static void waitForElementPresent(
			LiferaySelenium liferaySelenium, String locator)
		throws Exception {

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertElementPresent(locator);
			}

			try {
				if (liferaySelenium.isElementPresent(locator)) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	/**
	 * Waits the element text to not contain the specified pattern
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 */
	public static void waitForNotPartialText(
			LiferaySelenium liferaySelenium, String locator, String value)
		throws Exception {

		value = RuntimeVariables.replace(value);

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertNotPartialText(locator, value);
			}

			try {
				if (liferaySelenium.isNotPartialText(locator, value)) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	/**
	 * Wait for selection element to not be present
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	pattern pattern to search for in the element text
	 * @param	selectLocator location of the selection element
	 */
	public static void waitForNotSelectedLabel(
			LiferaySelenium liferaySelenium, String selectLocator,
			String pattern)
		throws Exception {

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertNotSelectedLabel(selectLocator, pattern);
			}

			try {
				if (liferaySelenium.isNotSelectedLabel(
						selectLocator, pattern)) {

					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	/**
	 * Waits the element text to not equal the specified pattern
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 */
	public static void waitForNotText(
			LiferaySelenium liferaySelenium, String locator, String value)
		throws Exception {

		value = RuntimeVariables.replace(value);

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertNotText(locator, value);
			}

			try {
				if (liferaySelenium.isNotText(locator, value)) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	/**
	 * Waits the element value to not equal the specified pattern
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	value text to search for in the element value
	 */
	public static void waitForNotValue(
			LiferaySelenium liferaySelenium, String locator, String value)
		throws Exception {

		value = RuntimeVariables.replace(value);

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertNotValue(locator, value);
			}

			try {
				if (liferaySelenium.isNotValue(locator, value)) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	/**
	 * Waits the element to not be visible
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element text
	 */
	public static void waitForNotVisible(
			LiferaySelenium liferaySelenium, String locator)
		throws Exception {

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertNotVisible(locator);
			}

			try {
				if (liferaySelenium.isNotVisible(locator)) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	/**
	 * Waits the element text to contain the specified value
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element
	 */
	public static void waitForPartialText(
			LiferaySelenium liferaySelenium, String locator, String value)
		throws Exception {

		value = RuntimeVariables.replace(value);

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertPartialText(locator, value);
			}

			try {
				if (liferaySelenium.isPartialText(locator, value)) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	/**
	 * Waits the selection element to be present
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	pattern pattern to search for in the element
	 */
	public static void waitForSelectedLabel(
			LiferaySelenium liferaySelenium, String selectLocator,
			String pattern)
		throws Exception {

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertSelectedLabel(selectLocator, pattern);
			}

			try {
				if (liferaySelenium.isSelectedLabel(selectLocator, pattern)) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	/**
	 * Waits the element text to equal the specified value
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	value text to search for in the element text
	 */
	public static void waitForText(
			LiferaySelenium liferaySelenium, String locator, String value)
		throws Exception {

		value = RuntimeVariables.replace(value);

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertText(locator, value);
			}

			try {
				if (liferaySelenium.isText(locator, value)) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	/**
	 * Waits the element text to not be present
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	value text to search for in the element text
	 */
	public static void waitForTextNotPresent(
			LiferaySelenium liferaySelenium, String value)
		throws Exception {

		value = RuntimeVariables.replace(value);

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertTextNotPresent(value);
			}

			try {
				if (liferaySelenium.isTextNotPresent(value)) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	/**
	 * Waits the element text to be present
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	value text to search for in the element text
	 */
	public static void waitForTextPresent(
			LiferaySelenium liferaySelenium, String value)
		throws Exception {

		value = RuntimeVariables.replace(value);

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertTextPresent(value);
			}

			try {
				if (liferaySelenium.isTextPresent(value)) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	/**
	 * Waits the element value to equal the specified value
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 * @param	value text to search for in the element value
	 */
	public static void waitForValue(
			LiferaySelenium liferaySelenium, String locator, String value)
		throws Exception {

		value = RuntimeVariables.replace(value);

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertValue(locator, value);
			}

			try {
				if (liferaySelenium.isValue(locator, value)) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	/**
	 * Waits the element text to not be visble
	 *
	 * @param	liferaySelenium liferaySelenium object
	 * @param	locator location of the element
	 */
	public static void waitForVisible(
			LiferaySelenium liferaySelenium, String locator)
		throws Exception {

		for (int second = 0;; second++) {
			if (second >= PropsValues.TIMEOUT_EXPLICIT_WAIT) {
				liferaySelenium.assertVisible(locator);
			}

			try {
				if (liferaySelenium.isVisible(locator)) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}
	}

	private static List<ScreenRegion> getScreenRegions(
			LiferaySelenium liferaySelenium, String image)
		throws Exception {

		ScreenRegion screenRegion = new DesktopScreenRegion();

		ImageTarget imageTarget = getImageTarget(liferaySelenium, image);

		return screenRegion.findAll(imageTarget);
	}

	private static final String _TEST_BASE_DIR_NAME =
		PoshiRunnerGetterUtil.getCanonicalPath(PropsValues.TEST_BASE_DIR_NAME);

	private static final String _TEST_DEPENDENCIES_DIR_NAME =
		PropsValues.TEST_DEPENDENCIES_DIR_NAME;

	private static final Pattern _aceEditorPattern = Pattern.compile(
		"\\(|\\$\\{line\\.separator\\}");
	private static final List<Exception> _javaScriptExceptions =
		new ArrayList<>();
	private static final List<Exception> _liferayExceptions = new ArrayList<>();
	private static int _screenshotCount = 0;
	private static int _screenshotErrorCount = 0;

}