package sampleShapley;

import java.util.ArrayList;


/**
 * The cost function (shortest distance) of traversing n Locations from the start location.
 */
public class DeliveryCost implements  ICostFunction{
    private DistanceFunction distanceFunction;
    private double distance;
    public DeliveryCost(DistanceFunction distanceFunction) {
        this.distanceFunction = distanceFunction;
    }

    @Override
    public double cost(Player[] players) {
        // players must be sampleShapley.Location player.
        if (!(players[0].getData() instanceof Location)) {
            throw new ClassFormatError("Players should contain sampleShapley.Location");
        }
        ArrayList locationOn = new ArrayList();
        for (Player p:players) {
            if (p.isInUse()) {
                locationOn.add(p);
            }
        }
        distanceFunction.distance(locationOn);
        this.distance = distanceFunction.getDistance();
        return this.distance;
    }
}
