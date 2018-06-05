package com.smarthome.socket.service.netty;

import java.net.InetSocketAddress;
import java.util.Calendar;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;
import org.jboss.netty.handler.timeout.ReadTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 类名称：Heartbeat 类描述： 心跳检测
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 下午9:11:16
 */
public class Heartbeat extends IdleStateAwareChannelHandler {
	
	int i = 0;
	int j = 0;
	int k = 0;
	Logger log = LoggerFactory.getLogger(Heartbeat.class.getName());

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception{
		super.messageReceived(ctx, e);
		InetSocketAddress address = (InetSocketAddress) e.getChannel().getRemoteAddress();
		String msg = (String) e.getMessage();
		log.info("accept client[ip:" + address.getAddress().getHostAddress() + "] heart Data:" + msg);
	}

	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		super.channelIdle(ctx, e);

		if (e.getState() == IdleState.WRITER_IDLE) {
			// if (e.getState() == IdleState.ALL_IDLE) {
			i++;
			System.out.println("write idle-------" + i);
			// e.getChannel().write("123");
		}
		if (e.getState() == IdleState.READER_IDLE) {
			// if (e.getState() == IdleState.ALL_IDLE) {
			j++;
			System.out.println("read idle-------" + j);
			// e.getChannel().write("123");
		}
		if (e.getState() == IdleState.ALL_IDLE) {
			// if (e.getState() == IdleState.ALL_IDLE) {
			k++;
			System.out.println("write and read idle-------" + k);
			// e.getChannel().write("123");
		}
		if (i == 18) {
			// e.getChannel().disconnect();
			// e.getChannel().close();
			i = 0;
			// System.out.println("终端空闲");
			log.info("server disconnect client...");
		}
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
	    if (e.getCause() instanceof ReadTimeoutException) {
	    	log.info(Calendar.getInstance().getTime().toLocaleString()
					+ ":exception.............");
	    }
	    ctx.getChannel().close();
	}

}
