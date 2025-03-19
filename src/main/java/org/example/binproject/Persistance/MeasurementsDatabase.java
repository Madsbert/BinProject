package org.example.binproject.Persistance;

import org.example.binproject.Domain.Measurements;
import org.example.binproject.Foundation.SQLManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Searches for a measurement in the list by its ID
 * @param
 * @return the measurement with the matching ID if found, or null if no such measurement exist
 */
public class MeasurementsDatabase
{
    public static Measurements read(int measurementId) throws IOException {
        String sql = "SELECT * FROM dbo.tblMeasurements WHERE measurementID = ?";
        try (Connection con = SQLManager.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, measurementId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String timeStamp = resultSet.getString("timestamp");
                int binLevel = resultSet.getInt("binlevel");
                String lastEmptyed = resultSet.getString("lastEmptyed");
                boolean containsMeat = resultSet.getBoolean("containsMeat");
                boolean appendDangerousTrash = resultSet.getBoolean("appendDangerousTrash");
                int binID = resultSet.getInt("binID");

                return new Measurements(timeStamp, binLevel, lastEmptyed, containsMeat, appendDangerousTrash, binID);

            }
        } catch (Exception e) {
            System.err.println("Database error: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }
}
