package com.herokuapp.ezhao.dropboxwayfinder;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private String size;
    private String floor;
    private String key;
    private List<String> landmarks;

    public static Room fromJson(JSONObject jsonObject) {
        Room room = new Room();
        try {
            room.setName(jsonObject.getString("name"));
            room.setSize(jsonObject.getString("size"));
            room.setFloor(jsonObject.getString("floor"));
            room.setKey(jsonObject.getString("key"));
            JSONArray jsonArray = jsonObject.getJSONArray("landmarks");
            ArrayList<String> landmarks = new ArrayList<>(jsonArray.length());
            for (int i=0; i < jsonArray.length(); i++) {
                landmarks.add(jsonArray.getString(i));
            }
            room.setLandmarks(landmarks);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return room;
    }

    public static ArrayList<Room> fromJsonFile(Activity activity) {
        // Get raw room json file from assets
        String jsonAsString = "";
        try {
            InputStream inputStream = activity.getAssets().open("rooms.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonAsString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Cast into jsonArray
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonAsString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Turn into Room objects
        ArrayList<Room> rooms = new ArrayList<Room>(jsonArray.length());
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject roomJson = null;
            try {
                roomJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Room room = Room.fromJson(roomJson);
            if (room != null) {
                rooms.add(room);
            }
        }
        return rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(List<String> landmarks) {
        this.landmarks = landmarks;
    }
}
