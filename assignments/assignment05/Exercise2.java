package assignments.assignment05;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("Database Management");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("\n1. Add course");
                System.out.println("2. Remove course");
                System.out.println("3. View registered courses");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                switch (scanner.nextLine().trim()) {
                    case "1":
                        System.out.print("Enter course to add: ");
                        String courseToAdd = scanner.nextLine().trim();
                        if (courseToAdd.isEmpty()) {
                            System.out.println("Course name cannot be empty.");
                        } else if (courses.contains(courseToAdd)) {
                            System.out.println("Course is already registered.");
                        } else {
                            courses.add(courseToAdd);
                            System.out.println("Course added.");
                        }
                        break;
                    case "2":
                        System.out.print("Enter course to remove: ");
                        String courseToRemove = scanner.nextLine().trim();
                        if (courses.remove(courseToRemove)) {
                            System.out.println("Course removed.");
                        } else {
                            System.out.println("Course not found.");
                        }
                        break;
                    case "3":
                        displayCourses(courses);
                        break;
                    case "4":
                        running = false;
                        break;
                    default:
                        System.out.println("Choose an option from 1 to 4.");
                }
            }
        }
    }

    private static void displayCourses(ArrayList<String> courses) {
        StringBuffer registeredCourses = new StringBuffer("Registered courses:\n");
        if (courses.isEmpty()) {
            registeredCourses.append("No courses registered.\n");
        } else {
            for (String course : courses) {
                registeredCourses.append("- ").append(course).append('\n');
            }
        }
        System.out.print(registeredCourses);
    }
}
