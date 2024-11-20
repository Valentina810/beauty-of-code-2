package com.github.valentina810.beauty_of_code_2.service;

import com.github.valentina810.beauty_of_code_2.model.StatusTransaction;

import java.util.List;
import java.util.UUID;

/**
 * Методы для тестового контроллера, только для тестирования!
 */
public interface TestTransactionService {
    List<UUID> createTransactions(StatusTransaction status, Integer countTransactions);
    void deleteTransactions(List<UUID> transactionIds);
    void deleteAllTransactions();
}