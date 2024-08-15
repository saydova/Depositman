package com.bank.depositman.repository;

import com.bank.depositman.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
Optional<Transaction>findByApprovalStatus(String approvalStatus);

}
