package assignments.assignment06;

class Outer {
    private final String message = "Welcome to Java Programming";

    class Inner {
        void display() {
            System.out.println("Message: " + message);
        }
    }
}

interface Greeting {
    void sayHello();
}

public class Exercise1 {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.display();

        Greeting greeting = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello from Anonymous Class");
            }
        };
        greeting.sayHello();
    }
}
