//package com.xinzhuxiansheng.agent.server;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioDatagramChannel;
//
//public class NettyUDPClient {
//
//    public void run() throws Exception{
//        EventLoopGroup group = new NioEventLoopGroup();
//        try{
//            Bootstrap b = new Bootstrap();
//            b.group(group).channel(NioDatagramChannel.class)
//                    .option(ChannelOption.SO_BROADCAST,true);
//            Channel ch = b.bind(0).sync().channel();
//
//        }
//    }
//}
