package com.vn.camthanh.spring.discovery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CamthanhDiscoveryApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CamthanhDiscoveryApplication.class).run(args);
	}

}
