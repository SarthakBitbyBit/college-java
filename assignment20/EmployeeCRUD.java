import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_lab";
    private static final String USER = "javauser";
    private static final String PASSWORD = "Java1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== EMPLOYEE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Employee (Create)");
            System.out.println("2. View All Employees (Read)");
            System.out.println("3. Search Employee by ID (Read)");
            System.out.println("4. Update Employee (Update)");
            System.out.println("5. Delete Employee (Delete)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            switch (choice) {
                case 1 -> addEmployee(scanner);
                case 2 -> viewAllEmployees();
                case 3 -> searchEmployee(scanner);
                case 4 -> updateEmployee(scanner);
                case 5 -> deleteEmployee(scanner);
                case 6 -> {
                    System.out.println("Exiting Employee Management System. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please choose 1 - 6.");
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void addEmployee(Scanner scanner) {
        try {
            System.out.print("Enter Employee ID: ");
            int empId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter Department: ");
            String dept = scanner.nextLine().trim();
            System.out.print("Enter Salary: ");
            double salary = Double.parseDouble(scanner.nextLine().trim());

            String sql = "INSERT INTO Employee (employee_id, name, department, salary) VALUES (?, ?, ?, ?)";
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, empId);
                ps.setString(2, name);
                ps.setString(3, dept);
                ps.setDouble(4, salary);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Employee record added successfully!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input error: Please enter valid numbers for employee ID and salary.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void viewAllEmployees() {
        String sql = "SELECT employee_id, name, department, salary FROM Employee ORDER BY employee_id";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n------------------------------------------------------------------");
            System.out.printf("%-12s %-20s %-15s %-12s%n", "Employee ID", "Name", "Department", "Salary");
            System.out.println("------------------------------------------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-12d %-20s %-15s %-12.2f%n",
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary"));
            }

            if (!found) {
                System.out.println("No employee records found.");
            }
            System.out.println("------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void searchEmployee(Scanner scanner) {
        try {
            System.out.print("Enter Employee ID to search: ");
            int empId = Integer.parseInt(scanner.nextLine().trim());

            String sql = "SELECT employee_id, name, department, salary FROM Employee WHERE employee_id = ?";
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, empId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("\nEmployee Found:");
                        System.out.println("ID         : " + rs.getInt("employee_id"));
                        System.out.println("Name       : " + rs.getString("name"));
                        System.out.println("Department : " + rs.getString("department"));
                        System.out.println("Salary     : " + rs.getDouble("salary"));
                    } else {
                        System.out.println("Employee with ID " + empId + " not found.");
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input error: Invalid employee ID.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void updateEmployee(Scanner scanner) {
        try {
            System.out.print("Enter Employee ID to update: ");
            int empId = Integer.parseInt(scanner.nextLine().trim());

            String checkSql = "SELECT employee_id FROM Employee WHERE employee_id = ?";
            try (Connection con = getConnection();
                 PreparedStatement checkPs = con.prepareStatement(checkSql)) {
                checkPs.setInt(1, empId);
                try (ResultSet rs = checkPs.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("Employee with ID " + empId + " does not exist.");
                        return;
                    }
                }

                System.out.print("Enter New Name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Enter New Department: ");
                String dept = scanner.nextLine().trim();
                System.out.print("Enter New Salary: ");
                double salary = Double.parseDouble(scanner.nextLine().trim());

                String updateSql = "UPDATE Employee SET name = ?, department = ?, salary = ? WHERE employee_id = ?";
                try (PreparedStatement updatePs = con.prepareStatement(updateSql)) {
                    updatePs.setString(1, name);
                    updatePs.setString(2, dept);
                    updatePs.setDouble(3, salary);
                    updatePs.setInt(4, empId);

                    int rows = updatePs.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Employee record updated successfully!");
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input error: Please enter valid numbers.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void deleteEmployee(Scanner scanner) {
        try {
            System.out.print("Enter Employee ID to delete: ");
            int empId = Integer.parseInt(scanner.nextLine().trim());

            String sql = "DELETE FROM Employee WHERE employee_id = ?";
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, empId);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Employee record deleted successfully!");
                } else {
                    System.out.println("Employee with ID " + empId + " not found.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input error: Invalid employee ID.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
