package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.clients.ClientFirst;
import com.example.model.beans.Account;
import com.example.model.beans.Wallet;
import com.example.service.MySecondService;

@RestController
@RequestMapping("second")
public class SecondRest {

	
	@Autowired
	private MySecondService service;
	
	// this is an instance that will be created dynamically because of @EnableFeingClients
	@Autowired
	private ClientFirst clientFirst;
	
	// calls via RestTemplate
	@GetMapping
	public ResponseEntity<Object> callMicroservice() {
		String result = service.callFirstMicroservice();
		return ResponseEntity.status(200).body("Second Microservices calls -> "+result);
	}
	@PutMapping("{amount}")
	public ResponseEntity<Object> callDebitService(@PathVariable("amount") double amount) {
		Account account = clientFirst.updateWallet(amount);
		Wallet wallet = new Wallet(); // initial balance is 500
		wallet.setBalance(wallet.getBalance() + account.getAmount()); // updates balance from account
		return ResponseEntity.status(200).body(wallet);
	}
}
