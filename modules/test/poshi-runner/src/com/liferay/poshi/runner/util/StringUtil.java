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

package com.liferay.poshi.runner.util;

import org.apache.commons.lang3.StringUtils;

/**
 * The String utility class.
 *
 * @author Brian Wing Shun Chan
 * @author Sandeep Soni
 * @author Ganesh Ram
 * @author Shuyang Zhou
 * @author Hugo Huijser
 * @author Michael Hashimoto
 */
public class StringUtil {

	/**
	 * Adds string <code>add</code> to string <code>s</code> resulting in a
	 * comma delimited list of strings, disallowing duplicate strings in the
	 * list.
	 *
	 * <p>
	 * The resulting string ends with a comma even if the original string does
	 * not.
	 * </p>
	 *
	 * @param  s the original string, representing a comma delimited list of
	 *         strings
	 * @param  add the string to add to the original, representing the string to
	 *         add to the list
	 * @return a string that represents the original string and the added string
	 *         separated by a comma, or <code>null</code> if the string to add
	 *         is <code>null</code>
	 */
	public static String add(String s, String add) {
		return add(s, add, StringPool.COMMA);
	}

	/**
	 * Adds string <code>add</code> to string <code>s</code> that represents a
	 * delimited list of strings, using a specified delimiter and disallowing
	 * duplicate words.
	 *
	 * <p>
	 * The returned string ends with the delimiter even if the original string
	 * does not.
	 * </p>
	 *
	 * @param  s the original string, representing a delimited list of strings
	 * @param  add the string to add to the original, representing the string to
	 *         add to the list
	 * @param  delimiter the delimiter used to separate strings in the list
	 * @return a string that represents the original string and the added string
	 *         separated by the delimiter, or <code>null</code> if the string to
	 *         add or the delimiter string is <code>null</code>
	 */
	public static String add(String s, String add, String delimiter) {
		return add(s, add, delimiter, false);
	}

	/**
	 * Adds string <code>add</code> to string <code>s</code> that represents a
	 * delimited list of strings, using a specified delimiter and optionally
	 * allowing duplicate words.
	 *
	 * <p>
	 * The returned string ends with the delimiter even if the original string
	 * does not.
	 * </p>
	 *
	 * @param  s the original string, representing a delimited list of strings
	 * @param  add the string to add to the original, representing the string to
	 *         add to the list
	 * @param  delimiter the delimiter used to separate strings in the list
	 * @param  allowDuplicates whether to allow duplicate strings
	 * @return a string that represents the original string and the added string
	 *         separated by the delimiter, or <code>null</code> if the string to
	 *         add or the delimiter string is <code>null</code>
	 */
	public static String add(
		String s, String add, String delimiter, boolean allowDuplicates) {

		if ((add == null) || (delimiter == null)) {
			return null;
		}

		if (s == null) {
			s = StringPool.BLANK;
		}

		if (allowDuplicates || !contains(s, add, delimiter)) {
			StringBuilder sb = new StringBuilder(4);

			sb.append(s);

			if (Validator.isNull(s) || s.endsWith(delimiter)) {
				sb.append(add);
				sb.append(delimiter);
			}
			else {
				sb.append(delimiter);
				sb.append(add);
				sb.append(delimiter);
			}

			s = sb.toString();
		}

		return s;
	}

	public static boolean contains(String s, String text) {
		return contains(s, text, StringPool.COMMA);
	}

	/**
	 * Returns <code>true</code> if the string contains the text as a delimited
	 * list entry.
	 *
	 * @param  s the string in which to search
	 * @param  text the text to search for in the string
	 * @param  delimiter the delimiter
	 * @return <code>true</code> if the string contains the text as a delimited
	 *         list entry; <code>false</code> otherwise
	 */
	public static boolean contains(String s, String text, String delimiter) {
		if ((s == null) || (text == null) || (delimiter == null)) {
			return false;
		}

		if (!s.endsWith(delimiter)) {
			s = s.concat(delimiter);
		}

		String dtd = delimiter.concat(text).concat(delimiter);

		int pos = s.indexOf(dtd);

		if (pos == -1) {
			String td = text.concat(delimiter);

			if (s.startsWith(td)) {
				return true;
			}

			return false;
		}

		return true;
	}

