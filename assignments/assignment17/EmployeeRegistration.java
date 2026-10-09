import javax.swing.*;
import java.awt.*;

public class EmployeeRegistration {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Registration Form");

        JLabel idLabel = new JLabel("Employee ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel deptLabel = new JLabel("Department:");
        JTextField deptField = new JTextField();

        JLabel salaryLabel = new JLabel("Salary:");
        JTextField salaryField = new JTextField();

        JButton submitButton = new JButton("Submit");

        frame.setLayout(new GridLayout(5, 2, 10, 10));

        frame.add(idLabel);
        frame.add(idField);

        frame.add(nameLabel);
        frame.add(nameField);

        frame.add(deptLabel);
        frame.add(deptField);

        frame.add(salaryLabel);
        frame.add(salaryField);

        frame.add(new JLabel(""));
        frame.add(submitButton);

        submitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                frame,
                "Employee ID: " + idField.getText() +
                "\nName: " + nameField.getText() +
                "\nDepartment: " + deptField.getText() +
                "\nSalary: " + salaryField.getText()
            );
        });

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
