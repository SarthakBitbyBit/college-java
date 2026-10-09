-- 20_crud.sql
-- Assignment 20: CRUD operations for Student and Employee tables

USE jdbc_lab;

-- ===================================================
-- STUDENT CRUD EXAMPLES
-- ===================================================

-- Create (Insert new student)
INSERT INTO Student (roll_no, name, course, marks)
VALUES (5, 'Karan', 'Mechanical', 76.50);

-- Read (Select all or by roll_no)
SELECT roll_no, name, course, marks FROM Student;
SELECT roll_no, name, course, marks FROM Student WHERE roll_no = 5;

-- Update (Modify course and marks)
UPDATE Student
SET name = 'Karan Sharma', course = 'Civil', marks = 82.00
WHERE roll_no = 5;

-- Delete (Remove student record)
DELETE FROM Student WHERE roll_no = 5;


-- ===================================================
-- EMPLOYEE CRUD EXAMPLES
-- ===================================================

-- Create (Insert new employee)
INSERT INTO Employee (employee_id, name, department, salary)
VALUES (5, 'Vikas Patel', 'Marketing', 48000.00);

-- Read (Select all or by employee_id)
SELECT employee_id, name, department, salary FROM Employee;
SELECT employee_id, name, department, salary FROM Employee WHERE employee_id = 5;

-- Update (Modify department and salary)
UPDATE Employee
SET name = 'Vikas K. Patel', department = 'Sales', salary = 52000.00
WHERE employee_id = 5;

-- Delete (Remove employee record)
DELETE FROM Employee WHERE employee_id = 5;
