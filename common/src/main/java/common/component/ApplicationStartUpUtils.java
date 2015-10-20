package common.component;

import java.io.File;
import java.net.InetAddress;
import java.net.URL;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;

import common.data.Connection;
import common.data.Setting;
import common.data.setting.SettingUtils;
import common.data.type.COMPONENT_TYPE;
import common.rest.UrlUtils;

public class ApplicationStartUpUtils {

    /**
     * On startup retrieve local setting data. If it is necessary update setting file.
     * 
     */

    public static void processLocalConnection(EmbeddedServletContainerInitializedEvent arg0) throws Exception {

        StringBuilder authority = new StringBuilder();
        authority.append(InetAddress.getLocalHost().getHostAddress());
        authority.append(":");
        authority.append(String.valueOf(arg0.getEmbeddedServletContainer().getPort()));

        UrlUtils.parseUrl(authority.toString());
        Connection localConnection = new Connection();
        localConnection.setUrl(UrlUtils.parseUrl(authority.toString()));

        Connection settingConnection = SettingUtils.getLocalConnection();

        if (!localConnection.equals(settingConnection)) {
            settingConnection.setUrl(localConnection.getUrl());
            settingConnection.setName("localConnection");
            settingConnection.setComponentType(COMPONENT_TYPE.LOCAL);
            Setting newSetting = SettingUtils.replaceConnection(settingConnection, COMPONENT_TYPE.LOCAL);
            SettingUtils.saveSetting(newSetting);
        }

    }

    public static String getBaseDirectory(Class<?> clazz) {
        URL jarUrl = clazz.getProtectionDomain().getCodeSource().getLocation();

        String baseDirectory = new File(jarUrl.getPath()).getParentFile().getPath();

        if (baseDirectory.startsWith("file:")) {
            baseDirectory = baseDirectory.substring(5);
        }
        baseDirectory = baseDirectory + File.separator;

        return baseDirectory;

    }

}
