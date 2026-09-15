ALTER TABLE client
ALTER COLUMN marital_status DROP NOT NULL;

ALTER TABLE client
ALTER COLUMN dependent_amount DROP NOT NULL;

ALTER TABLE application
ALTER COLUMN applied_offer DROP NOT NULL;

ALTER TABLE application
ALTER COLUMN sign_date DROP NOT NULL;

ALTER TABLE application
ALTER COLUMN ses_code DROP NOT NULL;

