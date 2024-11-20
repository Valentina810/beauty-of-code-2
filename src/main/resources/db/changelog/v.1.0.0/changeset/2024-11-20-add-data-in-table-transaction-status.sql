INSERT INTO transaction_status (status_name)
VALUES ('PENDING'),
       ('PROCESSED'),
       ('COMPLETED')
    ON CONFLICT (status_name) DO NOTHING;