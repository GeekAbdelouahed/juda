package com.academyatinfo.juda.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.academyatinfo.juda.data.DataProfile;
import com.academyatinfo.juda.ui.fragment.Slide1Fragment;
import com.academyatinfo.juda.ui.fragment.Slide2Fragment;
import com.academyatinfo.juda.ui.fragment.Slide3Fragment;
import com.academyatinfo.juda.ui.fragment.Slide4Fragment;
import com.github.paolorotolo.appintro.AppIntro2;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class IntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        addSlide(new Slide1Fragment());
        addSlide(new Slide2Fragment());
        addSlide(new Slide3Fragment());
        addSlide(new Slide4Fragment());

        showSkipButton(false);

        askForPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        DataProfile dataProfile = new DataProfile(this);
        dataProfile.firstLogin();
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
