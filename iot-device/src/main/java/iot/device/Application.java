package iot.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static ConfigurableApplicationContext CONTEXT = null;

    public static void main(String[] args) {
        CONTEXT = SpringApplication.run(Application.class, args);
    }
}
