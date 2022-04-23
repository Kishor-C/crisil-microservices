package com.example.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.User;

@RestController // this allows you to create webservices
@RequestMapping("hello") // this mentions the url
public class HelloRestApi {
	// Http methods: @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
	
	// Retrieve operation means  @GetMapping
	@GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser() {
		User user = new User("Raj", 30);
		return user;
	}
}
