package com.github.valentina810.beauty_of_code_2.service;

import com.github.valentina810.beauty_of_code_2.dto.SettingDto;

public interface SettingService {
    SettingDto getSetting(String parameterName);

    void updateSetting(SettingDto setting);
}
