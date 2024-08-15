package com.bank.depositman.controller;


import com.bank.depositman.service.TransactionService;
import com.bank.depositman.utils.dto.ApprovalDTO;
import com.bank.depositman.utils.dto.DepositDTO;
import com.bank.depositman.utils.dto.WithdrawDTO;
import com.bank.depositman.utils.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<?>deposit(@RequestBody DepositDTO req){
        return Response.renderJSON(
          transactionService.deposit(req),"deposit waiting list",
                HttpStatus.CREATED
        );
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?>withdraw(@RequestBody WithdrawDTO req){
        return Response.renderJSON(
                transactionService.withdraw(req),"withdraw waiting list",
                HttpStatus.CREATED
        );
    }

    @PutMapping("/approval")
    public ResponseEntity<?>approval(@RequestBody ApprovalDTO req){
        return Response.renderJSON(
                transactionService.approval(req),
                "approval status was updated"
        );
    }

    @GetMapping("/waiting")
    public ResponseEntity<?>getById(){
        return Response.renderJSON(transactionService.getTransWait());
    }

    @GetMapping
    public ResponseEntity<?>getAll(){
        return Response.renderJSON(transactionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Integer id){
        return Response.renderJSON(transactionService.getById(id));
    }


}
