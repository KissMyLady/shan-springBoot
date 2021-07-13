package top.mylady.consumer.client;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mylady.consumer.pojo.User;


//@FeignClient(value="mylady-service-provider")
public interface UserClient {

    @GetMapping("/user/get?id={id}")
    User queryUserById(@RequestParam("id") Long id);

}
