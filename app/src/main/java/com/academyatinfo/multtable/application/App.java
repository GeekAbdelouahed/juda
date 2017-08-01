package com.academyatinfo.multtable.application;

import android.app.Activity;
import android.app.Application;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.application.dagger.AppComponent;
import com.academyatinfo.multtable.application.dagger.AppModule;
import com.academyatinfo.multtable.application.dagger.DaggerAppComponent;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by geek on 11/1/16.
 */
public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/nat.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

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