
CREATE DATABASE IF NOT EXISTS jdbc_lab;

CREATE USER IF NOT EXISTS 'javauser'@'localhost'
IDENTIFIED BY 'Java1234';

ALTER USER 'javauser'@'localhost'
IDENTIFIED BY 'Java1234';

GRANT SELECT, INSERT, UPDATE, DELETE
ON jdbc_lab.* TO 'javauser'@'localhost';

USE jdbc_lab;

CREATE TABLE IF NOT EXISTS Student (
    roll_no INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    course VARCHAR(50) NOT NULL,
    marks DECIMAL(5,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Product (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Employee (
    employee_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS AppUser (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS HospitalStaff (
    login_id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Book (
    book_id INT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS BookIssue (
    issue_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    student_name VARCHAR(100) NOT NULL,
    issue_date DATE NOT NULL,
    return_date DATE NULL,
    FOREIGN KEY (book_id) REFERENCES Book(book_id)
);

INSERT IGNORE INTO Student VALUES
(1, 'Rahul', 'CSE', 85.00),
(2, 'Priya', 'IT', 92.00),
(3, 'Aman', 'CSE', 78.00),
(4, 'Sneha', 'ECE', 88.00);

INSERT IGNORE INTO Product VALUES
(101, 'Keyboard', 10, 1200.00),
(102, 'Mouse', 20, 650.00),
(103, 'Monitor', 5, 8500.00),
(104, 'Headphones', 8, 1800.00);

INSERT IGNORE INTO Employee VALUES
(1, 'Amit Sharma', 'IT', 45000.00),
(2, 'Neha Verma', 'HR', 40000.00),
(3, 'Rohan Singh', 'Finance', 50000.00),
(4, 'Priya Gupta', 'IT', 55000.00);

INSERT IGNORE INTO AppUser VALUES
('student', 'Student@123'),
('admin', 'Admin@123');

INSERT IGNORE INTO HospitalStaff VALUES
('doctor01', 'Doctor@123', 'doctor'),
('nurse01', 'Nurse@123', 'nurse');

INSERT IGNORE INTO Book VALUES
(1, 'Java Programming', 'Herbert Schildt'),
(2, 'Database System Concepts', 'Abraham Silberschatz'),
(3, 'Operating System Concepts', 'Abraham Silberschatz');

INSERT IGNORE INTO BookIssue
(book_id, student_name, issue_date, return_date)
VALUES
(1, 'Rahul', '2026-10-01', NULL),
(2, 'Priya', '2026-10-03', '2026-10-10');

SELECT 'JDBC lab setup completed' AS status;
