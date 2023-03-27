package edu.na.repository;

import edu.na.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends  JpaRepository<Transaction,Long> {
}
