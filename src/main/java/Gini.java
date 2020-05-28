import java.util.Arrays;

/**
 * The
 */
public class Gini {
    private static double[] cumsum(double[] columnData) {
        double csum = 0.0;
        double[] col = columnData.clone();

        for (int i = 0; i < columnData.length; i++) {
            double value = columnData[i];

            if (!Double.isNaN(value)) {
                csum += value;
                col[i] = csum;
            }
            else {
                col[i] = csum;
            }
        }
        return col;
    }

    private static double sum(double[] columnData) {
        double sum = 0.0;
        double[] col = columnData.clone();

        for (int i=0; i<columnData.length; i++) {
            double value = columnData[i];
            if (!Double.isNaN(value)) {
                sum += value;
            }
        }
        return sum;
    }

    public static double computeGini(double[] columnData) {
        int n = columnData.length;
        Arrays.sort(columnData);
        double[] cumx = cumsum(columnData);
        double gini = (n + 1 - 2 * sum(cumx) / cumx[cumx.length-1]) / n;
        return gini;
    }
}
