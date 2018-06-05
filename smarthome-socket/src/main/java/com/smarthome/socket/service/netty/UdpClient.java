package com.smarthome.socket.service.netty;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.smarthome.socket.service.netty.service.NettyService;
/**
 * 
 * 类名称：UdpClient 
 * 类描述：Udp客户端 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-10-30 上午10:56:37
 */
public class UdpClient {
	private static final Logger log = LoggerFactory.getLogger(UdpClient.class);
	
	public UdpClient(String ip,Map<String, String> service_status_map,NettyService nettyService) {

		send(ip, 7766,service_status_map,nettyService);
	}

	public void send(String ip, int port,Map<String, String> service_status_map,NettyService nettyService) {

		ConnectionlessBootstrap bootstrap = new ConnectionlessBootstrap(new NioDatagramChannelFactory(Executors.newCachedThreadPool()));

		bootstrap.setPipelineFactory(new UdpClientPipelineFactory(new UdpClientHandler(service_status_map,nettyService)));

		//bootstrap.connect(new InetSocketAddress(ip, port));
		bootstrap.connect(new InetSocketAddress(port));
	}

	public static void main(String[] arg) {
		//new UdpClient("127.0.0.1");
	}
}
