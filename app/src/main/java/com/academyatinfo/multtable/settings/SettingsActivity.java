package com.academyatinfo.multtable.settings;

import android.os.Bundle;
import android.view.WindowManager;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.ui.BaseActivity;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_settings);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}
