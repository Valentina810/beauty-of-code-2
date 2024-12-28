package com.github.valentina810.beauty_of_code_2.service.impl;

import com.github.valentina810.beauty_of_code_2.model.StatusTransaction;
import com.github.valentina810.beauty_of_code_2.repository.TransactionRepository;
import com.github.valentina810.beauty_of_code_2.service.TestTransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TestTransactionServiceImpl implements TestTransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public List<UUID> createTransactions(StatusTransaction status, Integer countTransactions) {
        return transactionRepository.generateTransactions(countTransactions, status.name());
    }

    @Override
    @Transactional
    public void deleteTransactions(List<UUID> transactionIds) {
        transactionRepository.deleteAllByIds(transactionIds);
    }

    @Override
    @Transactional
    public void deleteAllTransactions() {
        transactionRepository.deleteAllTransactions();
    }
}
