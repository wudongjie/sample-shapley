package sampleShapley;

import org.junit.jupiter.api.Test;
import sampleShapley.*;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryCostTest {
    @Test
    void cost() {
        //Create Three Locations
        Location l0 = new Location(-27.4859423,153.0046048);
        Location l1 = new Location(-27.4905092, 152.9912091);
        Location l2 = new Location(-27.4631724, 153.0171857);
        Location l3 = new Location(-27.4823876,153.0077367);
        Location[] locations = new Location[]{l1,l2,l3};
        LocationPlayer start = new LocationPlayer(l0);
        LocationPlayer[] players = new LocationPlayer[3];// Create Correspondent LocationPlayers
        for (int i=0; i<3; i++) {
            players[i] = new LocationPlayer(locations[i]);
        }
        ShortestDistanceFunction ds = new ShortestDistanceFunction(start); // Create sampleShapley.DistanceFunction
        DeliveryCost deliveryCost = new DeliveryCost(ds);// Initialize sampleShapley.DeliveryCost
        double dist = deliveryCost.cost(players);// run cost()
        System.out.println(ds.getOrderedLocations());
        double[] shapleyValues;
        Shapley shap1 = new Shapley();
        shap1.setCostFunction(new DeliveryCost(ds));
        shap1.setPlayers(players);
        shapleyValues = shap1.computeShapley(false, 1000);
        double sumShap = 0;
        for (double s:shapleyValues) {
            sumShap += s;
        }
        assertEquals(sumShap, dist); //The sum of sampleShapley.Shapley values should be equal to total distance.
    }


}