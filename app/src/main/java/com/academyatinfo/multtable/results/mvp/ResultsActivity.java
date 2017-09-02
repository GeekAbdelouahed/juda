package com.academyatinfo.multtable.results.mvp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.academyatinfo.multtable.databases.DataBaseResults;
import com.academyatinfo.multtable.modules.MyAdapter;
import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.ui.BaseActivity;

public class ResultsActivity extends BaseActivity implements ResultContract.View {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private DataBaseResults dataBaseResults;
    private TextView text_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_results);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dataBaseResults = new DataBaseResults(this);
        dataBaseResults.open();
        text_check = (TextView) findViewById(R.id.text_checkdata);
        if (dataBaseResults.checkCursor()) {
            recyclerView = (RecyclerView) findViewById(R.id.recycler);
            myAdapter = new MyAdapter(this, dataBaseResults.getResults());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(myAdapter);
            recyclerView.setVisibility(View.VISIBLE);
            text_check.setVisibility(View.GONE);
        }
    }
}
