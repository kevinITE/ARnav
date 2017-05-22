package com.cordacampus.arnav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.cordacampus.arnav.Adapters.ImageAdapter;
import com.cordacampus.arnav.Model.Company;
import com.cordacampus.arnav.Model.CordaCredentials;
import com.cordacampus.arnav.Services.CordaAPI;
import com.cordacampus.arnav.Services.ICordaAPI;
import com.google.gson.Gson;
import com.unity3d.player.UnityPlayer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.AdapterView.OnItemClickListener;

public class CompanyList extends Activity {

    public List<Company> companyGrid = new ArrayList<>();

    private Context me = this;
    private Company selectedCompany;
    private Gson gson = new Gson();

    private ICordaAPI cordaAPI = CordaAPI.getInstance().getICordaApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_list);

        final GridView gridView = (GridView) findViewById(R.id.lst_companies);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final SearchView searchView = (SearchView) findViewById(R.id.search_view);

        final OnItemClickListener companyClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectedCompany = (Company) parent.getAdapter().getItem(position);

                UnityPlayer.UnitySendMessage("ARCamera","Receive", gson.toJson(selectedCompany));

                finishActivity(1);
                finish();
            }
        };

        Call<Void> loginCall = cordaAPI.login(new CordaCredentials("SuperUser", "PXLCordaAr123!"));
        loginCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("CallBack", " response is " + response);

                Call<List<Company>> callCompanies = cordaAPI.getCompanies();
                callCompanies.enqueue(new Callback<List<Company>>() {
                    @Override
                    public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                        Log.d("CallBack", " response is " + response);

                        progressBar.setVisibility(View.INVISIBLE);

                        gridView.setOnItemClickListener(companyClickListener);

                        // Fill company grid
                        companyGrid = response.body();
                        final ImageAdapter imageAdapter = new ImageAdapter(me, companyGrid);

                        gridView.setAdapter(imageAdapter);
                        gridView.setTextFilterEnabled(true);

                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                imageAdapter.getFilter().filter(newText);
                                return false;
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<List<Company>> call, Throwable t) {
                        // Log error here since request failed
                        Log.d("Error: ", t.getMessage());
                    }
                });

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("CallBack", " Throwable is " +t);
            }
        });
    }

    public void gotoEmployeeList(View view) {
        Intent intent = new Intent(this, EmployeeList.class);
        startActivityForResult(intent, 2);
    }
}
