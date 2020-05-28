import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.Test;
import smile.data.formula.Formula;
import smile.regression.LinearModel;
import smile.regression.OLS;
import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ShapleyTest {
    Table readData(String fileName, ColumnType[] types) throws IOException {
        CsvReadOptions.Builder builder =
                CsvReadOptions.builder(fileName)
                        .separator(',')										// table is comma-delimited
                        .header(true).columnTypes(types);											// with header
        CsvReadOptions options = builder.build();

        Table t1 = Table.read().usingOptions(options);
        return t1;
    }

    @Test
    void computeShapley() throws IOException {
        BasicConfigurator.configure();
        ColumnType[] types = new ColumnType[16];
        for (int i=0; i<16; i++) {
            types[i] = ColumnType.DOUBLE;
        }
        Table Data = readData("I:\\java\\sample-shapley\\src\\test\\java\\2017i.csv", types);
        //DoubleColumn y = (DoubleColumn) X.column(0);
        Table X = Data.dropRowsWithMissingValues();
        DoubleColumn y = (DoubleColumn) X.column(0);
        X.replaceColumn(0, y.logN());
        String yName = X.column(0).name();
        System.out.println(X.structure());
        LinearModel lm = OLS.fit(Formula.lhs(yName), X.smile().toDataFrame());
        System.out.println(lm);
        X.removeColumns(0);
        double[] shapleyValues;
        VariablePlayer[] players = new VariablePlayer[X.columnCount()]; // new define players
        for (int i=0; i<X.columnCount(); i++) {
            players[i] = new VariablePlayer((DoubleColumn) X.column(i));
        }
        Shapley shap1 = new Shapley();
        shap1.setCostFunction(new LinearGiniCost(lm, yName));
        shap1.setPlayers(players);
        shapleyValues = shap1.computeShapley(true, 1000);
        System.out.println(Arrays.toString(shapleyValues));
    }
}