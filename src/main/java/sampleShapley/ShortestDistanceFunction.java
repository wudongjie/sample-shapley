package sampleShapley;

import java.util.ArrayList;

public class ShortestDistanceFunction extends DistanceFunction{
    private Location startLocation;

    /**
     * Given a startLocation,
     * initialize the sampleShapley.ShortestDistanceFunction
     * @param startLocation the start location
     */
    public ShortestDistanceFunction(Location startLocation) {
        this.startLocation = startLocation;
    }


    @Override
    public void distance(ArrayList locations) {
        Permutation p = new Permutation(locations.toArray());
        ArrayList<ArrayList> permutationSet = p.getPermutationSet();
        Location start = new Location(startLocation.getLongitude(),startLocation.getLatitude());
        double distance = 10000.0; //Initialize the distance
        ArrayList<Location> bestSeq = new ArrayList<Location>();
        for (int i=0; i<permutationSet.size(); i++) {
            double sumDist = 0;
            ArrayList<Location> seq = permutationSet.get(i);
            for (Location l:seq) {
                sumDist += distanceOfTwo(start, l);
                start = l;
            }
            if (sumDist<distance) {
                distance = sumDist;
                bestSeq = seq;
            }
        }
        this.distance = distance;
        this.orderedLocations = bestSeq;
    }


}
