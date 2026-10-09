-- 24_book_issue.sql
-- Assignment 24: Book and BookIssue tracking queries for Swing GUI

USE jdbc_lab;

-- View catalog of books
SELECT book_id, title, author FROM Book ORDER BY book_id ASC;

-- View all book issue records with book details
SELECT bi.issue_id, bi.book_id, b.title, bi.student_name, bi.issue_date, bi.return_date
FROM BookIssue bi
JOIN Book b ON bi.book_id = b.book_id
ORDER BY bi.issue_id ASC;

-- Sample Insert for testing
INSERT INTO BookIssue (book_id, student_name, issue_date, return_date)
VALUES (3, 'Aman', '2026-10-05', NULL);

-- Sample Update for testing
UPDATE BookIssue
SET return_date = '2026-10-12'
WHERE issue_id = 3;

-- Sample Delete for testing
DELETE FROM BookIssue WHERE issue_id = 3;
