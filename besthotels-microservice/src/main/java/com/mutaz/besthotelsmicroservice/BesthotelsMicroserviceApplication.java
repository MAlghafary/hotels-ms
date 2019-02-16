package com.mutaz.besthotelsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.mutaz.besthotelsmicroservice"})
public class BesthotelsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BesthotelsMicroserviceApplication.class, args);
	}

}

