
package com.github.valentina810.beauty_of_code_2.repository;

import com.github.valentina810.beauty_of_code_2.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

//    private final List<Transaction> transactions = new ArrayList<>();
//
//    public void updateTransaction(Transaction transaction) {
//        transactions.add(transaction);
//    }
//
//    public List<Transaction> getTransactions() {
//        return transactions;
//    }
}
