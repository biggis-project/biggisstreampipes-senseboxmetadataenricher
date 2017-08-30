package org.streampipes.biggis.pe.sensebox.flink.enrich.location;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lutz on 30.08.17.
 */
public class SenseboxLocationRegistryEntry {
    double latitude;
    double longitude;
    double altitude;
    String openSenseMapID;

    public SenseboxLocationRegistryEntry(double latitude, double longitude, double altitude, String openSenseMapID) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.openSenseMapID = openSenseMapID;
    }

    public SenseboxLocationRegistryEntry() {}

    @JsonProperty("latitude")
    double getLatitude() { return this.latitude; }
    void setLatitude(double latitude) { this.latitude = latitude; }
    @JsonProperty("longitude")
    double getLongitude() { return this.longitude; }
    void setLongitute(double longitude) { this.longitude = longitude; }
    @JsonProperty("altitude")
    double getAltitude() { return this.altitude; }
    void setAltitude(double altitude) { this.altitude = altitude; }
    @JsonProperty("openSenseMapID")
    String getOpenSenseMapIDId() { return this.openSenseMapID; }
    void setOpenSenseMapID(String openSenseMapID) { this.openSenseMapID = openSenseMapID; }
}
