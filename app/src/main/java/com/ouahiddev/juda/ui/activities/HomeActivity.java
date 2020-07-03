package com.ouahiddev.juda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.ouahiddev.juda.BaseActivity;
import com.ouahiddev.juda.R;
import com.ouahiddev.juda.data.DataProfile;

import static com.ouahiddev.juda.utils.Constants.REQUEST_CODE;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DataProfile dataProfile = new DataProfile(this);

        if (!dataProfile.isFirstLogin())
            startActivityForResult(new Intent(this, IntroActivity.class), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REQUEST_CODE) {
            boolean statusData = data.getExtras().getBoolean("status");
            if (!statusData)
                finish();
        }
    }

    public void clickLearn(View view) {
        startActivity(new Intent(this, TablesActivity.class));
    }

    public void clickResults(View view) {
        startActivity(new Intent(this, ResultsActivity.class));
    }

    public void clickSettings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void clickAbout(View view) {
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void clickExit(View view) {
        finish();
    }
}
