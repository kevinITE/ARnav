package com.cordacampus.arnav.Services;


import com.cordacampus.arnav.Model.Beacon;
import com.cordacampus.arnav.Model.Company;
import com.cordacampus.arnav.Model.CordaCredentials;
import com.cordacampus.arnav.Model.Employee;
import com.cordacampus.arnav.Model.Landmark;
import com.cordacampus.arnav.Model.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ICordaAPI {

    /* Login */

    @POST("Account/Login")
    Call<Void> login(@Body CordaCredentials cordaCredentials);

    /* Company api */

    @GET("Company")
    Call<List<Company>> getCompanies();

    @GET("Company/{id}")
    Call<Company> getCompany(@Path("id") long id);

    @POST("Company")
    Call<Company> createCompany(@Body Company company);

    @PUT("Company/{id}")
    Call<Company> updateCompany(@Path("id") long id);

    @DELETE("Company/{id}")
    Call<Void> deleteCompany(@Path("id") long id);

    /* Location api */

    @GET("Location")
    Call<List<Location>> getLocations();

    @GET("Location/{id}")
    Call<Location> getLocation(@Path("id") long id);

    @POST("Location")
    Call<Location> createLocation(@Body Location location);

    @PUT("Location/{id}")
    Call<Location> updateLocation(@Path("id") long id);

    @DELETE("Location/{id}")
    Call<Void> deleteLocation(@Path("id") long id);

    /* Beacon api */

    @GET("Beacon")
    Call<List<Beacon>> getBeacons();

    @GET("Beacon/{id}")
    Call<Beacon> getBeacon(@Path("id") long id);

    @POST("Beacon")
    Call<Beacon> createBeacon(@Body Beacon beacon);

    @PUT("Beacon/{id}")
    Call<Beacon> updateBeacon(@Path("id") long id);

    @DELETE("Beacon/{id}")
    Call<Void> deleteBeacon(@Path("id") long id);

    /* Employee api */

    @GET("Employee")
    Call<List<Employee>> getEmployees();

    @GET("Employee/{id}")
    Call<Employee> getEmployee(@Path("id") long id);

    @POST("Employee")
    Call<Employee> createEmployee(@Body Employee employee);

    @PUT("Employee/{id}")
    Call<Employee> updateEmployee(@Path("id") long id);

    @DELETE("Employee/{id}")
    Call<Void> deleteEmployee(@Path("id") long id);

    /* Landmark api */

    @GET("Landmark")
    Call<List<Landmark>> getLandmarks();

    @GET("Landmark/{id}")
    Call<Landmark> getLandmark(@Path("id") long id);

    @POST("Landmark")
    Call<Landmark> createLandmark(@Body Landmark landmark);

    @PUT("Landmark/{id}")
    Call<Landmark> updateLandmark(@Path("id") long id);

    @DELETE("Landmark/{id}")
    Call<Void> deleteLandmark(@Path("id") long id);
}