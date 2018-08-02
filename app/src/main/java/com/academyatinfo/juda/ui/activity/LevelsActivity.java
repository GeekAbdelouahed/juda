package com.academyatinfo.juda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.academyatinfo.juda.BaseActivity;
import com.academyatinfo.juda.R;
import com.academyatinfo.juda.data.DataTableLevel;
import com.academyatinfo.juda.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.academyatinfo.juda.utils.Constants.REQUEST_CODE;

public class LevelsActivity extends BaseActivity {

    @BindView(R.id.table_number)
    TextView tvTableNumber;

    private DataTableLevel dataTableLevel;
    private Intent intent;
    private int result = 0;
    private int table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_levels);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        dataTableLevel = new DataTableLevel(this);

        intent = getIntent();
        table = intent.getIntExtra(Constants.KEY_INDEX_TABLE, 0);

        String mTable = getString(R.string.table_number) + " " + table;
        tvTableNumber.setText(mTable);

        for (int i = 1; i <= 6; i++) {
            if (dataTableLevel.getData(table, i))
                addImageCheck(i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            result = data.getIntExtra(Constants.KEY_LEVEL, 0);
            dataTableLevel.updateData(table, result);
            addImageCheck(result);
        }
    }

    public void clickLevelOne(View view) {
        intent = new Intent(this, ReadingActivity.class);
        intent.putExtra(Constants.KEY_TABLE, table);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void clickLevelTwo(View view) {
        if (dataTableLevel.getData(table, 1)) {
            intent = new Intent(this, OperationsTableActivity.class);
            intent.putExtra(Constants.KEY_INDEX_TABLE, table);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void clickLevelThree(View view) {
        if (dataTableLevel.getData(table, 2)) {
            startExamActivity(3);
        }
    }

    public void clickLevelFour(View view) {
        if (dataTableLevel.getData(table, 3)) {
            startExamActivity(4);
        }
    }

    public void clickLevelFive(View view) {
        if (dataTableLevel.getData(table, 4)) {
            startExamActivity(5);
        }
    }

    public void clickReview(View view) {
        if (dataTableLevel.getData(table, 5)) {
            startExamActivity(6);
        }
    }

    private void startExamActivity(int level) {
        intent = new Intent(this, ExamActivity.class);
        intent.putExtra(Constants.KEY_TABLE, table);
        intent.putExtra(Constants.KEY_LEVEL, level);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void addImageCheck(int column_index) {
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

    public void clickFinish(View view) {
        if (result == 6)
            setResult(RESULT_OK);
        else
            setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (result == 6)
                setResult(RESULT_OK);
            else
                setResult(RESULT_CANCELED);
        }
        return super.onKeyDown(keyCode, event);
    }
}
