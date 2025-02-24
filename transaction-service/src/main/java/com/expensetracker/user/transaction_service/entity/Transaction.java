package com.expensetracker.user.transaction_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private UserDTO payer;  // The user who pays

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserDTO receiver; // The user who receives

    private Integer amount; // Amount to be transferred


}

