package com.example.hp.appstage1;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;

/**
 * Created by hp on 05/02/2018.
 */

public class Loader extends AsyncTaskLoader<List<News>> {
    public String The_Url;
    public boolean check = false;
    public Integer data;

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (check)
        {
            onReleaseResources(data);
            data = null;
            check = false;
        }
    }

    public Loader(Context The_context, String The_Url) {
        super(The_context);
        this.The_Url = The_Url;
    }

    @Override
    public List<News> loadInBackground() {
        List<News> newsList = Queries.Insert_news(The_Url);
        return newsList;
    }

    protected void onReleaseResources(int data) {
    }
}
