package com.cordacampus.arnav.Model;

import com.google.gson.annotations.SerializedName;

public class Company {
    @SerializedName("Id")
    private long id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Logo")
    private String logo;
    @SerializedName("WelcomeImage")
    private String welcomeImage;
    @SerializedName("Location")
    private Location location;

    public Company(String logo, Long id, String name) {
        setLogo(logo);
        setId(id);
        setName(name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWelcomeImage() {
        return welcomeImage;
    }

    public void setWelcomeImage(String welcomeImage) {
        this.welcomeImage = welcomeImage;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
