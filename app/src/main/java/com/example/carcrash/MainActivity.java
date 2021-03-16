package com.example.carcrash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.carcrash.Adapter.CarAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    //https://crashviewer.nhtsa.dot.gov/CrashAPI
    String url = "https://crashviewer.nhtsa.dot.gov/CrashAPI/crashes/GetCaseList?states=1,51&fromYear=2014&toYear=2015&minNumOfVehicles=1&maxNumOfVehicles=6&format=json";

    List<Car> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvStates = findViewById(R.id.rvStates);
        cars = new ArrayList<Car>();

        final CarAdapter adapter = new CarAdapter(cars);
        rvStates.setAdapter(adapter);
        rvStates.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                  Log.d(TAG, json.toString());

                try {
                 //   Log.d(TAG,  json.jsonObject.getJSONArray("Results \n").getJSONArray(0).toString());
                    JSONObject object = json.jsonObject;
                    JSONArray array = object.getJSONArray("Results");
                    System.out.println(array.getJSONArray(0).toString());
                    cars.addAll(Car.fromJsonArray(array.getJSONArray(0)));
                   Log.d(TAG, "Size: " + cars.size());
                   Log.d(TAG, Car.listToString(cars));

                    Log.d(TAG, "Top State: " + cars.get(0).state + " " + (cars.get(0).people + " " + cars.get(0).Fatals));
                    Log.d(TAG, Car.listToString(cars));
                    adapter.notifyDataSetChanged();
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.e(TAG, "Error Response", throwable);
            }
        });
    }
}