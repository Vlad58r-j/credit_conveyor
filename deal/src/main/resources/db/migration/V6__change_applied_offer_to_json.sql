ALTER TABLE application
ALTER COLUMN applied_offer
TYPE jsonb
USING to_jsonb(applied_offer);