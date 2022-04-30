package com.example.configclientapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig {

	// it reads the property file and injects value
	@Value("${environment.name}")
	private String environmentName;
	
	public String getName() {
		return environmentName;
	}
}
