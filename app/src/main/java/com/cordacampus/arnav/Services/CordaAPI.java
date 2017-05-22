package com.cordacampus.arnav.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CordaAPI {

    private static CordaAPI instance = null;
    public static String ENDPOINT = "http://corda-ar.azurewebsites.net/api/";


    // Keep your services here, build them in buildRetrofit method later
    private ICordaAPI iCordaAPI;

    public static CordaAPI getInstance() {
        if (instance == null) {
            instance = new CordaAPI();
        }

        return instance;
    }

    // Build retrofit once when creating a single instance
    private CordaAPI() {
        // Implement a method to build your retrofit
        buildRetrofit();
    }

    private void buildRetrofit() {

        CookieJar cookieJar = new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Build your services once
        this.iCordaAPI = retrofit.create(ICordaAPI.class);
    }

    public ICordaAPI getICordaApi() {
        return this.iCordaAPI;
    }
}
