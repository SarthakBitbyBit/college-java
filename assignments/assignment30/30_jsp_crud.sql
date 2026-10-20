-- Assignment 30: JSP CRUD Operations Database Setup
-- Run as root: mysql -u root -p < 30_jsp_crud.sql

CREATE DATABASE IF NOT EXISTS jsp_lab;

GRANT ALL PRIVILEGES ON jsp_lab.* TO 'javauser'@'localhost';
FLUSH PRIVILEGES;

USE jsp_lab;

CREATE TABLE IF NOT EXISTS Employee (
    employee_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Patient (
    patient_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    disease VARCHAR(100) NOT NULL,
    treatment_status VARCHAR(50) NOT NULL
);

-- Seed initial test records safely
INSERT IGNORE INTO Employee (employee_id, name, department, salary) VALUES
(101, 'Rohan Verma', 'Engineering', 75000.00),
(102, 'Ananya Sen', 'Marketing', 62000.00),
(103, 'Kavita Nair', 'Human Resources', 58000.00);

INSERT IGNORE INTO Patient (patient_id, name, age, disease, treatment_status) VALUES
(201, 'Sunil Gupta', 45, 'Hypertension', 'Under Observation'),
(202, 'Meera Joshi', 32, 'Viral Fever', 'Recovered'),
(203, 'Deepak Rao', 58, 'Diabetes', 'Active Treatment');
