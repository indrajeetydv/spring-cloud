package com.cs.pbs.spring.feign.api.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cs.pbs.services.model.UserResponse;

//Note:  FeignClient name should follow same nameing conversion like it should have hyphen(-) between two words only

//@FeignClient(url="https://jsonplaceholder.typicode.com",name="USER-CLIENT")
//@FeignClient(url="https://jsonplaceholder.typicode.com",name="user-client")
@FeignClient(url="https://jsonplaceholder.typicode.com",name="spring-cloud-eureka-client")
public interface UserClient {
	
	@GetMapping("/users")
	public List<UserResponse> getUsers();
}
