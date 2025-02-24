package com.expensetracker.user.transaction_service.controller;

import com.expensetracker.user.transaction_service.entity.TransactionDTO;
import com.expensetracker.user.transaction_service.entity.UserDTO;
import com.expensetracker.user.transaction_service.service.TransactionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService
            transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Retrieve a list of users with pending transactions.
    @GetMapping("/users")
    public String showUsers(Model model) {
        List<UserDTO> users = transactionService.getAllUsers();
        model.addAttribute("users", users);
        return "showUsers";
    }

    /**
     * Retrieve a list of users who should receive money.
     */
    @GetMapping("/owed")
    public String showTransactionsOwed(Model model) {
        List<TransactionDTO> owed = transactionService.fetchTransactionsOwed();
        model.addAttribute("owed", owed);
        return "transactionsOwed";
    }

    @GetMapping("/receivable")
    public String showTransactionsReceivable(Model model) {
        List<TransactionDTO> receivable = transactionService.fetchTransactionsToBeReceived();
        model.addAttribute("receivable", receivable);
        return "transactionsReceivable";
    }

    @GetMapping("/settlement")
    public String showSettlement(Model model) {
        List<String> settlementList = transactionService.calculateSettlement();
        model.addAttribute("settlements", settlementList);
        return "settlement"; // Thymeleaf template for settlement details
    }

    @PostMapping("/finalize")
    public String finalizeSettlement() {
        transactionService.updateSettlementExpenses();
        return "settlementSuccess"; // Redirect to settlement page or any appropriate view
    }
}
