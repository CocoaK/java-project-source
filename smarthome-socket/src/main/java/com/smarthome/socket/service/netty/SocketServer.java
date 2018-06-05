package com.smarthome.socket.service.netty;

import java.net.InetSocketAddress;

import org.jboss.netty.bootstrap.ServerBootstrap;

import java.util.concurrent.Executors;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smarthome.socket.common.constant.PubConstant;
import com.smarthome.socket.common.util.CacheUtil;
import com.smarthome.socket.service.netty.service.NettyService;

/**
 * 
 * 项目名称：smarthome-socket 类名称：SocketServer 类描述： socket服务器启动类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-4-19 下午6:00:15
 */
public class SocketServer {
	Logger logger = LoggerFactory.getLogger(SocketServer.class.getName());
	// 端口
	private int port;

	private Channel channel = null;

	private static Integer serverStatus = 0;
	// 处理类
	private SocketServerHandler socketServerHandler;

	private NettyService nettyService;

	public SocketServer(int port, SocketServerHandler socketServerHandler, NettyService nettyService)
	// public SocketServer(SocketServerHandler socketServerHandler)
	{
		super();
		this.port = port;
		this.socketServerHandler = socketServerHandler;
		this.nettyService = nettyService;

		start(port);
	}

	public static void main(String[] args) throws Exception {
		// start(port);

	}

	/**
	 * 
	 * 方法的描述: 启动
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-11 上午8:41:58
	 * @param port
	 */
	private void start(int port) {

		// Configure the server.
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));
		Timer timer = new HashedWheelTimer();

		// Set up the default event pipeline.
		bootstrap.setPipelineFactory(new SocketServerPipelineFactory(timer, socketServerHandler));
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		bootstrap.setOption("reuseAddress", true);
		// bootstrap.setOption("child.TIMEOUT", "120"); // 这里，很重要
		// Bind and start to accept incoming connections.
		channel = bootstrap.bind(new InetSocketAddress(port));
		if (channel != null) {
			boolean service_is_ok = CacheUtil.getInstance().serviceIsOK(PubConstant.TELNET_SERVICE_STATUS);
			if (service_is_ok) {
				nettyService.initAllDeviceStatus(PubConstant.OUTLINE);
				CacheUtil.getInstance().addCache(PubConstant.INITAL_ALL_DEVICE, PubConstant.INITAL_ALL_DEVICE_STATUS);
			}

		}

		// System.out.println("socket服务在端口"+port+"启动监听....");
		logger.info("tcp socket listener on port:" + port);

	}
}
