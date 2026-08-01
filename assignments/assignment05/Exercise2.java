package assignments.assignment05;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("Database Management");

        System.out.print("Enter a course to add: ");
        courses.add(scanner.nextLine());
        System.out.print("Enter a course to remove: ");
        courses.remove(scanner.nextLine());

        StringBuffer registeredCourses = new StringBuffer("Registered courses:\n");
        for (String course : courses) {
            registeredCourses.append("- ").append(course).append('\n');
        }
        System.out.print(registeredCourses);
    }
}
