package com.bank.depositman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer deposit;
    private Integer withdraw;
    private String approvalStatus;
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User userId;

}
