CREATE TABLE passport
(
    id           BIGSERIAL PRIMARY KEY,
    series       VARCHAR(4)  NOT NULL,
    number       VARCHAR(6)  NOT NULL,
    issue_date   DATE        NOT NULL,
    issue_branch VARCHAR(64) NOT NULL,
    UNIQUE (series, number)
);

CREATE TABLE employment
(
    id                      BIGSERIAL PRIMARY KEY,
    employment_status       VARCHAR(32) NOT NULL,
    employer_inn            VARCHAR(12) NOT NULL UNIQUE,
    salary                  DECIMAL     NOT NULL,
    position                VARCHAR(32) NOT NULL,
    work_experience_total   INT         NOT NULL,
    work_experience_current INT         NOT NULL
);

CREATE TABLE client
(
    id               BIGSERIAL primary key,
    last_name        VARCHAR(32) NOT NULL,
    first_name       VARCHAR(32) NOT NULL,
    middle_name      VARCHAR(32),
    birth_date       DATE,
    email            VARCHAR(128),
    gender           VARCHAR(32) NOT NULL,
    marital_status   VARCHAR(32) NOT NULL,
    dependent_amount INT         NOT NULL,
    passport_id      INT references passport (id) on DELETE CASCADE,
    employment_id    INT references employment (id) on DELETE CASCADE,
    UNIQUE (email, passport_id, employment_id)
);

CREATE TABLE credit
(
    id                   BIGSERIAL PRIMARY KEY,
    amount               DECIMAL     NOT NULL,
    term                 INT         NOT NULL,
    monthly_payment      DECIMAL     NOT NULL,
    rate                 DECIMAL     NOT NULL,
    psk                  DECIMAL     NOT NULL,
    payment_schedule     TEXT        NOT NULL,
    is_insurance_enabled BOOLEAN     NOT NULL,
    is_salary_client     BOOLEAN     NOT NULL,
    credit_status        VARCHAR(32) NOT NULL
);

CREATE TABLE application
(
    client_id      BIGINT references client (id) on DELETE CASCADE,
    credit_id      BIGINT references credit (id) on DELETE CASCADE,
    status         VARCHAR(64) NOT NULL,
    creation_date  DATE        NOT NULL,
    applied_offer  VARCHAR(32) NOT NULL,
    sign_date      DATE        NOT NULL,
    ses_code       VARCHAR(6)  NOT NULL,
    status_history VARCHAR(32) NOT NULL
);