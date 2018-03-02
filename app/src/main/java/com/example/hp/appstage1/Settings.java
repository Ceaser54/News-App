package com.example.hp.appstage1;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static class NewsListPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        private void bindvalue(Preference pref) {
            pref.setOnPreferenceChangeListener(this);
            SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(pref.getContext());
            String value = shared.getString(pref.getKey(), "0");
            onPreferenceChange(pref, value);
        }
        @Override
        public boolean onPreferenceChange(Preference pref, Object value) {
            String changingvalue = value.toString();
            pref.setSummary(changingvalue);
            return true;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.screen);
            Preference pref = findPreference("setting");
            bindvalue(pref);
        }
    }
}
