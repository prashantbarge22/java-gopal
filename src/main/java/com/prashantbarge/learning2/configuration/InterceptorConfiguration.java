package com.prashantbarge.learning2.configuration;

import com.prashantbarge.learning2.Interceptor.AuthenticationInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors( InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor());
    }
}
