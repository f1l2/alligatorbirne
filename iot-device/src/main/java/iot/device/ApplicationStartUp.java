package iot.device;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import common.data.Configuration;
import common.data.Connection;
import common.data.config.UtilsConfiguration;
import common.data.type.COMPONENT_TYPE;
import common.rest.UtilsUrl;
import iot.device.status.STATUS_TYPE;
import iot.device.status.Status;

@Component
public class ApplicationStartUp implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    final static Logger logger = LoggerFactory.getLogger(ApplicationStartUp.class);

    @Autowired
    private Status status;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent arg0) {

        /**
         * On startup retrieve local connection data. If it is necessary update configuration file.
         * 
         */

        try {

            StringBuilder authority = new StringBuilder();
            authority.append(InetAddress.getLocalHost().getHostAddress());
            authority.append(":");
            authority.append(String.valueOf(arg0.getEmbeddedServletContainer().getPort()));

            UtilsUrl.parseUrl(authority.toString());
            Connection localConnection = new Connection();
            localConnection.setUrl(UtilsUrl.parseUrl(authority.toString()));

            Connection configConnection = UtilsConfiguration.getIoTDeviceConnection();

            if (!localConnection.equals(configConnection)) {
                configConnection.setUrl(localConnection.getUrl());
                configConnection.setName("localConnection");
                configConnection.setComponentType(COMPONENT_TYPE.IOT_DEVICE);
                Configuration newConfiguration = UtilsConfiguration.replaceIoTDeviceConnection(configConnection);
                UtilsConfiguration.saveConfiguration(newConfiguration);
            }

        } catch (Exception e) {
            logger.error("Error retrieving local connection data.", e);
        } finally {
            status.setCurrent(STATUS_TYPE.STARTED_UP);
        }

    }
}
