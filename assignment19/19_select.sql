-- 19_select.sql
-- Assignment 19: SELECT queries for Student and Product tables

-- Queries for assignment19 database
USE assignment19;

-- Retrieve all student records
SELECT * FROM Student;

-- Retrieve specific student fields
SELECT id, name, department, marks FROM Student;

-- Retrieve all product records
SELECT * FROM Product;

-- Retrieve specific product fields
SELECT id, name, price, quantity FROM Product;
