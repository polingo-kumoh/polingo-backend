package com.tangtang.polingo.security.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class OpenEntityManagerConfig {
    @Bean
    public FilterRegistrationBean<OpenEntityManagerInViewFilter> openEntityManagerInViewFilter() {
        FilterRegistrationBean<OpenEntityManagerInViewFilter> filterRegistrationFilter = new FilterRegistrationBean<>();
        filterRegistrationFilter.setFilter(new OpenEntityManagerInViewFilter());
        filterRegistrationFilter.setOrder(Integer.MIN_VALUE);
        return filterRegistrationFilter;
    }
}
