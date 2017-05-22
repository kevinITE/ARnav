package com.cordacampus.arnav.Model;

import com.google.gson.annotations.SerializedName;

public class Landmark {
    @SerializedName("Id")
    private long id;
    @SerializedName("Description")
    private String description;
    @SerializedName("Image")
    private String image;
    @SerializedName("IsIndoor")
    private boolean isIndoor;
    @SerializedName("Location")
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isIndoor() {
        return isIndoor;
    }

    public void setIndoor(boolean indoor) {
        isIndoor = indoor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
