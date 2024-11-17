package com.github.valentina810.beauty_of_code_2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class ErrorTransactionDto {
    private UUID transactionId;
    private String errorMessage;
}
