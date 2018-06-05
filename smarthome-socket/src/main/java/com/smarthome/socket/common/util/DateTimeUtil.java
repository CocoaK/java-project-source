package com.smarthome.socket.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 
 * 类名称：DateTimeUtil 类描述：时间工具类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-4 下午10:33:55
 */
public class DateTimeUtil {
	/**
	 * 
	 * 方法的描述: 将Date对象转为XMLGregorianCalendar对象
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-4-26 下午9:46:28
	 * @param date
	 * @return
	 */
	public static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date date) {

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (DatatypeConfigurationException e) {

		}
		return gc;
	}
    /**
     * 
     * 方法的描述: 将XMLGregorianCalendar转为Date
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-5-9 下午4:10:39
     * @param gc
     * @param format
     * @return
     */
	public static Date convertXMLGregorianCalendarToDate(XMLGregorianCalendar gc, String format) {

		Date date = null;
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.set(gc.getYear(), gc.getMonth(), gc.getDay(), gc.getHour(), gc.getMinute(), gc.getSecond());
			SimpleDateFormat sFmt = new SimpleDateFormat(format);
			String str = sFmt.format(cal.getTime());
			date = sFmt.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
 
	/**
	 * 
	 * description: 获得两个时间相差的秒数
	 * 
	 * @param d1结束时间
	 * @param d2开始时间
	 * @return long
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static long getDiffSecond(Date endDate, Date startDate) {
		long a1 = endDate.getTime();
		long a2 = startDate.getTime();
		long a3 = Math.abs(a1 - a2) / 1000;

		return a3;
	}

	/**
	 * 
	 * description: 获得两个时间相差的秒数
	 * 
	 * @param d1结束时间
	 * @param d2开始时间
	 * @return long
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static long getDiffSecond(String endDate, String startDate) {

		return getDiffSecond(stringToDate(endDate, "yyyy-MM-dd HH:mm:ss"), stringToDate(startDate, "yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * 
	 * description: 给日期加年月日数
	 * 
	 * @param format
	 *            日期格式
	 * @param date
	 *            日期
	 * @param year
	 *            年数
	 * @param month
	 *            月数
	 * @param day
	 *            天数
	 * @return String
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static String dateAddYMD(String format, String date, int year, int month, int day) {

		return dateAddYMDHMS(format, date, year, month, day, 0, 0, 0);
	}

	/**
	 * 
	 * description: 给日期加年月日时分秒
	 * 
	 * @param format
	 *            日期格式
	 * @param date
	 *            日期或时间
	 * @param year
	 *            年数
	 * @param month
	 *            月数
	 * @param day
	 *            天数
	 * @param h
	 *            小时数
	 * @param m
	 *            分钟数
	 * @param s
	 *            秒数
	 * @return String
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static String dateAddYMDHMS(String format, String date, int year, int month, int day, int h, int m, int s) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sFmt = new SimpleDateFormat(format);
		cal.setTime(sFmt.parse((date), new ParsePosition(0)));

		if (day != 0) {
			cal.add(cal.DATE, day);
		}
		if (month != 0) {
			cal.add(cal.MONTH, month);
		}
		if (year != 0) {
			cal.add(cal.YEAR, year);

		}

		if (h != 0) {
			cal.add(cal.HOUR, h);

		}
		if (m != 0) {
			cal.add(cal.MINUTE, m);

		}
		if (s != 0) {
			cal.add(cal.SECOND, s);

		}
		return sFmt.format(cal.getTime());
	}

	/**
	 * 
	 * description: 给日期加年月日数转为字符串
	 * 
	 * @param format
	 *            日期格式
	 * @param date
	 *            日期
	 * @param year
	 *            年数
	 * @param month
	 *            月数
	 * @param day
	 *            天数
	 * @return String
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static String dateAddYMDToString(String format, Date date, int year, int month, int day) {
		return dateAddYMD(format, dateToString(date, format), year, month, day);
	}

	/**
	 * 
	 * description: 给日期加年月日数转为字符串
	 * 
	 * @param format
	 *            日期格式
	 * @param date
	 *            日期
	 * @param year
	 *            年数
	 * @param month
	 *            月数
	 * @param day
	 *            天数
	 * @return String
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static String dateAddYMDToString(String format, String date, int year, int month, int day) {
		return dateAddYMD(format, date, year, month, day);
	}

	/**
	 * 
	 * description: 给日期加年月日数转为
	 * 
	 * @param format
	 *            日期格式
	 * @param date
	 *            日期
	 * @param year
	 *            年数
	 * @param month
	 *            月数
	 * @param day
	 *            天数
	 * @return String
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static Date dateAddYMDToDate(String format, Date date, int year, int month, int day) {
		String d = dateAddYMD(format, dateToString(date, format), year, month, day);
		return stringToDate(d, format);
	}

	/**
	 * 
	 * description: 给日期加年月日数转为日期
	 * 
	 * @param format
	 *            日期格式
	 * @param date
	 *            日期
	 * @param year
	 *            年数
	 * @param month
	 *            月数
	 * @param day
	 *            天数
	 * @return String
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static Date dateAddYMDToDate(String format, String date, int year, int month, int day) {
		String d = dateAddYMD(format, date, year, month, day);
		return stringToDate(d, format);
	}

	/**
	 * 
	 * description: 将字符串转为日期
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            日期格式
	 * @return Date 日期
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static Date stringToDate(String str, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;

	}

	/**
	 * 
	 * description: 将日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return String 字符串
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static String dateToString(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int diffdates(String date1, String date2, String format) {
		return diffdates(stringToDate(date1, format), stringToDate(date2, format));
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int diffdates(Date date1, Date date2) {
		int result = 0;

		GregorianCalendar gc1 = new GregorianCalendar();
		GregorianCalendar gc2 = new GregorianCalendar();
		gc1.setTime(date1);
		gc2.setTime(date2);
		result = getDays(gc1, gc2);
		return result;
	}

	/**
	 * 
	 * description: 获得天数
	 * 
	 * @param g1
	 * @param g2
	 * @return int
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static int getDays(GregorianCalendar g1, GregorianCalendar g2) {
		int elapsed = 0;
		GregorianCalendar gc1, gc2;
		if (g2.after(g1)) {
			gc2 = (GregorianCalendar) g2.clone();
			gc1 = (GregorianCalendar) g1.clone();
		} else {
			gc2 = (GregorianCalendar) g1.clone();
			gc1 = (GregorianCalendar) g2.clone();
		}
		gc1.clear(Calendar.MILLISECOND);
		gc1.clear(Calendar.SECOND);
		gc1.clear(Calendar.MINUTE);
		gc1.clear(Calendar.HOUR_OF_DAY);
		gc2.clear(Calendar.MILLISECOND);
		gc2.clear(Calendar.SECOND);
		gc2.clear(Calendar.MINUTE);
		gc2.clear(Calendar.HOUR_OF_DAY);
		while (gc1.before(gc2)) {
			gc1.add(Calendar.DATE, 1);
			elapsed++;
		}
		return elapsed;
	}

	/**
	 * 
	 * description: 判断startdate是否在endDate之前
	 * 
	 * @param startdate
	 * @param endDate
	 * @param format
	 *            日期格式
	 * @return boolean 如果startdate是在endDate之前则返回true,否则返回false
	 * @author:kouy
	 * @since 1.0.0
	 */
	public boolean isBefore(String startdate, String endDate, String format) {
		int day = diffdates(startdate, endDate, format);
		if (day > 0) {
			return false;
		}
		return true;

	}

	/**
	 * 
	 * description: 判断startdate是否在endDate之前
	 * 
	 * @param startdate
	 * @param endDate
	 * @return boolean 如果startdate是在endDate之前则返回true,否则返回false
	 * @author:kouy
	 * @since 1.0.0
	 */
	public boolean isBefore(Date startdate, Date endDate) {
		int day = diffdates(startdate, endDate);
		if (day > 0) {
			return false;
		}
		return true;

	}

	/**
	 * 
	 * description: 判断startdate是否在endDate之后
	 * 
	 * @param startdate
	 * @param endDate
	 * @return boolean 如果startdate是在endDate之后则返回true,否则返回false
	 * @author:kouy
	 * @since 1.0.0
	 */
	public boolean isAfter(Date startdate, Date endDate) {
		if (isBefore(startdate, endDate)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * description: 判断startdate是否在endDate之后
	 * 
	 * @param startdate
	 * @param endDate
	 * @return boolean 如果startdate是在endDate之后则返回true,否则返回false
	 * @author:kouy
	 * @since 1.0.0
	 */
	public boolean isAfter(String startdate, String endDate, String format) {
		if (isBefore(startdate, endDate, format)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * description: 判断startdate是否与endDate相等
	 * 
	 * @param startdate
	 * @param endDate
	 * @return boolean 如果startdate与endDate相等则返回true,否则返回false
	 * @author:kouy
	 * @since 1.0.0
	 */
	public boolean isEqual(String startDate, String endDate, String format) {
		int day = diffdates(startDate, endDate, format);
		if (day == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * description: 判断startdate是否与endDate相等
	 * 
	 * @param startdate
	 * @param endDate
	 * @return boolean 如果startdate与endDate相等则返回true,否则返回false
	 * @author:kouy
	 * @since 1.0.0
	 */
	public boolean isEqual(Date startDate, Date endDate) {
		int day = diffdates(startDate, endDate);
		if (day == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * description: 从yyyy-mm-dd hh:mm:ss中获得yyyy-mm-dd
	 * 
	 * @param dateTime
	 *            yyyy-mm-dd hh:mm:ss
	 * @return Date yyyy-mm-dd
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static String getDateFormDateTime(String dateTime, String format) {

		if (dateTime != null) {
			return dateToString(stringToDate(dateTime, format), format);
		}

		return null;
	}

	/**
	 * 
	 * description: 从yyyy-mm-dd hh:mm:ss中获得yyyy-mm-dd
	 * 
	 * @param dateTime
	 *            yyyy-mm-dd hh:mm:ss
	 * @return Date yyyy-mm-dd
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static String getDateFormDateTime(Date dateTime, String format) {

		if (dateTime != null) {
			return dateToString(dateTime, format);
		}

		return null;
	}

	/**
	 * 
	 * description: 从yyyy-mm-dd hh:mm:ss中获得yyyy-mm-dd
	 * 
	 * @param dateTime
	 *            yyyy-mm-dd hh:mm:ss
	 * @return Date yyyy-mm-dd
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static Date dateTimeToDate(Date dateTime, String format) {

		if (dateTime != null) {
			return stringToDate(getDateFormDateTime(dateTime, format), format);
		}

		return null;
	}

	/**
	 * 
	 * description: 从yyyy-mm-dd hh:mm:ss中获得yyyy-mm-dd
	 * 
	 * @param dateTime
	 *            yyyy-mm-dd hh:mm:ss
	 * @return Date yyyy-mm-dd
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static Date dateTimeToDate(String dateTime, String format) {

		if (dateTime != null) {
			return stringToDate(getDateFormDateTime(dateTime, format), format);
		}

		return null;
	}

	/**
	 * 
	 * description: 将ddmmyyhhmmss转为date
	 * 
	 * @param str
	 * @return Date
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static Date formatDDMMYYHHMMSSToDate(String str) {
		if (str != null && !"".equals(str)) {
			StringBuffer strBuf = new StringBuffer();
			String year = str.substring(4, 6);
			strBuf.append("20" + year);
			strBuf.append("-");
			String mon = str.substring(2, 4);
			strBuf.append(mon);
			strBuf.append("-");
			String date = str.substring(0, 2);
			strBuf.append(date);
			strBuf.append(" ");
			String hh = str.substring(6, 8);
			strBuf.append(hh);
			strBuf.append(":");
			String mm = str.substring(8, 10);
			strBuf.append(mm);
			strBuf.append(":");
			String ss = str.substring(10, 12);
			strBuf.append(ss);
			String dateTime = strBuf.toString();
			System.out.println(dateTime);
			if (dateTime != null) {
				// String
				// dt=dateToString(stringToDate(dateTime,"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
				return stringToDate(dateAddYMDHMS("yyyy-MM-dd HH:mm:ss", dateTime, 0, 0, 0, 8, 0, 0), "yyyy-MM-dd HH:mm:ss");
			}
		}
		return null;
	}

	public static Date formatHHMMSSToDate(String str) {
		if (str != null && !"".equals(str)) {
			StringBuffer strBuf = new StringBuffer();

			strBuf.append(dateToString(new Date(), "yyyy-MM-dd"));
			strBuf.append(" ");
			String hh = str.substring(0, 2);
			strBuf.append(hh);
			strBuf.append(":");
			String mm = str.substring(2, 4);
			strBuf.append(mm);
			strBuf.append(":");
			String ss = str.substring(4, 6);
			strBuf.append(ss);
			String dateTime = strBuf.toString();
			System.out.println(dateTime);
			if (dateTime != null) {
				// String
				// dt=dateToString(stringToDate(dateTime,"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
				return stringToDate(dateAddYMDHMS("yyyy-MM-dd HH:mm:ss", dateTime, 0, 0, 0, 8, 0, 0), "yyyy-MM-dd HH:mm:ss");
			}
		}
		return null;
	}
	/**
	 * 
	 * 方法的描述:获得服务器当前时间 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-10-18 下午2:16:57
	 * @return
	 */
    public static Long getCurrentServerTime()
    {
    	Calendar rightNow = Calendar.getInstance();
		
		return rightNow.getTimeInMillis();
    }
	public static void main(String[] arg) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		long now = 1351390372049L;


		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(now);

		System.out.println(now + " = " + formatter.format(calendar.getTime()));


		
		
	}
}
