package com.xinzhuxiansheng.agent.server;

import java.net.DatagramSocket;
import java.net.SocketException;

public class JDKUDPClient {

    private DatagramSocket socket;

    public  synchronized DatagramSocket getClient(){
        if(socket==null){
            initDatagramSocket();
        }
        return socket;
    }

    private void initDatagramSocket(){
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
