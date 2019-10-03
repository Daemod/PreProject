package ru.severyuchin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ru.severyuchin"})
public class WebConfig implements WebMvcConfigurer {
    @Bean
    InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        resolver.setRequestContextAttribute("requestContext");
        return resolver;
    }

    /*public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("WEB-INF/index");
    }*/


}
