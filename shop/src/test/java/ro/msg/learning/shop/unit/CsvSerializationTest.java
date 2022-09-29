package ro.msg.learning.shop.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ro.msg.learning.shop.TestBase;
import ro.msg.learning.shop.dtos.StockDTO;
import ro.msg.learning.shop.utils.CSVConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CsvSerializationTest extends TestBase {
    private static final String CSV = "locationId,productId,quantity\n31,4,95\n";

    public CSVConverter<StockDTO> csvConversion = new CSVConverter<>();


    @Test
    void toCsvTestSuccess() throws IOException {
        List<StockDTO> existingStocks = new ArrayList<>();
        existingStocks.add(new StockDTO(31, 4, 95));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        csvConversion.convertToCsv(StockDTO.class, existingStocks, outputStream);

        Assertions.assertEquals(CSV, outputStream.toString());
    }
}
