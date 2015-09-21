package iot.device;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import common.data.Connection;
import common.data.config.UtilsConfiguration;
import common.data.configuration.XMLConfiguration;

@Component
public class ApplicationStartUp implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    final static Logger logger = LoggerFactory.getLogger(ApplicationStartUp.class);

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent arg0) {

        try {

            StringBuilder url = new StringBuilder();

            url.append(InetAddress.getLocalHost().getHostAddress());
            url.append(":");
            url.append(String.valueOf(arg0.getEmbeddedServletContainer().getPort()));

            Connection connection = UtilsConfiguration.getIoTDevicesConnection().get(0);

            if (!url.toString().equals(connection.getUrl())) {
                XMLConfiguration loadConfiguration = UtilsConfiguration.loadConfiguration();
                loadConfiguration.set
            }

        } catch (Exception e) {
            logger.error("Error {}", e);
        }

    }
}
