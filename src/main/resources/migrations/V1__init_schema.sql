-- 1. Users Table (ගෙදර අයගේ විස්තර)
CREATE TABLE users
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(100)        NOT NULL,
    email         VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255)        NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Categories Table (වියදම් සහ ආදායම් වර්ග - උදා: කෑම, බිල්පත්, වැටුප)
CREATE TABLE categories
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    type       ENUM('INCOME', 'EXPENSE') NOT NULL,
    icon       VARCHAR(50), -- Flutter/Angular UI එකේ පෙන්නන්න icon name එක
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Transactions Table (සියලුම ආදායම් සහ වියදම් සටහන්)
CREATE TABLE transactions
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id          BIGINT         NOT NULL,
    category_id      BIGINT         NOT NULL,
    amount           DECIMAL(10, 2) NOT NULL,
    transaction_date DATE           NOT NULL,
    note             VARCHAR(255), -- AI එකෙන් process කරන voice/text note එක save කරන්න
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

-- 4. Budgets Table (මාසික වියදම් සීමාවන්)
CREATE TABLE budgets
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id       BIGINT         NOT NULL,
    category_id   BIGINT         NOT NULL,
    monthly_limit DECIMAL(10, 2) NOT NULL,
    budget_month  VARCHAR(7)     NOT NULL, -- Format: 'YYYY-MM' (උදා: '2026-05')
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);