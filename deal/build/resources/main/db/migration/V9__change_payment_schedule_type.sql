ALTER TABLE credit
    ALTER COLUMN payment_schedule
        TYPE jsonb
        USING to_jsonb(payment_schedule);