import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

public class Q9 {
    
    private static final int NUM_THREADS = 4; // Number of threads to use
    private static final String FILE_PATH = "path/to/your/large/file.txt"; // Path to the large text file
    
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> wordCountMap = new ConcurrentHashMap<>();
        
        // Create an array of threads
        Thread[] threads = new Thread[NUM_THREADS];
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            // Get total number of lines in the file
            int totalLines = getTotalLines(FILE_PATH);
            // Calculate lines to be processed by each thread
            int linesPerThread = totalLines / NUM_THREADS;
            
            // Create and start threads
            for (int i = 0; i < NUM_THREADS; i++) {
                final int threadNumber = i;
                threads[i] = new Thread(() -> processLines(wordCountMap, threadNumber, linesPerThread));
                threads[i].start();
            }
            
            // Wait for all threads to finish
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        // Calculate total word count
        int totalWordCount = wordCountMap.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total word count: " + totalWordCount);
    }
    
    private static void processLines(ConcurrentHashMap<String, Integer> wordCountMap, int threadNumber, int linesPerThread) {
        String line;
        int lineNumber = 0;
        int startLine = threadNumber * linesPerThread;
        int endLine = startLine + linesPerThread;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            // Skip lines until the startLine
            while ((line = reader.readLine()) != null && lineNumber < startLine) {
                lineNumber++;
            }
            
            // Process lines from startLine to endLine
            while ((line = reader.readLine()) != null && lineNumber < endLine) {
                StringTokenizer tokenizer = new StringTokenizer(line, " ,.!?;:\"'()-");
                while (tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken().toLowerCase();
                    wordCountMap.merge(word, 1, Integer::sum); // Update word count in ConcurrentHashMap
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static int getTotalLines(String filePath) throws IOException {
        int totalLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                totalLines++;
            }
        }
        return totalLines;
    }
}
