package org.feh.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	private final static String _formats[] = {
		"yyyy-MM-dd HH:mm:ss",
		"yyyy-MM-dd hh:mm:ss",
		"yyyy/MM/dd HH:mm:ss",
		"yyyy/MM/dd hh:mm:ss",
		"yyyy-MM-dd HH:mm",
		"yyyy-MM-dd hh:mm",
		"yyyy/MM/dd HH:mm",
		"yyyy/MM/dd hh:mm",
		"yyyy-MM-dd HH",
		"yyyy-MM-dd hh",
		"yyyy/MM/dd HH",
		"yyyy/MM/dd hh",
		"yyyy-MM-dd",
		"yyyy/MM/dd",
	};
	
	private static SimpleDateFormat format;
	
	public static void setFormat(int idx) {
		DateUtils.format = new SimpleDateFormat(_formats[idx]);
	}
	
	public static void setFormat(String _format) {
		DateUtils.format = new SimpleDateFormat(_format);
	}
	
	public static String getDateStr(Date date, int idx) {
		setFormat(idx);
		return format.format(date);
	}
	
	public static String getDateStr(Date date, String _format) {
		setFormat(_format);
		return format.format(date);
	}
	
	public static Date getDate(String _date, String _format) {
		try {
			setFormat(_format);
			return format.parse(_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getDate(String _date, int idx) {
		setFormat(idx);
		try {
			return format.parse(_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
