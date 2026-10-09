import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeResultSetNavigation {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_lab";
    private static final String USER = "javauser";
    private static final String PASSWORD = "Java1234";

    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("     ASSIGNMENT 23: SCROLLABLE RESULTSET NAVIGATION DEMO         ");
        System.out.println("==================================================================");

        String sql = "SELECT employee_id, name, department, salary FROM Employee ORDER BY employee_id";

        // Request a scrollable, read-only ResultSet
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement(
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(sql)) {

            // 1. Sequential Display using next()
            System.out.println("\n--- 1. SEQUENTIAL NAVIGATION (rs.next()) ---");
            while (rs.next()) {
                printRecord(rs, "Sequential next()");
            }

            // 2. Navigation using rs.first()
            System.out.println("\n--- 2. FIRST RECORD (rs.first()) ---");
            if (rs.first()) {
                printRecord(rs, "rs.first()");
            }

            // 3. Navigation using rs.last()
            System.out.println("\n--- 3. LAST RECORD (rs.last()) ---");
            if (rs.last()) {
                printRecord(rs, "rs.last()");
            }

            // 4. Backward Navigation using rs.previous()
            System.out.println("\n--- 4. PREVIOUS RECORD FROM LAST (rs.previous()) ---");
            if (rs.previous()) {
                printRecord(rs, "rs.previous()");
            }

            // 5. Absolute Positioning using rs.absolute()
            System.out.println("\n--- 5. ABSOLUTE POSITIONING (rs.absolute(3)) ---");
            if (rs.absolute(3)) {
                printRecord(rs, "rs.absolute(3)");
            }

            System.out.println("\n--- 6. ABSOLUTE POSITIONING FROM END (rs.absolute(-1)) ---");
            if (rs.absolute(-1)) {
                printRecord(rs, "rs.absolute(-1) [Last Record]");
            }

            // 6. Relative Positioning using rs.relative()
            System.out.println("\n--- 7. RELATIVE POSITIONING ---");
            rs.first(); // Move to row 1
            printRecord(rs, "Reset to row 1 (rs.first())");

            if (rs.relative(2)) { // Move +2 forward (from row 1 to row 3)
                printRecord(rs, "rs.relative(+2) from row 1 -> row 3");
            }

            if (rs.relative(-1)) { // Move -1 backward (from row 3 to row 2)
                printRecord(rs, "rs.relative(-1) from row 3 -> row 2");
            }

            System.out.println("\n==================================================================");
            System.out.println("All ResultSet cursor navigation methods demonstrated successfully.");
            System.out.println("==================================================================");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void printRecord(ResultSet rs, String action) throws SQLException {
        int rowNumber = rs.getRow();
        int empId = rs.getInt("employee_id");
        String name = rs.getString("name");
        String dept = rs.getString("department");
        double salary = rs.getDouble("salary");

        System.out.printf("[%s | Row: %d] ID: %-3d | Name: %-15s | Dept: %-10s | Salary: %.2f%n",
                action, rowNumber, empId, name, dept, salary);
    }
}
