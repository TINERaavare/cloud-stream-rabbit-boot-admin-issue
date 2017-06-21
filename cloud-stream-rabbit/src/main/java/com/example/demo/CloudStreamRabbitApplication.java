package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling

@Configuration
@EnableIntegration
@IntegrationComponentScan
@ComponentScan
@EnableBinding(MessageChannels.class)
public class CloudStreamRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStreamRabbitApplication.class, args);
	}
}
