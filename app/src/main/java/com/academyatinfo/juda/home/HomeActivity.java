package com.academyatinfo.juda.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.academyatinfo.juda.R;
import com.academyatinfo.juda.about.AboutActivity;
import com.academyatinfo.juda.appintro.IntroActivity;
import com.academyatinfo.juda.databases.DataBaseInfo;
import com.academyatinfo.juda.results.ResultsActivity;
import com.academyatinfo.juda.settings.SettingsActivity;
import com.academyatinfo.juda.tables.TablesActivity;
import com.academyatinfo.juda.ui.activitys.BaseActivity;

public class HomeActivity extends BaseActivity {

    private DataBaseInfo dataBaseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dataBaseInfo = new DataBaseInfo(this);

        if (!dataBaseInfo.checkFirstLogin())
            startActivityForResult(new Intent(this, IntroActivity.class), 100);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            boolean statusData = data.getExtras().getBoolean("status");
            if (!statusData)
                finish();
        }
    }

    public void click_to_learn(View view) {
        startActivity(new Intent(this, TablesActivity.class));
    }

    public void click_to_result(View view) {
        startActivity(new Intent(this, ResultsActivity.class));
    }

    public void click_to_settings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void click_to_about(View view) {
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void click_back(View view) {
        finish();
    }
}
