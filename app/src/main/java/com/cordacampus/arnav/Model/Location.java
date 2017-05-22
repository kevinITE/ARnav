package com.cordacampus.arnav.Model;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("Id")
    private long id;
    @SerializedName("Description")
    private String description;
    @SerializedName("XValue")
    private double xValue;
    @SerializedName("YValue")
    private double yValue;
    @SerializedName("ZValue")
    private double zValue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getxValue() {
        return xValue;
    }

    public void setxValue(double xValue) {
        this.xValue = xValue;
    }

    public double getyValue() {
        return yValue;
    }

    public void setyValue(double yValue) {
        this.yValue = yValue;
    }

    public double getzValue() {
        return zValue;
    }

    public void setzValue(double zValue) {
        this.zValue = zValue;
    }

}
