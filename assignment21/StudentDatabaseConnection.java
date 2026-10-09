import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_lab";
    private static final String USER = "javauser";
    private static final String PASSWORD = "Java1234";

    public static void main(String[] args) {
        System.out.println("Attempting connection to MySQL database...");

        // Demonstrate DriverManager and Connection
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Required success message
            System.out.println("Student database connected successfully");

            // Demonstrate Statement and a simple SQL query
            try (Statement stmt = con.createStatement()) {
                System.out.println("\nExecuting query: SELECT roll_no, name, course, marks FROM Student");
                try (ResultSet rs = stmt.executeQuery("SELECT roll_no, name, course, marks FROM Student")) {
                    System.out.println("-------------------------------------------------------------");
                    System.out.printf("%-10s %-20s %-15s %-10s%n", "Roll No", "Name", "Course", "Marks");
                    System.out.println("-------------------------------------------------------------");

                    while (rs.next()) {
                        int rollNo = rs.getInt("roll_no");
                        String name = rs.getString("name");
                        String course = rs.getString("course");
                        double marks = rs.getDouble("marks");

                        System.out.printf("%-10d %-20s %-15s %-10.2f%n", rollNo, name, course, marks);
                    }
                    System.out.println("-------------------------------------------------------------");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("SQL State : " + e.getSQLState());
            System.out.println("Message   : " + e.getMessage());
        }
    }
}
