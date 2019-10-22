package com.beasts.czs.config;

import com.beasts.czs.intecpter.LoginIntecpter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: chenjie
 * @Date: 2019/3/12 17:12
 * @Description:拦截配置（区别于过滤器）
 */
@Configuration
public class IntecpterConfig implements WebMvcConfigurer {

    @Autowired
    private LoginIntecpter loginIntecpter;

    public void addInterceptors(InterceptorRegistry registry) {
        //excludePathPatterns不拦截的路径
        //addPathPatterns拦截的路径
        registry.addInterceptor(loginIntecpter).addPathPatterns("/**").excludePathPatterns("/sys/**","/api/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
