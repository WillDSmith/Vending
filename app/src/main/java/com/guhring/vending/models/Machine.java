package com.guhring.vending.models;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by SmithW on 8/4/2016.
 */
public class Machine {
    public String id;
    public String title;
    public String imageUrl;
    public String location;
    public String user;
    public String machine;
    public String model;
    public String lastcloudupload;

    public static ArrayList<Machine> getMachinesFromFile(String filename, Context context) {
        final ArrayList<Machine> machineList = new ArrayList<>();

        try {
            // Load Data
            String jsonString = loadJsonFromAsset("machines.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray machines = json.getJSONArray("machines");

            // Get Recipe objects from data
            for(int i = 0; i < machines.length(); i++){
                Machine machine = new Machine();

                machine.id = machines.getJSONObject(i).getString("id");
                machine.title = machines.getJSONObject(i).getString("title");
                machine.imageUrl = machines.getJSONObject(i).getString("image");
                machine.location = machines.getJSONObject(i).getString("location");
                machine.user = machines.getJSONObject(i).getString("user");
                machine.machine = machines.getJSONObject(i).getString("machine");
                machine.model = machines.getJSONObject(i).getString("model");
                machine.lastcloudupload = machines.getJSONObject(i).getString("lastcloudupload");

                machineList.add(machine);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return machineList;
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
