package com.github.valentina810.beauty_of_code_2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SettingDto {
    private String settingName;
    private String settingValue;
}