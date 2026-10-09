import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalStaffLogin {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_lab";
    private static final String USER = "javauser";
    private static final String PASSWORD = "Java1234";

    public static void main(String[] args) {
        System.out.println("===== HOSPITAL STAFF AUTHENTICATION SYSTEM =====");
        System.out.println("[Educational Demonstration: Role-based access control using PreparedStatement]\n");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter Login ID: ");
            String loginId = scanner.nextLine().trim();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine().trim();

            authenticateStaff(loginId, password);
        }
    }

    private static void authenticateStaff(String loginId, String password) {
        String sql = "SELECT login_id, role FROM HospitalStaff WHERE login_id = ? AND password = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, loginId);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("login_id");
                    String role = rs.getString("role");

                    System.out.println("\n-----------------------------------------------------");
                    System.out.println("STATUS: Authentication Successful!");
                    System.out.println("User ID: " + id);
                    System.out.println("Role   : " + role.toUpperCase());
                    System.out.println("-----------------------------------------------------");

                    if ("doctor".equalsIgnoreCase(role)) {
                        System.out.println(">>> ACCESS GRANTED: DOCTOR PORTAL <<<");
                        System.out.println("- Privileges: Patient diagnosis, medical prescription, and surgery scheduling.");
                    } else if ("nurse".equalsIgnoreCase(role)) {
                        System.out.println(">>> ACCESS GRANTED: NURSE PORTAL <<<");
                        System.out.println("- Privileges: Patient vitals recording, medication administration, and ward management.");
                    } else {
                        System.out.println(">>> ACCESS GRANTED: GENERAL STAFF PORTAL <<<");
                        System.out.println("- Privileges: General staff administrative access.");
                    }
                    System.out.println("-----------------------------------------------------");
                } else {
                    System.out.println("\nFAILURE: Authentication failed. Invalid login ID or password.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
