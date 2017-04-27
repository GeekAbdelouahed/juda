package com.academyatinfo.multtable;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.academyatinfo.multtable.Modules.Results;
import com.academyatinfo.multtable.databases.DataBaseResultExam;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Certification extends AppCompatActivity {

    private TextView textView, name, soccer;
    private Intent intent;
    private DataBaseResultExam dataBaseResultExam;
    private Results results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certification);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        intent = getIntent();

        results = new Results();
        results.setName(intent.getStringExtra("name"));
        results.setResult(intent.getStringExtra("soccer"));
        results.setDate(intent.getStringExtra("date"));
        results.setTime(intent.getStringExtra("time"));

        dataBaseResultExam = new DataBaseResultExam(this);
        dataBaseResultExam.open();
        dataBaseResultExam.insertResult(results);

        textView = (TextView) findViewById(R.id.back_certification);
        name = (TextView) findViewById(R.id.name);
        soccer = (TextView) findViewById(R.id.soccer);

        name.setText(results.getName());
        soccer.setText(results.getResult() + " من 90");
    }

    public void save_certification(View view) {
        textView.setVisibility(View.VISIBLE);
        takeScreenshot(findViewById(R.id.layout_certification));
        Toast.makeText(this, "تم حفظ الشهادة", Toast.LENGTH_LONG).show();
    }

    private void takeScreenshot(View view) {
        view.setDrawingCacheEnabled(true);
        final Bitmap bitmap = view.getDrawingCache();
        MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "جداء", "شهادة نجاح في لعبة جداء");
    }


    public void click_back(View view) {
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