	/**
	 * Returns the number of times the text appears in the string.
	 *
	 * @param  s the string in which to search
	 * @param  text the text to search for in the string
	 * @return the number of times the text appears in the string
	 */
	public static int count(String s, String text) {
		if ((s == null) || (s.length() == 0) || (text == null) ||
			(text.length() == 0)) {

			return 0;
		}

		int count = 0;

		int pos = s.indexOf(text);

		while (pos != -1) {
			pos = s.indexOf(text, pos + text.length());

			count++;
		}

		return count;
	}

	/**
	 * Returns <code>true</code> if the string ends with the string
	 * <code>end</code>, ignoring case.
	 *
	 * @param  s the string in which to search
	 * @param  end the string to check for at the end of the string
	 * @return <code>true</code> if the string ends with the string
	 *         <code>end</code>, ignoring case; <code>false</code> otherwise
	 */
	public static boolean endsWith(String s, String end) {
		if ((s == null) || (end == null)) {
			return false;
		}

		if (end.length() > s.length()) {
			return false;
		}

		String temp = s.substring(s.length() - end.length());

		if (equalsIgnoreCase(temp, end)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns <code>true</code> if the strings are equal, ignoring case.
	 *
	 * @param  s1 the first string to compare
	 * @param  s2 the second string to compare
	 * @return <code>true</code> if the strings are equal, ignoring case;
	 *         <code>false</code> otherwise
	 */
	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (s1 == s2) {
			return true;
		}

		if ((s1 == null) || (s2 == null)) {
			return false;
		}

		if (s1.length() != s2.length()) {
			return false;
		}

		for (int i = 0; i < s1.length(); i++) {
			char c1 = s1.charAt(i);

			char c2 = s2.charAt(i);

			if (c1 == c2) {
				continue;
			}

			if ((c1 > 127) || (c2 > 127)) {

				// Georgian alphabet needs to check both upper and lower case

				if ((Character.toLowerCase(c1) == Character.toLowerCase(c2)) ||
					(Character.toUpperCase(c1) == Character.toUpperCase(c2))) {

					continue;
				}

				return false;
			}

			int delta = c1 - c2;

			if ((delta != 32) && (delta != -32)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the substring of English characters from the string.
	 *
	 * @param  s the string from which to extract characters
	 * @return the substring of English characters from the string, or an empty
	 *         string if the given string is <code>null</code>
	 */
	public static String extractChars(String s) {
		if (s == null) {
			return StringPool.BLANK;
		}

		StringBuilder sb = new StringBuilder();

		char[] chars = s.toCharArray();

		for (char c : chars) {
			if (Validator.isChar(c)) {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * Returns a string consisting of all of the digits extracted from the
	 * string.
	 *
	 * @param  s the string from which to extract digits
	 * @return a string consisting of all of the digits extracted from the
	 *         string
	 */
	public static String extractDigits(String s) {
		if (s == null) {
			return StringPool.BLANK;
		}

		StringBuilder sb = new StringBuilder();

		char[] chars = s.toCharArray();

		for (char c : chars) {
			if (Validator.isDigit(c)) {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * Returns the substring of <code>s</code> up to but not including the first
	 * occurrence of the delimiter.
	 *
	 * @param  s the string from which to extract a substring
	 * @param  delimiter the character whose index in the string marks where to
	 *         end the substring
	 * @return the substring of <code>s</code> up to but not including the first
	 *         occurrence of the delimiter, <code>null</code> if the string is
	 *         <code>null</code> or the delimiter does not occur in the string
	 */
	public static String extractFirst(String s, String delimiter) {
		if (s == null) {
			return null;
		}

		int index = s.indexOf(delimiter);

		if (index < 0) {
			return null;
		}
		else {
			return s.substring(0, index);
		}
	}

	/**
	 * Returns the substring of <code>s</code> after but not including the last
	 * occurrence of the delimiter.
	 *
	 * @param  s the string from which to extract the substring
	 * @param  delimiter the character whose last index in the string marks
	 *         where to begin the substring
	 * @return the substring of <code>s</code> after but not including the last
	 *         occurrence of the delimiter, <code>null</code> if the string is
	 *         <code>null</code> or the delimiter does not occur in the string
	 */
	public static String extractLast(String s, String delimiter) {
		if (s == null) {
			return null;
		}

		int index = s.lastIndexOf(delimiter);

		if (index < 0) {
			return null;
		}
		else {
			return s.substring(index + delimiter.length());
		}
	}

	/**
	 * Returns the substring of all leading digits of string <code>s</code>, or
	 * an empty string if it has no leading digits.
	 *
	 * @param  s the string from which to extract the substring
	 * @return the substring of all leading digits of string <code>s</code>, or
	 *         an empty string if it has no leading digits
	 */
	public static String extractLeadingDigits(String s) {
		if (s == null) {
			return StringPool.BLANK;
		}

		StringBuilder sb = new StringBuilder();

		char[] chars = s.toCharArray();

		for (char c : chars) {
			if (Validator.isDigit(c)) {
				sb.append(c);
			}
			else {
				return sb.toString();
			}
		}

		return sb.toString();
	}

	/**
	 * Returns <code>true</code> if all the characters in string <code>s</code>
	 * are lower case, ignoring any non-alphabetic characters.
	 *
	 * @param  s the string in which to search
	 * @return <code>true</code> if all the characters in string <code>s</code>
	 *         are lower case, ignoring any non-alphabetic characters;
	 *         <code>false</code> otherwise
	 */
	public static boolean isLowerCase(String s) {
		if (s == null) {
			return false;
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// Fast path for ascii code, fallback to the slow unicode detection

			if (c <= 127) {
				if ((c >= CharPool.UPPER_CASE_A) &&
					(c <= CharPool.UPPER_CASE_Z)) {

					return false;
				}

				continue;
			}

			if (Character.isLetter(c) && Character.isUpperCase(c)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns <code>true</code> if all the characters in string <code>s</code>
	 * are upper case, ignoring any non-alphabetic characters.
	 *
	 * @param  s the string in which to search
	 * @return <code>true</code> if all the characters in string <code>s</code>
	 *         are upper case, ignoring any non-alphabetic characters;
	 *         <code>false</code> otherwise
	 */
	public static boolean isUpperCase(String s) {
		if (s == null) {
			return false;
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// Fast path for ascii code, fallback to the slow unicode detection

			if (c <= 127) {
				if ((c >= CharPool.LOWER_CASE_A) &&
					(c <= CharPool.LOWER_CASE_Z)) {

					return false;
				}

				continue;
			}

			if (Character.isLetter(c) && Character.isLowerCase(c)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Converts all of the characters in the string to lower case.
	 *
	 * @param  s the string to convert
	 * @return the string, converted to lower case, or <code>null</code> if the
	 *         string is <code>null</code>
	 * @see    String#toLowerCase()
	 */
	public static String lowerCase(String s) {
		return toLowerCase(s);
	}

	public static void lowerCase(String... array) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				array[i] = toLowerCase(array[i]);
			}
		}
	}

	/**
	 * Converts the first character of the string to lower case.
	 *
	 * @param  s the string whose first character is to be converted
	 * @return the string, with its first character converted to lower-case
	 */
	public static String lowerCaseFirstLetter(String s) {
		char[] chars = s.toCharArray();

		if ((chars[0] >= 65) && (chars[0] <= 90)) {
			chars[0] = (char)(chars[0] + 32);
		}

		return new String(chars);
	}

	/**
	 * Returns <code>true</code> if the specified pattern occurs at any position
	 * in the string.
	 *
	 * @param  s the string
	 * @param  pattern the pattern to search for in the string
	 * @return <code>true</code> if the specified pattern occurs at any position
	 *         in the string
	 */
	public static boolean matches(String s, String pattern) {
		String[] array = pattern.split("\\*");

		for (String element : array) {
			int pos = s.indexOf(element);

			if (pos == -1) {
				return false;
			}

			s = s.substring(pos + element.length());
		}

		return true;
	}

	/**
	 * Returns the string enclosed by the quote strings.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * quote("WARNING", "!!!") returns "!!!WARNING!!!"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to enclose in quotes
	 * @param  quote the quote string to insert to insert to the beginning of
	 *         and append to the end of the string
	 * @return the string enclosed in the quote strings, or <code>null</code> if
	 *         the string is <code>null</code>
	 */
	public static String quote(String s, String quote) {
		if (s == null) {
			return null;
		}

		return quote.concat(s).concat(quote);
	}

	/**
	 * Replaces all occurrences of the string with the new string.
	 *
	 * @param  s the original string
	 * @param  oldSub the string to be searched for and replaced in the original
	 *         string
	 * @param  newSub the string with which to replace the <code>oldSub</code>
	 *         string
	 * @return a string representing the original string with all occurrences of
	 *         the <code>oldSub</code> string replaced with the string
	 *         <code>newSub</code>, or <code>null</code> if the original string
	 *         is <code>null</code>
	 */
	public static String replace(String s, String oldSub, String newSub) {
		if (s == null) {
			return null;
		}

		return s.replace(oldSub, newSub);
	}

	/**
	 * Replaces all occurrences of the elements of the string array with the
	 * corresponding elements of the new string array.
	 *
	 * @param  s the original string
	 * @param  oldSubs the strings to be searched for and replaced in the
	 *         original string
	 * @param  newSubs the strings with which to replace the
	 *         <code>oldSubs</code> strings
	 * @return a string representing the original string with all occurrences of
	 *         the <code>oldSubs</code> strings replaced with the corresponding
	 *         <code>newSubs</code> strings, or <code>null</code> if the
	 *         original string, the <code>oldSubs</code> array, or the
	 *         <code>newSubs</code> is <code>null</code>
	 */
	public static String replace(String s, String[] oldSubs, String[] newSubs) {
		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = replace(s, oldSubs[i], newSubs[i]);
		}

		return s;
	}

	/**
	 * Replaces the first occurrence of the character with the new character.
	 *
	 * @param  s the original string
	 * @param  oldSub the character whose first occurrence in the original
	 *         string is to be searched for and replaced
	 * @param  newSub the character with which to replace the first occurrence
	 *         of the <code>oldSub</code> character
	 * @return a string representing the original string except with the first
	 *         occurrence of the character <code>oldSub</code> replaced with the
	 *         character <code>newSub</code>
	 */
	public static String replaceFirst(String s, char oldSub, char newSub) {
		if (s == null) {
			return null;
		}

		return replaceFirst(s, String.valueOf(oldSub), String.valueOf(newSub));
	}

	/**
	 * Replaces the first occurrence of the character with the new string.
	 *
	 * @param  s the original string
	 * @param  oldSub the character whose first occurrence in the original
	 *         string is to be searched for and replaced
	 * @param  newSub the string with which to replace the first occurrence of
	 *         the <code>oldSub</code> character
	 * @return a string representing the original string except with the first
	 *         occurrence of the character <code>oldSub</code> replaced with the
	 *         string <code>newSub</code>
	 */
	public static String replaceFirst(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}

		return replaceFirst(s, String.valueOf(oldSub), newSub);
	}

	/**
	 * Replaces the first occurrence of the string with the new string.
	 *
	 * @param  s the original string
	 * @param  oldSub the string whose first occurrence in the original string
	 *         is to be searched for and replaced
	 * @param  newSub the string with which to replace the first occurrence of
	 *         the <code>oldSub</code> string
	 * @return a string representing the original string except with the first
	 *         occurrence of the string <code>oldSub</code> replaced with the
	 *         string <code>newSub</code>
	 */
	public static String replaceFirst(String s, String oldSub, String newSub) {
		return replaceFirst(s, oldSub, newSub, 0);
	}

	/**
	 * Replaces the first occurrences of the elements of the string array with
	 * the corresponding elements of the new string array, beginning the element
	 * search from the index position.
	 *
	 * @param  s the original string
	 * @param  oldSub the strings whose first occurrences are to be searched for
	 *         and replaced in the original string
	 * @param  newSub the strings with which to replace the first occurrences of
	 *         the <code>oldSubs</code> strings
	 * @param  fromIndex the start index within the string
	 * @return a string representing the original string with the first
	 *         occurrences of the <code>oldSubs</code> strings replaced with the
	 *         corresponding <code>newSubs</code> strings, or <code>null</code>
	 *         if the original string, the <code>oldSubs</code> string, or the
	 *         <code>newSubs</code> string is <code>null</code>
	 */
	public static String replaceFirst(
		String s, String oldSub, String newSub, int fromIndex) {

		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		if (oldSub.equals(newSub)) {
			return s;
		}

		int y = s.indexOf(oldSub, fromIndex);

		if (y >= 0) {
			return s.substring(0, y).concat(newSub).concat(
				s.substring(y + oldSub.length()));
		}
		else {
			return s;
		}
	}

	/**
	 * Replaces the first occurrences of the elements of the string array with
	 * the corresponding elements of the new string array.
	 *
	 * @param  s the original string
	 * @param  oldSubs the strings whose first occurrences are to be searched
	 *         for and replaced in the original string
	 * @param  newSubs the strings with which to replace the first occurrences
	 *         of the <code>oldSubs</code> strings
	 * @return a string representing the original string with the first
	 *         occurrences of the <code>oldSubs</code> strings replaced with the
	 *         corresponding <code>newSubs</code> strings, or <code>null</code>
	 *         if the original string, the <code>oldSubs</code> array, or the
	 *         <code>newSubs</code> is <code>null</code>
	 */
	public static String replaceFirst(
		String s, String[] oldSubs, String[] newSubs) {

		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = replaceFirst(s, oldSubs[i], newSubs[i]);
		}

		return s;
	}

	/**
	 * Reverses the order of the characters of the string.
	 *
	 * @param  s the original string
	 * @return a string representing the original string with characters in
	 *         reverse order
	 */
	public static String reverse(String s) {
		if (s == null) {
			return null;
		}

		char[] chars = s.toCharArray();
		char[] reverse = new char[chars.length];

		for (int i = 0; i < chars.length; i++) {
			reverse[i] = chars[chars.length - i - 1];
		}

		return new String(reverse);
	}

	/**
	 * Splits string <code>s</code> around comma characters.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * split("Alice,Bob,Charlie") returns {"Alice", "Bob", "Charlie"}
	 * split("Alice, Bob, Charlie") returns {"Alice", " Bob", " Charlie"}
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to split
	 * @return the array of strings resulting from splitting string
	 *         <code>s</code> around comma characters, or an empty string array
	 *         if <code>s</code> is <code>null</code> or <code>s</code> is empty
	 */
	public static String[] split(String s) {
		return split(s, StringPool.COMMA);
	}

	/**
	 * Splits the string <code>s</code> around the specified delimiter string.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * splitLines("oneandtwoandthreeandfour", "and")
	 * returns {"one","two", "three","four"}
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to split
	 * @param  delimiter the delimiter
	 * @return the array of strings resulting from splitting string
	 *         <code>s</code> around the specified delimiter string, or an empty
	 *         string array if <code>s</code> is <code>null</code> or equals the
	 *         delimiter
	 */
	public static String[] split(String s, String delimiter) {
		if (s == null) {
			return null;
		}

		return s.split(delimiter);
	}

	/**
	 * Returns <code>true</code> if, ignoring case, the string starts with the
	 * specified start string.
	 *
	 * @param  s the original string
	 * @param  start the string against which the beginning of string
	 *         <code>s</code> are to be compared
	 * @return <code>true</code> if, ignoring case, the string starts with the
	 *         specified start string; <code>false</code> otherwise
	 */
	public static boolean startsWith(String s, String start) {
		if ((s == null) || (start == null)) {
			return false;
		}

		if (start.length() > s.length()) {
			return false;
		}

		String temp = s.substring(0, start.length());

		if (equalsIgnoreCase(temp, start)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns a string representing the combination of the substring of
	 * <code>s</code> up to but not including the string <code>begin</code>
	 * concatenated with the substring of <code>s</code> after but not including
	 * the string <code>end</code>.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * stripBetween(
	 * 	"One small step for man, one giant leap for mankind", "step", "giant ")
	 * returns "One small leap for mankind"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string from which to strip a substring
	 * @param  begin the beginning characters of the substring to be removed
	 * @param  end the ending characters of the substring to be removed
	 * @return a string representing the combination of the substring of
	 *         <code>s</code> up to but not including the string
	 *         <code>begin</code> concatenated with the substring of
	 *         <code>s</code> after but not including the string
	 *         <code>end</code>, or the original string if the value of
	 *         <code>s</code>, <code>begin</code>, or <code>end</code> are
	 *         <code>null</code>
	 */
	public static String stripBetween(String s, String begin, String end) {
		if (Validator.isBlank(s) || Validator.isBlank(begin) ||
			Validator.isBlank(end)) {

			return s;
		}

		StringBuilder sb = new StringBuilder(s.length());

		int pos = 0;

		while (true) {
			int x = s.indexOf(begin, pos);
			int y = s.indexOf(end, x + begin.length());

			if ((x == -1) || (y == -1)) {
				sb.append(s.substring(pos));

				break;
			}
			else {
				sb.append(s.substring(pos, x));

				pos = y + end.length();
			}
		}

		return sb.toString();
	}

	/**
	 * Converts all of the characters in the string to lower case, based on the
	 * portal instance's default locale.
	 *
	 * @param  s the string to convert
	 * @return the string, converted to lower case, or <code>null</code> if the
	 *         string is <code>null</code>
	 */
	public static String toLowerCase(String s) {
		if (s == null) {
			return null;
		}

		return StringUtils.lowerCase(s);
	}

	/**
	 * Converts all of the characters in the string to upper case, based on the
	 * portal instance's default locale.
	 *
	 * @param  s the string to convert
	 * @return the string, converted to upper case, or <code>null</code> if the
	 *         string is <code>null</code>
	 */
	public static String toUpperCase(String s) {
		if (s == null) {
			return null;
		}

		return StringUtils.upperCase(s);
	}

	/**
	 * Trims all leading and trailing whitespace from the string.
	 *
	 * @param  s the original string
	 * @return a string representing the original string with all leading and
	 *         trailing whitespace removed
	 */
	public static String trim(String s) {
		if (s == null) {
			return null;
		}

		if (s.length() == 0) {
			return s;
		}

		int len = s.length();

		int x = len;

		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);

			if (!Character.isWhitespace(c)) {
				x = i;

				break;
			}
		}

		if (x == len) {
			return StringPool.BLANK;
		}

		int y = x + 1;

		for (int i = len - 1; i > x; i--) {
			char c = s.charAt(i);

			if (!Character.isWhitespace(c)) {
				y = i + 1;

				break;
			}
		}

		if ((x == 0) && (y == len)) {
			return s;
		}

		return s.substring(x, y);
	}

	/**
	 * Trims all leading whitespace from the string.
	 *
	 * @param  s the original string
	 * @return a string representing the original string with all leading
	 *         whitespace removed
	 */
	public static String trimLeading(String s) {
		if (s == null) {
			return null;
		}

		if (s.length() == 0) {
			return s;
		}

		int len = s.length();
		int x = len;

		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);

			if (!Character.isWhitespace(c)) {
				x = i;

				break;
			}
		}

		if (x == len) {
			return StringPool.BLANK;
		}
		else if (x == 0) {
			return s;
		}
		else {
			return s.substring(x);
		}
	}

	/**
	 * Trims all trailing whitespace from the string.
	 *
	 * @param  s the original string
	 * @return a string representing the original string with all trailing
	 *         whitespace removed
	 */
	public static String trimTrailing(String s) {
		if (s == null) {
			return null;
		}

		if (s.length() == 0) {
			return s;
		}

		int len = s.length();
		int x = 0;

		for (int i = len - 1; i >= 0; i--) {
			char c = s.charAt(i);

			if (!Character.isWhitespace(c)) {
				x = i + 1;

				break;
			}
		}

		if (x == 0) {
			return StringPool.BLANK;
		}
		else if (x == len) {
			return s;
		}
		else {
			return s.substring(0, x);
		}
	}

	/**
	 * Removes leading and trailing double and single quotation marks from the
	 * string.
	 *
	 * @param  s the original string
	 * @return a string representing the original string with leading and
	 *         trailing double and single quotation marks removed, or the
	 *         original string if the original string is a <code>null</code> or
	 *         empty
	 */
	public static String unquote(String s) {
		if (Validator.isNull(s)) {
			return s;
		}

		if ((s.charAt(0) == CharPool.APOSTROPHE) &&
			(s.charAt(s.length() - 1) == CharPool.APOSTROPHE)) {

			return s.substring(1, s.length() - 1);
		}
		else if ((s.charAt(0) == CharPool.QUOTE) &&
				 (s.charAt(s.length() - 1) == CharPool.QUOTE)) {

			return s.substring(1, s.length() - 1);
		}

		return s;
	}

	/**
	 * Converts all of the characters in the string to upper case.
	 *
	 * @param  s the string to convert
	 * @return the string, converted to upper-case, or <code>null</code> if the
	 *         string is <code>null</code>
	 * @see    String#toUpperCase()
	 */
	public static String upperCase(String s) {
		return toUpperCase(s);
	}

	/**
	 * Converts the first character of the string to upper case.
	 *
	 * @param  s the string whose first character is to be converted
	 * @return the string, with its first character converted to upper-case
	 */
	public static String upperCaseFirstLetter(String s) {
		char[] chars = s.toCharArray();

		if ((chars[0] >= 97) && (chars[0] <= 122)) {
			chars[0] = (char)(chars[0] - 32);
		}

		return new String(chars);
	}

	/**
	 * Returns the string value of the object.
	 *
	 * @param  obj the object whose string value is to be returned
	 * @return the string value of the object
	 * @see    String#valueOf(Object obj)
	 */
	public static String valueOf(Object obj) {
		return String.valueOf(obj);
	}

}