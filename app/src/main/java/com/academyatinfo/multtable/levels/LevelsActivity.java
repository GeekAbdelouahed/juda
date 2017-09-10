package com.academyatinfo.multtable.levels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.databases.DataTableLevel;
import com.academyatinfo.multtable.exam.ExamActivity;
import com.academyatinfo.multtable.learn.LearnActivity;
import com.academyatinfo.multtable.tablelevels.TableLevels;
import com.academyatinfo.multtable.tools.Constants;
import com.academyatinfo.multtable.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LevelsActivity extends BaseActivity implements LevelsContract.View {

    @BindView(R.id.table_number)
    TextView table_number;

    private DataTableLevel dataTableLevel;
    private int table;
    private Intent intent;
    private int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_levels);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        dataTableLevel = new DataTableLevel(this);
        intent = getIntent();
        table = intent.getIntExtra(Constants.KEY_INDEX_TABLE, 0);

        table_number.setText("جدول رقم " + table);

        for (int i = 1; i <= 6; i++) {
            if (dataTableLevel.getData(table, i))
                add_image_check(i);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            result = data.getIntExtra(Constants.KEY_LEVEL, 0);
            dataTableLevel.updateData(table, result);
            add_image_check(result);
        }
    }

    public void level_one(View view) {
        intent = new Intent(this, LearnActivity.class);
        intent.putExtra(Constants.KEY_SEND_TABLE, table);
        startActivityForResult(intent, 1);
    }

    public void level_two(View view) {
        if (dataTableLevel.getData(table, 1)) {
            intent = new Intent(this, TableLevels.class);
            intent.putExtra(Constants.KEY_INDEX_TABLE, table);
            startActivityForResult(intent, 1);
        }
    }

    public void level_three(View view) {
        if (dataTableLevel.getData(table, 2)) {
            intent = new Intent(this, ExamActivity.class);
            intent.putExtra(Constants.KEY_LONG_TABLE, table);
            intent.putExtra(Constants.KEY_LEVEL, 3);
            startActivityForResult(intent, 1);
        }
    }

    public void level_four(View view) {
        if (dataTableLevel.getData(table, 3)) {
            intent = new Intent(this, ExamActivity.class);
            intent.putExtra(Constants.KEY_LONG_TABLE, table);
            intent.putExtra(Constants.KEY_LEVEL, 4);
            startActivityForResult(intent, 1);
        }
    }

    public void level_five(View view) {
        if (dataTableLevel.getData(table, 4)) {
            intent = new Intent(this, ExamActivity.class);
            intent.putExtra(Constants.KEY_LONG_TABLE, table);
            intent.putExtra(Constants.KEY_LEVEL, 5);
            startActivityForResult(intent, 1);
        }
    }

    public void review(View view) {
        if (dataTableLevel.getData(table, 5)) {
            intent = new Intent(this, ExamActivity.class);
            intent.putExtra(Constants.KEY_LONG_TABLE, table);
            intent.putExtra(Constants.KEY_LEVEL, 6);
            startActivityForResult(intent, 1);
        }
    }

    public void add_image_check(int column_index) {
        switch (column_index) {
            case 1:
                ((ImageView) findViewById(R.id.result_one)).setImageResource(R.drawable.ic_check_true);
                if (!dataTableLevel.getData(table, column_index + 1))
                    ((ImageView) findViewById(R.id.result_two)).setImageResource(R.drawable.ic_check_false);
                break;
            case 2:
                ((ImageView) findViewById(R.id.result_two)).setImageResource(R.drawable.ic_check_true);
                if (!dataTableLevel.getData(table, column_index + 1))
                    ((ImageView) findViewById(R.id.result_three)).setImageResource(R.drawable.ic_check_false);
                break;
            case 3:
                ((ImageView) findViewById(R.id.result_three)).setImageResource(R.drawable.ic_check_true);
                if (!dataTableLevel.getData(table, column_index + 1))
                    ((ImageView) findViewById(R.id.result_four)).setImageResource(R.drawable.ic_check_false);
                break;
            case 4:
                ((ImageView) findViewById(R.id.result_four)).setImageResource(R.drawable.ic_check_true);
                if (!dataTableLevel.getData(table, column_index + 1))
                    ((ImageView) findViewById(R.id.result_five)).setImageResource(R.drawable.ic_check_false);
                break;
            case 5:
                ((ImageView) findViewById(R.id.result_five)).setImageResource(R.drawable.ic_check_true);
                if (!dataTableLevel.getData(table, column_index + 1))
                    ((ImageView) findViewById(R.id.img_review)).setImageResource(R.drawable.ic_check_false);
                break;
            case 6:
                ((ImageView) findViewById(R.id.img_review)).setImageResource(R.drawable.ic_check_true);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (result == 6)
            setResult(1);
    }

    public void click_back(View view) {
        finish();
    }
}
