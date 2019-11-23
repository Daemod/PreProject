package ru.severyuchin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/jquery/**").addResourceLocations("classpath:/META-INF/resources/webjars/jquery/3.4.1/");
        registry.addResourceHandler("/popper/**").addResourceLocations("classpath:/META-INF/resources/webjars/popper.js/1.15.0/umd/");
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/4.3.1/");
        registry.addResourceHandler("/jquery-json/**").addResourceLocations("classpath:/META-INF/resources/webjars/jquery-json/2.6.0/dist/");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }
}
