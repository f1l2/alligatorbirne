package common.component;

import java.net.InetAddress;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;

import common.data.Configuration;
import common.data.Connection;
import common.data.config.UtilsConfiguration;
import common.data.type.COMPONENT_TYPE;
import common.rest.UtilsUrl;

public class ApplicationStartUpUtils {

    /**
     * On startup retrieve local connection data. If it is necessary update configuration file.
     * 
     */

    public static void processLocalConnection(EmbeddedServletContainerInitializedEvent arg0) throws Exception {

        StringBuilder authority = new StringBuilder();
        authority.append(InetAddress.getLocalHost().getHostAddress());
        authority.append(":");
        authority.append(String.valueOf(arg0.getEmbeddedServletContainer().getPort()));

        UtilsUrl.parseUrl(authority.toString());
        Connection localConnection = new Connection();
        localConnection.setUrl(UtilsUrl.parseUrl(authority.toString()));

        Connection configConnection = UtilsConfiguration.getLocalConnection();

        if (!localConnection.equals(configConnection)) {
            configConnection.setUrl(localConnection.getUrl());
            configConnection.setName("localConnection");
            configConnection.setComponentType(COMPONENT_TYPE.LOCAL);
            Configuration newConfiguration = UtilsConfiguration.replaceConnection(configConnection, COMPONENT_TYPE.LOCAL);
            UtilsConfiguration.saveConfiguration(newConfiguration);
        }

    }

}
