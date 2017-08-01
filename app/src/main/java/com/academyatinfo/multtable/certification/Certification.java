package com.academyatinfo.multtable.certification;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.databases.DataBaseResultExam;
import com.academyatinfo.multtable.modules.Results;
import com.academyatinfo.multtable.tools.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Certification extends BaseActivity implements CertificationContract.View {

    @BindView(R.id.back_certification)
    TextView textView;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.soccer)
    TextView soccer;

    private Intent intent;
    private DataBaseResultExam dataBaseResultExam;
    private Results results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certification);

        ButterKnife.bind(this);

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

}
