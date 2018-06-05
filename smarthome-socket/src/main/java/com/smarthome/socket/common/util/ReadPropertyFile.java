package com.smarthome.socket.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * 
 * 类名称：ReadPropertyFile 
 * 类描述： 读取属性文件
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-8-14 下午4:03:33
 */
public class ReadPropertyFile {
	//属性文件路径
	private String filePath;
	
	
   public ReadPropertyFile(String filePath) {
		super();
		this.filePath = filePath;
	}
/**
    * 
    * 方法的描述: 根据属性文件中的key获取对应的值
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-8-14 下午4:01:28
    * @param key
    * @return
    */
	public String getPropetyValue(String key) {
		String value = null;		
		Properties p = new Properties();
		try {
			FileInputStream fis = new FileInputStream(filePath);// 属性文件输入流 
			p.load(fis);
			value = p.getProperty(key);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return value;
	}
	public static void main(String [] arg)
	{
		ReadPropertyFile rpf=new ReadPropertyFile("c:/fileServer.properties");
		String value=rpf.getPropetyValue("upload.root.path");
		System.out.println(value);
	}

}
