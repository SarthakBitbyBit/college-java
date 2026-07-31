package assignments.assignment02;

class Student {
    String name;
    int rollNumber;

    Student() {
        name = "Unknown";
        rollNumber = 0;
    }

    Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    Student(Student other) {
        this.name = other.name;
        this.rollNumber = other.rollNumber;
    }

    void display() {
        System.out.println(rollNumber + " - " + name);
    }
}

public class Assignment02 {
    public static void main(String[] args) {
        Student first = new Student();
        Student second = new Student("Riya", 12);
        Student copy = new Student(second);

        first.display();
        second.display();
        copy.display();
    }
}
