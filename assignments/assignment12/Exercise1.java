package assignments.assignment12;

class StudentDetails {
    private final int rollNumber;
    private final String name;
    private final String course;

    StudentDetails(int rollNumber, String name, String course) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.course = course;
    }

    void display() {
        System.out.println("Student roll number: " + rollNumber);
        System.out.println("Student name: " + name);
        System.out.println("Student course: " + course);
    }
}

class FacultyDetails {
    private final int employeeId;
    private final String name;
    private final String subject;

    FacultyDetails(int employeeId, String name, String subject) {
        this.employeeId = employeeId;
        this.name = name;
        this.subject = subject;
    }

    void display() {
        System.out.println("Faculty employee ID: " + employeeId);
        System.out.println("Faculty name: " + name);
        System.out.println("Faculty subject: " + subject);
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        StudentDetails student = new StudentDetails(21, "Anaya Sharma", "Computer Science");
        FacultyDetails faculty = new FacultyDetails(101, "Dr. Rahul Mehta", "Java Programming");

        student.display();
        faculty.display();
    }
}
