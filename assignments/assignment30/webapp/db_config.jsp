<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/jsp_lab?useSSL=false&allowPublicKeyRetrieval=true";
    public static final String JDBC_USER = "javauser";
    public static final String JDBC_PASS = "Java1234";

    public static java.sql.Connection getConnection() throws java.sql.SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            return java.sql.DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch (java.sql.SQLException e) {
            String fallbackUrl = "jdbc:mysql://localhost:3306/jdbc_lab?useSSL=false&allowPublicKeyRetrieval=true";
            return java.sql.DriverManager.getConnection(fallbackUrl, JDBC_USER, JDBC_PASS);
        }
    }

    public static String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
%>
