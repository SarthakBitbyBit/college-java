-- 23_resultset.sql
-- Assignment 23: Employee records for ResultSet cursor navigation

USE jdbc_lab;

-- Fetch all employees ordered by employee_id for navigation testing
SELECT employee_id, name, department, salary
FROM Employee
ORDER BY employee_id ASC;
