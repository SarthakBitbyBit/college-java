package assignments.assignment10;

abstract class Shape {
    abstract void draw();

    void message() {
        System.out.println("Drawing Shape");
    }
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing Circle");
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.message();
        circle.draw();
    }
}
