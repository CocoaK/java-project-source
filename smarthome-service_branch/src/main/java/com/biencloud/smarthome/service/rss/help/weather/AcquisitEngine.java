package com.biencloud.smarthome.service.rss.help.weather;

import java.io.FileNotFoundException;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;

/**
 * 
 * 类名称：AcquisitEngine 
 * 类描述： 数据采集引擎 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-23 上午9:45:28
 */
public class AcquisitEngine
{
	
	/**   
	 * description: 
	 * @param args void
	 * @author:kouy   
	 * @since  1.0.0   
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * description: 数据采集
	 * @param configPath
	 * @param outPutPath
	 * @return String
	 * @author:kouy   
	 * @since  1.0.0
	 */
	public static String acquisitData(String configPath,String outPutPath)
	{
        String path=null;
		try
		{
			ScraperConfiguration config = new ScraperConfiguration(configPath);	
			
			Scraper scraper = new Scraper(config, outPutPath);
			
			
			// 设置爬虫代理
			
			//scraper.getHttpClientManager().setHttpProxy("218.56.64.210", 8080);
			
			//scraper.setDebug(true);
			
			scraper.execute();
			path=outPutPath;
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return path;
	}
	
}
