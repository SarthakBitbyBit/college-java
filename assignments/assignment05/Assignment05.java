package assignments.assignment05;

import java.util.ArrayList;
import java.util.Vector;

public class Assignment05 {
    public static void main(String[] args) {
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("Database");
        courses.add("Operating Systems");
        courses.remove("Database");

        Vector<String> tasks = new Vector<>();
        tasks.add("Complete assignment");
        tasks.add("Revise constructors");

        StringBuffer output = new StringBuffer("Courses: ");
        for (String course : courses) {
            output.append(course).append(" ");
        }
        output.append("\nTo-do tasks: ").append(tasks);

        System.out.println(output);
    }
}
