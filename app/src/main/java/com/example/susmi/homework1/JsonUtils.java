package com.example.susmi.homework1;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

// This class is responsible for parsing the news through JSON Parsing.

    public  static ArrayList<NewsItem> parseNews(String response) {
        ArrayList<NewsItem> list = new ArrayList<NewsItem>();
        try {
            JSONObject obj = new JSONObject(response);
            JSONArray array = obj.getJSONArray("articles");

            for(int i = 0; i < array.length(); i++) {
                list.add(new NewsItem(array.getJSONObject(i).getString("AUTHOR"),
                        array.getJSONObject(i).getString("TITLE"),
                        array.getJSONObject(i).getString("DESCRIPTION"),
                        array.getJSONObject(i).getString("URL"),
                        array.getJSONObject(i).getString("URLTOIMAGE"),
                        array.getJSONObject(i).getString("PUBLISHEDAT")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }




