package top.mylady.runner.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Data;
//import org.apache.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import top.mylady.runner.ctrl.HelloWorld;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


@Data
@Configuration
@ConfigurationProperties(prefix="mysql.core")
@PropertySource(value="classpath:mysql-core-jdbc.properties")
public class MySqlConfig {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MySqlConfig.class);
    //private final static Logger logger = Logger.getLogger(MySqlConfig.class);

    private String jdbcUrl;
    private String jdbcUserName;
    private String jdbcPassword;
    private String jdbcDriver;
    private String rootMapper;      //mapper文件在classpath下存放的根路径
    private String aliasesPackage;  //别名包

    @Bean("PrintMySql")
    public String printObject(){
        logger.debug(jdbcUrl+ jdbcUserName);
        return jdbcUserName;
    }

    @Bean("mysqlCoreDataSource")
    public DataSource mysqlCoreDataSource() throws PropertyVetoException {
        logger.debug("开始连接数据库");
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(jdbcUserName);
        dataSource.setPassword(jdbcPassword);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setDriverClass(jdbcDriver);

        logger.info(String.format("数据库dataSource加载完毕, 交给Spring管理, 现在的连接对象是: %s", dataSource.getJdbcUrl()));
        logger.debug("返回给spring容器");
        return dataSource;
    }

}
