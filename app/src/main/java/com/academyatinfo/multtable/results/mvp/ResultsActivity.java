package com.academyatinfo.multtable.results.mvp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.adapters.ResultsAdapter;
import com.academyatinfo.multtable.databases.DataBaseResults;
import com.academyatinfo.multtable.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultsActivity extends BaseActivity implements ResultContract.View {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.text_checkdata)
    TextView text_check;

    private ResultsAdapter resultsAdapter;
    private DataBaseResults dataBaseResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_results);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        dataBaseResults = new DataBaseResults(this);
        dataBaseResults.open();

        if (dataBaseResults.checkCursor()) {
            resultsAdapter = new ResultsAdapter(this, dataBaseResults.getResults());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(resultsAdapter);
            recyclerView.setVisibility(View.VISIBLE);
            text_check.setVisibility(View.GONE);
        }
    }

    public void click_back(View view) {
        finish();
    }
}
