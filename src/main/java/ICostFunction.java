/**
 * This is the interface of the cost function.
 * The cost function has a method cost to compute the cost given a set of players.
 */
public interface ICostFunction {
    double cost(Player[] players);
}
