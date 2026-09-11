INSERT INTO deal.employment(id, employment_status, employer_inn, salary, position,
                            work_experience_total, work_experience_current)
VALUES (1, 'EMPLOYED', '123456789', 3489, 'WORKER',
        15,10);

INSERT INTO deal.passport (id, series, number, issue_date, issue_branch)
VALUES (1, '12', '1456', DATE '2025-01-24', DATE '2025-01-21');

INSERT INTO deal.client (id, last_name, first_name, middle_name, birth_date, email, gender, marital_status,
                         dependent_amount, passport_id, employment_id)
VALUES (1, 'Vlad', 'Krivonos', 'Igorevich', DATE '2004-12-22',
        'vlad58r@gmail.com', 'MALE', 'SINGLE',
        30000, 1,1);

INSERT INTO deal.credit (id, amount, term, monthly_payment, rate, psk, payment_schedule, is_insurance_enabled,
                         is_salary_client, credit_status)
VALUES (1, 25544,12,2294, 15, 54, 'Text',
        true, false, 'CALCULATED');

INSERT INTO deal.application (client_id, credit_id, status, creation_date, applied_offer, sign_date,
                              ses_code, status_history, id)
VALUES (1, 1, 'PREAPPROVAL',DATE '2025-02-25',
        null,
        DATE '2026-09-01', '123456', ARRAY['PREAPPROVAL', 'APPROVED'], 1);
