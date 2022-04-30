package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.beans.Account;
import com.example.model.service.AccountService;

/*
 * This must access service layer
 */
@RestController
@RequestMapping("first")
public class FirstRest {
	
	@Autowired
	private AccountService service;
	
	@GetMapping("/test")
	public ResponseEntity<Object> greet() {
		System.out.println("----------greet() of FirstRest-----");
		return ResponseEntity.status(200).body("Hello first microservice");
	}
	
	@PutMapping("/debit/{amount}")
	public ResponseEntity<Object> debitAPI(@PathVariable("amount") double amount) {
		Account account = service.debit(amount);
		return ResponseEntity.status(200).body(account);
	}
}
