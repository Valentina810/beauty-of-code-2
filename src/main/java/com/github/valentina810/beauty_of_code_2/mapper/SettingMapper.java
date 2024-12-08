package com.github.valentina810.beauty_of_code_2.mapper;

import com.github.valentina810.beauty_of_code_2.dto.SettingDto;
import com.github.valentina810.beauty_of_code_2.model.Setting;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SettingMapper {
    Setting toSetting(SettingDto settingDto);
    SettingDto toSettingDto(Setting setting);
}