package assignments.assignment08;

class Person {
    protected final String name;

    Person(String name) {
        this.name = name;
    }

    void introduce() {
        System.out.println("Person: " + name);
    }
}

class Student extends Person {
    private final int rollNumber;

    Student(String name, int rollNumber) {
        super(name);
        this.rollNumber = rollNumber;
    }

    void display() {
        super.introduce();
        System.out.println("Roll number: " + rollNumber);
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        new Student("Anaya", 21).display();
    }
}
