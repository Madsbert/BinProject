package org.example.binproject.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvConverter {

    List<List<String>> records = new ArrayList<>();

    /**
     * Import a CSV file from specified path, read the data and put it in an ArrayList
     * @param file csv file path
     * @throws FileNotFoundException u get it
     */

    public void importCsv(String file) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<String>> getRecords() {
        return records;
    }


}
