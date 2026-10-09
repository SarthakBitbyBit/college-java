-- 22_authentication.sql
-- Assignment 22: Authentication queries for AppUser and HospitalStaff

USE jdbc_lab;

-- AppUser login query
-- Parameterized equivalent: SELECT username FROM AppUser WHERE username = ? AND password = ?
SELECT username FROM AppUser WHERE username = 'student' AND password = 'Student@123';
SELECT username FROM AppUser WHERE username = 'admin' AND password = 'Admin@123';

-- HospitalStaff login query with role detection
-- Parameterized equivalent: SELECT login_id, role FROM HospitalStaff WHERE login_id = ? AND password = ?
SELECT login_id, role FROM HospitalStaff WHERE login_id = 'doctor01' AND password = 'Doctor@123';
SELECT login_id, role FROM HospitalStaff WHERE login_id = 'nurse01' AND password = 'Nurse@123';
