package com.smarthome.socket.service.netty;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.util.CharsetUtil;
/**
 * 
 * 类名称：UdpClientPipelineFactory 
 * 类描述： udp客户端管道工厂类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-11-29 上午10:48:52
 */
public class UdpClientPipelineFactory implements ChannelPipelineFactory {
	private UdpClientHandler udpClientHandler;

	public UdpClientPipelineFactory(UdpClientHandler udpClientHandler) {
		this.udpClientHandler=udpClientHandler;
	}

	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = pipeline();

		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(10240,
				Delimiters.lineDelimiter()));
		
		pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

		// and then business logic.
		pipeline.addLast("handler", udpClientHandler);

		return pipeline;
	}

}
