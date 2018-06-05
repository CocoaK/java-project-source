package com.biencloud.smarthome.service.rss.help.weather;

import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;
import com.biencloud.smarthome.service.common.utils.JDomUtil;
import com.biencloud.smarthome.service.rss.vo.WeatherData;




/**
 * 
 * 类名称：AnalyzEngine 
 * 类描述： 数据解析引擎
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-23 上午9:45:45
 */
public class AnalyzEngine
{
	
	/**
	 * description:
	 * 
	 * @param args void
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static void main(String[] args)
	{
		WeatherData data=analyzData("F:/tomcat-6.0.26/webapps/hdips/weather/weather-info.xml");
		//System.out.println(data.getDataLists().size());
		
	}
	
	/**
	 * 
	 * 方法的描述: 解析xml，并转为 对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-23 上午9:46:44
	 * @param filePath
	 * @return
	 */
	public static WeatherData analyzData(String filePath)
	{
		WeatherData data = null;
		try
		{
			Document doc =JDomUtil.readXML(filePath);
			if (doc != null)
			{
				data=analyzXmlToDataObj(doc);
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	/**
	 * 
	 * 方法的描述: 将xml转为data对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-23 上午9:46:59
	 * @param document
	 * @return
	 * @throws JDOMException
	 */
	private static WeatherData analyzXmlToDataObj(Document document) throws JDOMException
	{
		WeatherData data = null;
		if (document != null)
		{
			data = new WeatherData();
			Element root = document.getRootElement();
			List<Element> elementList = XPath.selectNodes(root,"//data/list");
			if (elementList != null && elementList.size() > 0)
			{
				for (Element e : elementList)
				{
					if(e!=null)	
					{
						String type=e.getAttributeValue("type");
						if("name".equals(type))
						{
							data.setDate(e.getAttributeValue("date"));
							data.setWeek(e.getAttributeValue("week"));
						}else if("weather".equals(type))
						{
							List<Element> itemList = e.getChildren("item");
							if(itemList!=null&&!itemList.isEmpty())
							{
								for(Element it : itemList)
								{
									if(it!=null)
									{
										String t=it.getAttributeValue("type");
										if("ui_top".equals(t))
										{
											data.setDayTemp(it.getAttributeValue("temp"));
											data.setDayWeatherDesc(it.getAttributeValue("weather_desc"));
										}else if("ui_bottom".equals(t))
										{
											data.setNightTemp(it.getAttributeValue("temp"));
											data.setNightWeatherDesc(it.getAttributeValue("weather_desc"));
										}else if("ui_tb".equals(t))
										{
											data.setNightTemp(it.getAttributeValue("temp"));
											data.setNightWeatherDesc(it.getAttributeValue("weather_desc"));
										}
									}
								}
							}
						}
					}
				}
				
			}
			
		}
		return data;
		
	}
	
	
}
