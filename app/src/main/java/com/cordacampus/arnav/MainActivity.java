package com.cordacampus.arnav;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cordacampus.arnav.Model.CordaCredentials;
import com.cordacampus.arnav.Services.CordaAPI;
import com.cordacampus.arnav.Services.ICordaAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoCompanyList(View view) {
        Intent intent = new Intent(this, CompanyList.class);
        startActivityForResult(intent, 2);
    }

    public void gotoEmployeeList(View view) {
        Intent intent = new Intent(this, EmployeeList.class);
        startActivityForResult(intent, 2);
    }

    public static void Call(Activity activity)
    {
        // Creating an intent with the current activity and the activity we wish to start
        Intent myIntent = new Intent(activity, MainActivity.class);
        activity.startActivityForResult(myIntent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
