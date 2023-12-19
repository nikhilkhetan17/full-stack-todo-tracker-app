package com.niit.todo.usertodoservice;

import com.niit.todo.usertodoservice.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserToDoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserToDoServiceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean jwtFilterBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/api/v2/user/*");
        return filterRegistrationBean;
    }

}
