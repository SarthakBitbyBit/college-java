package assignments.assignment07;

interface Product {
    void showDetails();
}

class Shape {
    void display() {
        System.out.println("This is a shape.");
    }
}

class Circle extends Shape implements Product {
    private double radius = 5;

    public void showDetails() {
        System.out.println("Circle area: " + (Math.PI * radius * radius));
    }
}

class Rectangle extends Shape implements Product {
    private double length = 4;
    private double width = 3;

    public void showDetails() {
        System.out.println("Rectangle area: " + (length * width));
    }
}

public class Assignment07 {
    public static void main(String[] args) {
        Product circle = new Circle();
        Product rectangle = new Rectangle();
        circle.showDetails();
        rectangle.showDetails();
    }
}
