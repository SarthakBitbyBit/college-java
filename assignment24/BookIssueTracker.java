import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class BookIssueTracker extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_lab";
    private static final String USER = "javauser";
    private static final String PASSWORD = "Java1234";

    // Form fields
    private JTextField txtIssueId;
    private JTextField txtBookId;
    private JTextField txtStudentName;
    private JTextField txtIssueDate;
    private JTextField txtReturnDate;

    // Table
    private JTable table;
    private DefaultTableModel tableModel;

    public BookIssueTracker() {
        setTitle("Library Book Issue Tracking System");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Header Panel
        JLabel lblHeader = new JLabel("Book Issue Management System", SwingConstants.CENTER);
        lblHeader.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblHeader, BorderLayout.NORTH);

        // 2. Input Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Issue Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0: Issue ID (Read-only)
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Issue ID:"), gbc);
        gbc.gridx = 1;
        txtIssueId = new JTextField(15);
        txtIssueId.setEditable(false);
        txtIssueId.setToolTipText("Auto-generated for new records; selected from table");
        formPanel.add(txtIssueId, gbc);

        // Row 1: Book ID
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1;
        txtBookId = new JTextField(15);
        txtBookId.setToolTipText("Enter valid Book ID (e.g., 1, 2, 3)");
        formPanel.add(txtBookId, gbc);

        // Row 2: Student Name
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1;
        txtStudentName = new JTextField(15);
        formPanel.add(txtStudentName, gbc);

        // Row 3: Issue Date
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Issue Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        txtIssueDate = new JTextField(15);
        txtIssueDate.setText(LocalDate.now().toString());
        formPanel.add(txtIssueDate, gbc);

        // Row 4: Return Date
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Return Date (YYYY-MM-DD or empty):"), gbc);
        gbc.gridx = 1;
        txtReturnDate = new JTextField(15);
        formPanel.add(txtReturnDate, gbc);

        // Buttons Panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnAdd = new JButton("Add Issue");
        JButton btnUpdate = new JButton("Update Issue");
        JButton btnDelete = new JButton("Delete Issue");
        JButton btnClear = new JButton("Clear Form");
        JButton btnRefresh = new JButton("Refresh Table");

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);
        btnPanel.add(btnRefresh);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(btnPanel, gbc);

        add(formPanel, BorderLayout.WEST);

        // 3. Table Panel
        String[] columnNames = {"Issue ID", "Book ID", "Book Title", "Student Name", "Issue Date", "Return Date"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Non-editable cells in table view
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Issued Books Records"));
        add(scrollPane, BorderLayout.CENTER);

        // Event: Populate Form when table row is selected
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    txtIssueId.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    txtBookId.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    txtStudentName.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    txtIssueDate.setText(tableModel.getValueAt(selectedRow, 4).toString());
                    Object retDate = tableModel.getValueAt(selectedRow, 5);
                    txtReturnDate.setText(retDate == null ? "" : retDate.toString());
                }
            }
        });

        // Button Action Listeners
        btnAdd.addActionListener(e -> addIssue());
        btnUpdate.addActionListener(e -> updateIssue());
        btnDelete.addActionListener(e -> deleteIssue());
        btnClear.addActionListener(e -> clearForm());
        btnRefresh.addActionListener(e -> loadIssueData());

        // Initial Data Load
        loadIssueData();
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void loadIssueData() {
        tableModel.setRowCount(0);
        String sql = "SELECT bi.issue_id, bi.book_id, b.title, bi.student_name, bi.issue_date, bi.return_date " +
                     "FROM BookIssue bi " +
                     "JOIN Book b ON bi.book_id = b.book_id " +
                     "ORDER BY bi.issue_id ASC";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int issueId = rs.getInt("issue_id");
                int bookId = rs.getInt("book_id");
                String title = rs.getString("title");
                String studentName = rs.getString("student_name");
                Date issueDate = rs.getDate("issue_date");
                Date returnDate = rs.getDate("return_date");

                tableModel.addRow(new Object[]{
                        issueId,
                        bookId,
                        title,
                        studentName,
                        issueDate,
                        returnDate == null ? "" : returnDate
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to load records from database:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addIssue() {
        String bookIdStr = txtBookId.getText().trim();
        String studentName = txtStudentName.getText().trim();
        String issueDateStr = txtIssueDate.getText().trim();
        String returnDateStr = txtReturnDate.getText().trim();

        // Validations
        if (bookIdStr.isEmpty() || studentName.isEmpty() || issueDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Book ID, Student Name, and Issue Date are required fields.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(bookIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Book ID must be a valid integer.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate issueDate;
        try {
            issueDate = LocalDate.parse(issueDateStr);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Issue Date format. Please use YYYY-MM-DD.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate returnDate = null;
        if (!returnDateStr.isEmpty()) {
            try {
                returnDate = LocalDate.parse(returnDateStr);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this,
                    "Invalid Return Date format. Please use YYYY-MM-DD.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Verify book existence
        if (!isBookValid(bookId)) {
            JOptionPane.showMessageDialog(this,
                    "Book ID " + bookId + " does not exist in the Book catalog.",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO BookIssue (book_id, student_name, issue_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ps.setString(2, studentName);
            ps.setDate(3, Date.valueOf(issueDate));
            if (returnDate != null) {
                ps.setDate(4, Date.valueOf(returnDate));
            } else {
                ps.setNull(4, Types.DATE);
            }

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this,
                        "Book issue record added successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadIssueData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database error while adding record:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateIssue() {
        String issueIdStr = txtIssueId.getText().trim();
        if (issueIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select an issue record from the table to update.",
                    "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int issueId = Integer.parseInt(issueIdStr);
        String bookIdStr = txtBookId.getText().trim();
        String studentName = txtStudentName.getText().trim();
        String issueDateStr = txtIssueDate.getText().trim();
        String returnDateStr = txtReturnDate.getText().trim();

        if (bookIdStr.isEmpty() || studentName.isEmpty() || issueDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Book ID, Student Name, and Issue Date are required fields.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(bookIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Book ID must be a valid integer.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate issueDate;
        try {
            issueDate = LocalDate.parse(issueDateStr);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Issue Date format. Use YYYY-MM-DD.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate returnDate = null;
        if (!returnDateStr.isEmpty()) {
            try {
                returnDate = LocalDate.parse(returnDateStr);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this,
                    "Invalid Return Date format. Use YYYY-MM-DD.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        if (!isBookValid(bookId)) {
            JOptionPane.showMessageDialog(this,
                    "Book ID " + bookId + " does not exist in the Book catalog.",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "UPDATE BookIssue SET book_id = ?, student_name = ?, issue_date = ?, return_date = ? WHERE issue_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ps.setString(2, studentName);
            ps.setDate(3, Date.valueOf(issueDate));
            if (returnDate != null) {
                ps.setDate(4, Date.valueOf(returnDate));
            } else {
                ps.setNull(4, Types.DATE);
            }
            ps.setInt(5, issueId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this,
                        "Record updated successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadIssueData();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Record with Issue ID " + issueId + " not found.",
                        "Update Failed", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database error while updating record:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteIssue() {
        String issueIdStr = txtIssueId.getText().trim();
        if (issueIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select an issue record from the table to delete.",
                    "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int issueId = Integer.parseInt(issueIdStr);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete Issue record #" + issueId + "?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM BookIssue WHERE issue_id = ?";
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, issueId);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this,
                            "Record deleted successfully!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                    loadIssueData();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Record not found.",
                            "Delete Failed", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Database error while deleting record:\n" + e.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isBookValid(int bookId) {
        String sql = "SELECT book_id FROM Book WHERE book_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }

    private void clearForm() {
        txtIssueId.setText("");
        txtBookId.setText("");
        txtStudentName.setText("");
        txtIssueDate.setText(LocalDate.now().toString());
        txtReturnDate.setText("");
        table.clearSelection();
    }

    public static void main(String[] args) {
        // Run Swing UI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            BookIssueTracker app = new BookIssueTracker();
            app.setVisible(true);
        });
    }
}
