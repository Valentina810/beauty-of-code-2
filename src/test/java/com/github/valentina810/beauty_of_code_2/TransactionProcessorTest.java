
package com.github.valentina810.beauty_of_code_2;

import com.github.valentina810.beauty_of_code_2.service.TransactionProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionProcessorTest {

    @Autowired
    private TransactionProcessor processor;

    @Test
    public void testProcessTransactions() {
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(new Transaction("1", 5000, "2023-01-01", "PENDING"));
//        transactions.add(new Transaction("2", 15000, "2023-01-02", "PENDING"));
//        transactions.add(new Transaction("3", 2000, "2023-01-03", "COMPLETED"));
//
//        processor.processTransactions(transactions);
//
//        for (Transaction transaction : transactions) {
//            System.out.println("Transaction ID: " + transaction.getId() + " Status: " + transaction.getStatus());
//        }
    }
}
