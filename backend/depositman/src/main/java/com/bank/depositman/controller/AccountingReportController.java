package com.bank.depositman.controller;

import com.bank.depositman.service.AccountingReportService;
import com.bank.depositman.utils.dto.DepositDTO;
import com.bank.depositman.utils.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accountingreports")
@RequiredArgsConstructor
public class AccountingReportController {
    private final AccountingReportService accountingReportService;

    @GetMapping
    public ResponseEntity<?>getAll(){
        return Response.renderJSON(accountingReportService.getAll());
    }



    }


