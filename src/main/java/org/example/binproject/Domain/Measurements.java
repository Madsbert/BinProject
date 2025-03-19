package org.example.binproject.Domain;

public class Measurements {

    private int measurementID;
    private String timeStamp;
    private int binLevel;
    private String lastEmptyed;
    private Boolean containsMeat;


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

    private Boolean appendDangerousTrash;
    private int binID;



}
