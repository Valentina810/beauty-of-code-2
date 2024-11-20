package com.github.valentina810.beauty_of_code_2.controller;

import com.github.valentina810.beauty_of_code_2.model.StatusTransaction;
import com.github.valentina810.beauty_of_code_2.service.TestTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Методы для тестирования обработки транзакций")
public class TestController {

    private final TestTransactionService testTransactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Сгенерировать транзакции")
    public List<UUID> generateTransactions(@RequestParam @Parameter(description = "Статус транакций") StatusTransaction status,
                                           @RequestParam @Parameter(description = "Количество транзакций") Integer count) {
        return testTransactionService.createTransactions(status,count);
    }
}
