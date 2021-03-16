package com.example.carcrash;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Car {
    public String state;
    int people;
    int Fatals;
    public static final String TAG = "State";

    public Car(JSONObject jsonObject) throws JSONException {
        state = jsonObject.getString("StateName");
        people = jsonObject.getInt("Persons");
        Fatals = jsonObject.getInt("Fatals");

        Log.d(TAG, toString());
    }

    @Override
    public String toString() {
        return "Car{" +
                "state='" + state + '\'' +
                ", people=" + people +
                ", Fatals=" + Fatals +
                '}';
    }
    public static String listToString(List<Car> cars)
    {
        String result = "\n";
        for (Car s : cars)
            result += s.toString() +"\n";
        return result;
    }

    public static List<Car> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Car> cars = new ArrayList<Car>();

        for (int i=0; i<jsonArray.length(); i++)
            cars.add(new Car(jsonArray.getJSONObject(i)));



        return cars;
    }
    public String getState() {
        return state;
    }

    public int getPeople() {
        return people;
    }

    public int getFatals() {
        return Fatals;
    }
}
