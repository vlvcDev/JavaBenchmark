import java.util.Random;
import java.util.Scanner;

public class TestController {
    public static void main(String[] args) {
                
        final int DEFAULT_ITERATIONS = 50000000;
        final int DEFAULT_ARRAYLENGTH = 960;
        boolean nanoseconds = true;


        int arrayLength = DEFAULT_ARRAYLENGTH;
        int iterations = DEFAULT_ITERATIONS;
        String nano;


        // Commented code is for user input
        // Scanner s = new Scanner(System.in);

        // System.out.print("Input array length: ");
        // arrayLength = s.nextInt();
        // System.out.print("Input number of iterations: ");
        // iterations = s.nextInt();
        // s.nextLine();
        // System.out.print("Nanoseconds/Milliseconds: (n/m)");
        // nano = s.nextLine();
        // s.close();

        // if (arrayLength <= 0) arrayLength = DEFAULT_ARRAYLENGTH;
        // if (iterations <= 0) iterations = DEFAULT_ITERATIONS;
        // if (nano.equals("m")) nanoseconds = false;

        // Create array and fill with random numbers
        int[] values = new int[arrayLength];
        Random r = new Random();

        for (int i = 0; i < arrayLength; i++) {
            // For random numbers
            // values[i] = r.nextInt(100) + 1;

            // For 1-n numbers
            values[i] = i + 1;
        }

        ArithmeticTest exam = new ArithmeticTest(values, nanoseconds);

        exam.toString(iterations, arrayLength);
    }
}
