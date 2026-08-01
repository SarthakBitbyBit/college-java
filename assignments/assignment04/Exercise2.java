package assignments.assignment04;

import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Employee ID: ");
        Integer employeeId = Integer.valueOf(scanner.nextLine());
        System.out.print("Basic salary: ");
        Double basicSalary = Double.valueOf(scanner.nextLine());
        System.out.print("Bonus: ");
        Double bonus = Double.valueOf(scanner.nextLine());

        if (employeeId > 0 && basicSalary >= 0 && bonus >= 0) {
            System.out.println("Employee ID: " + employeeId);
            System.out.println("Net salary: " + (basicSalary + bonus));
        } else {
            System.out.println("Employee ID must be positive and salary values cannot be negative.");
        }
    }
}
