import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_lab";
    private static final String USER = "javauser";
    private static final String PASSWORD = "Java1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student (Create)");
            System.out.println("2. View All Students (Read)");
            System.out.println("3. Search Student by Roll No (Read)");
            System.out.println("4. Update Student (Update)");
            System.out.println("5. Delete Student (Delete)");
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
                case 1 -> addStudent(scanner);
                case 2 -> viewAllStudents();
                case 3 -> searchStudent(scanner);
                case 4 -> updateStudent(scanner);
                case 5 -> deleteStudent(scanner);
                case 6 -> {
                    System.out.println("Exiting Student Management System. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please choose 1 - 6.");
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void addStudent(Scanner scanner) {
        try {
            System.out.print("Enter Roll Number: ");
            int rollNo = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter Course: ");
            String course = scanner.nextLine().trim();
            System.out.print("Enter Marks: ");
            double marks = Double.parseDouble(scanner.nextLine().trim());

            String sql = "INSERT INTO Student (roll_no, name, course, marks) VALUES (?, ?, ?, ?)";
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, rollNo);
                ps.setString(2, name);
                ps.setString(3, course);
                ps.setDouble(4, marks);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Student record added successfully!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input error: Please enter valid numbers for roll number and marks.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void viewAllStudents() {
        String sql = "SELECT roll_no, name, course, marks FROM Student ORDER BY roll_no";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n-------------------------------------------------------------");
            System.out.printf("%-10s %-20s %-15s %-10s%n", "Roll No", "Name", "Course", "Marks");
            System.out.println("-------------------------------------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-10d %-20s %-15s %-10.2f%n",
                        rs.getInt("roll_no"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getDouble("marks"));
            }

            if (!found) {
                System.out.println("No student records found.");
            }
            System.out.println("-------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void searchStudent(Scanner scanner) {
        try {
            System.out.print("Enter Roll Number to search: ");
            int rollNo = Integer.parseInt(scanner.nextLine().trim());

            String sql = "SELECT roll_no, name, course, marks FROM Student WHERE roll_no = ?";
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, rollNo);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("\nStudent Found:");
                        System.out.println("Roll No : " + rs.getInt("roll_no"));
                        System.out.println("Name    : " + rs.getString("name"));
                        System.out.println("Course  : " + rs.getString("course"));
                        System.out.println("Marks   : " + rs.getDouble("marks"));
                    } else {
                        System.out.println("Student with Roll No " + rollNo + " not found.");
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input error: Invalid roll number.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void updateStudent(Scanner scanner) {
        try {
            System.out.print("Enter Roll Number to update: ");
            int rollNo = Integer.parseInt(scanner.nextLine().trim());

            // First check if student exists
            String checkSql = "SELECT roll_no FROM Student WHERE roll_no = ?";
            try (Connection con = getConnection();
                 PreparedStatement checkPs = con.prepareStatement(checkSql)) {
                checkPs.setInt(1, rollNo);
                try (ResultSet rs = checkPs.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("Student with Roll No " + rollNo + " does not exist.");
                        return;
                    }
                }

                System.out.print("Enter New Name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Enter New Course: ");
                String course = scanner.nextLine().trim();
                System.out.print("Enter New Marks: ");
                double marks = Double.parseDouble(scanner.nextLine().trim());

                String updateSql = "UPDATE Student SET name = ?, course = ?, marks = ? WHERE roll_no = ?";
                try (PreparedStatement updatePs = con.prepareStatement(updateSql)) {
                    updatePs.setString(1, name);
                    updatePs.setString(2, course);
                    updatePs.setDouble(3, marks);
                    updatePs.setInt(4, rollNo);

                    int rows = updatePs.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Student record updated successfully!");
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input error: Please enter valid numbers.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void deleteStudent(Scanner scanner) {
        try {
            System.out.print("Enter Roll Number to delete: ");
            int rollNo = Integer.parseInt(scanner.nextLine().trim());

            String sql = "DELETE FROM Student WHERE roll_no = ?";
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, rollNo);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Student record deleted successfully!");
                } else {
                    System.out.println("Student with Roll No " + rollNo + " not found.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input error: Invalid roll number.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
