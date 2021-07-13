package top.mylady.provider.mappers.User;
import top.mylady.provider.pojo.User;


public interface UserMapper{

    User queryUserById(Long id);

    User feignQueryUserById(Long id);
}
