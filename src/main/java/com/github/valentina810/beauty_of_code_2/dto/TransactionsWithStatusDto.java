package com.github.valentina810.beauty_of_code_2.dto;

import com.github.valentina810.beauty_of_code_2.model.TransactionStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class TransactionsWithStatusDto {
    private UUID id;
    private TransactionStatus status;
}
