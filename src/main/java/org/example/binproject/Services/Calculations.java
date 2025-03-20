package org.example.binproject.Services;
import org.example.binproject.Domain.Measurements;
import java.util.List;

/**
 * Class to calculate statistics
 */
public class Calculations {

    /**
     * Method to calculate red, yellow and green lights
     * @param measurements
     */
    public static void calculateStatistics(List<Measurements> measurements) {
        int redCount = 0;
        int yellowCount = 0;
        int greenCount = 0;

        for (Measurements m : measurements) {
            if (m.getBinLevel() > 90) {
                redCount++;
            } else if (m.getBinLevel() > 33 ||m.getContainsMeat()) {
                yellowCount++;
            } else {
                greenCount++;
            }
        }
    }
}
