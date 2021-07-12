package top.mylady.admin.confi;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


//@ComponentScan("top.mylady.runner.config")
@Data
@ConfigurationProperties(prefix="spring.datasource")
@Configuration
public class RefMySql {

    private static final Logger logger = LoggerFactory.getLogger(RefMySql.class);

    private String name;
    private String password;

    /**
     * 使用druid链接池
     */
    @Bean
    public DataSource getDruidConn(){
        logger.debug("Admin模块, 开始配置Druid链接模块");
        //DruidDataSource dataSource = new DruidDataSource();
        //dataSource.setUsername("");
        return new DruidDataSource();
    }

    //配置Druid的监控
    //1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        logger.debug("配置Druid监控");
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername", name);
        initParams.put("loginPassword", password);
        initParams.put("allow", "localhost");//默认就是允许所有访问
        initParams.put("deny", "");
        bean.setInitParameters(initParams);
        return bean;
    }

    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return  bean;
    }


}
