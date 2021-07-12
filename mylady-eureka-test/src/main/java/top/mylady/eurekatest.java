package top.mylady;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class eurekatest {

    public static void main(String[] args) {
        SpringApplication.run(eurekatest.class, args);
    }
}
