package assignments.assignment09;

final class College {
    private final int collegeId = 101;

    final void display() {
        System.out.println("College ID: " + collegeId);
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        College college = new College();
        college.display();
        System.out.println("The final variable, method, and class cannot be changed.");
    }
}
