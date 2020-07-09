package sampleShapley;

import org.apache.log4j.BasicConfigurator;
import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;
import tech.tablesaw.io.csv.CsvReader;

import java.io.IOException;

/**
 * The ShapleyCsvReader class reads data from external CSV tables
 * and generates players for the Shapley computation.
 */
public class ShapleyCsvReader implements ShapleyReader {
    private DoubleColumn y;
    private Table X;
    @Override
    public Player[] read(String fileName) throws IOException {
        BasicConfigurator.configure();
        CsvReadOptions.Builder builder =
                CsvReadOptions.builder(fileName)
                        .separator(',')             // table is comma-delimited
                        .header(true);              // with header
        CsvReadOptions options = builder.build();
        CsvReader csvReader = new CsvReader();
        String typeCount = csvReader.printColumnTypes(options);
        ColumnType[] types = new ColumnType[typeCount.split(",").length - 1];
        for (int i=0; i<types.length;i++) {
            types[i] = ColumnType.DOUBLE;
        }
        CsvReadOptions.Builder builder2 = CsvReadOptions.builder(fileName)
                .separator(',')             // table is comma-delimited
                .header(true).columnTypes(types);             // with header
        options = builder2.build();
        Table Data = Table.read().usingOptions(options);
        Table X = Data.dropRowsWithMissingValues();
        y = (DoubleColumn) X.column(0);
        X.removeColumns(0);
        this.X = X;
        VariablePlayer[] players = new VariablePlayer[X.columnCount()]; // new define players
        for (int i=0; i<X.columnCount(); i++) {
            players[i] = new VariablePlayer((DoubleColumn) X.column(i));
        }
        return players;
    }

    public DoubleColumn getY() {
        return y;
    }

    public Table getX() {
        return X;
    }
}
