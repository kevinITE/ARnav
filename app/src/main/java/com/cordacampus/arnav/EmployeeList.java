package com.cordacampus.arnav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.cordacampus.arnav.Adapters.EmployeeAdapter;
import com.cordacampus.arnav.Model.CordaCredentials;
import com.cordacampus.arnav.Model.Employee;
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

public class EmployeeList extends Activity {

    public List<Employee> employeeList = new ArrayList<>();

    private Context me = this;
    private Employee selectedEmployee;
    private Gson gson = new Gson();

    private ICordaAPI cordaAPI = CordaAPI.getInstance().getICordaApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_list);

        final ListView gridView = (ListView) findViewById(R.id.lst_employees);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final SearchView searchView = (SearchView) findViewById(R.id.search_view);

        final OnItemClickListener employeeClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectedEmployee = (Employee) parent.getAdapter().getItem(position);

                UnityPlayer.UnitySendMessage("ARCamera","Receive", gson.toJson(selectedEmployee));

                finishActivity(1);
                finish();
            }
        };

        Call<Void> loginCall = cordaAPI.login(new CordaCredentials("SuperUser", "PXLCordaAr123!"));

        loginCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("CallBack", " response is " + response);
                Call<List<Employee>> callEmployees = cordaAPI.getEmployees();

                callEmployees.enqueue(new Callback<List<Employee>>() {
                    @Override
                    public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                        Log.d("CallBack", " response is " + response);

                        progressBar.setVisibility(View.INVISIBLE);

                        gridView.setOnItemClickListener(employeeClickListener);

                        // Fill employee list
                        employeeList = response.body();
                        final EmployeeAdapter employeeAdapter = new EmployeeAdapter(me, employeeList);
                        gridView.setAdapter(employeeAdapter);
                        gridView.setTextFilterEnabled(true);

                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                employeeAdapter.getFilter().filter(newText);
                                return false;
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<List<Employee>> call, Throwable t) {
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


    public void gotoCompanyList(View view) {
        Intent intent = new Intent(this, CompanyList.class);
        startActivityForResult(intent, 2);
    }
}
