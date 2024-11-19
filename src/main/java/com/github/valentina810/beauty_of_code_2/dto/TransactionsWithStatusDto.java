package com.github.valentina810.beauty_of_code_2.dto;

import com.github.valentina810.beauty_of_code_2.model.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class TransactionsWithStatusDto {
    private UUID id;
    private TransactionStatus status;
}
