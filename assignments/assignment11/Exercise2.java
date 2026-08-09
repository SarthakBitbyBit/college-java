package assignments.assignment11;

interface Printable {
    void printDetails();
}

class Student implements Printable {
    @Override
    public void printDetails() {
        System.out.println("Student: Anaya, Roll number: 21");
    }
}

class Employee implements Printable {
    @Override
    public void printDetails() {
        System.out.println("Employee: Rahul, ID: 101");
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        Printable[] records = {new Student(), new Employee()};
        for (Printable record : records) {
            record.printDetails();
        }
    }
}
