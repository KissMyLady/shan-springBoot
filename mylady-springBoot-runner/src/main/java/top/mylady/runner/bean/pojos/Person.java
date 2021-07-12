package top.mylady.runner.bean.pojos;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 将yam配置映射到这里
 * `@ConfigurationProperties: 告诉SpringBoot将本类中的所有属性和配置文件中的相绑定
 * 要在容器中才能使用绑定
 */
@Component
@Data
@ConfigurationProperties(prefix="person")
public class Person {

    @Value("${person.last-name}")
    private String lastName;
    private Integer age;
    private boolean boss;
    private String idCard;
    private Date birthday;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;
}
