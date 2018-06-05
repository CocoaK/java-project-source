package com.smarthome.socket.common.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 
 * 类名称：TelnetUtil 
 * 类描述： 实现telnet功能
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-10-31 下午3:30:55
 */
public class TelnetUtil {
	/**
	 * 
	 * 方法的描述: 实现telnet功能
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-10-31 下午3:29:55
	 * @param ip ip地址
	 * @param port 端口
	 * @return telnet成功为true,否则为false
	 */
	public static boolean telnetHoust(String ip, int port) {
		boolean isOK = false;
		try {
			Socket server = new Socket();
			InetSocketAddress address = new InetSocketAddress(ip, port);
			server.connect(address, 5000);
			server.close();
			isOK = true;
		} catch (IOException e) {
			System.out.println("telnet host[ip: " + ip + ",port:" + port + "] failure!");
		}
		return isOK;
	}
}
