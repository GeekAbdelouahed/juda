package com.academyatinfo.juda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.academyatinfo.juda.BaseActivity;
import com.academyatinfo.juda.R;
import com.academyatinfo.juda.data.DataTableLevel;
import com.academyatinfo.juda.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.academyatinfo.juda.utils.Constants.REQUEST_CODE;

public class TablesActivity extends BaseActivity {

    @BindView(R.id.lock_two)
    ImageView imgLockTwo;
    @BindView(R.id.lock_three)
    ImageView imgLockThree;
    @BindView(R.id.lock_four)
    ImageView imgLockFour;
    @BindView(R.id.lock_five)
    ImageView imgLockFive;
    @BindView(R.id.lock_six)
    ImageView imgLockSix;
    @BindView(R.id.lock_seven)
    ImageView imgLockSeven;
    @BindView(R.id.lock_eight)
    ImageView imgLockEight;
    @BindView(R.id.lock_nine)
    ImageView imgLockNine;
    @BindView(R.id.img_lock_certification)
    ImageView imgLockExam;

    private DataTableLevel dataTableLevel;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tables);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        dataTableLevel = new DataTableLevel(this);

        intent = new Intent(this, LevelsActivity.class);

        checkTablesStatus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            checkTablesStatus();
        }
    }

    public void clickTableOne(View view) {
        intent.putExtra(Constants.KEY_INDEX_TABLE, 1);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void clickTableTwo(View view) {
        if (checkTableDone(1)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 2);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void clickTableThree(View view) {
        if (checkTableDone(2)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 3);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void clickTableFour(View view) {
        if (checkTableDone(3)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 4);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void clickTableFive(View view) {
        if (checkTableDone(4)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 5);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void clickTableSix(View view) {
        if (checkTableDone(5)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 6);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void clickTableSeven(View view) {
        if (checkTableDone(6)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 7);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void clickTableEight(View view) {
        if (checkTableDone(7)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 8);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void clickTableNine(View view) {
        if (checkTableDone(8)) {
            intent.putExtra(Constants.KEY_INDEX_TABLE, 9);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void clickExam(View view) {
        if (checkTableDone(9)) {
            intent = new Intent(this, ExamActivity.class);
            intent.putExtra(Constants.KEY_TABLE, 9);
            intent.putExtra(Constants.KEY_LEVEL, 7);
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.alert_steps_learn), Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkTableDone(int id_table) {
        return dataTableLevel.getData(id_table, 6);
    }

    private void checkTablesStatus() {
        if (checkTableDone(1)) {
            imgLockTwo.setVisibility(View.GONE);
            if (checkTableDone(2)) {
                imgLockThree.setVisibility(View.GONE);
                if (checkTableDone(3)) {
                    imgLockFour.setVisibility(View.GONE);
                    if (checkTableDone(4)) {
                        imgLockFive.setVisibility(View.GONE);
                        if (checkTableDone(5)) {
                            imgLockSix.setVisibility(View.GONE);
                            if (checkTableDone(6)) {
                                imgLockSeven.setVisibility(View.GONE);
                                if (checkTableDone(7)) {
                                    imgLockEight.setVisibility(View.GONE);
                                    if (checkTableDone(8)) {
                                        imgLockNine.setVisibility(View.GONE);
                                        if (checkTableDone(9)) {
                                            imgLockExam.setVisibility(View.GONE);
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

    public void clickFinish(View view) {
        finish();
    }
}
