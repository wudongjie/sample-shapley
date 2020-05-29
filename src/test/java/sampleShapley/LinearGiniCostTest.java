package sampleShapley;

import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.Test;
import sampleShapley.LinearGiniCost;
import sampleShapley.VariablePlayer;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.regression.LinearModel;
import smile.regression.OLS;
import tech.tablesaw.api.DoubleColumn;

import static org.junit.jupiter.api.Assertions.*;

class LinearGiniCostTest {

    @Test
    void cost() {
        double[][] data = {{15.0, 8.0, 3.4},
                           {9.1, 4.5, 2.3},
                           {11.0, 5.1, 2.9},
                           {19.0, 9.2, 4.3},
                           {4.8, 2.1, 1.0},
                           {7.9, 4.0, 2.1},
                           {5.6, 2.8, 1.5},
                           {7.3, 3.6, 1.7}};
        DataFrame df = DataFrame.of(data, "y", "x1", "x2");
        LinearModel lm = OLS.fit(Formula.lhs("y"), df);
        System.out.println(lm);
        LinearGiniCost linearGiniCost= new LinearGiniCost(lm, "y");
        VariablePlayer v1 = new VariablePlayer(DoubleColumn.create("x1", df.column("x1").toDoubleArray()));
        VariablePlayer v2 = new VariablePlayer(DoubleColumn.create("x2", df.column("x2").toDoubleArray()));
        VariablePlayer[] players = {v1,v2};
        double cost = Precision.round(linearGiniCost.cost(players), 4);
        double expected = 0.2516;
        assertEquals(expected, cost);
    }
}