CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS transaction_status
(
    status_id   bigint   PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY,
    status_name VARCHAR(255) NOT NULL,
    CONSTRAINT uq_status_name UNIQUE (status_name)
);

CREATE TABLE IF NOT EXISTS transaction
(
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    amount    NUMERIC      NOT NULL,
    date      VARCHAR(255) NOT NULL,
    id_status bigint       NOT NULL,
    CONSTRAINT status_id_id_status FOREIGN KEY (id_status)
        REFERENCES transaction_status (status_id)
);
