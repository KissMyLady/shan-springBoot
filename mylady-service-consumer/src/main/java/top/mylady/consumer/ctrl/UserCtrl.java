package top.mylady.consumer.ctrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.mylady.consumer.pojo.User;


@RestController
@RequestMapping("/consumer/user")
public class UserCtrl {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get")
    public User queryUserById(@RequestParam(value="id", required=true)  Long id){
        User user = restTemplate.getForObject("http://127.0.0.1:8003/user/get?id="+id, User.class);
        return user;
    }

}
