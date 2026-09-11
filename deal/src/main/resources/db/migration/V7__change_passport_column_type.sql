ALTER TABLE passport
ALTER COLUMN issue_branch
TYPE VARCHAR(64)
USING issue_branch::VARCHAR;

ALTER TABLE credit
ALTER COLUMN payment_schedule
TYPE jsonb[]
USING ARRAY[payment_schedule::jsonb];
