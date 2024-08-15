package com.bank.depositman.service;

import com.bank.depositman.model.Transaction;
import com.bank.depositman.utils.dto.ApprovalDTO;
import com.bank.depositman.utils.dto.DepositDTO;
import com.bank.depositman.utils.dto.WithdrawDTO;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction deposit (DepositDTO req);
    Transaction withdraw (WithdrawDTO req);
    Transaction approval (ApprovalDTO req);
    Optional<Transaction> getTransWait ();
    List<Transaction> getAll();
    Transaction getById(Integer id);

}
