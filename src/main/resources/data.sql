INSERT INTO transaction_status (status_name)
VALUES ('PENDING'),
       ('COMPLETED'),
       ('PROCESSED')
ON CONFLICT (status_name) DO NOTHING;