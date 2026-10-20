<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
    private String escapeHtml(String str) {
        if (str == null) return "";
        return str.replace("&", "&amp;")
                  .replace("<", "&lt;")
                  .replace(">", "&gt;")
                  .replace("\"", "&quot;")
                  .replace("'", "&#x27;");
    }
%>
<%
    String rollNoRaw = request.getParameter("rollNo");
    String nameRaw = request.getParameter("name");
    String courseRaw = request.getParameter("course");
    String marksRaw = request.getParameter("marks");

    boolean isValid = true;
    String error = null;

    if (rollNoRaw == null || rollNoRaw.trim().isEmpty() ||
        nameRaw == null || nameRaw.trim().isEmpty() ||
        courseRaw == null || courseRaw.trim().isEmpty() ||
        marksRaw == null || marksRaw.trim().isEmpty()) {
        isValid = false;
        error = "all fields are required.";
    }
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>student information submitted</title>
    <style>
        body { font-family: sans-serif; background: #f8fafc; margin: 40px; color: #1e293b; }
        .box { background: #ffffff; padding: 24px; border-radius: 8px; max-width: 500px; margin: 0 auto; box-shadow: 0 2px 8px rgba(0,0,0,0.08); }
        h2 { margin-top: 0; font-size: 18px; color: #0f172a; }
        .success { background: #dcfce7; color: #166534; padding: 10px 14px; border-radius: 4px; font-weight: bold; margin-bottom: 16px; font-size: 14px; }
        .error { background: #fee2e2; color: #991b1b; padding: 10px 14px; border-radius: 4px; font-weight: bold; margin-bottom: 16px; font-size: 14px; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { text-align: left; padding: 10px; border-bottom: 1px solid #e2e8f0; font-size: 14px; }
        th { color: #64748b; width: 40%; }
        td { font-weight: bold; }
        .links { margin-top: 20px; text-align: center; }
        .links a { margin: 0 10px; color: #2563eb; text-decoration: none; font-size: 13px; font-weight: bold; }
    </style>
</head>
<body>
    <div class="box">
        <h2>submitted student information</h2>
        <% if (!isValid) { %>
            <div class="error"><%= error %></div>
        <% } else { %>
            <div class="success">student details submitted successfully!</div>
            <table>
                <tr>
                    <th>roll number</th>
                    <td><%= escapeHtml(rollNoRaw) %></td>
                </tr>
                <tr>
                    <th>student name</th>
                    <td><%= escapeHtml(nameRaw) %></td>
                </tr>
                <tr>
                    <th>course</th>
                    <td><%= escapeHtml(courseRaw) %></td>
                </tr>
                <tr>
                    <th>marks / score</th>
                    <td><%= escapeHtml(marksRaw) %></td>
                </tr>
            </table>
        <% } %>
        <div class="links">
            <a href="student_form.jsp">&larr; submit another record</a>
            <a href="index.jsp">home</a>
        </div>
    </div>
</body>
</html>
