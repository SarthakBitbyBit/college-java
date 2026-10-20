<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="db_config.jsp" %>
<%
    String action = request.getParameter("action");
    String message = null;
    String error = null;

    if ("create".equalsIgnoreCase(action)) {
        String idStr = request.getParameter("employee_id");
        String name = request.getParameter("name");
        String dept = request.getParameter("department");
        String salaryStr = request.getParameter("salary");

        if (idStr == null || name == null || dept == null || salaryStr == null ||
            idStr.trim().isEmpty() || name.trim().isEmpty() || dept.trim().isEmpty() || salaryStr.trim().isEmpty()) {
            error = "all fields are required to add an employee.";
        } else {
            try {
                int empId = Integer.parseInt(idStr.trim());
                double salary = Double.parseDouble(salaryStr.trim());
                String sql = "INSERT INTO Employee (employee_id, name, department, salary) VALUES (?, ?, ?, ?)";
                try (Connection conn = getConnection();
                     PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, empId);
                    ps.setString(2, name.trim());
                    ps.setString(3, dept.trim());
                    ps.setDouble(4, salary);
                    ps.executeUpdate();
                    message = "employee record #" + empId + " created successfully.";
                }
            } catch (NumberFormatException e) {
                error = "invalid number format for employee id or salary.";
            } catch (SQLIntegrityConstraintViolationException e) {
                error = "employee id already exists. please use a unique id.";
            } catch (Exception e) {
                error = "database error: " + e.getMessage();
            }
        }
    } else if ("update".equalsIgnoreCase(action)) {
        String idStr = request.getParameter("employee_id");
        String name = request.getParameter("name");
        String dept = request.getParameter("department");
        String salaryStr = request.getParameter("salary");

        if (idStr == null || name == null || dept == null || salaryStr == null ||
            idStr.trim().isEmpty() || name.trim().isEmpty() || dept.trim().isEmpty() || salaryStr.trim().isEmpty()) {
            error = "all fields are required to update an employee.";
        } else {
            try {
                int empId = Integer.parseInt(idStr.trim());
                double salary = Double.parseDouble(salaryStr.trim());
                String sql = "UPDATE Employee SET name = ?, department = ?, salary = ? WHERE employee_id = ?";
                try (Connection conn = getConnection();
                     PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, name.trim());
                    ps.setString(2, dept.trim());
                    ps.setDouble(3, salary);
                    ps.setInt(4, empId);
                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        message = "employee record #" + empId + " updated successfully.";
                    } else {
                        error = "employee id #" + empId + " not found.";
                    }
                }
            } catch (NumberFormatException e) {
                error = "invalid number format.";
            } catch (Exception e) {
                error = "database error: " + e.getMessage();
            }
        }
    } else if ("delete".equalsIgnoreCase(action)) {
        String idStr = request.getParameter("employee_id");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int empId = Integer.parseInt(idStr.trim());
                String sql = "DELETE FROM Employee WHERE employee_id = ?";
                try (Connection conn = getConnection();
                     PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, empId);
                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        message = "employee record #" + empId + " deleted successfully.";
                    } else {
                        error = "employee id #" + empId + " not found.";
                    }
                }
            } catch (Exception e) {
                error = "database error: " + e.getMessage();
            }
        }
    }

    String editId = request.getParameter("edit_id");
    Integer editEmpId = null;
    String editName = "";
    String editDept = "";
    String editSalary = "";

    if (editId != null && !editId.trim().isEmpty()) {
        try {
            int empId = Integer.parseInt(editId.trim());
            String sql = "SELECT name, department, salary FROM Employee WHERE employee_id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, empId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        editEmpId = empId;
                        editName = rs.getString("name");
                        editDept = rs.getString("department");
                        editSalary = String.valueOf(rs.getDouble("salary"));
                    }
                }
            }
        } catch (Exception ignored) {}
    }
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>employee management crud</title>
    <style>
        body { font-family: sans-serif; background: #f8fafc; margin: 30px; color: #1e293b; }
        .container { max-width: 900px; margin: 0 auto; }
        h1 { font-size: 20px; color: #0f172a; }
        .alert-success { background: #dcfce7; color: #166534; padding: 10px 14px; border-radius: 4px; margin-bottom: 16px; font-size: 14px; font-weight: bold; }
        .alert-error { background: #fee2e2; color: #991b1b; padding: 10px 14px; border-radius: 4px; margin-bottom: 16px; font-size: 14px; font-weight: bold; }
        .form-card { background: #ffffff; padding: 20px; border-radius: 6px; border: 1px solid #e2e8f0; margin-bottom: 24px; box-shadow: 0 1px 3px rgba(0,0,0,0.05); }
        .form-row { display: flex; gap: 12px; margin-top: 10px; }
        .form-group { flex: 1; }
        label { display: block; font-size: 12px; font-weight: bold; color: #64748b; margin-bottom: 4px; }
        input { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #cbd5e1; border-radius: 4px; font-size: 14px; }
        button { padding: 9px 18px; background: #2563eb; color: #fff; border: none; border-radius: 4px; font-weight: bold; cursor: pointer; font-size: 14px; }
        button:hover { background: #1d4ed8; }
        table { width: 100%; border-collapse: collapse; background: #ffffff; border-radius: 6px; overflow: hidden; border: 1px solid #e2e8f0; box-shadow: 0 1px 3px rgba(0,0,0,0.05); }
        th, td { padding: 10px 14px; text-align: left; font-size: 14px; border-bottom: 1px solid #e2e8f0; }
        th { background: #f1f5f9; color: #475569; font-weight: bold; }
        .action-link { margin-right: 8px; text-decoration: none; font-size: 13px; font-weight: bold; color: #2563eb; }
        .action-del { color: #dc2626; }
        .nav-link { display: inline-block; margin-bottom: 16px; color: #64748b; text-decoration: none; font-size: 13px; }
    </style>
</head>
<body>
    <div class="container">
        <a class="nav-link" href="index.jsp">&larr; back to assignment 30 home</a>
        <h1>employee management system (crud)</h1>

        <% if (message != null) { %><div class="alert-success"><%= message %></div><% } %>
        <% if (error != null) { %><div class="alert-error"><%= error %></div><% } %>

        <div class="form-card">
            <h3><%= (editEmpId != null) ? "edit employee record #" + editEmpId : "add new employee" %></h3>
            <form action="employees.jsp" method="post">
                <input type="hidden" name="action" value="<%= (editEmpId != null) ? "update" : "create" %>">
                <div class="form-row">
                    <div class="form-group">
                        <label for="employee_id">employee id</label>
                        <input type="number" id="employee_id" name="employee_id" required value="<%= (editEmpId != null) ? editEmpId : "" %>" <%= (editEmpId != null) ? "readonly" : "" %>>
                    </div>
                    <div class="form-group">
                        <label for="name">employee name</label>
                        <input type="text" id="name" name="name" required value="<%= escape(editName) %>">
                    </div>
                    <div class="form-group">
                        <label for="department">department</label>
                        <input type="text" id="department" name="department" required value="<%= escape(editDept) %>">
                    </div>
                    <div class="form-group">
                        <label for="salary">salary</label>
                        <input type="number" step="0.01" id="salary" name="salary" required value="<%= escape(editSalary) %>">
                    </div>
                </div>
                <div style="margin-top: 14px;">
                    <button type="submit"><%= (editEmpId != null) ? "save changes" : "add employee" %></button>
                    <% if (editEmpId != null) { %>
                        <a href="employees.jsp" style="margin-left: 10px; font-size: 13px; color: #64748b; text-decoration: none;">cancel edit</a>
                    <% } %>
                </div>
            </form>
        </div>

        <h3>current employee records</h3>
        <table>
            <thead>
                <tr>
                    <th>employee id</th>
                    <th>name</th>
                    <th>department</th>
                    <th>salary</th>
                    <th>actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    boolean hasRecords = false;
                    try (Connection conn = getConnection();
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT employee_id, name, department, salary FROM Employee ORDER BY employee_id")) {
                        while (rs.next()) {
                            hasRecords = true;
                            int eId = rs.getInt("employee_id");
                %>
                    <tr>
                        <td><%= eId %></td>
                        <td><%= escape(rs.getString("name")) %></td>
                        <td><%= escape(rs.getString("department")) %></td>
                        <td><%= String.format("%.2f", rs.getDouble("salary")) %></td>
                        <td>
                            <a class="action-link" href="employees.jsp?edit_id=<%= eId %>">edit</a>
                            <a class="action-link action-del" href="employees.jsp?action=delete&employee_id=<%= eId %>" onclick="return confirm('are you sure you want to delete employee #<%= eId %>?');">delete</a>
                        </td>
                    </tr>
                <%
                        }
                    } catch (Exception e) {
                %>
                    <tr><td colspan="5" style="color: #dc2626;">error loading records: <%= e.getMessage() %></td></tr>
                <%
                    }
                    if (!hasRecords) {
                %>
                    <tr><td colspan="5" style="text-align: center; color: #94a3b8;">no employee records found.</td></tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
