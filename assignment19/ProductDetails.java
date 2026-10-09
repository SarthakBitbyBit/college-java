import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDetails {
    private static final String URL = "jdbc:mysql://localhost:3306/assignment19";
    private static final String USER = "javauser";
    private static final String PASSWORD = "Java1234";

    public static void main(String[] args) {
        String sql = "SELECT id, name, quantity, price FROM Product";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Connected to MySQL database successfully!\n");
            System.out.println("===============================================================");
            System.out.printf("%-12s %-20s %-12s %-12s%n", "Product ID", "Product Name", "Quantity", "Price");
            System.out.println("===============================================================");

            while (rs.next()) {
                int productId = rs.getInt("id");
                String productName = rs.getString("name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                System.out.printf("%-12d %-20s %-12d %-12.2f%n", productId, productName, quantity, price);
            }
            System.out.println("===============================================================");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
