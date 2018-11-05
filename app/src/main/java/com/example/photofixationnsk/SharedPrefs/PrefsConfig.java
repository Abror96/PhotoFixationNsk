package com.example.photofixationnsk.SharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.photofixationnsk.R;

public class PrefsConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefsConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(
                "AccountData", Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), status);
        editor.commit();
    }

    public boolean readLoginStatus() {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);
    }

    public void writeToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_token), token);
        editor.commit();
    }

    public String readToken() {
        return sharedPreferences.getString(context.getString(R.string.pref_token), "");
    }
}
