package com.banking.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.bank.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}
