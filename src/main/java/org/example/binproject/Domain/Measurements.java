package org.example.binproject.Domain;

import org.example.binproject.Persistance.MeasurementDBInterface;
import org.example.binproject.Persistance.MeasurementsDatabase;

/**
 * Class to describe a measurement
 */
public class Measurements {

    private int measurementID;
    private String timeStamp;
    private int binLevel;
    private String lastEmptyed;
    private Boolean containsMeat;
    private Boolean appendDangerousTrash;
    private int binID;


    /**
     * A Measurement Obejct
     * @param timeStamp
     * @param binLevel
     * @param lastEmptyed
     * @param containsMeat
     * @param appendDangerousTrash
     * @param binID
     */
    public Measurements(String timeStamp, int binLevel, String lastEmptyed, boolean containsMeat, boolean appendDangerousTrash, int binID) {
        this.timeStamp = timeStamp;
        this.binLevel = binLevel;
        this.lastEmptyed = lastEmptyed;
        this.containsMeat = containsMeat;
        this.appendDangerousTrash = appendDangerousTrash;
        this.binID = binID;
    }

    public int getMeasurementID() {
        return measurementID;
    }


    public Boolean getAppendDangerousTrash() {
        return appendDangerousTrash;
    }

    public void setAppendDangerousTrash(Boolean appendDangerousTrash) {
        this.appendDangerousTrash = appendDangerousTrash;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getBinLevel() {
        return binLevel;
    }

    public void setBinLevel(int binLevel) {
        this.binLevel = binLevel;
    }

    public String getLastEmptyed() {
        return lastEmptyed;
    }

    public void setLastEmptyed(String lastEmptyed) {
        this.lastEmptyed = lastEmptyed;
    }

    public Boolean getContainsMeat() {
        return containsMeat;
    }

    public void setContainsMeat(Boolean containsMeat) {
        this.containsMeat = containsMeat;
    }

    public int getBinID() {
        return binID;
    }

    public void setBinID(int binID) {
        this.binID = binID;
    }

    /**
     * a method to be able to read a measurement
     * @return
     */
    @Override
    public String toString() {
        return "Measurements{" +
                "timestamp='" + timeStamp + '\'' +
                ", binLevel=" + binLevel +
                ", lastEmptied='" + lastEmptyed + '\'' +
                ", containsMeat=" + containsMeat +
                ", appendDangerousTrash=" + appendDangerousTrash +
                ", binID=" + binID +
                '}';
    }

    public String getColor (){
        String color = "";

        if (getBinLevel() >= 90) {
            color = "Red";
        } else if (getBinLevel() >= 33 ||getContainsMeat()) {
            color = "Yellow";
        } else {
            color = "Green";
        }
        return color;
    }


}
