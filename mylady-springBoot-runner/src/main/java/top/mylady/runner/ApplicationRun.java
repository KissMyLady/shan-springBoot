package top.mylady.runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//@EnableAutoConfiguration
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Controller
public class ApplicationRun {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationRun.class);

    @RequestMapping("/sayHello")
    @ResponseBody
    public String sayHello(){
        logger.warn("测试消息... Hello World");
        return "Hello SpringBoot";
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);
    }
}
