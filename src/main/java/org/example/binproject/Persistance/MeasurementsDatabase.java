package org.example.binproject.Persistance;

import org.example.binproject.Domain.Measurements;
import org.example.binproject.Foundation.SQLManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MeasurementsDatabase implements MeasurementInterface
{
    @Override
    public void createMeasurement(Measurements measurement) {
        String sql = "INSERT INTO dbo.tblMeasurements(timestamp, binLevel, lastEmptyed, containsMeat, appendDangerousTrash,binID) VALUES (?,?,?,?,?,?)";
        try {
            Connection conn = SQLManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, measurement.getTimeStamp());
            ps.setInt(2, measurement.getBinLevel());
            ps.setString(3, measurement.getLastEmptyed());
            ps.setBoolean(4, measurement.getContainsMeat());
            ps.setBoolean(5, measurement.getAppendDangerousTrash());
            ps.setInt(6, measurement.getBinID());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to connect to database in CreateMeasurement() or Bin ID didn't exist");
        }
    }


/**
 * Searches for a measurement in the list by its ID
 * @param
 * @return the measurement with the matching ID if found, or null if no such measurement exist
 */
    @Override
    public Measurements read(int measurementId) throws IOException {
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
            System.err.println("Database error: " + e.getMessage() + "program failed in read()");
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Measurements> readAll() throws Exception
    {
        String sql = "SELECT * FROM dbo.tblMeasurements";
        List<Measurements> measurements = new ArrayList<>();
        try (Connection con = SQLManager.getConnection();
             PreparedStatement statement = con.prepareStatement(sql))
        {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                String timeStamp = resultSet.getString("timeStamp");
                int binLevel = resultSet.getInt("binlevel");
                String lastEmptyed = resultSet.getString("lastEmptyed");
                boolean containsMeat = resultSet.getBoolean("containsMeat");
                boolean appendDangerousTrash = resultSet.getBoolean("appendDangerousTrash");
                int binID = resultSet.getInt("binID");
                Measurements measurement = new Measurements(timeStamp, binLevel, lastEmptyed, containsMeat, appendDangerousTrash, binID);
                measurements.add(measurement);
            }
        }
        return measurements;

    }
}
