CREATE OR REPLACE FUNCTION generate_transactions(n int, statusName varchar)
    RETURNS UUID[]
    LANGUAGE plpgsql AS $$
DECLARE
    status_id1 bigint;
    i          int := 1;
    transaction_ids UUID[] := '{}';
    generated_id UUID;
BEGIN
    SELECT ts.status_id
    INTO status_id1
    FROM public.transaction_status ts
    WHERE ts.status_name = statusName;

    IF status_id1 IS NULL THEN
        RAISE EXCEPTION 'Status name not found!';
    END IF;

    WHILE i <= n LOOP
            INSERT INTO transaction (id, amount, date, id_status)
            VALUES (gen_random_uuid(), (random() * 1000)::numeric, CURRENT_DATE, status_id1)
            RETURNING id INTO generated_id;

            transaction_ids := array_append(transaction_ids, generated_id);
            i := i + 1;
        END LOOP;

    RETURN transaction_ids;
END;
$$;
