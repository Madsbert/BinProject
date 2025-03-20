package org.example.binproject.Persistance;

import org.example.binproject.Domain.Measurements;

import java.io.IOException;
import java.util.List;

/**
 * Interface with the methods that we use - create, read and real all.
 */

public interface MeasurementDBInterface
{
    void createMeasurement(Measurements measurement); //Create measurement

    public Measurements read(int measurementId) throws IOException; //Read one measurement by ID

    public List<Measurements> readAll() throws Exception; //Read all measurements
}


