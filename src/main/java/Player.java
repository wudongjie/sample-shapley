/**
 * The abstract class Player defines the player in the Shapley computation.
 * The player could be a variable/factor in the regression, could be a pixel in a picture,
 * or it could be a location of a place.
 * The player could cooperate (on) or not cooperate (off) in the Shapley computation.
 * Data held by the player could be changed based on whether the player is on or off.
 * @param <T> type T could be Double[], DoubleColumn, a single value or a lat/long pair.
 */
public abstract class Player<T> {
    protected T data;
    protected T dataOn;
    protected T dataOff;
    protected boolean inUse = true;
    public void on() {
        inUse = true;
    }
    public void off() {
        inUse = false;
    }
    public void setData(T data) {
        this.data = data;
    };
    public abstract void generateDataOff();
    public abstract void generateDataOn();

    /**
     * @return return the dataOn if the player is on,
     * otherwise, return the dataOff
     */
    public T getData() {
        if (inUse) {
            return dataOn;
        } else {
            return dataOff;
        }
    }

    public boolean isInUse() {
        return inUse;
    }
}
