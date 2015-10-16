package iot.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApplicationTestContext {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
