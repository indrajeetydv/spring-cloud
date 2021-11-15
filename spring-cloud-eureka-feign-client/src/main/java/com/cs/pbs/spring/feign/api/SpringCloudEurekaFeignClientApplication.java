package com.cs.pbs.spring.feign.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.pbs.services.model.UserResponse;
import com.cs.pbs.spring.feign.api.client.UserClient;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableEurekaClient
public class SpringCloudEurekaFeignClientApplication {
	
	@Autowired
	public UserClient client;

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaFeignClientApplication.class, args);
	}
	
	@GetMapping("/findAllUsers")
	public List<UserResponse> getAllUsers(){
		return client.getUsers();
	}
}
