package sampleShapley;

import org.junit.jupiter.api.Test;
import sampleShapley.LinearGiniCost;
import sampleShapley.Player;
import sampleShapley.Shapley;
import sampleShapley.ShapleyCsvReader;
import smile.data.formula.Formula;
import smile.regression.LinearModel;
import smile.regression.OLS;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ShapleyTest {
    @Test
    void computeShapley() throws IOException {
        ShapleyCsvReader reader = new ShapleyCsvReader();
        Player[] players = reader.read("src/test/resources/testData.csv");
        DoubleColumn y = reader.getY();
        Table X = reader.getX();
        X.addColumns(y);
        LinearModel lm = OLS.fit(Formula.lhs("y"), X.smile().toDataFrame());
        System.out.println(lm);
        X.removeColumns("y");
        double[] shapleyValues;
        Shapley shap1 = new Shapley();
        shap1.setCostFunction(new LinearGiniCost(lm, "y"));
        shap1.setPlayers(players);
        shapleyValues = shap1.computeShapley(true, 1000);
        assertEquals(10, shapleyValues.length);
    }
}