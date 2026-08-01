package assignments.assignment07;

interface Measurable {
    double area();
}

class Shape {
    void display() {
        System.out.println("This is a shape.");
    }
}

class Circle extends Shape implements Measurable {
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape implements Measurable {
    private final double length;
    private final double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double area() {
        return length * width;
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 3);
        circle.display();
        System.out.println("Circle area: " + circle.area());
        rectangle.display();
        System.out.println("Rectangle area: " + rectangle.area());
    }
}
