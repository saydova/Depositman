package com.bank.depositman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "accounting_reports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountingReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer deposit;
    private Integer withdraw;
    private String approvalStatus;
    private LocalDate trasactionDate;

    @ManyToOne
    @JoinColumn(name = "transactionId")
    @JsonIgnore
    private Transaction transactionId;


}
