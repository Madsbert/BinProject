package org.example.binproject.Services;

import org.example.binproject.Domain.Measurements;
import org.example.binproject.Persistance.MeasurementInterface;
import org.example.binproject.Persistance.MeasurementsDatabase;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvConverter {


    /**
     * Import a CSV file from specified path, read the data and put it in a database
     * @param file csv file path
     * @throws FileNotFoundException u get it
     */

    public void importCsv(String file) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if(values.length <6 ) {
                    System.out.println("Invalid csv row: " + line);
                    continue;
                }
                Measurements measurement = new Measurements(
                        values[1],                          //timestamp
                        Integer.parseInt(values[2]),        //binlevel
                        values[3],                          //lastEmptied
                        Boolean.parseBoolean(values[4]),    //containsMeat
                        Boolean.parseBoolean(values[5]),    //appendDangerousTrash
                        Integer.parseInt(values[0])         //binID
                );

                //insert into database
                MeasurementInterface measurementInterface = new MeasurementsDatabase();
                measurementInterface.createMeasurement(measurement);
            }
            System.out.println("CSV data imported");
        } catch (IOException e) {
            System.out.println("Error importing csv file: " + e.getMessage());
            throw new RuntimeException(e);
        }catch (NumberFormatException e) {
            System.out.println("Invalid csv row: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
