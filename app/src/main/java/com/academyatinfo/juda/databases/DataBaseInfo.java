package com.academyatinfo.juda.databases;

import android.content.Context;
import android.content.SharedPreferences;

public class DataBaseInfo {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public DataBaseInfo(Context context) {
        sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
    }

    public void setName(String name) {
        editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.commit();
    }

    public String getName() {
        return sharedPreferences.getString("name", "null");
    }

    public void setFamilyName(String familyName) {
        editor = sharedPreferences.edit();
        editor.putString("family_name", familyName);
        editor.commit();
    }

    public String getFamilyName() {
        return sharedPreferences.getString("family_name", "null");
    }

    public void setGender(String gender) {
        editor = sharedPreferences.edit();
        editor.putString("gender", gender);
        editor.commit();
    }

    public String getGender() {
        return sharedPreferences.getString("gender", null);
    }

    public void firstLogin() {
        editor = sharedPreferences.edit();
        editor.putBoolean("login", true);
        editor.commit();
    }

    public boolean checkFirstLogin() {
        return sharedPreferences.getBoolean("login", false);
    }
}
