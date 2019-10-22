package com.beasts.czs.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * @Auther: chenjie
 * @Date: 2019/3/11 17:21
 * @Description:开关过滤器
 */
@WebFilter(urlPatterns = "/*" , filterName = "offFilter")
public class OffFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);

        if(hour >= 0 && hour < 5){//0点至5点间禁止网站访问
            PrintWriter out = response.getWriter();
            out.write("<h1 align=\"center\">系统维护中....</h1>");
            out.flush();//强制将缓冲区中的数据发送出去,不必等到缓冲区满
            out.close();
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    public void destroy() {

    }
}
