package com.github.valentina810.beauty_of_code_2.repository;

import com.github.valentina810.beauty_of_code_2.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, String> {}
