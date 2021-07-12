package top.mylady.provider.ctrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mylady.provider.pojo.User;
import top.mylady.provider.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public User queryById(@RequestParam(value="id", required=true) Long id){
        if (id == null){
            User user = new User();
            user.setUsername("警告, id值为空了");
            return user;
        }
        logger.debug("路由转发层, 开始调用服务层查询用户并返回, 打印传递过来的id值: "+ id);
        return this.userService.queryById(id);
    }

    @GetMapping("/hi")
    public String Hi(){
        return "Hi";
    }
}
