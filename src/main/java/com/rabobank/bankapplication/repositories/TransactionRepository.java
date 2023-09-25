package com.rabobank.bankapplication.repositories;

import com.rabobank.bankapplication.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> getTransactionsByFromIban(String fromIban);
    List<Transaction> getTransactionsByToIban(String toIban);
}
