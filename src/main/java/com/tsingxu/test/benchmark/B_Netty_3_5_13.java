package com.tsingxu.test.benchmark;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * User: xuhuiqing
 * Date: 13-12-5
 * Time: 下午6:18
 */
public class B_Netty_3_5_13 {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();

                pipeline.addLast("handler", Handler.getInstance());

                return pipeline;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        bootstrap.bind(new InetSocketAddress(1025));
    }

    static class Handler extends SimpleChannelUpstreamHandler {
        private static final Handler instance = new Handler();

        static Handler getInstance() {
            return instance;
        }

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            e.getChannel().write(e.getMessage());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
            //
        }
    }
}
