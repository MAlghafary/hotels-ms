package com.mutaz.aggregatormicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages= {"com.mutaz.aggregatormicroservice"})
public class AggregatorMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AggregatorMicroserviceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}

