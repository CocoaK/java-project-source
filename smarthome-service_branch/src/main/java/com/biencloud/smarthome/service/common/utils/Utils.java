package com.biencloud.smarthome.service.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 
 * 类名称：Utils 类描述： 常用方法工具类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-11-29 下午2:55:42
 */
public class Utils {
	/**
	 * 
	 * 方法的描述: 字符串转化时间类型
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-29 下午2:56:06
	 * @param strDate
	 *            :转化的字符串
	 * @return 时间类型
	 */
	public static Date covertStringToDate(String strDate) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * 方法的描述: 自动生成8位数字
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-29 下午3:03:48
	 * @return
	 */
	public static String generatorDigit() {
		String result = System.currentTimeMillis() + "";
		return result.substring(result.length() - 8, result.length());
	}

	/**
	 * 
	 * 获取传入日期一天中的最大的Date, 会改变传入的日期值! 例如传入当前日期 2012-6-8 12:12:12,
	 * 则会将这个时间重置为2012-6-8 00:00:00, 并返回2012-6-9 00:00:00
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-29 下午3:04:10
	 * @param date
	 * @return
	 */
	public static Date getDayEndingDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		date.setTime(cal.getTimeInMillis());
		return new Date(date.getTime() + (86400000 - 1));
	}

	/**
	 * 
	 * 方法的描述: 自动生成字母
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-29 下午3:05:15
	 * @return
	 */
	public static String generatorChar() {
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		char[] c = s.toCharArray();
		Random random = new Random();
		return c[random.nextInt(c.length)] + "";
	}

	/**
	 * 
	 * 方法的描述: 将标准时间格式转换为整形
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-29 下午3:23:38
	 * @param value
	 * @return
	 */
	public static String covertTimeToInt(String value) {
		try {
			if (isBlank(value)) {
				return null;
			}
			value = value.substring(0, 19);
			value = value.replaceAll("-", "");
			value = value.replaceAll(":", "");
			value = value.replaceAll(" ", "");
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * 方法的描述: 格式化当前时间
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-29 下午3:23:48
	 * @return字符串
	 */
	public static String formatDate() {
		String time = "";
		try {
			time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 
	 * 方法的描述: 计算某个时间加上或者减去指定分钟后得到新的时间
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-29 下午3:24:20
	 * @param d
	 * @param minute
	 * @return
	 * @throws ParseException
	 */
	public static Date addMinute(Date d, int minute) throws ParseException {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d);
			calendar.add(Calendar.MINUTE, minute);
			Date T3 = calendar.getTime();
			return T3;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * 方法的描述: 格式当前日期，时间置为：00:00:00
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-29 下午3:24:29
	 * @return
	 */
	public static String formatCurrentDayStart() {
		String time = "";
		try {
			time = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 
	 * 方法的描述: 格式当前日期，时间置为：23:59:59
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-29 下午3:25:01
	 * @return
	 */
	public static String formatCurrentDayEnd() {
		String time = "";
		try {
			time = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 
	 * 方法的描述: 判断字符串是否为空
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-29 下午3:25:18
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (str == null) {
			return true;
		} else {
			if (str.trim().equals("") || str.trim().equalsIgnoreCase("null")) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * 使用二分法查询判断lis中是否包含li中的元素，并将包含的元素返回
	 * 类似于取交集
	 * @param lis
	 * @param li
	 * @return List
	 */
	public static List<String> getContainList(List<String> lis,List<String> li){
		List<String> list = new ArrayList<String>();
		String[] array = lis.toArray(new String[lis.size()]);  
     	Arrays.sort(array); 
     	for(String s : li){
            int index = Arrays.binarySearch(array, s); 
            if(index>0){
            	list.add(s);
            }
          } 
     return list;
	}
}
