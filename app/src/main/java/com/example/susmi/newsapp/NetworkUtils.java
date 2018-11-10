package com.example.susmi.newsapp;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    final static String GITHUB_BASE_URL = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey= ";
    final static String PARAM_QUERY = "q";


    public static URL buildurl(String searchQuery) {
        Uri builtUri = Uri.parse(GITHUB_BASE_URL).buildUpon().appendQueryParameter(PARAM_QUERY, "2f0e14dd951e46a49cdbb4492bfdecc8").build();


        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;

    }
    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }

        } finally{
            urlConnection.disconnect();
        }
    }
    public  static ArrayList<NewsItem> responseToNewsItems(String response) throws JSONException {
        ArrayList<NewsItem> list = new ArrayList<NewsItem>();
        JSONObject obj = new JSONObject(response);
        JSONArray array = obj.getJSONArray("articles");


        for(int i = 0; i < array.length(); i++) {
            list.add(new NewsItem(array.getJSONObject(i).getString("author"),
                    array.getJSONObject(i).getString("title"),
                    array.getJSONObject(i).getString("description"),
                    array.getJSONObject(i).getString("url"),
                    array.getJSONObject(i).getString("urlToImage"),
                    array.getJSONObject(i).getString("publishedAt")));
        }

        return list;
    }
}
