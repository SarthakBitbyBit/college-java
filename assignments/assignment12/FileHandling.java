package assignments.assignment12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandling {
    private static final File FILE = new File("filename.txt");

    public static void main(String[] args) {
        fileCreator();
        fileInfo();
        fileWriter();
        fileReader();
        fileAppend();
        fileReader();
    }

    private static void fileCreator() {
        try {
            if (FILE.createNewFile()) {
                System.out.println("File " + FILE.getName() + " is created successfully.");
            } else {
                System.out.println("File already exists in the directory.");
            }
        } catch (IOException exception) {
            System.out.println("Unable to create the file.");
            exception.printStackTrace();
        }
    }

    private static void fileInfo() {
        if (FILE.exists()) {
            System.out.println("The name of the file is: " + FILE.getName());
            System.out.println("The absolute path of the file is: " + FILE.getAbsolutePath());
            System.out.println("Is the file writable? " + FILE.canWrite());
            System.out.println("Is the file readable? " + FILE.canRead());
            System.out.println("The size of the file in bytes is: " + FILE.length());
        } else {
            System.out.println("The file does not exist.");
        }
    }

    private static void fileWriter() {
        try (FileWriter writer = new FileWriter(FILE)) {
            writer.write("A named location used to store related information is referred to as a file.");
            System.out.println("Content was successfully written to the file.");
        } catch (IOException exception) {
            System.out.println("Unable to write to the file.");
            exception.printStackTrace();
        }
    }

    private static void fileReader() {
        try (Scanner reader = new Scanner(FILE)) {
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to read the file.");
            exception.printStackTrace();
        }
    }

    private static void fileAppend() {
        try (FileWriter writer = new FileWriter(FILE, true)) {
            writer.write(System.lineSeparator()
                    + "Files can also be used to append new information.");
        } catch (IOException exception) {
            System.out.println("Unable to append to the file.");
            exception.printStackTrace();
        }
    }
}
