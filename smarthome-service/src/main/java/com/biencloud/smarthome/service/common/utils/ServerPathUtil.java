package com.biencloud.smarthome.service.common.utils;

/**
 * 
 * description: Struts2工具类 ,取JBOSS环境变量
 * 
 * @fileName:Struts2Util.java
 * @createTime:2010-10-13 下午03:10:39
 * @author:kouy
 * @version 1.0.0
 * 
 */
public class ServerPathUtil {
	//Rss天气预报目录
	public final static String weather_root_path = System.getProperty("jboss.server.base.dir")+"/rss/weather/";
	//JBoss日志目录
	public final static String server_log_path = System.getProperty("jboss.server.log.dir");
	//standalone/deployments/目录
	public final static String server_deployments_path = System.getProperty("jboss.server.base.dir")+"/deployments/";
}
