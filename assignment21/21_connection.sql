-- 21_connection.sql
-- Assignment 21: Connection verification and basic metadata queries

USE jdbc_lab;

-- Check connected database and server version
SELECT DATABASE() AS current_database, USER() AS current_user, VERSION() AS mysql_version;

-- Verification query: fetch student records
SELECT roll_no, name, course, marks FROM Student;
