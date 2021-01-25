package com.pickapp.data.local.pref;

import android.content.SharedPreferences;

import com.pickapp.app.AppConstant;
import com.pickapp.app.PickApp;
import com.pickapp.data.repository.sources.IPreferencesSource;

import javax.inject.Inject;

public class PrefDataSource implements IPreferencesSource {

    private SharedPreferences preferences;
    private PickApp context;

    @Inject
    public PrefDataSource() {
        context = PickApp.getInstance();
        this.preferences = context.getSharedPreferences(AppConstant.APP_NAME, 0);
    }


    @Override
    public void setString(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    @Override
    public String getString(String key) {
        return preferences.getString(key, "");
    }
}
