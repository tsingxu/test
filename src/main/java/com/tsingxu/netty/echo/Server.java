package com.tsingxu.netty.echo;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.logging.Log4JLoggerFactory;

public class Server
{
	private int port;

	public Server(int port)
	{
		this.port = port;
	}

	public void run()
	{
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

		bootstrap.setPipelineFactory(new ChannelPipelineFactory()
		{

			@Override
			public ChannelPipeline getPipeline() throws Exception
			{
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("handler", new handler());

				return pipeline;
			}
		});

		bootstrap.bind(new InetSocketAddress(this.port));
	}

	public static void main(String[] args)
	{
		InternalLoggerFactory.setDefaultFactory(new Log4JLoggerFactory());
		new Server(8081).run();
	}

	class handler extends SimpleChannelUpstreamHandler
	{
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception
		{
			e.getChannel().write(e.getMessage());
		}
	}

}
