package com.academyatinfo.multtable.application;

import android.app.Activity;
import android.app.Application;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.application.dagger.AppComponent;
import com.academyatinfo.multtable.application.dagger.AppModule;
import com.academyatinfo.multtable.application.dagger.DaggerAppComponent;
import com.google.firebase.analytics.FirebaseAnalytics;

import io.realm.Realm;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by geek on 11/1/16.
 */
public class App extends Application {

    private AppComponent component;

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

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public static App get(Activity activity) {
        return (App) activity.getApplication();
    }

    public AppComponent component() {
        return component;
    }

}