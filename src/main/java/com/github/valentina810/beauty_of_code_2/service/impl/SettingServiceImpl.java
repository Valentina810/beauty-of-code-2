package com.github.valentina810.beauty_of_code_2.service.impl;

import com.github.valentina810.beauty_of_code_2.dto.SettingDto;
import com.github.valentina810.beauty_of_code_2.exception.NotFoundException;
import com.github.valentina810.beauty_of_code_2.mapper.SettingMapper;
import com.github.valentina810.beauty_of_code_2.repository.SettingRepository;
import com.github.valentina810.beauty_of_code_2.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
public class SettingServiceImpl implements SettingService {

    private final SettingRepository settingRepository;
    private final SettingMapper settingMapper;

    @Override
    public SettingDto getSetting(String parameterName) {
        return settingRepository.findById(parameterName)
                .map(settingMapper::toSettingDto)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Параметр с названием %s не найден", parameterName))
                );
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void updateSetting(SettingDto settingDto) {
        getSetting(settingDto.getSettingName());
        settingRepository.save(settingMapper.toSetting(settingDto));
    }
}