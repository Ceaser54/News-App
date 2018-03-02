package com.example.hp.appstage1;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener,
        android.app.LoaderManager.LoaderCallbacks<List<News>> {
    private AdapterNews adapterNews;
    private TextView notification;
    private static final String guardinapis
            = "http://content.guardianapis.com/search?q=debates&section=politics&show-tags=contributor&api-key=test";
    SharedPreferences prefs;
    @Override
    public void onLoadFinished(android.content.Loader<List<News>> listLoader, List<News> newsList) {
        adapterNews.clear();
        if (newsList != null)
        {
            int num_of_items = Integer.parseInt(prefs.getString("setting","-1"));
            if (num_of_items > 0&&num_of_items<newsList.size())
                newsList = newsList.subList(0,num_of_items);
            adapterNews.clear();
            adapterNews.addAll(newsList);
        }else
            {
            notification = (TextView) findViewById(R.id.check);
            notification.setVisibility(View.VISIBLE);
            Toast.makeText(this, R.string.big, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void onSharedPreferenceChanged(SharedPreferences shared, String pref) {
        if (pref.equals("setting")) {
            adapterNews.clear();
        }
    }
    @Override
    public android.content.Loader<List<News>> onCreateLoader(int id, Bundle bundle) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        return new Loader(this, guardinapis);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu_item) {
        int i = menu_item.getItemId();
        if (i == R.id.settings) {
            Toast.makeText(getBaseContext(), getBaseContext().getString(R.string.welcome), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<News>> loader) {
    }

    public void Check_Network() {
        final ConnectivityManager connect = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connect.getActiveNetworkInfo() != null
                && connect.getActiveNetworkInfo().isAvailable()
                && connect.getActiveNetworkInfo().isConnected()) {
            Toast.makeText(getBaseContext(), getBaseContext().getString(R.string.avail), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(), getBaseContext().getString(R.string.disconnect), Toast.LENGTH_SHORT).show();
        }
    }
    ListView listnews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Check_Network();
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_main);
        getLoaderManager().initLoader(0, null, this);
        listnews = (ListView) findViewById(R.id.listView);
        listnews.setEmptyView(findViewById(R.id.check));
        TextView Check = (TextView) findViewById(R.id.check);
        Check.setText(R.string.check);

        adapterNews = new AdapterNews(this, new ArrayList<News>());
        listnews.setAdapter(adapterNews);


        listnews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                News news_now = adapterNews.getItem(i);
                Uri uri = Uri.parse(news_now.getWebUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                Toast.makeText(getBaseContext(), getBaseContext().getString(R.string.read), Toast.LENGTH_SHORT).show();
                if (intent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(intent);
                }
            }
        });
        SharedPreferences Preference = PreferenceManager.getDefaultSharedPreferences(this);
        Preference.registerOnSharedPreferenceChangeListener((SharedPreferences.OnSharedPreferenceChangeListener) this);
    }


}