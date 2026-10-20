public class RunnableTasksDemo {
    static class TaskA implements Runnable {
        private final int count;

        public TaskA(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 1; i <= count; i++) {
                System.out.println("task-a processing item: " + i);
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("task-a completed all items");
        }
    }

    static class TaskB implements Runnable {
        private final int count;

        public TaskB(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 1; i <= count; i++) {
                System.out.println("task-b computing batch: " + i);
                try {
                    Thread.sleep(110);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("task-b completed all batches");
        }
    }

    public static void main(String[] args) {
        System.out.println("demonstrating concurrent tasks using runnable interface");

        Runnable taskA = new TaskA(5);
        Runnable taskB = new TaskB(5);

        Thread threadA = new Thread(taskA, "worker-a");
        Thread threadB = new Thread(taskB, "worker-b");

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            System.out.println("main thread interrupted");
        }

        System.out.println("both runnable tasks have finished execution successfully");
    }
}
