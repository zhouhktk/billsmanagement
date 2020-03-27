package com.zfnotes.config;

import com.zfnotes.component.MyLocaleResolver;
import com.zfnotes.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author:zhoufeng
 * @Date:2020/3/22
 */
@Configuration
public class MyMvcConfiger{

    //添加自定义MVC配置
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            //添加自定义视图映射
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("main/login");
                registry.addViewController("/login").setViewName("main/login");
                registry.addViewController("/index").setViewName("main/index");
            }

            //添加自定义拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/", "/sign", "/login")
                        //springboot2之后需要将静态资源的路径页排除掉
                        .excludePathPatterns("/css/**", "/img/**", "/js/**", "**/favicon.ico");
            }
        };
    }

    //添加自定义区域解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

}
