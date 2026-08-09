package assignments.assignment13;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Exercise2 {
    private static final File PATIENT_FILE = new File("patient.txt");

    public static void main(String[] args) {
        createFile();
        writePatientDetails();
        readPatientDetails();
    }

    private static void createFile() {
        try {
            if (PATIENT_FILE.createNewFile()) {
                System.out.println("Patient file created successfully.");
            } else {
                System.out.println("Patient file already exists.");
            }
        } catch (IOException exception) {
            System.out.println("Unable to create the patient file.");
            exception.printStackTrace();
        }
    }

    private static void writePatientDetails() {
        try (FileWriter writer = new FileWriter(PATIENT_FILE)) {
            writer.write("Patient ID: 501\n");
            writer.write("Name: Priya Shah\n");
            writer.write("Age: 32\n");
            writer.write("Diagnosis: Seasonal flu\n");
            System.out.println("Patient details written successfully.");
        } catch (IOException exception) {
            System.out.println("Unable to write patient details.");
            exception.printStackTrace();
        }
    }

    private static void readPatientDetails() {
        System.out.println("Patient details:");
        try (Scanner reader = new Scanner(PATIENT_FILE)) {
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to read patient details.");
            exception.printStackTrace();
        }
    }
}
