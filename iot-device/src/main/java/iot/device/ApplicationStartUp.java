package iot.device;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import common.data.Configuration;
import common.data.Connection;
import common.data.config.UtilsConfiguration;
import common.data.util.Util;

@Component
public class ApplicationStartUp implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    final static Logger logger = LoggerFactory.getLogger(ApplicationStartUp.class);

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent arg0) {

        try {

            StringBuilder authority = new StringBuilder();
            authority.append(InetAddress.getLocalHost().getHostAddress());
            authority.append(":");
            authority.append(String.valueOf(arg0.getEmbeddedServletContainer().getPort()));

            Connection localConnection = UtilsConfiguration.getIoTDeviceConnection();

            if (null == localConnection) {
                localConnection = new Connection();
                localConnection.setUrl(Util.parseUrl(authority.toString()));
            }

            if (!authority.toString().equals(localConnection.getUrl())) {
                Configuration configuration = UtilsConfiguration.loadConfiguration();
                // configuration.
            }

        } catch (Exception e) {
            logger.error("Error {}", e);
        }

    }
}
