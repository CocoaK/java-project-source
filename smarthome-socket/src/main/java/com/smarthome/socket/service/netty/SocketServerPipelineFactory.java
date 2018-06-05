package com.smarthome.socket.service.netty;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.util.CharsetUtil;
import org.jboss.netty.util.Timer;
import static org.jboss.netty.channel.Channels.pipeline;
/**
 * 
 * 项目名称：smarthome-socket 
 * 类名称：SocketServerPipelineFactory 
 * 类描述： 管道工厂类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-4-19 下午5:59:31
 */
public class SocketServerPipelineFactory implements ChannelPipelineFactory
{
	
	private Timer timer;
	
	private SocketServerHandler socketServerHandler;
	
	public SocketServerPipelineFactory(Timer timer,
			SocketServerHandler socketServerHandler)
	{
		super();
		this.timer = timer;
		this.socketServerHandler = socketServerHandler;
	}
	
	public ChannelPipeline getPipeline()
		throws Exception
	{
		ChannelPipeline pipeline = pipeline();
		// pipeline.addLast("decoder", new HttpRequestDecoder());
		
		// pipeline.addLast("encoder", new HttpResponseEncoder());
		// pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
		// pipeline.addLast("decoder", new MessageDecoder());
		// pipeline.addLast("encoder", new MessageEncoder());
		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(81920,
				Delimiters.lineDelimiter()));
		//pipeline.addLast("decoder", new StringDecoder("UTF-8"));
		//pipeline.addLast("encoder", new StringEncoder("UTF-8"));
		pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
		//pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());//对应 
		//pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());//此对象为 netty默认支持protocolbuf的编解码器 

		pipeline.addLast("handler", socketServerHandler);
		//Timer timers = new HashedWheelTimer();

		//pipeline.addLast("timeout", new ReadTimeoutHandler(timers, 60));
		//pipeline.addLast("timeout", new IdleStateHandler(timer, 10, 0, 0));// 此两项为添加心跳机制
		// 10秒查看一次在线的客户端channel是否空闲，IdleStateHandler为netty
		// jar包中提供的类
		//pipeline.addLast("hearbeat", new Heartbeat());// 此类
		// 实现了IdleStateAwareChannelHandler接口
		//pipeline.addLast("idleHandler", new Heartbeat()); 
		//Timer timer = new HashedWheelTimer();   
	  // pipeline.addLast("timeout", new ReadTimeoutHandler(timer, 10));   
       //pipeline.addLast("hearbeat", new Heartbeat());   

		return pipeline;
		
	}
}
