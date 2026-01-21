-- ============================================
-- SQL Queries to Fix Authorities/Roles
-- ============================================

-- 1. CHECK CURRENT AUTHORITIES FOR CUSTOMER_ID 1
-- This shows what authorities currently exist for customer_id 1
SELECT * FROM `authorities` WHERE `customer_id` = 1;

-- 2. CHECK IF OLD "admin" AUTHORITY EXISTS
-- This checks if there's an old "admin" authority (without ROLE_ prefix)
SELECT * FROM `authorities` WHERE `customer_id` = 1 AND `name` = 'admin';

-- 3. DELETE OLD "admin" AUTHORITY IF IT EXISTS
-- Remove any old "admin" authority that doesn't have ROLE_ prefix
DELETE FROM `authorities` WHERE `customer_id` = 1 AND `name` = 'admin';

-- 4. DELETE ALL AUTHORITIES FOR CUSTOMER_ID 1 (CLEAN SLATE)
-- Use this if you want to start fresh and remove all existing authorities
-- DELETE FROM `authorities` WHERE `customer_id` = 1;

-- 5. INSERT ROLE_USER (if not already exists)
-- This adds ROLE_USER authority for customer_id 1
-- Note: This will fail if ROLE_USER already exists (due to potential unique constraint or duplicate)
INSERT INTO `authorities` (`customer_id`, `name`)
SELECT 1, 'ROLE_USER'
WHERE NOT EXISTS (
    SELECT 1 FROM `authorities` 
    WHERE `customer_id` = 1 AND `name` = 'ROLE_USER'
);

-- 6. INSERT ROLE_ADMIN (if not already exists)
-- This adds ROLE_ADMIN authority for customer_id 1
INSERT INTO `authorities` (`customer_id`, `name`)
SELECT 1, 'ROLE_ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM `authorities` 
    WHERE `customer_id` = 1 AND `name` = 'ROLE_ADMIN'
);

-- ============================================
-- ALTERNATIVE: SIMPLE INSERT (if you know they don't exist)
-- ============================================
-- If you're sure ROLE_USER and ROLE_ADMIN don't exist, you can use:
-- INSERT INTO `authorities` (`customer_id`, `name`) VALUES (1, 'ROLE_USER');
-- INSERT INTO `authorities` (`customer_id`, `name`) VALUES (1, 'ROLE_ADMIN');

-- ============================================
-- FOR OTHER CUSTOMERS (e.g., customer_id 2)
-- ============================================
-- If you want to add roles for customer_id 2 (Abebe kebede):
-- INSERT INTO `authorities` (`customer_id`, `name`) VALUES (2, 'ROLE_USER');

-- ============================================
-- VERIFY THE RESULT
-- ============================================
-- After running the queries, verify with:
SELECT c.customer_id, c.name, c.email, a.name as authority
FROM `customer` c
LEFT JOIN `authorities` a ON c.customer_id = a.customer_id
WHERE c.customer_id = 1;

-- ============================================
-- IMPORTANT NOTES:
-- ============================================
-- 1. The foreign key relationship is CORRECT:
--    - One customer (customer_id) can have MANY authorities
--    - This is a one-to-many relationship (1:N)
--    - Multiple rows with the same customer_id is EXPECTED and CORRECT
--
-- 2. After updating the database:
--    - The user needs to LOG OUT and LOG BACK IN
--    - OR restart the application
--    - This is because Spring Security caches the authentication in the session
--
-- 3. The `customer.role` column is NOT used by Spring Security
--    - Spring Security uses the `authorities` table
--    - The `customer.role` column is legacy/not used in your current setup

