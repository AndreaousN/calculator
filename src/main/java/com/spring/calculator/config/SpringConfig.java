package com.spring.calculator.config;

import com.spring.calculator.service.NumberService;
import com.spring.calculator.service.NumberServiceImpl;
import com.spring.calculator.service.UserService;
import com.spring.calculator.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    @Qualifier("UserService")
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    @Bean
    @Qualifier("NumberService")
    public NumberService getNumberService() {
        return new NumberServiceImpl();
    }
}
