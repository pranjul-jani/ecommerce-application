package com.friday.ecommerceapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EcommerceApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApiGatewayApplication.class, args);
	}

}
