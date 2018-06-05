package com.smarthome.socket.service.netty;

import java.net.InetSocketAddress;
import java.util.Map;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.smarthome.socket.common.constant.PubConstant;
import com.smarthome.socket.service.netty.service.NettyService;
/**
 * 
 * 类名称：UdpClientHandler 
 * 类描述： udp客户端处理类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-11-29 上午10:48:26
 */
public class UdpClientHandler extends SimpleChannelUpstreamHandler {
	private static final Logger log = LoggerFactory.getLogger(UdpClientHandler.class);
	private static Map<String, String> service_status_map;
	private NettyService nettyService;
	public UdpClientHandler(Map<String, String> service_status_map,NettyService nettyService)
	{
		service_status_map=service_status_map;
		this.nettyService=nettyService;
	}
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		String msg = (String) e.getMessage();
		
		// System.out.println("accept client Data:" + msg);
		log.info("accept service Data:" + msg);
		if(msg!=null&&"service_is_ok".equals(msg))
		{
			service_status_map.put("server_status", "ok");
			
			e.getChannel().close();
			if("false".equals(service_status_map.get("init_device_status")))
			{
			nettyService.initialDeviceStatus(PubConstant.OUTLINE);
			service_status_map.put("init_device_status", "true");
			}
		}

	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		log.info("channelConnected");
		send(e);
	}

	@Override
	public void channelInterestChanged(ChannelHandlerContext ctx, ChannelStateEvent e) {
		log.info("channelInterestChanged");
		//send(e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		//log.info("Unexpected exception from downstream.", e.getCause());
	}

	private void send(ChannelStateEvent e) {
		Channel channel = e.getChannel();

		channel.write("tcp_server_heat_beat\r\n");

	}
	
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {

		InetSocketAddress address = (InetSocketAddress) e.getChannel().getRemoteAddress();

		super.channelDisconnected(ctx, e);
		log.info("udp client:" + address.getAddress().getHostAddress() + " disconnect...");
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		InetSocketAddress address = (InetSocketAddress) e.getChannel().getRemoteAddress();
		super.channelClosed(ctx, e);
		log.info("udp client:" + address.getAddress().getHostAddress() + " close...");
	}


}
