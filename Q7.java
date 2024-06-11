import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Q7 {

    public static void main(String[] args) {
        // Simulate fetching data from two remote servers
        CompletableFuture<String> fetchFromServer1 = CompletableFuture.supplyAsync(() -> {
            sleep(2); // Simulate delay
            return "Data from Server 1";
        });

        CompletableFuture<String> fetchFromServer2 = CompletableFuture.supplyAsync(() -> {
            sleep(3); // Simulate delay
            return "Data from Server 2";
        });

        // Process data from Server 1
        CompletableFuture<String> processedData1 = fetchFromServer1.thenApply(data -> {
            return processData(data);
        });

        // Process data from Server 2
        CompletableFuture<String> processedData2 = fetchFromServer2.thenApply(data -> {
            return processData(data);
        });

        // Combine results from both servers
        CompletableFuture<String> combinedResult = processedData1.thenCombine(processedData2, (data1, data2) -> {
            return data1 + " & " + data2;
        });

        // Display the final combined result
        combinedResult.thenAccept(result -> {
            System.out.println("Combined Result: " + result);
        });

        // Wait for all tasks to complete
        combinedResult.join();
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static String processData(String data) {
        // Simulate data processing
        return "Processed(" + data + ")";
    }
}
