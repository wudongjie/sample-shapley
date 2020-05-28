import smile.regression.LinearModel;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

public class LinearGiniCost implements  ICostFunction{
    private LinearModel linearModel;
    private String yName;
    public LinearGiniCost(LinearModel lm, String yName) {
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
        double gini = Gini.computeGini(totalCost);
        return gini;
    }
}
