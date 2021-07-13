package top.mylady.provider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * 服务提供方, SpringBoot 启动器
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderApp {
    public static void main(String[] args) {

        SpringApplication.run(ProviderApp.class, args);
    }
}
