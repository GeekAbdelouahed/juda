package com.academyatinfo.juda.data;

import android.content.Context;
import android.content.SharedPreferences;

public class DataProfile {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public DataProfile(Context context) {
        sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
    }

    public void setName(String name) {
        editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.apply();
    }

    public String getName() {
        return sharedPreferences.getString("name", "null");
    }

    public void setFamilyName(String familyName) {
        editor = sharedPreferences.edit();
        editor.putString("family_name", familyName);
        editor.apply();
    }

    public String getFamilyName() {
        return sharedPreferences.getString("family_name", "null");
    }

    public void setGender(String gender) {
        editor = sharedPreferences.edit();
        editor.putString("gender", gender);
        editor.apply();
    }

    public String getGender() {
        return sharedPreferences.getString("gender", null);
    }

    public void firstLogin() {
        editor = sharedPreferences.edit();
        editor.putBoolean("login", true);
        editor.apply();
    }

    public boolean checkFirstLogin() {
        return sharedPreferences.getBoolean("login", false);
    }
}
