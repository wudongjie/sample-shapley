package sampleShapley;

import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.Test;
import sampleShapley.VariablePlayer;
import tech.tablesaw.api.DoubleColumn;

import static org.junit.jupiter.api.Assertions.*;

class VariablePlayerTest {
    @Test
    void getData() {
        double[] d = new double[]{1.0, 3.2, 5.9, 8.7, 6.4, 2.3};
        double mean_d = 0;
        for (double i:d) {
            mean_d += i;
        }
        mean_d = Precision.round(mean_d/d.length,4);
        DoubleColumn doubleColumn = DoubleColumn.create("test1", d);
        VariablePlayer variablePlayer = new VariablePlayer(doubleColumn);
        variablePlayer.on();
        DoubleColumn onData = (DoubleColumn) variablePlayer.getData();
        assertArrayEquals(onData.asDoubleArray(), d);
        variablePlayer.off();
        DoubleColumn offData = (DoubleColumn) variablePlayer.getData();
        assertEquals(offData.get(3), offData.get(5));
        assertEquals(Precision.round(offData.get(3), 4), mean_d);
    }
}