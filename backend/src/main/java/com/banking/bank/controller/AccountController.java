package com.banking.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bank.exceptions.NoDataFoundException;
import com.banking.bank.exceptions.ResourceNotFoundException;
import com.banking.bank.model.Account;
import com.banking.bank.model.AccountStatement;
import com.banking.bank.model.Transaction;
import com.banking.bank.service.AccountService;

@RestController//("AccountController")
@CrossOrigin("*")
public class AccountController {
	@Autowired
	AccountService accService;
	
	@PostMapping("/createAccount/{uname}")
	public String createAccount(@RequestBody Account account, @PathVariable("uname") String userid)
	{
		String result = "";
		Account acc = accService.createAccount(account, userid);
		
		if(acc != null)
			result = "Account created!";
		else
			result = "Account creation failed!";
		return result;
	}
	
	@GetMapping("/fetchTransactions/{accno}")
	public List<Transaction> fetchTransactions(@PathVariable("accno") long accno) throws NoDataFoundException
	{
		List<Transaction> result = accService.fetchTransactions(accno);
		return result;
	}
	
	@GetMapping("/getAccountDetails/{accno}")
	public Account getAccountDetails(@PathVariable("accno") long accno) throws ResourceNotFoundException
	{
		return accService.getAccountDetails(accno);
	}
	
	@PostMapping("/accountStatement/{accno}")
	public List<Transaction> accountStatement(@RequestBody AccountStatement accStat, @PathVariable("accno") long accno)
	{
		return accService.accountStatement(accno, accStat);
	}
}
