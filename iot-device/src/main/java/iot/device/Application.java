package iot.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static String RELATIVE_PATH_TO_CONFIG = "setting/dev-setting.xml";

    public static void main(String[] args) {

        /**
         * At the moment only one parameter will be processed. It is reserved to define a relative path to the setting file.
         * 
         */
        for (int i = 0; i < args.length;) {
            RELATIVE_PATH_TO_CONFIG = args[i];
            break;
        }

        SpringApplication.run(Application.class, args);
    }
}
