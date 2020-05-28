import org.junit.jupiter.api.Test;
import tech.tablesaw.api.DoubleColumn;

import static org.junit.jupiter.api.Assertions.*;

class VariablePlayerTest {
    @Test
    void getData() {
        double[] d = new double[]{1.0, 3.2, 5.9, 8.7, 6.4, 2.3};
        DoubleColumn doubleColumn = DoubleColumn.create("test1", d);
        VariablePlayer variablePlayer = new VariablePlayer(doubleColumn);
        variablePlayer.on();
        DoubleColumn onData = (DoubleColumn) variablePlayer.getData();
        System.out.println(onData.print());
        variablePlayer.off();
        DoubleColumn offData = (DoubleColumn) variablePlayer.getData();
        System.out.println(offData.print());
    }
}