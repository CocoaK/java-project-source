package com.biencloud.smarthome.web.common.util;

/**
 * 
 * 类名称：SystemPropertiesUtil 类描述： 系统属性工具类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-7-4 下午2:13:14
 */
public class SystemPropertiesUtil {
	/**
	 * 
	 * 方法的描述:获得操作系统文件分隔符， Windows环境下为"\"，Unix环境下为"/”
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-4 下午2:15:49
	 * @return
	 */
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}

	/**
	 * 
	 * 方法的描述:获得操作系统名称
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-4 下午2:15:49
	 * @return
	 */
	public static String getOsName() {
		return System.getProperty("os.name");
	}

	/**
	 * 
	 * 方法的描述:获得操作系统版本
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-4 下午2:15:49
	 * @return
	 */
	public static String getOsVersion() {
		return System.getProperty("os.version");
	}

	/**
	 * 
	 * 方法的描述:当前操作系统的路径分隔符
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-4 下午2:15:49
	 * @return
	 */
	public static String getPathSeparator() {
		return System.getProperty("path.separator");
	}

	/**
	 * 
	 * 方法的描述:当前用户程序所在目录
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-4 下午2:15:49
	 * @return
	 */
	public static String getUserDir() {
		return System.getProperty("user.dir");
	}
	//测试
	public static void main(String [] arg)
	{
		System.out.println(getFileSeparator());
	}
}
