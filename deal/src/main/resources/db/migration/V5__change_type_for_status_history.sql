ALTER TABLE application
ALTER COLUMN status_history
TYPE VARCHAR(32)[]
USING Array[status_history];

ALTER TABLE application
ALTER COLUMN applied_offer
TYPE BOOLEAN
USING applied_offer::boolean;