package top.mylady.consumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;


//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker   //开启熔断 三个简写 -> @SpringCloudApplication
//@EnableFeignClients     //开启Feign, 自动集成Ribbon负载均衡
@SpringCloudApplication
public class ConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }
}
