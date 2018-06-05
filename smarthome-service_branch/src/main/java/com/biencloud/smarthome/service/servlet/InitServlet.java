package com.biencloud.smarthome.service.servlet;

import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import com.biencloud.smarthome.service.common.utils.FileUtil;
import com.biencloud.smarthome.service.common.utils.ServerPathUtil;

/**
 * 
 * 类名称：InitServlet 类描述： 启动初始化类
 * 
 * @author: 寇勇
 * @version: 0.1
 * @date: 2012-9-12 下午7:31:13
 */
@SuppressWarnings("serial")
public class InitServlet extends HttpServlet {
	
	/**
	 * 
	 * 方法的描述:初始化操作
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-9-12 下午7:31:13
	 * @see javax.servlet.GenericServlet#init()
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		//System.out.println("***************************weather_root_path:"+ServerPathUtil.weather_root_path);
		//System.out.println("***************************server_log_path:"+ServerPathUtil.server_log_path);
		//System.out.println("***************************server_deployments_path:"+ServerPathUtil.server_deployments_path);
		String basePath=this.getServletContext().getRealPath("/").replace("\\", "/");
		try {
			String configPath = basePath + "/WEB-INF/classes/rss/weather-config.xml";
			String logPath = basePath + "/WEB-INF/classes/log.properties";
							
			String newConfigPath = ServerPathUtil.weather_root_path+"weather-config.xml";
			String logConfigPath = ServerPathUtil.server_deployments_path+"/log.properties";
			File weatherFile = new File(newConfigPath);
			File logFile = new File(logConfigPath);
			//如果目录不存在，创建目录
			if (!weatherFile.exists()) {
				if(!weatherFile.getParentFile().exists()){
					if(!weatherFile.getParentFile().mkdirs()){
						System.out.println("weather目录创建失败！");
					}
				}
				FileUtil.copyFile(configPath, newConfigPath);
			}
			//如果目录不存在，创建目录
			if (!logFile.exists()) {
				if(!logFile.getParentFile().exists()){
					if(!logFile.getParentFile().mkdirs()){
						System.out.println("log目录创建失败！");
					}
				}
				FileUtil.copyFile(logPath, logConfigPath);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
