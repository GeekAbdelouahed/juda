package com.academyatinfo.multtable.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.about.AboutActivity;
import com.academyatinfo.multtable.appintro.IntroActivity;
import com.academyatinfo.multtable.databases.DataBaseInfo;
import com.academyatinfo.multtable.results.mvp.ResultsActivity;
import com.academyatinfo.multtable.settings.SettingsActivity;
import com.academyatinfo.multtable.tables.TablesActivity;
import com.academyatinfo.multtable.ui.BaseActivity;

public class HomeActivity extends BaseActivity {

    private DataBaseInfo dataBaseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dataBaseInfo = new DataBaseInfo(this);

        if (!dataBaseInfo.checkFirstLogin())
            startActivity(new Intent(this, IntroActivity.class));

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
