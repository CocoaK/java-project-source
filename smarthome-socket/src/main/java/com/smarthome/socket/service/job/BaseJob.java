package com.smarthome.socket.service.job;

import java.util.Calendar;
/**
 * 
 * 类名称：BaseJob 
 * 类描述：基类 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-11-29 上午10:21:49
 */
public class BaseJob
{
	/**
	 * 
	 * 方法的描述: 打印输出功能
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-29 上午10:22:04
	 * @param s
	 */
	public void print(String s)
	{
		System.out.println(Calendar.getInstance().getTime().toLocaleString()
				+ ":" + s);
	}
	public void printNoLn(String s)
	{
		System.out.print(Calendar.getInstance().getTime().toLocaleString()
				+ ":" + s+"||");
	}
	public void printSplitLine()
	{
		System.out.println(Calendar.getInstance().getTime().toLocaleString()
				+ ":-----------------------------------------------------------------------------");
	}
	public void execute()
	{
		// do somthing
		
	}
	
}
