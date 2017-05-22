package com.cordacampus.arnav.Model;

import com.google.gson.annotations.SerializedName;

public class Beacon {
    @SerializedName("Id")
    private long id;
    @SerializedName("Description")
    private String description;
    @SerializedName("Major")
    private long major;
    @SerializedName("Minor")
    private long minor;
    @SerializedName("Location")
    private Location location;


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

    public long getMajor() {
        return major;
    }

    public void setMajor(long major) {
        this.major = major;
    }

    public long getMinor() {
        return minor;
    }

    public void setMinor(long minor) {
        this.minor = minor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
