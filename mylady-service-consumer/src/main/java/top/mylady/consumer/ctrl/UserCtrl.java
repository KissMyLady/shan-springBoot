package top.mylady.consumer.ctrl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.mylady.consumer.client.UserClient;
import top.mylady.consumer.pojo.User;
import java.util.List;


//@DefaultProperties(defaultFallback="ErrorServerBackMessage")
@RestController
@RequestMapping("/consumer/user")
public class UserCtrl {

    private static final Logger logger = LoggerFactory.getLogger(UserCtrl.class);

    @GetMapping("/test")
    public String test(){
        return "Conusmer 测试成功, 返回String类型字符串";
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/get")
    public User queryUserById(@RequestParam(value="id", required=true)  Long id){
        User user = restTemplate.getForObject("http://127.0.0.1:8003/user/get?id="+id, User.class);
        //User user = new User();
        return user;
    }

    @Autowired
    private UserClient userClient;

    /*
    * http://127.0.0.1:8004/consumer/user/feign?id=28
    * */
    @GetMapping("/feign")
    @ResponseBody
    public User feignProxy(@RequestParam(value="id", required=false)  Long id){
        System.out.println("进入到feign测试逻辑区, 打印传递的id: "+ id);
        User user = this.userClient.feignQueryUserById(id);
        return user;
    }

    @GetMapping("/geteureka")
    @ResponseBody
    @HystrixCommand(fallbackMethod="ErrorServerBackMessage",
            commandProperties = {
            //@HystrixProperty(name="circuitBreaker.enabled", value = "true"), // 开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "10"), //触发次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "5000"), //休眠时常
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "50")  //失败率
    })
    public User getEureka(@RequestParam(value="id", required=true)  Long id){
        System.out.println("开始熔断测试......");

        if (id == 1){
            throw new RuntimeException("服务器太忙了");
        }

        List<ServiceInstance> instances = null;

        try {
            instances = discoveryClient.getInstances("mylady-service-provider");
        }
        catch (Exception e){
            System.out.println("警告, ServiceInstance为空, 原因e: "+ e);
        }

        if (instances != null){
            ServiceInstance instance = instances.get(0);
            String baseUrl = "http://"+ instance.getHost()+ ":"+ instance.getPort()+ "/user/get?id="+ id;
            logger.debug("打印拼接的url: "+ baseUrl);
            //User user = restTemplate.getForObject(baseUrl, User.class);
            User user = new User();
            return user;
        }else {
            String reqUrl = "http://127.0.0.1:8003/user/get?id=28";
            //User user = restTemplate.getForObject(reqUrl, User.class);
            User user = new User();
            return user;
        }

    }


    /**
     * 熔断
     */
    public User ErrorServerBackMessage(Long id){
        System.out.println(" ");
        System.out.println("熔断返回消息...... ");
        return new User();
    }
}
