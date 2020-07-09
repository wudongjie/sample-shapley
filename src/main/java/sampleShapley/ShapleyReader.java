package sampleShapley;

import java.io.IOException;

/**
 * The sampleShapley.ShapleyReader reads data from external sources
 * and generates players for the Shapley computation.
 */
public interface ShapleyReader {
    Player[] read(String fileName) throws IOException;
}
