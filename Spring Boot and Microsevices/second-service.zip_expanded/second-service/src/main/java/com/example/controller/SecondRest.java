package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("second")
public class SecondRest {

	// this is a load balanced RestTemplate
	@Autowired
	private RestTemplate template;
	
	@GetMapping
	public ResponseEntity<Object> callMicroservice() {
		String result = template.getForObject("http://APP1/first/test", String.class);
		return ResponseEntity.status(200).body("Second Microservices calls -> "+result);
	}
}
