//import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import top.mylady.runner.ApplicationRun;
import top.mylady.runner.bean.pojos.Person;
import top.mylady.runner.config.MySqlConfig;
import javax.sql.DataSource;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationRun.class)
public class TestSpringBoot {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TestSpringBoot.class);
    //private final static Logger logger = Logger.getLogger(TestSpringBoot.class);

    @Autowired
    private Person person;


    @Autowired
    private DataSource mysqlCoreDataSource;

    @Test
    public void testOne(){
        logger.debug("单元测试打印Person: "+ person);
    }

    @Test
    public void testMySqlConn() throws Exception{
        logger.debug("mysqlCoreDataSource: "+ mysqlCoreDataSource.getConnection());
    }

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testRestTemplate(){

    }
}
