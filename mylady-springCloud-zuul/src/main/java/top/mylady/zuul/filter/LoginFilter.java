package top.mylady.zuul.filter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;


@Component
public class LoginFilter extends ZuulFilter {

    /**
     * 过滤类型, 前置过滤器
     */
    @Override
    public String filterType() {

        /*
        * 1, pre
        * 2, route
        * 3, post
        * 4, error
        * */
        return "pre";  //请求在被路由之前执行
    }

    /**
     * 执行顺序
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否生效
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤逻辑
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("进入到过滤逻辑当中, 判断是否有token");

        //获取zuul上下文对象
        RequestContext context = RequestContext.getCurrentContext();
        //从上下文对象中获取请求对象
        HttpServletRequest request = context.getRequest();
        //获取token
        String token = request.getParameter("access-token");
        if(StringUtils.isBlank(token)) {
            System.out.println("没有token");
            //拒绝
            context.setSendZuulResponse(false);
            //401
            context.setResponseStatusCode(401);  //HttpStatus.SC_UNAUTHORIZED
            context.setResponseBody("{\"status\":\"401\", \"text\":\"you have a wrong\"}");
        }else {
            context.set("token", token);
        }
        return null;
    }
}
