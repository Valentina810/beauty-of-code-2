package com.github.valentina810.beauty_of_code_2.service;

import com.github.valentina810.beauty_of_code_2.dto.TransactionsResultDto;
import com.github.valentina810.beauty_of_code_2.model.StatusTransaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    TransactionsResultDto updateTransactionStatuses(List<UUID> transactionIds, StatusTransaction status);
}
