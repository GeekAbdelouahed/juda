package com.academyatinfo.juda.application;

import android.app.Application;

import com.academyatinfo.juda.R;
import com.google.firebase.analytics.FirebaseAnalytics;

import io.realm.Realm;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAnalytics.getInstance(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Realm.init(this);

    }

}