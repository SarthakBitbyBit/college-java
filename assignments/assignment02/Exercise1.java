package assignments.assignment02;

class Student {
    private String name;
    private int rollNumber;

    Student() {
        this("Unknown", 0);
    }

    Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    void display() {
        System.out.println("Roll number: " + rollNumber + ", Name: " + name);
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        Student defaultStudent = new Student();
        Student namedStudent = new Student("Riya", 12);
        defaultStudent.display();
        namedStudent.display();
    }
}
