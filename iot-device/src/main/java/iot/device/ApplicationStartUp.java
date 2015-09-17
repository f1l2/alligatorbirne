package iot.device;

import iot.device.status.Status;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartUp implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    final static Logger logger = LoggerFactory.getLogger(ApplicationStartUp.class);

    @Autowired
    private Status status;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent arg0) {

        String port = String.valueOf(arg0.getEmbeddedServletContainer().getPort());

        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error(e.getMessage());
        }
    }
}
