package com.academyatinfo.multtable.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.certification.mvp.CertificationActivity;
import com.academyatinfo.multtable.modules.AboutApp;
import com.academyatinfo.multtable.results.mvp.ResultsActivity;
import com.academyatinfo.multtable.settings.SettingsActivity;
import com.academyatinfo.multtable.tables.TablesActivity;
import com.academyatinfo.multtable.ui.BaseActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startActivity(new Intent(this, CertificationActivity.class));

    }

    public void click_to_learn(View view) {
        startActivity(new Intent(this, TablesActivity.class));
    }

    public void click_to_result(View view) {
        startActivity(new Intent(this, ResultsActivity.class));
    }

    public void click_to_about(View view) {
        startActivity(new Intent(this, AboutApp.class));
    }

    public void click_to_settings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
