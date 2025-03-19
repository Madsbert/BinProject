package org.example.binproject.Persistance;

import org.example.binproject.Domain.Measurements;
import org.example.binproject.Foundation.SQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MeasurementsDatabase {

    public static void createMeasurement(Measurements measurement) {
        String sql = "INSERT INTO dbo.tblMeasurement(timestamp, binLevel, lastEmptyed, containsMeat, appendDangerousTrash,binID) VALUES (?,?,?,?,?,?)";
        try {
            Connection conn = SQLManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,measurement.getTimeStamp());
            ps.setInt(2,measurement.getBinLevel());
            ps.setString(3,measurement.getLastEmptyed());
            ps.setBoolean(4,measurement.getContainsMeat());
            ps.setBoolean(5,measurement.getAppendDangerousTrash());
            ps.setInt(6,measurement.getBinID());
            ps.executeUpdate();

        }catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to connect to database in CreateMeasurement()");
        }
    }
}
