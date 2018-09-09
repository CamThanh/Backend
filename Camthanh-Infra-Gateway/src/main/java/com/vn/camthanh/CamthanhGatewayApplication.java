package com.vn.camthanh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableZuulProxy
//@EnableOAuth2Sso
@EnableDiscoveryClient
@ComponentScan("com.vn")
public class CamthanhGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamthanhGatewayApplication.class, args);
	}

}