CREATE TABLE IF NOT EXISTS settings
(
    setting_name VARCHAR(255) PRIMARY KEY NOT NULL,
    setting_value VARCHAR(255) NOT NULL,
    CONSTRAINT uq_setting_name UNIQUE (setting_name)
);

INSERT INTO settings (setting_name, setting_value)
VALUES ('batchSize','1000')
    ON CONFLICT (setting_name) DO NOTHING;