CREATE TABLE family_groups
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    family_id     BIGINT              NOT NULL,
    username      VARCHAR(100)        NOT NULL,
    full_name     VARCHAR(100)        NOT NULL,
    email         VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255)        NOT NULL,
    role          VARCHAR(50)         NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (family_id) REFERENCES family_groups (id) ON DELETE CASCADE
);

CREATE TABLE categories
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(50)                NOT NULL,
    type       ENUM ('INCOME', 'EXPENSE') NOT NULL,
    icon       VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE transactions
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id          BIGINT         NOT NULL,
    category_id      BIGINT         NOT NULL,
    amount           DECIMAL(10, 2) NOT NULL,
    transaction_date DATE           NOT NULL,
    transaction_time TIME           NOT NULL,
    note             VARCHAR(255),
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE budgets
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id       BIGINT         NOT NULL,
    category_id   BIGINT         NOT NULL,
    monthly_limit DECIMAL(10, 2) NOT NULL,
    budget_month  VARCHAR(7)     NOT NULL, -- Format: 'YYYY-MM'
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);