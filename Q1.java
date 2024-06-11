// Import necessary packages
import java.util.Scanner;

// Define the main class
public class Q1 {

    // Define a nested class for addition
    static class Addition extends Thread {
        private double num1, num2;
        
        Addition(double num1, double num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        public void run() {
            System.out.println("Addition result: " + (num1 + num2));
        }
    }

    // Define a nested class for subtraction
    static class Subtraction extends Thread {
        private double num1, num2;

        Subtraction(double num1, double num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        public void run() {
            System.out.println("Subtraction result: " + (num1 - num2));
        }
    }

    // Define a nested class for multiplication
    static class Multiplication extends Thread {
        private double num1, num2;

        Multiplication(double num1, double num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        public void run() {
            System.out.println("Multiplication result: " + (num1 * num2));
        }
    }

    // Define a nested class for division
    static class Division extends Thread {
        private double num1, num2;

        Division(double num1, double num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        public void run() {
            if (num2 != 0) {
                System.out.println("Division result: " + (num1 / num2));
            } else {
                System.out.println("Error: Division by zero is not allowed.");
            }
        }
    }

    // Main method to run the calculator
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for input
        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();

        // Close the scanner
        scanner.close();

        // Create and start threads for each operation
        Thread additionThread = new Addition(num1, num2);
        Thread subtractionThread = new Subtraction(num1, num2);
        Thread multiplicationThread = new Multiplication(num1, num2);
        Thread divisionThread = new Division(num1, num2);

        additionThread.start();
        subtractionThread.start();
        multiplicationThread.start();
        divisionThread.start();

        // Wait for all threads to finish
        try {
            additionThread.join();
            subtractionThread.join();
            multiplicationThread.join();
            divisionThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
