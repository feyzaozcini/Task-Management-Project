package com.turkcell.taskservice;

import com.turkcell.tcellfinalcore.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableSecurity
@EnableFeignClients
@SpringBootApplication
public class TaskserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskserviceApplication.class, args);
	}

}
