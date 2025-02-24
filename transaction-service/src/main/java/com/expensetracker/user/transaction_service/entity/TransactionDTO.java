package com.expensetracker.user.transaction_service.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDTO {

    private String userName;
    private int amount;
    private String transactionType; // "owe" or "receive"
}

