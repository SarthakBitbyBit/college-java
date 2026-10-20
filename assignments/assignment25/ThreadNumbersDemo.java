public class ThreadNumbersDemo {
    static class NumberPrinter extends Thread {
        private final int start;
        private final int end;

        public NumberPrinter(String name, int start, int end) {
            super(name);
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                System.out.println(getName() + " printed: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("demonstrating thread lifecycle with thread class");

        NumberPrinter t1 = new NumberPrinter("thread-1", 1, 5);
        NumberPrinter t2 = new NumberPrinter("thread-2", 6, 10);
        NumberPrinter t3 = new NumberPrinter("thread-3", 11, 15);

        System.out.println(t1.getName() + " state before start: " + t1.getState());
        System.out.println(t2.getName() + " state before start: " + t2.getState());
        System.out.println(t3.getName() + " state before start: " + t3.getState());

        t1.start();
        t2.start();
        t3.start();

        System.out.println(t1.getName() + " state after start: " + t1.getState());
        System.out.println(t2.getName() + " state after start: " + t2.getState());
        System.out.println(t3.getName() + " state after start: " + t3.getState());

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("main thread interrupted");
        }

        System.out.println(t1.getName() + " state after completion: " + t1.getState());
        System.out.println(t2.getName() + " state after completion: " + t2.getState());
        System.out.println(t3.getName() + " state after completion: " + t3.getState());
        System.out.println("all number printer threads have completed");
    }
}
