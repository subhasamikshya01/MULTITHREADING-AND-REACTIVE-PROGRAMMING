public class Q3 {

    // Matrix dimensions
    private static final int ROWS = 4; // Number of rows in the matrices
    private static final int COLS = 4; // Number of columns in the matrices

    // Thread class to handle the multiplication of one row of the result matrix
    static class RowMultiplier extends Thread {
        private int[][] A, B, C;
        private int row;

        public RowMultiplier(int[][] A, int[][] B, int[][] C, int row) {
            this.A = A;
            this.B = B;
            this.C = C;
            this.row = row;
        }

        @Override
        public void run() {
            for (int j = 0; j < COLS; j++) {
                C[row][j] = 0;
                for (int k = 0; k < COLS; k++) {
                    C[row][j] += A[row][k] * B[k][j];
                }
            }
        }
    }

    public static void main(String[] args) {
        // Initialize matrices A and B with example values
        int[][] A = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };

        int[][] B = {
            {17, 18, 19, 20},
            {21, 22, 23, 24},
            {25, 26, 27, 28},
            {29, 30, 31, 32}
        };

        // Result matrix C
        int[][] C = new int[ROWS][COLS];

        // Array to hold threads
        Thread[] threads = new Thread[ROWS];

        // Create and start threads
        for (int i = 0; i < ROWS; i++) {
            threads[i] = new RowMultiplier(A, B, C, i);
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < ROWS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the result matrix
        System.out.println("Result matrix C is:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}
