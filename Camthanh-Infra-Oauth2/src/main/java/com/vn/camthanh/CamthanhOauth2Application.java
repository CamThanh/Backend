package com.vn.camthanh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableAutoConfiguration//(exclude = {OAuth2ClientAutoConfiguration.class, SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
@SpringBootApplication
@EnableJpaRepositories(basePackages="com.vn")
@ComponentScan("com.vn")
@EnableAutoConfiguration
public class CamthanhOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(CamthanhOauth2Application.class, args);
    }
}
