package com.banking.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.bank.dao.AccRepository;
import com.banking.bank.dao.CustomerRepository;
import com.banking.bank.dao.AdminRepository;
import com.banking.bank.dao.TransactionRepository;
import com.banking.bank.exceptions.NoDataFoundException;
import com.banking.bank.exceptions.ResourceNotFoundException;
import com.banking.bank.model.Account;
import com.banking.bank.model.Customer;
import com.banking.bank.model.Admin;
import com.banking.bank.model.Transaction;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	AccRepository accRepo;
	
	@Autowired
	TransactionRepository transRepo;
	
	@Autowired
	AdminRepository adminRepo;
	
	public String saveAdmin(Admin admin)
	{
		String result = "";
		Optional<Admin> o = adminRepo.findById(admin.getUserid());
		if(o.isPresent())
		{
			result = "Admin already exists!";
		}
		else {
			result = "Admin created successfully!";
			Admin obj = adminRepo.save(admin);
		}
		return result;
	}
	
	public String adminLogin(Admin u)
	{
		Admin admin = null;
		String result = "";
		
		Optional<Admin> obj = adminRepo.findById(u.getUserid());
		
		if(obj.isPresent())
		{
			admin = obj.get();
		}
		if(admin == null)
		{
			result = "Invalid Admin";
		}
		else
		{
			if(u.getPassword().equals(admin.getPassword()))
			{
				result = "Login success";
			}
			else
			{
				result = "Login failed";
			}
		}
		return result;
	}
	
	public String toggleDisable(long accno, String userid)
	{
		if(accRepo.findById(accno).isEmpty())
			return "Account not found";
		Account acc = accRepo.findById(accno).get();
		if(acc.isDisabled() == true)
		{
			acc.setDisabled(false);
			accRepo.save(acc);
			return "Account enabled";
		}
		else {
			if((acc.getBalance() < 10000 && acc.getAcctype().equals("Savings")) || (acc.getBalance() < 25000 && acc.getAcctype().equals("Current")) || (acc.getBalance() < 5000 && acc.getAcctype().equals("Salary")))
			{
				acc.setDisabled(true);
				accRepo.save(acc);
				return "Account Disabled";
			}
			else
				return "Account cannot be disabled";
		}
	}
	
	public List<Customer> getCustomers(String userid) throws NoDataFoundException
	{
		if(adminRepo.findById(userid).isEmpty())
		{
			throw new NoDataFoundException("No Customers to Display");
//			List<Customer> res = new ArrayList<>();
//			return res;
		}
		return custRepo.findAll();
	}
	
	public List<Account> getAccounts(String userid)  throws NoDataFoundException
	{
		if(adminRepo.findById(userid).isEmpty())
		{
//			List<Account> res = new ArrayList<>();
//			return res;
			throw new NoDataFoundException("No Accounts to Display");
		}
		return accRepo.findAll();
	}
	
	public List<Transaction> getTransactions(String userid)  throws NoDataFoundException
	{
		if(adminRepo.findById(userid).isEmpty())
		{
//			List<Transaction> res = new ArrayList<>();
//			return res;
			throw new NoDataFoundException("No Transactions to Display");
		}
		return transRepo.findAll();
	}
	
	
	public Admin fetchAdmin(String username) throws ResourceNotFoundException
	{
		Admin a  = adminRepo.findById(username).orElse(null);
		if(a==null)
			throw new ResourceNotFoundException("Admin Not Found");
		else
			return a;
	}
}
