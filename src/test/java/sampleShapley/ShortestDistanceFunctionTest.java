package sampleShapley;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

class ShortestDistanceFunctionTest {

    @Test
    void distance() {
        Location l0 = new Location(-27.4859423,153.0046048);
        Location l1 = new Location(-27.4905092, 152.9912091);
        Location l2 = new Location(-27.4631724, 153.0171857);
        Location l3 = new Location(-27.4823876,153.0077367);
        Location l4 = new Location(-27.4800412, 152.9887013);
        Location l5 = new Location(-27.4711134, 152.9718302);
        Location l6 = new Location(-27.4912345, 153.0112345);
        Location l7 = new Location(-27.4624923, 152.1102938);
        Location l8 = new Location(-27.4523183, 153.02192384);
        ArrayList<Location> locations1 = new ArrayList<Location>();
        locations1.addAll(Arrays.asList(l1,l2,l3));
        ArrayList<Location> locations2 = new ArrayList<Location>();
        locations2.addAll(Arrays.asList(l1,l2,l3,l4,l5,l6,l7,l8));
        ShortestDistanceFunction ds = new ShortestDistanceFunction(l0); // Create sampleShapley.DistanceFunction
        SampleShortestDistanceFunction ds2 = new SampleShortestDistanceFunction(l0, 1000);
        ds.distance(locations1);
        ds2.distance(locations2);
        System.out.println(ds.getDistance());
        System.out.println(ds2.getDistance());
    }
}