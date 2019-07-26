package com.team02.handicraftmyanmar.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesUtil {
    public static SharedPreferencesUtil INSTANCE;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public static SharedPreferencesUtil getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SharedPreferencesUtil(context);
        }
        return INSTANCE;
    }

    private SharedPreferencesUtil(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mSharedPreferences.edit();
    }

    public String getValue(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void setValue(String key, String value) {
        mEditor.putString(key, value).apply();
    }

    public void setBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value).apply();
    }

    public void clearData() {
        mEditor.clear().apply();
    }
}
