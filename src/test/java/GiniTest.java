import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GiniTest {

    @Test
    void computeGini() {
        double[] income1 = new double[]{1000,2000,3000,4000,5000,6000,7000};
        double[] income2 = new double[]{9,3,7,100,50,3000,400};
        double gini1 = Gini.computeGini(income1);
        double gini2 = Gini.computeGini(income2);
        double result1 = 0.2857;
        double result2 = 0.7900;
        gini1 = Precision.round(gini1, 4);
        gini2 = Precision.round(gini2, 4);
        assertEquals(gini1, result1);
        assertEquals(gini2, result2);
        System.out.println(gini1);
        System.out.println(gini2);
    }
}