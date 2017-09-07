package com.github.junyinghu.automation;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadCsv {
    String csvFilename = "src/test/resources/destingation.csv";

    public List<String> getDestinations() throws IOException {

        List<String> destinations = new ArrayList<>();
        Reader in = new FileReader(csvFilename);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
        for (CSVRecord record : records) {
            String value = record.get("Destination");
            destinations.add(value);
        }
        return destinations;
    }

}
