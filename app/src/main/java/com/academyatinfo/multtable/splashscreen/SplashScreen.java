package com.academyatinfo.multtable.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.modules.AboutApp;
import com.academyatinfo.multtable.previewresults.mvp.PreviewResults;
import com.academyatinfo.multtable.tables.Tables;
import com.academyatinfo.multtable.ui.BaseActivity;

public class SplashScreen extends BaseActivity {

    private Button learn, result, about;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        learn = (Button) findViewById(R.id.btn_learn);
        result = (Button) findViewById(R.id.btn_result);
        about = (Button) findViewById(R.id.btn_about);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        learn.setAnimation(animation);
        result.setAnimation(animation);
        about.setAnimation(animation);
    }

    public void click_to_learn(View view) {
        startActivity(new Intent(this, Tables.class));
    }
    public void click_to_result(View view) {
        startActivity(new Intent(this, PreviewResults.class));
    }

    public void click_to_about(View view) {
        startActivity(new Intent(this, AboutApp.class));
    }
}
