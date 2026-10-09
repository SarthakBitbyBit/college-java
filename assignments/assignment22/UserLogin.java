import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserLogin {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_lab";
    private static final String USER = "javauser";
    private static final String PASSWORD = "Java1234";

    public static void main(String[] args) {
        System.out.println("===== USER LOGIN SYSTEM (AppUser) =====");
        System.out.println("[Educational Demonstration: Plaintext credentials for lab testing only]\n");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine().trim();

            authenticateUser(username, password);
        }
    }

    private static void authenticateUser(String username, String password) {
        // PreparedStatement prevents SQL Injection attacks
        String sql = "SELECT username FROM AppUser WHERE username = ? AND password = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String authenticatedUser = rs.getString("username");
                    System.out.println("\nSUCCESS: Login successful! Welcome, " + authenticatedUser + ".");
                } else {
                    System.out.println("\nFAILURE: Login failed. Invalid username or password.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
