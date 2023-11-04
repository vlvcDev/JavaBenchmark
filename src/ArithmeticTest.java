import java.text.DecimalFormat;

public class ArithmeticTest {

    private long avgAdditionTime;
    private long addMinTime = 0;
    private long addMaxTime = 0;
    private long addTotalRunTime = 0;
    private long avgSubtractionTime;
    private long subtractMinTime = 0;
    private long subtractMaxTime = 0;
    private long subtractTotalRunTime = 0;
    private long avgMultiplicationTime;
    private long multiplyMinTime = 0;
    private long multiplyMaxTime = 0;
    private long multiplyTotalRunTime = 0;
    private long avgDivisionTime;
    private long divisionMinTime = 0;
    private long divisionMaxTime = 0;
    private long divisionTotalRunTime = 0;
    private long currentRuntime;
    private boolean nanoseconds;

    private int[] values;

    DecimalFormat df = new DecimalFormat("#.00");

    public ArithmeticTest(int[] values, boolean nanoseconds) {
        this.values = values;
        this.nanoseconds = nanoseconds;
    }

    private long add() {
        long start;
        int k = 0;
        if (nanoseconds) {
            start = System.nanoTime();
            for (int i = 0; i < this.values.length; i++) {
                k += values[i];
            }
            return System.nanoTime() - start;
        }
        start = System.currentTimeMillis();
        for (int i = 0; i < this.values.length; i++) {
            k += values[i];
        }
        return System.currentTimeMillis() - start;
    }    

    private long subtract() {
        long start;
        int k = 0;
        if (nanoseconds) {
            start = System.nanoTime();
            for (int i = this.values.length - 1; i > 0; i--) {
                k -= values[i];
            }
            return System.nanoTime() - start;
        }
        start = System.currentTimeMillis();
        for (int i = this.values.length - 1; i > 0; i--) {
            k -= values[i];
        }
        return System.currentTimeMillis() - start;
    }

    private long multiply() {
        long start;
        int k = 0;
        if (nanoseconds) {
            start = System.nanoTime();
            for (int i = 0; i < this.values.length; i++) {
            k *= values[i];
            }
            return System.nanoTime() - start;
        }
        start = System.currentTimeMillis();
        for (int i = 0; i < this.values.length; i++) {
            k *= values[i];
        }
        return System.currentTimeMillis() - start;
    }

    private long divide() {
        long start;
        int k = 0;
        if (nanoseconds){
            start = System.nanoTime();
            for (int i = this.values.length - 1; i > 0; i--) {
                k /= values[i];
            }
            return System.nanoTime() - start;
        }
        start = System.currentTimeMillis();
        for (int i = this.values.length - 1; i > 0; i--) {
            k /= values[i];
        }
        return System.currentTimeMillis() - start;
    }

    private void addTest(int iterations) {
        addMinTime = add();
        for (int i = 0; i < iterations; i++) {
            currentRuntime = add();
            if (currentRuntime > addMaxTime) addMaxTime = currentRuntime;
            if (currentRuntime < addMinTime) addMinTime = currentRuntime;

            addTotalRunTime += currentRuntime;
        }
        avgAdditionTime = addTotalRunTime / iterations;
    }
    
    private void subtractTest(int iterations) {
        addMinTime = subtract();
        for (int i = 0; i < iterations; i++) {
            currentRuntime = subtract();
            if (currentRuntime > subtractMaxTime) subtractMaxTime = currentRuntime;
            if (currentRuntime < subtractMinTime) subtractMinTime = currentRuntime;

            subtractTotalRunTime += currentRuntime;
        }
        avgSubtractionTime = subtractTotalRunTime / iterations;
    }

