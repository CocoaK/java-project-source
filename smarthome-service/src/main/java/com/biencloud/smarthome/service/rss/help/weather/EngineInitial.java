package com.biencloud.smarthome.service.rss.help.weather;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.xpath.XPath;

import com.biencloud.smarthome.service.common.utils.JDomUtil;



/**
 * 
 * 类名称：EngineInitial 
 * 类描述： 引擎初始化
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-23 上午9:46:00
 */
public class EngineInitial
{
	/**
	 * 
	 * 方法的描述: 修改xml配置文件
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-23 上午9:46:14
	 * @param filePath
	 * @param httpUrl
	 * @param fileName
	 * @return
	 */
	public static boolean modifyXML(String filePath,String httpUrl,String fileName)
	{
		boolean flag=true;
		try
		{
			Document doc=JDomUtil.readXML(filePath);
			if(doc!=null)
			{
				Element root = doc.getRootElement();

				List<Element> httpList=XPath.selectNodes(root,"//config/var-def[@name='start']/html-to-xml/http");
				if(httpList!=null&&httpList.size()>0)
				{
					Element http=httpList.get(0);
					if(http!=null)
					{
						http.setAttribute("url", httpUrl);
					}
				}
				List<Element> fileList=XPath.selectNodes(root,"//config/file");
				if(fileList!=null&&fileList.size()>0)
				{
					Element file=fileList.get(0);
					if(file!=null)
					{
						file.setAttribute("path", fileName);						
					}
				}
				JDomUtil.writerXML(filePath, doc, "UTF-8");
			}
		}
		catch (Exception e)
		{
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}
	public static void main(String [] args)
	{
		String filePath="C:/weather-config.xml";
		String httpUrl="http://www.nmc.gov.cn/publish/forecast/AGD/shenzhen.html";
		String fileName="C:/test1111.xml";
		boolean flag=modifyXML(filePath, httpUrl, fileName);
		System.out.println(flag);
	}
}
