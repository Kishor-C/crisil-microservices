package com.example.model.service;

import org.springframework.stereotype.Service;

import com.example.model.beans.Account;

// This Service should implement some interface & autowire DAO
@Service
public class AccountService {

	public Account debit(double amount) {
		Account account = new Account();
		account.setAccountNumber(12345);
		account.setAmount(amount);
		return account;
	}
}
