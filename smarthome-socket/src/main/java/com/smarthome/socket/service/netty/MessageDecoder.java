package com.smarthome.socket.service.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 * 
 * 项目名称：smarthome-socket 
 * 类名称：MessageDecoder 
 * 类描述： 消息解码
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-4-19 下午6:01:24
 */
public class MessageDecoder extends FrameDecoder 
{
	
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer)
		throws Exception
	{
		 if (buffer.readableBytes() < 4) {
	            return null;//(1)
	        }
	        int dataLength = buffer.getInt(buffer.readerIndex());
	        if (buffer.readableBytes() < dataLength + 4) {
	            return null;//(2)
	        }
	 
	        buffer.skipBytes(4);//(3)
	        byte[] decoded = new byte[dataLength];
	        buffer.readBytes(decoded);
	        String msg = new String(decoded);//(4)
	        return msg;
	}

}
