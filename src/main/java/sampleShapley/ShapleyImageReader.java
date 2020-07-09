package sampleShapley;

import java.io.IOException;
import org.datavec.image.recordreader.ImageRecordReader;
/**
 * The ShapleyImageReader class reads data from external JPEG/PNG image files
 * and generates players for the Shapley computation.
 */
public class ShapleyImageReader implements ShapleyReader{
    @Override
    public Player[] read(String fileName) throws IOException {
        return new Player[0];
    }
}
