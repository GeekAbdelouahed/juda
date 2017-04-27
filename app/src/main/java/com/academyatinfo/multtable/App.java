package com.academyatinfo.multtable;

import android.app.Application;

import com.academyatinfo.multtable.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by geek on 11/1/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/nat.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}