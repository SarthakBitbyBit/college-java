package assignments.assignment08;

class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    String name;

    Dog(String name) {
        super("Bird");
        this.name = name;
    }

    void display() {
        System.out.println("Parent Name: " + super.name);
        System.out.println("Child Name: " + name);
        super.sound();
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        Dog dog = new Dog("Dog");
        dog.display();
    }
}
