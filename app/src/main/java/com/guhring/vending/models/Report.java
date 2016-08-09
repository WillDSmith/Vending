package com.guhring.vending.models;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SmithW on 8/9/2016.
 */
public class Report {
    public int id;
    public String title;
    public String subtitle;
    public String date;
    public List<String> listOfReports;


    public static ArrayList<Report> getReportsFromFile(String filename, Context context) {
        final ArrayList<Report> reportList = new ArrayList<>();

        try {
            // Load Data
            String data = loadJsonFromAsset("reports.json", context);
            JSONObject json = new JSONObject(data);
            JSONArray reports = json.getJSONArray("reports");

            // Get Report objects from data
            for(int i = 0; i < reports.length(); i++) {
                Report report = new Report();

                report.id = reports.getJSONObject(i).getInt("id");
                report.title = reports.getJSONObject(i).getString("title");
                report.subtitle = reports.getJSONObject(i).getString("subtitle");
                report.date = reports.getJSONObject(i).getString("date");

                reportList.add(report);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return reportList;
    }

    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}
