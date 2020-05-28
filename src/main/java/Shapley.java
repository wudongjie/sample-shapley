import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * This is a class for computing Shapley Value.
 * The Shapley value is originally introduced by Shapley(1951) to solve the cooperative game problem.
 * It can also be applied in many other situations, e.g. Transportation, machine learning.
 * This project provides two ways to compute the Shapley value (non-sampling / sampling)
 * Non-sampling is computational expensive with n! steps, while sampling massively reduce the computational complexity.
 * In addition, we provide several cost functions in line with different use cases.
 *
 * @author Dongjie Wu
 * @email d.wu2@uq.edu.au
 * @version 1.0.1
 */
public class Shapley {
    private ICostFunction costFunction;
    private Player[] players;
    public void setCostFunction(ICostFunction cF) {
        this.costFunction = cF;
    }
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * This is the main method for Shapley computation.
     * @param isSampling use the sampling method if it is true.
     * @param steps define the number of steps using in computing one Shapley value.
     * @return a double[] in which each value corresponding to the Shapley value for the players array.
     */
    public double[] computeShapley(boolean isSampling, int steps) {
        ArrayList<ArrayList> permutationSet;
        Integer[] nPlayers = IntStream.rangeClosed(0, players.length-1).boxed().toArray(Integer[]::new);
        Permutation p = new Permutation(nPlayers);
        double[] shapleySet = new double[nPlayers.length];
        if (!isSampling) {
            permutationSet = p.getPermutationSet();
        } else {
            permutationSet = p.getSamplePermutationSet(steps);
        }
        for (int s = 0; s < players.length; s++) {
            // compute the Shapley value for each player
            // s is the index of the original set of players
            double shapleyValue = 0; // initialize the Shapley value
            System.out.println("Start computing " + s + " player");
            for (ArrayList<Integer> a:permutationSet) {
                // use the permutation set to determine the subset used in the cost function
                int targetIndex = a.indexOf(s); // find where s is permuted
                for (int i=0; i <nPlayers.length; i++) {
                    if (i <= targetIndex) {
                        players[a.get(i)].off(); // for each player index precedes s, set it off.
                    } else {
                        players[a.get(i)].on(); // for each player index succeeds s, set it on.
                    }
                }
                double costTargetOff = costFunction.cost(players);
                players[s].on();
                double costTargetOn = costFunction.cost(players);
                double marginalCost = costTargetOn - costTargetOff;
                shapleyValue += marginalCost;
            }
            shapleyValue = shapleyValue / permutationSet.size();
            shapleySet[s] = shapleyValue;
            System.out.println("The Shapley value is: " + shapleyValue);
        }
        return shapleySet;
    }
}
