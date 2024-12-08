package com.github.valentina810.beauty_of_code_2.controller;

import com.github.valentina810.beauty_of_code_2.dto.SettingDto;
import com.github.valentina810.beauty_of_code_2.service.SettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@Tag(name = "SettingController | Методы для работы с настройками для обработки транзакций")
@RequestMapping("/setting")
public class SettingController {

    private final SettingService settingService;

    @GetMapping
    @ResponseStatus(OK)
    @Operation(summary = "Получить текущее значение параметра")
    public SettingDto getSetting(@RequestParam @Parameter(description = "Название параметра") String parameterName) {
        return settingService.getSetting(parameterName);
    }

    @PatchMapping
    @ResponseStatus(OK)
    @Operation(summary = "Изменить значение параметра")
    public void updateSetting(@RequestBody SettingDto setting) {
        settingService.updateSetting(setting);
    }
}