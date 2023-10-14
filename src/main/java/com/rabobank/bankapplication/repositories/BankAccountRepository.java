package com.rabobank.bankapplication.repositories;


import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> getBankAccountByUser(User user);
}

