import java.util.Random;
import java.util.Scanner;
import mpi.*;

public class App {
    public static void main(String[] args) throws MPIException {
        
        MPI.Init(args);

        int cluster_size = MPI.COMM_WORLD.getSize(); // Retrieve the size of the cluster
        int pi_rank = MPI.COMM_WORLD.getRank(); // Retrieve the rank of the current pi


        final int DEFAULT_ITERATIONS = 50000000;
        final int DEFAULT_ARRAYLENGTH = 960;
        boolean nanoseconds = true;


        int arrayLength = DEFAULT_ARRAYLENGTH;
        int iterations = DEFAULT_ITERATIONS;
        String nano;


        // Commented code is for user input values
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

        if (pi_rank == 0) { // Master node will create the initial array
            for (int i = 0; i < arrayLength; i++) {
                // For random numbers
                // values[i] = r.nextInt(100) + 1;

                // For 1-n numbers
                values[i] = i + 1;
            }
        }

        // The array will be divvied up by the nodes depending on how many nodes you have
        int arraySectionLength = arrayLength / cluster_size; // Determine the length of each section of the array based on the amount of nodes
        int[] section = new int[arraySectionLength]; // Create a new array that is a section of the full array

        // We need to send each node their section of the array
        MPI.COMM_WORLD.scatter(values, arraySectionLength, MPI.INT, section, arraySectionLength, MPI.INT, 0);

        // Each node will make their own ArithmeticTest
        ArithmeticTest exam = new ArithmeticTest(section, nanoseconds);

        // Each node will print our their timed results independently
        exam.toString(iterations, arraySectionLength);

        MPI.Finalize();
    }
}
