package sampleShapley;

import java.util.ArrayList;

/**
 * Given a start location, compute the shortest path to traverse all locations.
 */
public class ShortestDistanceFunction extends DistanceFunction{
    private LocationPlayer startLocation;

    /**
     * Given a startLocation,
     * initialize the sampleShapley.ShortestDistanceFunction
     * @param startLocation the start location
     */
    public ShortestDistanceFunction(LocationPlayer startLocation) {
        this.startLocation = startLocation;
    }


    @Override
    public void distance(ArrayList<LocationPlayer> locationPlayers) {
        Permutation p = new Permutation(locationPlayers.toArray());
        ArrayList<ArrayList> permutationSet = p.getPermutationSet();
        LocationPlayer start = startLocation;
        double distance = 10000.0; //Initialize the distance
        ArrayList<LocationPlayer> bestSeq = new ArrayList<>();
        for (int i=0; i<permutationSet.size(); i++) {
            double sumDist = 0;
            ArrayList<LocationPlayer> seq = permutationSet.get(i);
            for (LocationPlayer l:seq) {
                sumDist += distanceOfTwo((Location) start.getData(), (Location) l.getData());
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
