import java.util.ArrayList;

public class DeliveryCost implements  ICostFunction{
    private DistanceFunction distanceFunction;
    private double distance;
    public DeliveryCost(DistanceFunction distanceFunction) {
        this.distanceFunction = distanceFunction;
    }

    @Override
    public double cost(Player[] players) {
        // players must be Location player.
        if (!(players[0].getData() instanceof Location)) {
            throw new ClassFormatError("Players should contain Location");
        }
        ArrayList locationOn = new ArrayList();
        for (Player p:players) {
            if (p.isInUse()) {
                locationOn.add(p.getData());
            }
        }
        distanceFunction.distance(locationOn);
        this.distance = distanceFunction.getDistance();
        return this.distance;
    }
}
