package com.vn.camthanh.spring.security.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.vn.camthanh")
@ComponentScan("com.vn.camthanh")
public class CamthanhOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(CamthanhOauth2Application.class, args);
    }
}
