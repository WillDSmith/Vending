package com.guhring.vending.models;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;

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
    public String id;
    public String title;
    public String subtitle;
    public String date;
    public ArrayList<String> machinereports = new ArrayList<>();

    public static ArrayList<Report> getReportsFromFile(String filename, Context context) {
        final ArrayList<Report> reportList = new ArrayList<>();

        try {
            // Load Data
            String jsonStr = loadJsonFromAsset("reports.json", context);
            JSONObject jsonOne = new JSONObject(jsonStr);
            JSONArray reports = jsonOne.getJSONArray("reports");


            // Get Report objects from data
            for(int i = 0; i < reports.length(); i++) {
                Report report = new Report();

                report.id = reports.getJSONObject(i).getString("id");
                report.title = reports.getJSONObject(i).getString("title");
                report.subtitle = reports.getJSONObject(i).getString("subtitle");
                report.date = reports.getJSONObject(i).getString("date");

                // Get inner array listOrReports
                /* JSONArray rList = jsonOne.getJSONArray("machinereports");*/
                JSONArray rList = reports.getJSONObject(i).getJSONArray("machinereports");

                for(int j = 0; j < rList.length(); j++) {
                    JSONObject jsonTwo = rList.getJSONObject(j);
                    report.machinereports.add(jsonTwo.getString("name"));
                    report.machinereports.add(jsonTwo.getString("count"));
                }
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
