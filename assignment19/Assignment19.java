import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Assignment19 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/assignment19";
        String user = "javauser";
        String password = "Java1234";

        try {
            try (Connection con = DriverManager.getConnection(url, user, password);
                 Statement stmt = con.createStatement()) {
                System.out.println("Connected to MySQL successfully!");

                System.out.println("\nSTUDENT RECORDS");
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM Student")) {
                    while (rs.next()) {
                        System.out.println(
                                rs.getInt("id") + " | " +
                                        rs.getString("name") + " | " +
                                        rs.getString("department") + " | " +
                                        rs.getInt("marks")
                        );
                    }
                }

                System.out.println("\nPRODUCT RECORDS");
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM Product")) {
                    while (rs.next()) {
                        System.out.println(
                                rs.getInt("id") + " | " +
                                        rs.getString("name") + " | " +
                                        rs.getBigDecimal("price") + " | " +
                                        rs.getInt("quantity")
                        );
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
