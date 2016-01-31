/**
 * date   : 2016年1月27日
 * author : Iveely Liu
 * contact: sea11510@mail.ustc.edu.cn
 */
package com.iveely.robot.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author {Iveely Liu}
 *
 */
public class StringUtil {

	/**
	 * Split text into chinese\number\letter\punct.
	 * 
	 * @param text
	 *            The text to be split.
	 * @return The array of the result.
	 */
	public static String[] split(String text) {
		// 1. Check null.
		if (text == null) {
			return null;
		}

		// 2.Split.
		List<String> list = new ArrayList<>();
		int flag = -1; // chinese(0)\number(1)\letter(2)
		String temp = "";
		char[] array = text.toCharArray();
		for (char c : array) {
			if (isNumeric(c)) {
				if (flag != 0 && temp.length() > 0) {
					list.add(temp);
					temp = "";
				}
				flag = 0;
				temp += c;
			} else if (isLetter(c) || c == 60 || c == 62 || c == 47 || c == 91 || c == 93) {
				if (flag != 1 && temp.length() > 0) {
					list.add(temp);
					temp = "";
				}
				flag = 1;
				temp += c;
			} else {
				if (temp.length() > 0) {
					list.add(temp);
					temp = "";
				}
				list.add(c + "");
				flag = 2;
			}
		}
		if (flag < 2) {
			list.add(temp);
		}
		String[] ret = new String[list.size()];
		return list.toArray(ret);
	}

	/**
	 * Check the char whether is a number.
	 * 
	 * @param c
	 *            The char to be check.
	 * @return true is number, or is not.
	 */
	public static boolean isNumeric(char c) {
		return Character.isDigit(c);
	}

	/**
	 * Check the char whether is a letter.
	 * 
	 * @param c
	 *            The char to be check.
	 * @return true is a letter, or is not.
	 */
	public static boolean isLetter(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	/**
	 * Check a string whether is a number.
	 * 
	 * @param val
	 *            The string to be check.
	 * @return true is number, or is not.
	 */
	public static boolean isNumber(String val) {
		return val.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	/**
	 * Check a string whether is a datetime format.
	 * 
	 * @param val
	 *            The string to be check.
	 * @return true is datetime format,or is not.
	 */
	public static boolean isDateTime(String val) {
		return val.matches(
				"^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
	}

	/**
	 * Check a string whether is a email format.
	 * 
	 * @param val
	 *            The string to be check.
	 * @return true is email format,or is not.
	 */
	public static boolean isEmail(String val) {
		return val.matches(
				"^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
	}

	/**
	 * Check a string whether is a ip address format.
	 * 
	 * @param val
	 *            The string to be check.
	 * @return true is ip address format,or is not.
	 */
	public static boolean isIPAddress(String val) {
		return val.matches(
				"\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
	}

	/**
	 * Simple check a string whether is an English word.
	 * 
	 * @param val
	 *            The string to be check.
	 * @return true is an English word,or is not.
	 */
	public static boolean isEnWord(String val) {
		return val.matches("^[A-Za-z]+$");
	}

	/**
	 * Check a string whether is a telephone number(mobile & ip phone).
	 * 
	 * @param val
	 *            The string to be check.
	 * @return true is a telephone number,or is not.
	 */
	public static boolean isTelNumber(String val) {
		return val.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$")
				|| val.matches("\\d{3}-\\d{8}|\\d{4}-\\d{7}");
	}

	/**
	 * Check a string whether is a url.
	 * 
	 * @param val
	 *            The string to be check.
	 * @return true is a url,or is not.
	 */
	public static boolean isUrl(String val) {
		return val.matches(
				"^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-Z0-9\\.&amp;%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&amp;%\\$#\\=~_\\-@]*)*$");
	}
}