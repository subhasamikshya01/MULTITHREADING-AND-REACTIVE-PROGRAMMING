import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Q6 {

    private static final int LIMIT = 100; // Upper limit to generate prime numbers
    private static final int THREAD_COUNT = 4; // Number of threads to use

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<List<Integer>>> futures = new ArrayList<>();

        int range = LIMIT / THREAD_COUNT;
        
        // Submit tasks to executor service
        for (int i = 0; i < THREAD_COUNT; i++) {
            int start = i * range + 1;
            int end = (i == THREAD_COUNT - 1) ? LIMIT : (i + 1) * range;
            futures.add(executorService.submit(() -> findPrimesInRange(start, end)));
        }

        List<Integer> primes = new ArrayList<>();
        
        // Collect results from futures
        for (Future<List<Integer>> future : futures) {
            try {
                primes.addAll(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

        System.out.println("Prime numbers up to " + LIMIT + ": " + primes);
    }

    public static List<Integer> findPrimesInRange(int start, int end) {
        List<Integer> primes = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
