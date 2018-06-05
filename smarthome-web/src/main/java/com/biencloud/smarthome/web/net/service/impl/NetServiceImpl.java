package com.biencloud.smarthome.web.net.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.DateTimeUtil;
import com.biencloud.smarthome.web.net.service.INetService;
import com.biencloud.smarthome.web.net.vo.NetVO;

/**
 * 
 * 类名称：NetServiceImpl 类描述： net service实现
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-10-17 下午7:29:01
 */
public class NetServiceImpl extends BaseService<NetVO> implements INetService {

	@Override
	public String ping(String ip) {
		//ping结果
		String pingResult = null;
		//运行时间对象
		Runtime runtime = Runtime.getRuntime();
		//处理进程对象
		Process process = null;
		String line = null;
		//输入流
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
        //字符串连接buffer
		StringBuffer sb = new StringBuffer();
		try {
			//ip不为null或空
			if (ip != null&&!"".equals(ip)) {
				Date d = new Date();
				sb.append(
						"---------------------start ping [time:" + DateTimeUtil.dateToString(d, "yyyy-MM-dd HH:mm:ss")
								+ "]---------------------").append("<br/>");
				//获得操作系统名称
				String os=System.getProperty("os.name");
				//windows操作系统执行ping命令
				if(os!=null&&os.toLowerCase().contains("windows"))
				{
					process = runtime.exec("ping " + ip);
				}else//Linux操作系统执行ping命令
				{
					process = runtime.exec("ping -c2 " + ip);
				}
				//ping执行结果流
				is = process.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
                //处理ping结果，并转为字符串
				while ((line = br.readLine()) != null) {

					sb.append(line).append("<br/>");
				}
				sb.append("--------------------------------end ping-------------------------").append("<br/>");
				//页面分行处理
				sb.append("<br/>");
				sb.append("<br/>");
				sb.append("<br/>");
				pingResult = sb.toString();
                //关闭操作
				is.close();
				isr.close();
				br.close();
			}
		} catch (IOException e) {//异常处理
			System.out.println(e);
			runtime.exit(1);
		}
		return pingResult;
	}

}
