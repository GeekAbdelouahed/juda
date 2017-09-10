package com.academyatinfo.multtable.tables;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.databases.DataTableLevel;
import com.academyatinfo.multtable.exam.ExamActivity;
import com.academyatinfo.multtable.levels.LevelsActivity;
import com.academyatinfo.multtable.tools.Constants;
import com.academyatinfo.multtable.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TablesActivity extends BaseActivity implements TablesContract.View {


    @BindView(R.id.lock_two)
    ImageView lock_two;
    @BindView(R.id.lock_three)
    ImageView lock_three;
    @BindView(R.id.lock_four)
    ImageView lock_four;
    @BindView(R.id.lock_five)
    ImageView lock_five;
    @BindView(R.id.lock_six)
    ImageView lock_six;
    @BindView(R.id.lock_seven)
    ImageView lock_seven;
    @BindView(R.id.lock_eight)
    ImageView lock_eight;
    @BindView(R.id.lock_nine)
    ImageView lock_nine;
    @BindView(R.id.img_lock_certification)
    ImageView img_lock_exam;

    private Intent intent;
    private DataTableLevel dataTableLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tables);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        dataTableLevel = new DataTableLevel(this);

        intent = new Intent(this, LevelsActivity.class);
    }

    public void one(View view) {
        intent.putExtra(Constants.KEY_INDEX_TABLE, 1);
        startActivity(intent);
    }

    public void two(View view) {
        if (check_done_table(1)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 2);
            startActivity(intent);
        }
    }

    public void three(View view) {
        if (check_done_table(2)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 3);
            startActivity(intent);
        }
    }

    public void four(View view) {
        if (check_done_table(3)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 4);
            startActivity(intent);
        }
    }

    public void five(View view) {
        if (check_done_table(4)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 5);
            startActivity(intent);
        }
    }

    public void six(View view) {
        if (check_done_table(5)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 6);
            startActivity(intent);
        }
    }

    public void seven(View view) {
        if (check_done_table(6)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 7);
            startActivity(intent);
        }
    }

    public void eight(View view) {
        if (check_done_table(7)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 8);
            startActivity(intent);
        }
    }

    public void nine(View view) {
        if (check_done_table(8)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 9);
            startActivity(intent);
        }
    }

    public void click_exam(View view) {
        if (check_done_table(9)) {
            intent = new Intent(this, ExamActivity.class);
            intent.putExtra(Constants.KEY_LONG_TABLE, 9);
            intent.putExtra(Constants.KEY_LEVEL, 7);
            startActivity(intent);
        } else {
            Toast.makeText(this, "يجب إكمال جميع مراحل التعليم", Toast.LENGTH_LONG).show();
        }
    }

    public boolean check_done_table(int id_table) {
        return dataTableLevel.getData(id_table, 6);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (check_done_table(1)) {
            lock_two.setVisibility(View.GONE);
            if (check_done_table(2)) {
                lock_three.setVisibility(View.GONE);
                if (check_done_table(3)) {
                    lock_four.setVisibility(View.GONE);
                    if (check_done_table(4)) {
                        lock_five.setVisibility(View.GONE);
                        if (check_done_table(5)) {
                            lock_six.setVisibility(View.GONE);
                            if (check_done_table(6)) {
                                lock_seven.setVisibility(View.GONE);
                                if (check_done_table(7)) {
                                    lock_eight.setVisibility(View.GONE);
                                    if (check_done_table(8)) {
                                        lock_nine.setVisibility(View.GONE);
                                        if (check_done_table(9)) {
                                            img_lock_exam.setVisibility(View.GONE);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void click_back(View view) {
        finish();
    }
}
