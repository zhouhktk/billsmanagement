package com.zfnotes.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 绑定druid的相关信息
 */
@Configuration
public class DruidConfig {


    /**
     * 将配置文件中的值注入到DruidDataSource对象中
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    /**
     * 配置一个druid的监控
     * 1.配置一个druid的后台管理servlet
     * 2.配置一个druid的filter
     */

    // 1.配置一个druid的后台管理servlet
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean<StatViewServlet> servlet = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //设置初始化参数值
        Map<String,String> initParams = new HashMap<>();
        initParams.put(StatViewServlet.PARAM_NAME_USERNAME, "admin");
        initParams.put(StatViewServlet.PARAM_NAME_PASSWORD, "123456");
        //如果不写则所有ip均可访问后台
        initParams.put(StatViewServlet.PARAM_NAME_ALLOW, "");
        //拒绝哪些ip访问
        initParams.put(StatViewServlet.PARAM_NAME_DENY, "192.168.10.2");

        servlet.setInitParameters(initParams);

        return servlet;
    }

    // 2.配置一个druid的filter
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new WebStatFilter());

        //设置初始化参数值
        Map<String,String> initParams = new HashMap<>();
        initParams.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*");
        filter.setInitParameters(initParams);

        //配置拦截请求
        filter.setUrlPatterns(Arrays.asList("/*"));

        return filter;
    }
}
