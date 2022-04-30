package com.example.configclientapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my-app")
public class RestApi {
	
	@Autowired
	private HelloConfig config; 
	@GetMapping
	public String getConfig() {
		return config.getName(); 
	}
}
