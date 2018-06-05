package com.smarthome.socket.service.netty;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipelineCoverage;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
/**
 * 
 * 项目名称：smarthome-socket 
 * 类名称：MessageEncoder 
 * 类描述： 消息编码
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-4-19 下午6:00:55
 */
@ChannelPipelineCoverage("all")
public class MessageEncoder extends OneToOneEncoder 
{
	
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg)
		throws Exception
	{
		if (!(msg instanceof String)) {
            return msg;//(1)
        }
 
        String res = (String)msg;
        byte[] data = res.getBytes();
        int dataLength = data.length;
        ChannelBuffer buf = ChannelBuffers.dynamicBuffer();//(2)
        buf.writeInt(dataLength);
        buf.writeBytes(data);
        return buf;//(3)
	}

}
