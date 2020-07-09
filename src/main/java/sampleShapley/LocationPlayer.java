package sampleShapley;

public class LocationPlayer<T extends Location> extends Player<T> {
    public LocationPlayer(T data) {
        setData(data);
        generateDataOff();
        generateDataOn();
    }

    public LocationPlayer(T data, String name) {
        setData(data);
        generateDataOff();
        generateDataOn();
        this.name = name;
    }
    @Override
    public void generateDataOn() {
        this.dataOn = data;
    }

    @Override
    public void generateDataOff() {
        this.dataOff = (T) new Location(0,0);
    }
}
