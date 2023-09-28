package com.banking.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.bank.dao.AccRepository;
import com.banking.bank.dao.CustomerRepository;
import com.banking.bank.model.Account;
import com.banking.bank.model.Customer;
import com.banking.bank.model.LoginModel;
import com.banking.bank.exceptions.NoDataFoundException;
import com.banking.bank.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository custRepo;

	@Autowired
	AccRepository accRepo;

	public String saveCustomer(Customer cust) {
		String result = "";
		Optional<Customer> o = custRepo.findById(cust.getUserId());
		if (o.isPresent()) {
			result = "Customer already exists!";
		} else {
			result = "Customer created successfully!";
			Customer obj = custRepo.save(cust);
		}
		return result;
	}

	public String validateCustomer(LoginModel u) {
		Customer cust = null;
		String result = "";

		Optional<Customer> obj = custRepo.findById(u.getUserId());

		if (obj.isPresent()) {
			cust = obj.get();
		}
		if (cust == null) {
			result = "Invalid Customer";
		} else {
			if (u.getPassword().equals(cust.getPassword())) {
				result = "Login success";
			} else {
				result = "Login failed";
			}
		}
		return result;
	}

	public List<Account> fetchAccounts(String username) throws NoDataFoundException {
		if (accRepo.findByUsername(username).isEmpty()) {
			throw new NoDataFoundException("No Data to Display");
		}
		return accRepo.findByUsername(username);
	}

	public String changePassword(LoginModel u, String otp) {
		String result = "";
		Customer cust = null;
		Optional<Customer> obj = custRepo.findById(u.getUserId());

		if (obj.isPresent())
			cust = obj.get();
		if (cust == null)
			result = "Invalid customer";
		else {
			if (otp.equals("123456")) {
				cust.setPassword(u.getPassword());
				custRepo.save(cust);
				result = "Success!";
			} else
				result = "Invalid OTP";
		}
		return result;
	}

	public String changeDetails(Customer u) {
		Optional<Customer> obj = custRepo.findById(u.getUserId());
		Customer cust = null;
		String result = "";
		if (obj.isPresent())
			cust = obj.get();
		if (cust == null)
			result = "Invalid Customer";
		else {
			cust.setFathername(u.getFathername());
			cust.setMothername(u.getMothername());
			cust.setPermanentAddress(u.getPermanentAddress());
			cust.setCurrentAddress(u.getCurrentAddress());
			custRepo.save(cust);
			result = "Success!";
		}
		return result;
	}

	public Customer fetchUser(String userid) throws ResourceNotFoundException {
		Customer u = custRepo.findById(userid).orElse(null);

		if (u == null)
			throw new ResourceNotFoundException("User not found");
		else
			return u;
	}

}