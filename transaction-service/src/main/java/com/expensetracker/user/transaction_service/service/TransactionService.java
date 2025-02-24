package com.expensetracker.user.transaction_service.service;

import com.expensetracker.user.transaction_service.entity.TransactionDTO;
import com.expensetracker.user.transaction_service.entity.UserDTO;
import com.expensetracker.user.transaction_service.feign.TransactionServiceProxy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Transactional
@Service
public class TransactionService {


    @Autowired
    private TransactionServiceProxy userFeignClient;


    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userFeignClient.showUsers();
        return users;
    }

    public List<TransactionDTO> fetchTransactionsOwed() {
        List<UserDTO> users = userFeignClient.showUsers();
        List<TransactionDTO> transactions = new ArrayList<>();

        int totalExpense = users.stream().mapToInt(UserDTO::getExpense).sum();
        int equalShare = totalExpense / users.size();

        for (UserDTO user : users) {
            int balance = user.getExpense() - equalShare;
            if (balance < 0) {
                transactions.add(new TransactionDTO(user.getName(), Math.abs(balance), "owe"));
            }
        }
        return transactions;
    }


    public List<TransactionDTO> fetchTransactionsToBeReceived() {
        List<UserDTO> users = userFeignClient.showUsers();
        List<TransactionDTO> transactions = new ArrayList<>();

        int totalExpense = users.stream().mapToInt(UserDTO::getExpense).sum();
        int equalShare = totalExpense / users.size();

        for (UserDTO user : users) {
            int balance = user.getExpense() - equalShare;
            if (balance > 0) {
                transactions.add(new TransactionDTO(user.getName(), balance, "receive"));
            }
        }
        return transactions;
    }

    public List<String> calculateSettlement() {
        List<String> settlements = new ArrayList<>();

        // Fetch transactions
        List<TransactionDTO> oweList = fetchTransactionsOwed();
        List<TransactionDTO> receiveList = fetchTransactionsToBeReceived();

        // Build maps for debtors (owesMap) and creditors (receivesMap)
        Map<String, Integer> owesMap = new HashMap<>();
        Map<String, Integer> receivesMap = new HashMap<>();

        for (TransactionDTO owe : oweList) {
            String debtor = owe.getUserName();
            int amount = owe.getAmount();
            owesMap.put(debtor, owesMap.getOrDefault(debtor, 0) + amount);
        }

        for (TransactionDTO receive : receiveList) {
            String creditor = receive.getUserName();
            int amount = receive.getAmount();
            receivesMap.put(creditor, receivesMap.getOrDefault(creditor, 0) + amount);
        }

        // Process settlements using iterators to update creditor balances on the fly
        for (Map.Entry<String, Integer> debtorEntry : owesMap.entrySet()) {
            String debtor = debtorEntry.getKey();
            int amountOwed = debtorEntry.getValue();

            Iterator<Map.Entry<String, Integer>> creditorIterator = receivesMap.entrySet().iterator();
            while (creditorIterator.hasNext() && amountOwed > 0) {
                Map.Entry<String, Integer> creditorEntry = creditorIterator.next();
                String creditor = creditorEntry.getKey();
                int amountToReceive = creditorEntry.getValue();

                int settledAmount = Math.min(amountOwed, amountToReceive);
                settlements.add(debtor + " pays Rs. " + settledAmount + " to " + creditor);

                // Update debtor's and creditor's balances
                amountOwed -= settledAmount;
                int remainingCredit = amountToReceive - settledAmount;
                if (remainingCredit == 0) {
                    creditorIterator.remove();
                } else {
                    creditorEntry.setValue(remainingCredit);
                }
            }
        }
        return settlements;
    }

    public void updateSettlementExpenses() {
        List<UserDTO> allUsers = userFeignClient.showUsers();
        for (UserDTO user : allUsers) {
            userFeignClient.updateUserExpense(user.getId(), 0);
        }
    }
}
