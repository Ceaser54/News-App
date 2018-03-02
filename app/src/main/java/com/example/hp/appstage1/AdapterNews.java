package com.example.hp.appstage1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by hp on 05/02/2018.
 */

public class AdapterNews extends ArrayAdapter<News> {

    public AdapterNews(Context the_context, List<News> newsList) {
        super(the_context, 0, newsList);
    }
    @Override
    public View getView(int i, View view, ViewGroup father) {
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_news,father,false);
        }
        News now = getItem(i);
        TextView webtitle = (TextView) view.findViewById(R.id.webTitle);
        webtitle.setText(now.getAuthorname());

        TextView apiUrl = (TextView) view.findViewById(R.id.api_url);
        apiUrl.setText(now.getsection());

        TextView weburl = (TextView) view.findViewById(R.id.webUrl);
        weburl.setText(now.getWebUrl());

        TextView web_Date = (TextView) view.findViewById(R.id.web_date);
        web_Date.setText(now.getWeb_date());


        TextView webtitletags = (TextView) view.findViewById(R.id.webtitletags);
        webtitletags.setText(now.getwebtitle());

        return view;
    }
}
