public class Q4 {

    private static final int MAX_NUMBER = 10;
    private final Object lock = new Object();
    private boolean isEvenTurn = true;

    public static void main(String[] args) {
        Q4 printer = new Q4();
        Thread evenThread = new Thread(() -> printer.printEvenNumbers());
        Thread oddThread = new Thread(() -> printer.printOddNumbers());

        evenThread.start();
        oddThread.start();
    }

    public void printEvenNumbers() {
        for (int i = 2; i <= MAX_NUMBER; i += 2) {
            synchronized (lock) {
                while (!isEvenTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Even: " + i);
                isEvenTurn = false;
                lock.notify();
            }
        }
    }

    public void printOddNumbers() {
        for (int i = 1; i <= MAX_NUMBER; i += 2) {
            synchronized (lock) {
                while (isEvenTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Odd: " + i);
                isEvenTurn = true;
                lock.notify();
            }
        }
    }
}
