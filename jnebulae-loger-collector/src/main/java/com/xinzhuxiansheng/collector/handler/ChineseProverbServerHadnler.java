package com.xinzhuxiansheng.collector.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChineseProverbServerHadnler extends SimpleChannelInboundHandler<DatagramPacket> {
    private static final Logger logger = LoggerFactory.getLogger(ChineseProverbServerHadnler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        String req = msg.content().toString(CharsetUtil.UTF_8);
        System.out.println(req);
//        if("dic".equals(req)){
//            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("谚语查询结果："+ nextQuote(),CharsetUtil.UTF_8)
//            ,msg.sender()));
//        }
    }

    /**
     * 异常捕获
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        logger.error("ctx close,cause:", cause);
    }
}
