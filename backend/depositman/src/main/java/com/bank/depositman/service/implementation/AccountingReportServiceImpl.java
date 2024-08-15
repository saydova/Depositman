package com.bank.depositman.service.implementation;

import com.bank.depositman.model.AccountingReport;
import com.bank.depositman.repository.AccountingReportRepository;
import com.bank.depositman.service.AccountingReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountingReportServiceImpl implements AccountingReportService {
    private final AccountingReportRepository accountingReportRepository;

    //GET_ALL
    @Override
    public List<AccountingReport> getAll() {
        return accountingReportRepository.findAll();
    }
}
