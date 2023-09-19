package com.banking.bank.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.bank.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
	
}
