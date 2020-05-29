package sampleShapley;

import java.io.IOException;

/**
 * The sampleShapley.ShapleyReader class reads data from external sources
 * and generates players for the sampleShapley.Shapley computation.
 */
public interface ShapleyReader {
    Player[] read(String fileName) throws IOException;
}
