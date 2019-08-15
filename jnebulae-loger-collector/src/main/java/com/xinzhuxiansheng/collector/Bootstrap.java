package com.xinzhuxiansheng.collector;

import com.xinzhuxiansheng.collector.server.NettyUDPServer;

public class Bootstrap {

    public static void main(String[] args) {
        NettyUDPServer nettyUDPServer = new NettyUDPServer();
        nettyUDPServer.start();
    }
}
