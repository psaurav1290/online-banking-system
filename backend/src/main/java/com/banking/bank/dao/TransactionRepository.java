package com.banking.bank.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.banking.bank.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	@Query("select transaction from Transaction transaction where (transaction.accFrom=?1 or transaction.accTo=?1) and transaction.status=?2")
	public List<Transaction> findByAccountNumber(long accno, String status);
}