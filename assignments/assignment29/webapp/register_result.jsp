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
    String nameRaw = request.getParameter("name");
    String emailRaw = request.getParameter("email");
    String phoneRaw = request.getParameter("phone");
    String passwordRaw = request.getParameter("password");

    boolean isValid = true;
    String error = null;

    if (nameRaw == null || nameRaw.trim().isEmpty() ||
        emailRaw == null || emailRaw.trim().isEmpty() ||
        phoneRaw == null || phoneRaw.trim().isEmpty() ||
        passwordRaw == null || passwordRaw.trim().isEmpty()) {
        isValid = false;
        error = "all fields are required.";
    } else if (!emailRaw.contains("@") || !emailRaw.contains(".")) {
        isValid = false;
        error = "invalid email address format.";
    } else if (phoneRaw.replaceAll("[^0-9]", "").length() < 10) {
        isValid = false;
        error = "phone number must contain at least 10 digits.";
    } else if (passwordRaw.length() < 6) {
        isValid = false;
        error = "password must be at least 6 characters long.";
    }
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>student registration submitted</title>
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
        .masked { font-family: monospace; letter-spacing: 2px; color: #475569; }
        .links { margin-top: 20px; text-align: center; }
        .links a { margin: 0 10px; color: #2563eb; text-decoration: none; font-size: 13px; font-weight: bold; }
    </style>
</head>
<body>
    <div class="box">
        <h2>registration confirmation</h2>
        <% if (!isValid) { %>
            <div class="error"><%= error %></div>
        <% } else { %>
            <div class="success">student registration completed successfully!</div>
            <table>
                <tr>
                    <th>registered name</th>
                    <td><%= escapeHtml(nameRaw) %></td>
                </tr>
                <tr>
                    <th>email address</th>
                    <td><%= escapeHtml(emailRaw) %></td>
                </tr>
                <tr>
                    <th>phone number</th>
                    <td><%= escapeHtml(phoneRaw) %></td>
                </tr>
                <tr>
                    <th>account password</th>
                    <td><span class="masked">••••••••</span> (password securely verified)</td>
                </tr>
            </table>
        <% } %>
        <div class="links">
            <a href="register_form.jsp">&larr; register another student</a>
            <a href="index.jsp">home</a>
        </div>
    </div>
</body>
</html>
