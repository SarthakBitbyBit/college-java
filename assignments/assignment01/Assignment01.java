package assignments.assignment01;

public class Assignment01 {
    static int square(int number) {
        return number * number;
    }

    public static void main(String[] args) {
        String studentName = "Aarav";
        int marks = 85;

        System.out.println("Student: " + studentName);
        System.out.println("Marks: " + marks);
        System.out.println("Square of marks: " + square(marks));
    }
}
