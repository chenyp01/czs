package com.beasts.szuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @Auther: chenjie
 * @Date: 2018/11/4 23:18
 * @Description:
 */
@Component
public class GatewayFilter extends ZuulFilter {

    /**
     *
     * 功能描述: 过滤器类型，主要为前置过滤器与后置过滤器
     *
     * @param:
     * @return:
     * @auther: chenjie
     * @date: 2018/11/4 下午11:18
     */
    @Override
    public String filterType() {
        //前置过滤器
        return PRE_TYPE;
    }

    /**
     *
     * 功能描述: 执行顺序，越小越先执行
     *
     * @param:
     * @return:数字，可以是负数
     * @auther: chenjie
     * @date: 2018/11/4 下午11:21
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     *
     * 功能描述: 是否拦截
     *
     * @param:
     * @return:true 拦截，false 不拦截
     * @auther: chenjie
     * @date: 2018/11/4 下午11:22
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.out.println("是否拦截处理类");
        //如果请求的路径中包含了"xxxxx"(模拟请求url),则不拦截
        if("xxxxx".equalsIgnoreCase(request.getRequestURI())){
            return false;
        }

        return true;
    }

    /**
     *
     * 功能描述: 拦截器处理业务逻辑方法
     * shouldFilter方法返回false时则不触发该方法
     *
     * @param:
     * @return:
     * @auther: chenjie
     * @date: 2018/11/4 下午11:23
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("拦截器业务处理类");
        return null;
    }
}
