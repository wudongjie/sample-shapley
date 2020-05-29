package sampleShapley;

import tech.tablesaw.api.DoubleColumn;

/**
 * The variable player contains a variable/column of data.
 * @param <T> DoubleColumn is used for the data type.
 */
public class VariablePlayer<T extends DoubleColumn> extends Player<T> {
    public VariablePlayer(T data) {
        setData(data);
        generateDataOff();
        generateDataOn();
    }

    /**
     * The mean of the variable is used when data is set "off"
     */
    @Override
    public void generateDataOff() {
        double[] means = new double[data.size()];
        double mean = data.mean();
        for (int i=0; i<means.length; i++) {
            means[i] = mean;
        }
        this.dataOff = (T) T.create(data.name(), means);
    }

    @Override
    public void generateDataOn() {
        this.dataOn = this.data;
    }
}
