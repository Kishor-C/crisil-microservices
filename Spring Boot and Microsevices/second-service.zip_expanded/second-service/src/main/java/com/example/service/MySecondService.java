package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class MySecondService {

	@Autowired
	private RestTemplate template;
	// fallback method is called when the CB is open
	@CircuitBreaker(name = "fetchFirstApp", fallbackMethod = "alternateService")
	public String callFirstMicroservice() {
		System.out.println("----Request goes to First Microservice -----");
		String result = template.getForObject("http://APP1/first/test", String.class);
		return result;
	}
	// this is the fallback method called when circuit is open, this can call another Microservice
	public String alternateService(Throwable t) {
		System.out.println("--------Original microservice is down, hence this fallback is called-------------");
		return "An alternate response from another microservice because service is down";
	}
}
