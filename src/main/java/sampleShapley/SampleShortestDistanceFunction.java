package sampleShapley;

import java.util.ArrayList;

/**
 * Use the sampling method to compute the shortest path to travel all locations.
 */
public class SampleShortestDistanceFunction extends DistanceFunction {
    private LocationPlayer startLocation;
    private int steps;
    public SampleShortestDistanceFunction(LocationPlayer startLocation, int steps) {
        this.startLocation = startLocation;
        this.steps = steps;
    }

    @Override
    public void distance(ArrayList<LocationPlayer> locationPlayers) {
        Permutation p = new Permutation(locationPlayers.toArray());
        ArrayList<ArrayList> permutationSet = p.getSamplePermutationSet(steps);
        LocationPlayer start = startLocation;
        double distance = 10000.0; //Initialize the distance
        ArrayList<LocationPlayer> bestSeq = new ArrayList<LocationPlayer>();
        for (int i = 0; i < permutationSet.size(); i++) {
            double sumDist = 0;
            ArrayList<LocationPlayer> seq = permutationSet.get(i);
            for (LocationPlayer l : seq) {
                sumDist += distanceOfTwo((Location) start.getData(), (Location) l.getData());
                start = l;
            }
            if (sumDist < distance) {
                distance = sumDist;
                bestSeq = seq;
            }
        }
        this.distance = distance;
        this.orderedLocations = bestSeq;
    }
}
