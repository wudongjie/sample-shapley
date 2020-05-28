import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PermutationTest {
    private static final long MEGABYTE = 1024L * 1024L;
    String[] s0 = new String[] {"A", "B", "C"};
    Integer[] s1 = new Integer[] {1, 2, 3, 4,5,6,7,8,9,10};
    Permutation p0 = new Permutation(s0);
    Permutation p1 = new Permutation(s1);

    @org.junit.jupiter.api.Test
    void swap() {
    }

    @org.junit.jupiter.api.Test
    void getPermutationSet() {
        float startTime = System.currentTimeMillis();
        //System.out.println(p1.getPermutationSet());
        //p1.getPermutationSet();
        System.out.println(p1.getSamplePermutationSet(1000));
        float endTime = System.currentTimeMillis();
        System.out.println("The computational time consumption is: " + (endTime - startTime));
        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: "
                + memory/MEGABYTE);
    }

}