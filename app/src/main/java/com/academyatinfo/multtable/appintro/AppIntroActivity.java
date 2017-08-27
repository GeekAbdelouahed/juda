package com.academyatinfo.multtable.appintro;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.appintro.slides.Slide1Fragment;
import com.academyatinfo.multtable.appintro.slides.Slide2Fragment;
import com.academyatinfo.multtable.appintro.slides.Slide3Fragment;
import com.academyatinfo.multtable.splashscreen.SplashScreen;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AppIntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(Slide1Fragment.newInstance("slide1", "intro1"));
        addSlide(Slide2Fragment.newInstance("slide2", "intro2"));
        addSlide(Slide3Fragment.newInstance("slide3", "intro3"));

        addSlide(AppIntroFragment.newInstance("intro", "just try", R.mipmap.ic_launcher, Color.GREEN));

        showSkipButton(false);

        setColorDoneText(Color.WHITE);
        setDoneText("موافق");

        askForPermissions(
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}
                , 2);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(this, SplashScreen.class));
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
