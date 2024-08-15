package com.bank.depositman.repository;

import com.bank.depositman.model.AccountingReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingReportRepository extends JpaRepository<AccountingReport, Integer> {
}
