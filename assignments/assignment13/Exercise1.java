package assignments.assignment13;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Exercise1 {
    private static final File EMPLOYEE_FILE = new File("employee.txt");

    public static void main(String[] args) {
        createFile();
        writeEmployeeDetails();
        readEmployeeDetails();
    }

    private static void createFile() {
        try {
            if (EMPLOYEE_FILE.createNewFile()) {
                System.out.println("Employee file created successfully.");
            } else {
                System.out.println("Employee file already exists.");
            }
        } catch (IOException exception) {
            System.out.println("Unable to create the employee file.");
            exception.printStackTrace();
        }
    }

    private static void writeEmployeeDetails() {
        try (FileWriter writer = new FileWriter(EMPLOYEE_FILE)) {
            writer.write("Employee ID: 101\n");
            writer.write("Name: Rahul Mehta\n");
            writer.write("Department: Computer Science\n");
            System.out.println("Employee details written successfully.");
        } catch (IOException exception) {
            System.out.println("Unable to write employee details.");
            exception.printStackTrace();
        }
    }

    private static void readEmployeeDetails() {
        System.out.println("Employee details:");
        try (Scanner reader = new Scanner(EMPLOYEE_FILE)) {
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to read employee details.");
            exception.printStackTrace();
        }
    }
}
