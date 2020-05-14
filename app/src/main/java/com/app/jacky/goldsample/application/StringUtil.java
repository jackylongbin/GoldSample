package com.app.jacky.goldsample.application;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isStringValid(String str) {

		if (str == null || str == "" || str.isEmpty()
				|| "null".equalsIgnoreCase(str)) {
			return false;
		}

		return true;
	}

	public static String getCurrentLanguage(Context context) {
		String language = "en";
		String country = "en";
		Locale locale = context.getResources().getConfiguration().locale;
		language = locale.getLanguage();
		country = locale.getCountry().toLowerCase();
		Log.i("language", "------国家代码  = " + country + "------语言代码 = "
				+ language);
		// if (language.equalsIgnoreCase("nb")) {
		// language = "no";
		// }
		return language;
	}

	public static String trim(String s) {
		if (s.charAt(0) == ' ') {
			s = s.substring(1, s.length());
		}
		if (s.charAt(s.length() - 1) == ' ') {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	public static String mergeBlank(String s) {
		int numberBlank = 0;
		String a1;// 字符串的第一部分
		String a2;// 字符串的第二部分
		for (int index = 0; index < s.length(); index++) {// 循环整个字符串，判断是否有连续空格
			numberBlank = getBlankNumber(s, index);
			if (numberBlank >= 2) {// 根据连续空格的个数以及当前的位置，截取字符串
				a1 = s.substring(0, index);
				a2 = s.substring(index + numberBlank - 1, s.length());
				s = a1 + a2;// 合并字符串
			}
		}
		return s.replace("\r\n", " ");
	}

	public static int getBlankNumber(String s, int index) {
		if (index < s.length()) {
			if (s.charAt(index) == ' ') {
				return getBlankNumber(s, index + 1) + 1;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * Yumi WiFi密码长度限制
	 * 
	 * @param password
	 * @return
	 */
	public static boolean isPasswordValid(String password) {

		if (Pattern.matches("^[0-9a-zA-Z]{8,8}", password)) {
			return true;
		}

		return false;
	}

	public static boolean isEmailAddressValid(String emailAddress) {
		Pattern pattern = Pattern
				.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(emailAddress);
		if (matcher.matches()) {
			return true;
		}

		return false;
	}

	// a integer to xx:xx:xx
	public static String secToTime(int time) {
		String timeStr = null;
		// int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00:00";
		else {
			minute = time / 60;
			second = time % 60;
			timeStr = unitFormat(minute) + ":" + unitFormat(second);
			/*
			 * if (minute < 60) { } else { hour = minute / 60; if (hour > 99)
			 * return "99:59:59"; minute = minute % 60; second = time - hour *
			 * 3600 - minute * 60; timeStr = unitFormat(hour) + ":" +
			 * unitFormat(minute) + ":" + unitFormat(second); }
			 */
		}
		return timeStr;
	}

	public static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

	/**
	 * 从本地文件获取字符串
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String filePath) throws Exception {

		String reads = "";
		// FileInputStream fis = new FileInputStream(filePath); byte[] b = new
		// byte[1024]; ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// while (fis.read(b) != -1) { baos.write(b, 0, b.length); }
		// baos.close(); fis.close();
		// reads = baos.toString();
		File urlFile = new File(filePath);
		if (!urlFile.exists())
			return null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(
				urlFile), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String mimeTypeLine = null;
		while ((mimeTypeLine = br.readLine()) != null) {
			reads = reads + mimeTypeLine;
		}
		br.close();
		return reads;
	}
}