    private void multiplyTest(int iterations) {
        multiplyMinTime = multiply();
        for (int i = 0; i < iterations; i++) {
            currentRuntime = multiply();
            if (currentRuntime > multiplyMaxTime) multiplyMaxTime = currentRuntime;
            if (currentRuntime < multiplyMinTime) multiplyMinTime = currentRuntime;
            multiplyTotalRunTime += currentRuntime;
        }
        avgMultiplicationTime = multiplyTotalRunTime / iterations;
    }

    private void divideTest(int iterations) {
        divisionMinTime = divide();
        for (int i = 0; i < iterations; i++) {
            currentRuntime = divide();
            if (currentRuntime > divisionMaxTime) divisionMaxTime = currentRuntime;
            if (currentRuntime < divisionMinTime) divisionMinTime = currentRuntime;
            divisionTotalRunTime += currentRuntime;
        }
        avgDivisionTime = divisionTotalRunTime / iterations;
    }

    public void toString(int iterations, int arrayLength) {
        multiplyTest(iterations);
        divideTest(iterations);
        addTest(iterations);
        subtractTest(iterations);

        String prefix = (nanoseconds) ? "ns" : "ms";
        System.out.println("\n+--------------------------------------");
        System.out.printf("| Array Length: %d %n", arrayLength);
        System.out.printf("| Number of Iterations: %d %n", iterations);
        System.out.println("+--------------------------------------");        
        System.out.printf("| Min Multiplication Time: %d " + prefix + "%n", multiplyMinTime);
        System.out.printf("| Max Multiplication Time: %d " + prefix + "%n", multiplyMaxTime);
        System.out.printf("| Avg Multiplication Time: %d " + prefix + "%n", avgMultiplicationTime);
        System.out.printf("| Total Multiplication Time: %d " + prefix + "%n", multiplyTotalRunTime);
        System.out.println("+--------------------------------------");
        System.out.printf("| Min Division Time: %d " + prefix + "%n", divisionMinTime);
        System.out.printf("| Max Division Time: %d " + prefix + "%n", divisionMaxTime);
        System.out.printf("| Avg Division Time: %d " + prefix + "%n", avgDivisionTime);
        System.out.printf("| Total Division Time: %d " + prefix + "%n", divisionTotalRunTime);
        System.out.println("+--------------------------------------");
        System.out.printf("| Min Addition Time: %d " + prefix + "%n", addMinTime);
        System.out.printf("| Max Addition Time: %d " + prefix + "%n", addMaxTime);
        System.out.printf("| Avg Addition Time: %d " + prefix + "%n", avgAdditionTime);
        System.out.printf("| Total Addition Time: %d " + prefix + "%n", addTotalRunTime);
        System.out.println("+--------------------------------------");
        System.out.printf("| Min Subtraction Time: %d " + prefix + "%n", subtractMinTime);
        System.out.printf("| Max Subtraction Time: %d " + prefix + "%n", subtractMaxTime);
        System.out.printf("| Avg Subtraction Time: %d " + prefix + "%n", avgSubtractionTime);
        System.out.printf("| Total Subtraction Time: %d " + prefix + "%n", subtractTotalRunTime);
        System.out.println("+--------------------------------------");
        System.out.printf("| Total Time Elapsed: %d " + prefix + "%n", subtractTotalRunTime + addTotalRunTime + divisionTotalRunTime + multiplyTotalRunTime);
        if (multiplyTotalRunTime > addTotalRunTime) System.out.println("| Multiplication took " + df.format((double)multiplyTotalRunTime/addTotalRunTime) + "x longer than Addition");
        else System.out.println("| Addition took " + df.format((double)addTotalRunTime/multiplyTotalRunTime) + "x longer than Multiplication");
        if (divisionTotalRunTime > subtractTotalRunTime) System.out.println("| Division took " + df.format((double)divisionTotalRunTime/subtractTotalRunTime) + "x longer than Subtraction");
        else System.out.println("| Subtraction took " + df.format((double)subtractTotalRunTime/divisionTotalRunTime) + "x longer than Division");
        System.out.println("+--------------------------------------");
    }
}
