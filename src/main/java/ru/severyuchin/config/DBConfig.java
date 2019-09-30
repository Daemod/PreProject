package ru.severyuchin.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan("ru.severyuchin")

public class DBConfig {

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "root");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        return properties;
    }
    @Bean
    public EntityManagerFactory entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("ru.severyuchin");
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }
    @Bean
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("ru.severyuchin.entitys");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }




}
