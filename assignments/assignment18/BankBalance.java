import javax.swing.*;
import java.awt.*;

public class BankBalance {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bank Balance Calculator");

        JLabel balanceLabel = new JLabel("Initial Balance:");
        JTextField balanceField = new JTextField();

        JLabel amountLabel = new JLabel("Transaction Amount:");
        JTextField amountField = new JTextField();

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        JLabel resultLabel = new JLabel("Balance: ");

        frame.setLayout(new GridLayout(4, 2, 10, 10));

        frame.add(balanceLabel);
        frame.add(balanceField);

        frame.add(amountLabel);
        frame.add(amountField);

        frame.add(depositButton);
        frame.add(withdrawButton);

        frame.add(resultLabel);
        frame.add(new JLabel(""));

        depositButton.addActionListener(e -> {
            double balance = Double.parseDouble(balanceField.getText());
            double amount = Double.parseDouble(amountField.getText());

            balance = balance + amount;

            resultLabel.setText("Balance: " + balance);
        });

        withdrawButton.addActionListener(e -> {
            double balance = Double.parseDouble(balanceField.getText());
            double amount = Double.parseDouble(amountField.getText());

            balance = balance - amount;

            resultLabel.setText("Balance: " + balance);
        });

        frame.setSize(450, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
