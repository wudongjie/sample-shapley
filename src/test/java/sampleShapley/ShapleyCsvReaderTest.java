package sampleShapley;

import org.junit.jupiter.api.Test;
import sampleShapley.Player;
import sampleShapley.ShapleyCsvReader;
import tech.tablesaw.api.DoubleColumn;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ShapleyCsvReaderTest {

    @Test
    void read() throws IOException {
        ShapleyCsvReader reader = new ShapleyCsvReader();
        Player[] players = reader.read("src/test/resources/testData.csv");
        DoubleColumn data = (DoubleColumn) players[0].getData();
        assertEquals(99, data.size());
    }
}