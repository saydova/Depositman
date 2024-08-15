package com.bank.depositman.service.implementation;
import com.bank.depositman.model.User;
import com.bank.depositman.model.AccountingReport;
import com.bank.depositman.model.Transaction;
import com.bank.depositman.repository.AccountingReportRepository;
import com.bank.depositman.repository.TransactionRepository;
import com.bank.depositman.repository.UserRepository;
import com.bank.depositman.security.JwtService;
import com.bank.depositman.service.TransactionService;
import com.bank.depositman.utils.dto.ApprovalDTO;
import com.bank.depositman.utils.dto.DepositDTO;
import com.bank.depositman.utils.dto.WithdrawDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountingReportRepository accountingReportRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    //DEPOSIT
    @Override
    public Transaction deposit(DepositDTO req) {
        Transaction transaction= Transaction.builder()
                .userId(jwtService.getAuthenticated())
                .deposit(req.getDeposit())
                .approvalStatus("waiting")
                .createdAt(LocalDate.now())
                .build();

        AccountingReport accountingReport= AccountingReport.builder()
                .deposit(req.getDeposit())
                .approvalStatus("waiting")
                .trasactionDate(LocalDate.now())
                .build();

        transactionRepository.save(transaction);
        accountingReportRepository.save(accountingReport);
        return transaction;
    }

    //WITHDRAW
    @Override
    public Transaction withdraw(WithdrawDTO req) {
        Transaction transaction= Transaction.builder()
                .userId(jwtService.getAuthenticated())
                .deposit(req.getWithdraw())
                .approvalStatus("waiting")
                .createdAt(LocalDate.now())
                .build();

        AccountingReport accountingReport= AccountingReport.builder()
                .deposit(req.getWithdraw())
                .approvalStatus("waiting")
                .trasactionDate(LocalDate.now())
                .build();

        transactionRepository.save(transaction);
        accountingReportRepository.save(accountingReport);
        return transaction;
    }

    //APPROVAL
    //waiting - approved - reject
    @Override
    public Transaction approval(ApprovalDTO req) {
        Transaction transaction= getById(req.getIdTrans());
        User user = userRepository.findById(transaction.getUserId());
        if (req.getApprovalStatus() != null) {
            transaction.setApprovalStatus(req.getApprovalStatus());
            if (req.getApprovalStatus() == "approved"){
                if (transaction.getDeposit() != null){
                    user.setAccountBalanced(user.getAccountBalanced()+transaction.getDeposit());
                }
                if (transaction.getWithdraw() != null){
                    user.setAccountBalanced(user.getAccountBalanced()-transaction.getWithdraw());
                }
            }
        }

        transactionRepository.save(transaction);
        return  transaction;
    }


    //GET_TRANSACTION_WAIT
    @Override
    public Optional<Transaction> getTransWait() {
        return transactionRepository.findByApprovalStatus("waiting");
    }

    //GET_ALL
    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    //GETBY_ID
    @Override
    public Transaction getById(Integer id) {
        return transactionRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException()
        );
    }



}
