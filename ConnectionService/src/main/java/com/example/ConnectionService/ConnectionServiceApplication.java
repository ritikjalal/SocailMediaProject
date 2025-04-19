package com.example.ConnectionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConnectionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectionServiceApplication.class, args);
	}

}
