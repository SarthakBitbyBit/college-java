package assignments.assignment05;

import java.util.ArrayList;

public class Exercise1 {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("Complete Java assignment");
        tasks.add("Revise constructors");
        tasks.add("Practice collections");

        StringBuffer output = new StringBuffer("To-Do List:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1).append(". ").append(tasks.get(i)).append('\n');
        }
        System.out.print(output);
    }
}
