<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="db_config.jsp" %>
<%
    String action = request.getParameter("action");
    String message = null;
    String error = null;

    if ("create".equalsIgnoreCase(action)) {
        String idStr = request.getParameter("patient_id");
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        String disease = request.getParameter("disease");
        String status = request.getParameter("treatment_status");

        if (idStr == null || name == null || ageStr == null || disease == null || status == null ||
            idStr.trim().isEmpty() || name.trim().isEmpty() || ageStr.trim().isEmpty() || disease.trim().isEmpty() || status.trim().isEmpty()) {
            error = "all fields are required to register a patient.";
        } else {
            try {
                int patientId = Integer.parseInt(idStr.trim());
                int age = Integer.parseInt(ageStr.trim());
                String sql = "INSERT INTO Patient (patient_id, name, age, disease, treatment_status) VALUES (?, ?, ?, ?, ?)";
                try (Connection conn = getConnection();
                     PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, patientId);
                    ps.setString(2, name.trim());
                    ps.setInt(3, age);
                    ps.setString(4, disease.trim());
                    ps.setString(5, status.trim());
                    ps.executeUpdate();
                    message = "patient record #" + patientId + " registered successfully.";
                }
            } catch (NumberFormatException e) {
                error = "invalid number format for patient id or age.";
            } catch (SQLIntegrityConstraintViolationException e) {
                error = "patient id already exists. please enter a unique patient id.";
            } catch (Exception e) {
                error = "database error: " + e.getMessage();
            }
        }
    } else if ("update".equalsIgnoreCase(action)) {
        String idStr = request.getParameter("patient_id");
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        String disease = request.getParameter("disease");
        String status = request.getParameter("treatment_status");

        if (idStr == null || name == null || ageStr == null || disease == null || status == null ||
            idStr.trim().isEmpty() || name.trim().isEmpty() || ageStr.trim().isEmpty() || disease.trim().isEmpty() || status.trim().isEmpty()) {
            error = "all fields are required to update a patient.";
        } else {
            try {
                int patientId = Integer.parseInt(idStr.trim());
                int age = Integer.parseInt(ageStr.trim());
                String sql = "UPDATE Patient SET name = ?, age = ?, disease = ?, treatment_status = ? WHERE patient_id = ?";
                try (Connection conn = getConnection();
                     PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, name.trim());
                    ps.setInt(2, age);
                    ps.setString(3, disease.trim());
                    ps.setString(4, status.trim());
                    ps.setInt(5, patientId);
                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        message = "patient record #" + patientId + " updated successfully.";
                    } else {
                        error = "patient id #" + patientId + " not found.";
                    }
                }
            } catch (NumberFormatException e) {
                error = "invalid number format.";
            } catch (Exception e) {
                error = "database error: " + e.getMessage();
            }
        }
    } else if ("delete".equalsIgnoreCase(action)) {
        String idStr = request.getParameter("patient_id");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int patientId = Integer.parseInt(idStr.trim());
                String sql = "DELETE FROM Patient WHERE patient_id = ?";
                try (Connection conn = getConnection();
                     PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, patientId);
                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        message = "patient record #" + patientId + " deleted successfully.";
                    } else {
                        error = "patient id #" + patientId + " not found.";
                    }
                }
            } catch (Exception e) {
                error = "database error: " + e.getMessage();
            }
        }
    }

    String editId = request.getParameter("edit_id");
    Integer editPatientId = null;
    String editName = "";
    String editAge = "";
    String editDisease = "";
    String editStatus = "";

    if (editId != null && !editId.trim().isEmpty()) {
        try {
            int patientId = Integer.parseInt(editId.trim());
            String sql = "SELECT name, age, disease, treatment_status FROM Patient WHERE patient_id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, patientId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        editPatientId = patientId;
                        editName = rs.getString("name");
                        editAge = String.valueOf(rs.getInt("age"));
                        editDisease = rs.getString("disease");
                        editStatus = rs.getString("treatment_status");
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
    <title>patient management crud</title>
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
        input, select { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #cbd5e1; border-radius: 4px; font-size: 14px; }
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
        <h1>patient record management system (crud)</h1>

        <% if (message != null) { %><div class="alert-success"><%= message %></div><% } %>
        <% if (error != null) { %><div class="alert-error"><%= error %></div><% } %>

        <div class="form-card">
            <h3><%= (editPatientId != null) ? "edit patient record #" + editPatientId : "register new patient" %></h3>
            <form action="patients.jsp" method="post">
                <input type="hidden" name="action" value="<%= (editPatientId != null) ? "update" : "create" %>">
                <div class="form-row">
                    <div class="form-group">
                        <label for="patient_id">patient id</label>
                        <input type="number" id="patient_id" name="patient_id" required value="<%= (editPatientId != null) ? editPatientId : "" %>" <%= (editPatientId != null) ? "readonly" : "" %>>
                    </div>
                    <div class="form-group">
                        <label for="name">patient name</label>
                        <input type="text" id="name" name="name" required value="<%= escape(editName) %>">
                    </div>
                    <div class="form-group" style="flex: 0.5;">
                        <label for="age">age</label>
                        <input type="number" id="age" name="age" required value="<%= escape(editAge) %>">
                    </div>
                    <div class="form-group">
                        <label for="disease">disease / condition</label>
                        <input type="text" id="disease" name="disease" required value="<%= escape(editDisease) %>">
                    </div>
                    <div class="form-group">
                        <label for="treatment_status">treatment status</label>
                        <select id="treatment_status" name="treatment_status">
                            <option value="Active Treatment" <%= "Active Treatment".equals(editStatus) ? "selected" : "" %>>Active Treatment</option>
                            <option value="Under Observation" <%= "Under Observation".equals(editStatus) ? "selected" : "" %>>Under Observation</option>
                            <option value="Recovered" <%= "Recovered".equals(editStatus) ? "selected" : "" %>>Recovered</option>
                            <option value="Discharged" <%= "Discharged".equals(editStatus) ? "selected" : "" %>>Discharged</option>
                        </select>
                    </div>
                </div>
                <div style="margin-top: 14px;">
                    <button type="submit"><%= (editPatientId != null) ? "save patient details" : "register patient" %></button>
                    <% if (editPatientId != null) { %>
                        <a href="patients.jsp" style="margin-left: 10px; font-size: 13px; color: #64748b; text-decoration: none;">cancel edit</a>
                    <% } %>
                </div>
            </form>
        </div>

        <h3>current patient records</h3>
        <table>
            <thead>
                <tr>
                    <th>patient id</th>
                    <th>name</th>
                    <th>age</th>
                    <th>disease</th>
                    <th>treatment status</th>
                    <th>actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    boolean hasRecords = false;
                    try (Connection conn = getConnection();
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT patient_id, name, age, disease, treatment_status FROM Patient ORDER BY patient_id")) {
                        while (rs.next()) {
                            hasRecords = true;
                            int pId = rs.getInt("patient_id");
                %>
                    <tr>
                        <td><%= pId %></td>
                        <td><%= escape(rs.getString("name")) %></td>
                        <td><%= rs.getInt("age") %></td>
                        <td><%= escape(rs.getString("disease")) %></td>
                        <td><%= escape(rs.getString("treatment_status")) %></td>
                        <td>
                            <a class="action-link" href="patients.jsp?edit_id=<%= pId %>">edit</a>
                            <a class="action-link action-del" href="patients.jsp?action=delete&patient_id=<%= pId %>" onclick="return confirm('are you sure you want to delete patient #<%= pId %>?');">delete</a>
                        </td>
                    </tr>
                <%
                        }
                    } catch (Exception e) {
                %>
                    <tr><td colspan="6" style="color: #dc2626;">error loading records: <%= e.getMessage() %></td></tr>
                <%
                    }
                    if (!hasRecords) {
                %>
                    <tr><td colspan="6" style="text-align: center; color: #94a3b8;">no patient records found.</td></tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
