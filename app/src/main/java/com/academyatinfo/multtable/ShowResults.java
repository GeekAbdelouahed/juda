package com.academyatinfo.multtable;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.academyatinfo.multtable.Modules.MyAdapter;
import com.academyatinfo.multtable.databases.DataBaseResultExam;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ShowResults extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private DataBaseResultExam dataBaseResultExam;
    private TextView text_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showresults);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dataBaseResultExam = new DataBaseResultExam(this);
        dataBaseResultExam.open();
        text_check = (TextView) findViewById(R.id.text_checkdata);
        if (dataBaseResultExam.checkCursor()) {
            recyclerView = (RecyclerView) findViewById(R.id.recycler);
            myAdapter = new MyAdapter(this, dataBaseResultExam.getResults());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(myAdapter);
            recyclerView.setVisibility(View.VISIBLE);
            text_check.setVisibility(View.GONE);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
