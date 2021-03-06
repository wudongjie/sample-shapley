package sampleShapley;

import java.util.ArrayList;

public abstract class DistanceFunction {
    protected double distance;
    protected ArrayList<LocationPlayer> orderedLocations;
    public abstract void distance(ArrayList<LocationPlayer> locationPlayers);
    protected double distanceOfTwo(Location a, Location b){
        double earthRadius = 6371; //kilometers
        double dLat = Math.toRadians(b.getLatitude()-a.getLatitude());
        double dLng = Math.toRadians(b.getLongitude()-a.getLongitude());
        double d = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(a.getLatitude())) * Math.cos(Math.toRadians(b.getLatitude())) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(d), Math.sqrt(1-d));
        return earthRadius * c;
    }

    public double getDistance() {
        return distance;
    }
    public ArrayList<LocationPlayer> getOrderedLocations() {
        return orderedLocations;
    }
}
