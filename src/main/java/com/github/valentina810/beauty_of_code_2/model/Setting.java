package com.github.valentina810.beauty_of_code_2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "settings", schema = "public")
public class Setting {
    @Id
    @Column(name = "setting_name", nullable = false, unique = true)
    private String settingName;
    @Column(name = "setting_value", nullable = false)
    private String settingValue;
}