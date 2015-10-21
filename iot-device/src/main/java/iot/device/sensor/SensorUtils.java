package iot.device.sensor;

import org.springframework.util.StringUtils;

public class SensorUtils {

    public static String convertToClassName(String beanName) {

        if (StringUtils.isEmpty(beanName)) {
            return null;
        }

        if (beanName.length() == 1) {
            return beanName.toUpperCase();
        }

        String firstLetter = beanName.substring(0, 1).toUpperCase();
        String lastLetters = beanName.substring(1, beanName.length()).toLowerCase();

        return firstLetter.concat(lastLetters);
    }

}
