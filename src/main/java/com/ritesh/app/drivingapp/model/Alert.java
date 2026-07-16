package com.ritesh.app.drivingapp.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class Alert {
    @Schema(description = "Unique alert identifier", example = "1")
    private int alertId;

    @Schema(description = "Alert timestamp", example = "Wed Jul 16 20:30:45 IST 2026")
    private String timestamp;

    @Schema(description = "Whether the vehicle was over speeding", example = "true")
    private boolean isOverSpeeding;

    @Schema(description = "Vehicle identifier", example = "VEH001", required = true)
    private String vehicleId;

    @Schema(description = "Location type", example = "highway", allowableValues = {"highway", "residential", "commercial", "city_center"}, required = true)
    private String locationType;

    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isOverSpeeding() {
        return isOverSpeeding;
    }

    public void setOverSpeeding(boolean overSpeeding) {
        isOverSpeeding = overSpeeding;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }
}
