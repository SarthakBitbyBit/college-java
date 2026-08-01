package assignments.assignment04;

import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Integer employeeId = readPositiveInteger(scanner, "Employee ID: ");
            Double basicSalary = readNonNegativeAmount(scanner, "Basic salary: ");
            Double bonus = readNonNegativeAmount(scanner, "Bonus: ");

            System.out.println("Employee ID: " + employeeId);
            System.out.printf("Net salary: %.2f%n", basicSalary + bonus);
        }
    }

    private static Integer readPositiveInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                Integer value = Integer.valueOf(scanner.nextLine().trim());
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Ask again when the input is not an integer.
            }
            System.out.println("Enter a valid positive employee ID.");
        }
    }

    private static Double readNonNegativeAmount(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                Double value = Double.valueOf(scanner.nextLine().trim());
                if (value >= 0 && !value.isInfinite() && !value.isNaN()) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Ask again when the input is not a number.
            }
            System.out.println("Enter a valid non-negative amount.");
        }
    }
}
