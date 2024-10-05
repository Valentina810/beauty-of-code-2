
package com.bank.transactions;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    public void updateTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
