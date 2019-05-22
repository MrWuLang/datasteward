package com.young.datasteward.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 自定义druid数据源配置类
 * @author: WL
 * @create: 2019-02-19 14:01
 **/
@Configuration
public class DruidConfiguration {
    @Value("${druid.loginUsername}")
    private String loginUsername;
    @Value("${druid.loginPassword}")
    private String loginPassword;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid() {
        return new DruidDataSource();
    }

    /**
     * 拦截器
     * loginUsername，loginPassword必须拼写正确
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewName() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> param = new HashMap();
        param.put("allow", "");
        param.put("deny", "192.10.1.1");
        param.put("loginUsername", loginUsername);
        param.put("loginPassword", loginPassword);
        registrationBean.setInitParameters(param);
        return registrationBean;
    }

    /**
     * 过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());

        Map<String, String> param = new HashMap();
        param.put("exclusions", "*.png,*.jpg,/druid/*");
        filterRegistrationBean.setInitParameters(param);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }
}
