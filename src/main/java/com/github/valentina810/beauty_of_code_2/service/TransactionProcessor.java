
package com.github.valentina810.beauty_of_code_2.service;

import com.github.valentina810.beauty_of_code_2.Logger;
import com.github.valentina810.beauty_of_code_2.model.Transaction;
import com.github.valentina810.beauty_of_code_2.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TransactionProcessor {

    private final TransactionRepository repository;
    private final Logger logger;

    @Autowired
    public TransactionProcessor(TransactionRepository repository, Logger logger) {
        this.repository = repository;
        this.logger = logger;
    }

    public void processTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount().compareTo(new BigDecimal(10000))>0) {
                logger.log("Processing large transaction: " + transaction.getId());
            }
            processTransaction(transaction);
        }
    }

    private void processTransaction(Transaction transaction) {
        try {
            if (transaction.getStatus().getStatusName().equals("PENDING")) {
//                transaction.setStatus("PROCESSED");
                repository.save(transaction);
            }
        } catch (Exception e) {
            logger.log("Error processing transaction: " + e.getMessage());
        }
    }
}
