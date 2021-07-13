package top.mylady.provider.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mylady.provider.mappers.User.UserMapper;
import top.mylady.provider.pojo.User;


@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据id查询用户
     */
    public User queryById(Long id){
        return this.userMapper.queryUserById(id);
    }

    public User feignQueryUserById(Long id){
        return this.userMapper.queryUserById(id);
    }
}
