import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Q5 {

    private int counter = 0;
    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Q5 example = new Q5();

        Thread thread1 = new Thread(() -> example.incrementCounter());
        Thread thread2 = new Thread(() -> example.incrementCounter());
        Thread thread3 = new Thread(() -> example.incrementCounter());

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final counter value: " + example.getCounter());
    }

    public void incrementCounter() {
        for (int i = 0; i < 1000; i++) {
            lock.lock();
            try {
                counter++;
            } finally {
                lock.unlock();
            }
        }
    }

    public int getCounter() {
        return counter;
    }
}
