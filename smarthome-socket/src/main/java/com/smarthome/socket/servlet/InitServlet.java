package com.smarthome.socket.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smarthome.socket.common.constant.PubConstant;
import com.smarthome.socket.common.util.CacheUtil;
import com.smarthome.socket.common.util.ReadPropertyFile;
import com.smarthome.socket.service.job.TelnetJob;
import com.smarthome.socket.service.quartz.QuartzManagerJob;

/**
 * 
 * 类名称：InitServlet 类描述： 启动初始化类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-8-14 下午5:42:32
 */
public class InitServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(InitServlet.class);

	/**
	 * 
	 * 方法的描述:初始化操作
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-14 下午5:43:43
	 * @see javax.servlet.GenericServlet#init()
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		CacheUtil.getInstance().getAllMapsSize();
		CacheUtil.getInstance().clearAllMaps();
		CacheUtil.getInstance().removeKey(PubConstant.CRON_TIME_INSTANCY);
		CacheUtil.getInstance().removeKey(PubConstant.CRON_TIME_SECONDARY);
		CacheUtil.getInstance().removeKey(PubConstant.CRON_TIME_NORMAL);
		CacheUtil.getInstance().removeKey(PubConstant.CRON_TIME_HEARTBEAT);
		CacheUtil.getInstance().getAllMapsSize();
		String basePath = this.getServletContext().getRealPath("/").replace("\\", "/");
		System.out.println("basepath----------"+basePath);

		String serviceUrl = new ReadPropertyFile(basePath + "/WEB-INF/classes/webservices.properties").getPropetyValue("service.url");
		System.out.println("serviceUrl----------"+serviceUrl);
		if (serviceUrl != null) {
			String[] servicePath = serviceUrl.split(":");
			if (servicePath != null && servicePath.length > 0) {
				String ip = servicePath[1];
				String portString = servicePath[2];
				String port = null;
				if (portString != null) {
					String[] strArray = portString.split("/");
					if (strArray != null && strArray.length > 0) {
						port = strArray[0];
					}
				}
				if (ip != null) {
					ip = ip.replace("//", "");

				}
				if (ip != null && port != null) {
					log.info("每2秒向telnet一下Service服务器[ip:" + ip + ",port:" + port);
					CacheUtil.getInstance().removeKey(PubConstant.TELNET_SERVICE_STATUS);
					CacheUtil.getInstance().removeKey(PubConstant.INITAL_ALL_DEVICE);
					QuartzManagerJob.addJob("telnet_job", new TelnetJob(ip, port), "0/2 * * * * ?");
				}
			}
		}

	}

}
