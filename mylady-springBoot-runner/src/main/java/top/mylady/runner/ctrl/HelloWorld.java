package top.mylady.runner.ctrl;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import top.mylady.runner.ApplicationRun;
import top.mylady.runner.bean.pojos.Person;


@RestController
@RequestMapping("/say")
public class HelloWorld {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HelloWorld.class);
    //private final static Logger logger = Logger.getLogger(HelloWorld.class);

    @Autowired
    private Person person;

    @Value("${person.hiName}")
    private String hiName;

    @ResponseBody
    @GetMapping("/hello")
    public String sayHello(){
        logger.debug("打印自动配置的Person类: "+ person);
        return "Hello World";
    }

    @GetMapping("/hi")
    public String sayHi(){
        return "Hi"+ hiName;
    }
}
