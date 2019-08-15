package com.xinzhuxiansheng.collector.server;

import com.xinzhuxiansheng.collector.handler.ChineseProverbServerHadnler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyUDPServer implements Server{
    private static final Logger logger = LoggerFactory.getLogger(NettyUDPServer.class);

    @Override
    public void preStart() {

    }

    @Override
    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); //udp不能使用ServerBootstrap
            b.option(ChannelOption.SO_BROADCAST, true);
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .handler(new ChineseProverbServerHadnler());
            b.bind(4993).sync().channel().closeFuture().await();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
//        //主从Reactor多线程模型
//        EventLoopGroup bossGroup = new NioEventLoopGroup(CommonConstants.BOSS_GROUP_SIZE, new DefaultThreadFactory("boss", true));
//        EventLoopGroup workerGroup = new NioEventLoopGroup(CommonConstants.WORKER_GROUP_SIZE, new DefaultThreadFactory("worker", true));
//        try {
//            long start = System.currentTimeMillis();
//            ServerBootstrap b = new ServerBootstrap();
//            b.option(ChannelOption.SO_BACKLOG, 1024);
//            b.group(bossGroup, workerGroup) //
//                    .channel(NioServerSocketChannel.class)   //设置NIO的双向通道
////             .handler(new LoggingHandler(LogLevel.INFO))
//                    .childHandler(new NettyUDPServerInitializer()); //配置子处理器，用于处理workdGroup的任务
//            //每一个channel由多个handler共同组成管道(pipeline)
//
//            //绑定端口，同步等待成功
//            ChannelFuture future = b.bind(CommonConstants.SERVER_PORT).sync();
//            long cost = System.currentTimeMillis()-start;
//            logger.info("[NettyUDPServer] Startup at port:{} cost:{}[ms]",CommonConstants.SERVER_PORT,cost);
//
//            // 等待服务端Socket关闭
//            future.channel().closeFuture().sync();
//        } catch (InterruptedException e) {
//            logger.error("[NettyUDPServer] InterruptedException:",e);
//        } finally {
//            //优雅退出，释放线程池资源
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }

    }
}
