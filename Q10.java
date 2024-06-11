import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Q10 {

    private static final int NUM_THREADS = 4; // Number of threads to use
    private static final String[] IMAGE_PATHS = {"image1.jpg", "image2.jpg", "image3.jpg"}; // Paths to the images

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        // Submit tasks to process each image
        for (String imagePath : IMAGE_PATHS) {
            executorService.submit(() -> processImage(imagePath));
        }

        // Shutdown the executor service
        executorService.shutdown();
    }

    private static void processImage(String imagePath) {
        // Apply image processing operations to the image
        System.out.println("Processing image: " + imagePath + " on thread: " + Thread.currentThread().getName());
        // Add your image processing logic here
    }
}
