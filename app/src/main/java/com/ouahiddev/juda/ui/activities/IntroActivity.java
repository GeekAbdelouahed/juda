package com.ouahiddev.juda.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro2;
import com.ouahiddev.juda.data.DataProfile;
import com.ouahiddev.juda.ui.fragments.Slide1Fragment;
import com.ouahiddev.juda.ui.fragments.Slide2Fragment;
import com.ouahiddev.juda.ui.fragments.Slide3Fragment;
import com.ouahiddev.juda.ui.fragments.Slide4Fragment;

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
