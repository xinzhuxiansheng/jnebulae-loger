package com.xinzhuxiansheng.agent.appender;

import com.xinzhuxiansheng.agent.server.JDKUDPClient;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Plugin(name="UDPAppender",category = "Core",elementType = "appender",printObject = true)
public class UDPAppender extends AbstractAppender {
    private DatagramSocket socket;
    private InetAddress address ;

    protected UDPAppender(String name, Filter filter, Layout<? extends Serializable> layout,boolean ignoreException) {
        super(name, filter, layout,ignoreException);

        //创建UDP Client
        socket =new JDKUDPClient().getClient();
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void append(LogEvent event) {
        final byte[] bytes = getLayout().toByteArray(event);
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length,address,4993);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PluginFactory
    public static UDPAppender createAppender(@PluginAttribute("name") String name,
                                             @PluginElement("Filter") final Filter filter,
                                             @PluginElement("Layout") Layout<? extends Serializable> layout,
                                             @PluginAttribute("ignoreExceptions")boolean ignoreExceptions){
        if(name ==null){
            LOGGER.error("no name defined in conf.");
            return null;
        }
        if(layout == null){
            layout = PatternLayout.createDefaultLayout();
        }
        return new UDPAppender(name,filter,layout,ignoreExceptions);
    }
}
