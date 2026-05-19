-- 1. Users Table (ගෙදර අයගේ විස්තර)
INSERT INTO users (name, email, password_hash)
VALUES
    ('Kasun Perera', 'kasun@example.lk', '$2a$12$eImiTXuWVxfM37uY4JANj...'),
    ('Nimali Silva', 'nimali@example.lk', '$2a$12$K.z890F.Jz3XhT7V1p2qO...');

-- 2. Categories Table (වියදම් සහ ආදායම් වර්ග)
-- INCOME types
INSERT INTO categories (name, type, icon)
VALUES
    ('Salary', 'INCOME', 'work'),           -- 1
    ('Freelance', 'INCOME', 'laptop_mac');  -- 2

-- EXPENSE types
INSERT INTO categories (name, type, icon)
VALUES
    ('Groceries', 'EXPENSE', 'shopping_cart'), -- 3
    ('Utility Bills', 'EXPENSE', 'receipt'),   -- 4
    ('Transport', 'EXPENSE', 'directions_bus'),-- 5
    ('Medical', 'EXPENSE', 'local_hospital'),  -- 6
    ('Education', 'EXPENSE', 'school');        -- 7

-- 3. Transactions Table (සියලුම ආදායම් සහ වියදම් සටහන්)
-- Assuming May 2026 data
INSERT INTO transactions (user_id, category_id, amount, transaction_date, note)
VALUES
    -- Kasun's Income
    (1, 1, 150000.00, '2026-05-01', 'May Office Salary'),

    -- Kasun's Expenses
    (1, 3, 22500.00, '2026-05-03', 'Keells Super - Monthly groceries (සති අන්තයේ බඩු)'),
    (1, 4, 4500.00, '2026-05-05', 'CEB Electricity Bill'),
    (1, 4, 1200.00, '2026-05-05', 'Water Board Bill (ජල බිල්පත)'),
    (1, 5, 8000.00, '2026-05-10', 'Ceypetco Petrol for Bike'),
    (1, 4, 2500.00, '2026-05-12', 'Dialog Wi-Fi Reload'),
    (1, 3, 4500.00, '2026-05-15', 'Cargills Food City - Vegetables & Fruits'),

    -- Nimali's Income
    (2, 2, 45000.00, '2026-05-08', 'Fiverr graphic design project'),

    -- Nimali's Expenses
    (2, 5, 1500.00, '2026-05-09', 'Bus fare to Colombo'),
    (2, 6, 3500.00, '2026-05-14', 'Channeling Dr. at Nawaloka Hospital'),
    (2, 7, 15000.00, '2026-05-18', 'BIT Semester Course Fee');

-- 4. Budgets Table (මාසික වියදම් සීමාවන්)
-- Setting up budgets for Kasun for May 2026
INSERT INTO budgets (user_id, category_id, monthly_limit, budget_month)
VALUES
    (1, 3, 35000.00, '2026-05'), -- Groceries limit: 35,000 LKR
    (1, 4, 10000.00, '2026-05'), -- Utility Bills limit: 10,000 LKR
    (1, 5, 12000.00, '2026-05'); -- Transport limit: 12,000 LKR