package com.biencloud.smarthome.service.common.utils;

import java.util.Random;

/**
 * 
 * 类名称：StringUtils 类描述：字符串工具类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-9-18 下午12:02:26
 */
public class StringUtils {
	/**
	 * 
	 * 方法的描述: 获得ip段
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-9-18 下午12:08:24
	 * @param ip
	 * @return
	 */
	public static String getIPSection(String ip) {
		String str = null;
		if (ip != null && !"".equals(ip.trim())) {

			String [] s = ip.split("[.]");
			if (s != null && s.length == 4) {
				str = s[2];
			}
		}
		return str;
	}
	/**
	 * 
	 * 方法的描述: 将null转为空字符串
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-10-8 下午5:27:01
	 * @param s
	 * @return
	 */
	public static String covertNullToEmpty(String s)
	{
		if(s==null)
		{
			return "";
		}
		return s.trim();
	}
	
	/**
	 * 生成随机数
	 * @param length 位数，位数不大于9
	 * @return length位随机数
	 */
	public static String randomNum(int length){
		if(length>9){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<length-1;i++){
			sb.append("0");
		}
		int x = Integer.parseInt("9"+sb.toString());
		int y = Integer.parseInt("1"+sb.toString());
		Integer i = new Random().nextInt(x)+y;
		return i.toString();
	}
	
	/**
	 * 将10进制num转换为length位数16进制
	 * @param length 位数，位数不大于9
	 * @return length位随机数
	 */
	public static String toHex(Long num,int length){
		StringBuilder sb = new StringBuilder();
		String x = Long.toHexString(num).toUpperCase();
		if(length>x.length())
		for(int i=x.length();i<length;i++){
			sb.append("0");
		}
		sb.append(x);
		return sb.toString();
	}
	
	/**
	 * 转换mac地址去掉冒号
	 * @param str
	 * @return
	 */
	public static String coverMacToString(String str){
		   if (str!=null && !"".equals(str)){
			   return str.replace(":", "");
		   }
		   return null;
	   }
	
	public static void main(String[] args){
		String s = toHex(123L,1);
		System.out.println(s);
	}
}
