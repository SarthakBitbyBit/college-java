import javax.swing.*;
import java.awt.*;

public class Calculator {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");

        JLabel num1Label = new JLabel("Number 1:");
        JTextField num1Field = new JTextField();

        JLabel num2Label = new JLabel("Number 2:");
        JTextField num2Field = new JTextField();

        JButton addButton = new JButton("Addition");
        JButton subButton = new JButton("Subtraction");

        JLabel resultLabel = new JLabel("Result:");

        frame.setLayout(new GridLayout(4, 2, 10, 10));

        frame.add(num1Label);
        frame.add(num1Field);

        frame.add(num2Label);
        frame.add(num2Field);

        frame.add(addButton);
        frame.add(subButton);

        frame.add(resultLabel);
        frame.add(new JLabel(""));

        addButton.addActionListener(e -> {
            int n1 = Integer.parseInt(num1Field.getText());
            int n2 = Integer.parseInt(num2Field.getText());

            resultLabel.setText("Result: " + (n1 + n2));
        });

        subButton.addActionListener(e -> {
            int n1 = Integer.parseInt(num1Field.getText());
            int n2 = Integer.parseInt(num2Field.getText());

            resultLabel.setText("Result: " + (n1 - n2));
        });

        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
