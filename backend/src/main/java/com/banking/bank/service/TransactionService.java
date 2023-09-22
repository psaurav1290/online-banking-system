package com.banking.bank.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.bank.dao.AccRepository;
import com.banking.bank.dao.TransactionRepository;
import com.banking.bank.model.Account;
import com.banking.bank.model.Transaction;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transRepo;

    @Autowired
    AccRepository accRepo;

    public Transaction transact(Transaction trans) {
        long accnumber = trans.getAccFrom();
        long toAccNum = trans.getAccTo();
        Account acc = accRepo.findById(accnumber).get();
        double balance = acc.getBalance();
        double amt = trans.getAmount();
        if (amt > balance || accRepo.findById(toAccNum).isEmpty() || acc.isDisabled() == true
                || accRepo.findById(toAccNum).get().isDisabled() == true)
            trans.setStatus("FAIL");
        else {
            Account acc2 = accRepo.findById(toAccNum).get();
            trans.setStatus("SUCCESS");
            balance -= amt;
            if (accnumber != toAccNum) {
                double balance2 = acc2.getBalance();
                balance2 += amt;
                acc2.setBalance(balance2);
                accRepo.save(acc2);
            }
            acc.setBalance(balance);
            accRepo.save(acc);
        }
        trans.setAcc_no(acc);
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        trans.setTimestamp(timeStamp);
        return transRepo.save(trans);
    }
}