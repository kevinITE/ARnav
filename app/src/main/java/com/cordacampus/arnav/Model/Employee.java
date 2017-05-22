package com.cordacampus.arnav.Model;

import com.google.gson.annotations.SerializedName;

public class Employee {
    @SerializedName("Id")
    private long id;
    @SerializedName("Name")
    private String name;
    @SerializedName("FirstName")
    private String firstName;
    @SerializedName("Company")
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
