package com.academyatinfo.multtable.databases;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by geek on 02/09/17.
 */

public class DataBaseInfo {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public DataBaseInfo(Context context) {
        sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
    }

    public void setName(String info) {
        editor = sharedPreferences.edit();
        editor.putString("name", info);
        editor.commit();
    }

    public String getName() {
        return sharedPreferences.getString("name", "null");
    }

    public void setGender(String gender) {
        editor = sharedPreferences.edit();
        editor.putString("gender", gender);
        editor.commit();
    }

    public String getGender() {
        return sharedPreferences.getString("gender", null);
    }
}
