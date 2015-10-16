package iot.device.sensor;

import java.net.URL;
import java.net.URLClassLoader;

public class DynamicSensorLoader extends URLClassLoader {

    public DynamicSensorLoader(URLClassLoader classLoader) {
        super(classLoader.getURLs());
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

}
