package sampleShapley;

import smile.regression.LinearModel;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

public class LogLinearGiniCost implements ICostFunction {
    private LinearModel linearModel;
    private String yName;
    public LogLinearGiniCost(LinearModel lm, String yName) {
        this.yName = yName;
        this.linearModel = lm;
    }

    @Override
    public double cost(Player[] players) {
        Table t = Table.create("t1");
        t.addColumns(DoubleColumn.create(yName, linearModel.fittedValues()));
        for (Player p:players) {
            t.addColumns((DoubleColumn) p.getData());
        }
        //System.out.println(t.shape());
        double[] totalCost = linearModel.predict(t.smile().toDataFrame());
        for (int i=0; i<totalCost.length; i++) {
            totalCost[i] = Math.exp(totalCost[i]);
        }
        double gini = Gini.computeGini(totalCost);
        return gini;
    }
}
