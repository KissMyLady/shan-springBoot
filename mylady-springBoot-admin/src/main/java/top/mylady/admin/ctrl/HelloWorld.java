package top.mylady.admin.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin")
public class HelloWorld {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    /**
     * 返回一个简单的Hello World
     */
    @RequestMapping("/hi")
    public String sayHi(){
        return "Hi";
    }

}
