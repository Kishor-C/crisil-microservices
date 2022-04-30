package com.example.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.model.beans.Account;

// This is a REST client to access APP1 which is the first microservice
@FeignClient("APP1")
public interface ClientFirst {

	// you will get {"accountNumber", "amount"} from first microservice
	// you need to return the model that represents above structure
	@PutMapping("/first/debit/{amt}")
	public Account updateWallet(@PathVariable("amt") double amt); // this calls /debit of APP1/debit through PUT
}
