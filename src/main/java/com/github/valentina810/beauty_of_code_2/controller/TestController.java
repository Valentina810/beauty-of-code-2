package com.github.valentina810.beauty_of_code_2.controller;

import com.github.valentina810.beauty_of_code_2.model.StatusTransaction;
import com.github.valentina810.beauty_of_code_2.service.TestTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@Tag(name = "TestController | Методы для тестирования обработки транзакций")
public class TestController {

    private final TestTransactionService testTransactionService;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Сгенерировать транзакции")
    public List<UUID> generateTransactions(@RequestParam @Parameter(description = "Статус транзакций") StatusTransaction status,
                                           @RequestParam @Parameter(description = "Количество транзакций") Integer count) {
        return testTransactionService.createTransactions(status,count);
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Удалить транзакции")
    public void deleteTransactions(@RequestBody List<UUID> transactionsId) {
        testTransactionService.deleteTransactions(transactionsId);
    }
}
