package top.mylady.consumer.client;


import org.springframework.stereotype.Component;
import top.mylady.consumer.pojo.User;


@Component
public class BackError implements UserClient{

    @Override
    public User feignQueryUserById(Long id) {
        User user = new User();
        user.setUsername("对不起, 服务器繁忙 !");
        return user;
    }
}
