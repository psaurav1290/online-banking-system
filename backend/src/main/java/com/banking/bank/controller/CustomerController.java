package com.banking.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.banking.bank.model.Account;
import com.banking.bank.model.Customer;
import com.banking.bank.model.LoginModel;
import com.banking.bank.service.CustomerService;
import com.banking.bank.exceptions.NoDataFoundException;
import com.banking.bank.exceptions.ResourceNotFoundException;

@RestController
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	CustomerService custService;

	@PostMapping("/saveCustomer")
	public String saveCustomer(@RequestBody Customer cust) {
		return custService.saveCustomer(cust);
	}

	@PostMapping("/login")
	public String validateCustomer(@RequestBody LoginModel u) {
		return custService.validateCustomer(u);
	}

	@GetMapping("/fetchAccounts/{username}")
	public List<Account> fetchAccounts(@PathVariable("username") String username) throws NoDataFoundException {
		List<Account> acc = custService.fetchAccounts(username);
		return acc;
	}

	@PutMapping("/changePassword/{otp}")
	public String changePassword(@RequestBody LoginModel u, @PathVariable("otp") String otp) {
		return custService.changePassword(u, otp);
	}

	@PutMapping("/changeDetails")
	public String changeDetails(@RequestBody Customer u) {
		return custService.changeDetails(u);
	}

	@GetMapping("/fetchUser/{username}")
	public Customer fetchUser(@PathVariable("username") String username) throws ResourceNotFoundException {

		return custService.fetchUser(username);
	}
}