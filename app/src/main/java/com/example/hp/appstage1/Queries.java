package com.example.hp.appstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 05/02/2018.
 */


public class Queries {

    private static Context context;

    private Queries() {
    }

    private static String RequestHttp(URL The_url) throws IOException {
        InputStream input = null;
        HttpURLConnection connect = null;
        String result = "";
        if (The_url == null) {
            return result;
        }
        try {
            connect = (HttpURLConnection) The_url.openConnection();
            connect.setRequestMethod("GET");
            connect.setRequestProperty("Content-length", "0");
            connect.setConnectTimeout(100000);
            connect.setUseCaches(false);
            connect.setAllowUserInteraction(false);
            connect.setReadTimeout(100000);
            connect.connect();
            if (connect.getResponseCode() == 200) {
                input = connect.getInputStream();
                result = Read(input);
            } else {
            }
        } catch (IOException e) {
        }
        return result;
    }


    private static URL buildurl(String The_Url) {
        URL The_url = null;
        try {
            The_url = new URL(The_Url);
        } catch (MalformedURLException exception) {
        }
        return The_url;
    }
    @NonNull
    private static String Read(InputStream input) throws IOException {
        StringBuilder out = new StringBuilder();
        if (input != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(input, Charset.forName("UTF-8"));
            BufferedReader bufferedreader = new BufferedReader(inputStreamReader);
            String text = bufferedreader.readLine();
            while (text != null) {
                out.append(text);
                text = bufferedreader.readLine();
            }
        }
        return out.toString();
    }

    public static List<News> Insert_news(String The_Url) {
        String result = null;
        URL url = buildurl(The_Url);
        try {
            result = RequestHttp(url);
        } catch (IOException exception) {
        }
        List<News> newsList = GetNews(result);
        return newsList;
    }
    @Nullable
    private static List<News> GetNews(String news) {
        List<News> newsList = new ArrayList<>();
        if (TextUtils.isEmpty(news))
        {
            return null;
        }
        try
        {
            JSONObject object = new JSONObject(news);
            JSONObject news_array = object.getJSONObject("response");
            if (news_array.has("results"))
            {
                int counter = 0;
                JSONArray array_results = news_array.getJSONArray("results");
                JSONObject new_now = array_results.getJSONObject(counter);
                while (counter < array_results.length())
                {
                    String webtitle = new_now.getString("webTitle");
                    String weburl = new_now.getString("webUrl");
                    String section = new_now.getString("sectionName");
                    String web_date = new_now.getString("webPublicationDate");
                    String webtitletags = new_now.getString("type");
                    if (webtitletags == null||webtitle==null)
                    {
                        Toast.makeText(context,R.string.presenttitle, Toast.LENGTH_SHORT).show();
                    }
                        newsList.add(new News(webtitle, weburl, section, web_date, webtitletags));
                        ++counter;
                }
            } else
            {
                return null;
            }
        } catch (JSONException e) {
        }
        return newsList;
    }
}
