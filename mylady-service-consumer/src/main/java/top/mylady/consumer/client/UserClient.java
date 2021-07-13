package top.mylady.consumer.client;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import top.mylady.consumer.pojo.User;


@Component
@FeignClient(value="mylady-service-provider", fallback=BackError.class)
public interface UserClient {

    //参考: https://www.cnblogs.com/LixiaoFeng1650062546/p/14040650.html
    @GetMapping("/user/get?id={id}")
    User feignQueryUserById(@PathVariable Long id);

    //User feignQueryUserById(@PathVariable Long id);
}
