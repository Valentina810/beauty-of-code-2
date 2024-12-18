package com.github.valentina810.beauty_of_code_2.controller;

import com.github.valentina810.beauty_of_code_2.dto.TransactionsResultDto;
import com.github.valentina810.beauty_of_code_2.model.StatusTransaction;
import com.github.valentina810.beauty_of_code_2.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.github.valentina810.beauty_of_code_2.model.StatusTransaction.PROCESSED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@Tag(name = "TransactionController | Работа с транзакциями")
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PatchMapping
    @Operation(summary = "Обновление статусов переданного списка транзакций")
    @ResponseStatus(OK)
    public TransactionsResultDto updateStatusTransaction(@RequestParam @Parameter(description = "Статус, в который необходимо перевести транзакции") StatusTransaction status,
                                                         @RequestBody List<UUID> transactionsId) {
        if (PROCESSED.equals(status)) {
            return transactionService.updateTransactionStatuses(transactionsId, status);
        } else
            throw new UnsupportedOperationException("Перевод транзакций в статус " + status + " не поддерживается");
    }

}
