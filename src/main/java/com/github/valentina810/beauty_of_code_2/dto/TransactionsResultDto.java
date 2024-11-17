package com.github.valentina810.beauty_of_code_2.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class TransactionsResultDto {
    private Long countSuccessfullyProcessedTransactions;
    private List<UUID> successfullyProcessedTransactions;
    private Long countErroneousTransactions;
    private List<ErrorTransactionDto> erroneousTransactions;
}
