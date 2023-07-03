package com.rabobank.bankapplication.repositories;

import com.rabobank.bankapplication.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
