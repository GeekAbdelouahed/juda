package com.academyatinfo.juda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.academyatinfo.juda.BaseActivity;
import com.academyatinfo.juda.R;
import com.academyatinfo.juda.data.DataTables;
import com.academyatinfo.juda.utils.Constants;

import static com.academyatinfo.juda.utils.Constants.REQUEST_CODE;

public class OperationsTableActivity extends BaseActivity {

    private DataTables dataTables;
    private Intent intent;
    private int indexTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_operations_table);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        indexTable = getIntent().getIntExtra(Constants.KEY_INDEX_TABLE, 0);
        intent = new Intent(OperationsTableActivity.this, OperationsActivity.class);
        intent.putExtra(Constants.KEY_INDEX_TABLE, indexTable);

        setImageNumber(indexTable);

        dataTables = new DataTables(this);

        for (int i = 1; i <= 10; i++) {
            if (dataTables.getData(i, indexTable))
                updateLevelStatus(i - 1);
            else
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data.getBooleanExtra(Constants.KEY_CHECK, false)) {
                updateLevelStatus(data.getIntExtra(Constants.KEY_MULT, -1));
                dataTables.updateData(data.getIntExtra(Constants.KEY_MULT, -1) + 1, indexTable);
            }
        }
    }

    public void clickZero(View view) {
        startOperationsActivity(0);
    }

    public void clickOne(View view) {
        if (dataTables.getData(1, indexTable))
            startOperationsActivity(1);
    }

    public void clickTwo(View view) {
        if (dataTables.getData(2, indexTable))
            startOperationsActivity(2);
    }

    public void clickThree(View view) {
        if (dataTables.getData(3, indexTable))
            startOperationsActivity(3);
    }

    public void clickFour(View view) {
        if (dataTables.getData(4, indexTable))
            startOperationsActivity(4);
    }

    public void clickFive(View view) {
        if (dataTables.getData(5, indexTable))
            startOperationsActivity(5);
    }

    public void clickSix(View view) {
        if (dataTables.getData(6, indexTable))
            startOperationsActivity(6);
    }

    public void clickSeven(View view) {
        if (dataTables.getData(7, indexTable))
            startOperationsActivity(7);
    }

    public void clickEight(View view) {
        if (dataTables.getData(8, indexTable))
            startOperationsActivity(8);
    }

    public void clickNine(View view) {
        if (dataTables.getData(9, indexTable)) {
            startOperationsActivity(9);
        }
    }

    public void startOperationsActivity(int index) {
        intent.putExtra(Constants.KEY_NUMBER, index);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void updateLevelStatus(int index) {
        switch (index) {
            case 0:
                ((ImageView) findViewById(R.id.check_zero)).setImageResource(R.drawable.ic_check_true);
                if (!dataTables.getData(2, indexTable))
                    ((ImageView) findViewById(R.id.check_one)).setImageResource(R.drawable.ic_check_false);
                break;
            case 1:
                ((ImageView) findViewById(R.id.check_one)).setImageResource(R.drawable.ic_check_true);
                if (!dataTables.getData(3, indexTable))
                    ((ImageView) findViewById(R.id.check_two)).setImageResource(R.drawable.ic_check_false);
                break;
            case 2:
                ((ImageView) findViewById(R.id.check_two)).setImageResource(R.drawable.ic_check_true);
                if (!dataTables.getData(4, indexTable))
                    ((ImageView) findViewById(R.id.check_three)).setImageResource(R.drawable.ic_check_false);
                break;
            case 3:
                ((ImageView) findViewById(R.id.check_three)).setImageResource(R.drawable.ic_check_true);
                if (!dataTables.getData(5, indexTable))
                    ((ImageView) findViewById(R.id.check_four)).setImageResource(R.drawable.ic_check_false);
                break;
            case 4:
                ((ImageView) findViewById(R.id.check_four)).setImageResource(R.drawable.ic_check_true);
                if (!dataTables.getData(6, indexTable))
                    ((ImageView) findViewById(R.id.check_five)).setImageResource(R.drawable.ic_check_false);
                break;
            case 5:
                ((ImageView) findViewById(R.id.check_five)).setImageResource(R.drawable.ic_check_true);
                if (!dataTables.getData(7, indexTable))
                    ((ImageView) findViewById(R.id.check_six)).setImageResource(R.drawable.ic_check_false);
                break;
            case 6:
                ((ImageView) findViewById(R.id.check_six)).setImageResource(R.drawable.ic_check_true);
                if (!dataTables.getData(8, indexTable))
                    ((ImageView) findViewById(R.id.check_seven)).setImageResource(R.drawable.ic_check_false);
                break;
            case 7:
                ((ImageView) findViewById(R.id.check_seven)).setImageResource(R.drawable.ic_check_true);
                if (!dataTables.getData(9, indexTable))
                    ((ImageView) findViewById(R.id.check_eight)).setImageResource(R.drawable.ic_check_false);
                break;
            case 8:
                ((ImageView) findViewById(R.id.check_eight)).setImageResource(R.drawable.ic_check_true);
                if (!dataTables.getData(10, indexTable))
                    ((ImageView) findViewById(R.id.check_nine)).setImageResource(R.drawable.ic_check_false);
                break;
            case 9:
                ((ImageView) findViewById(R.id.check_nine)).setImageResource(R.drawable.ic_check_true);
                break;
        }
    }

    private void setImageNumber(int number) {
        int imgDrawable = -1;

        switch (number) {
            case 1:
                imgDrawable = R.drawable.ic_one_g;
                break;
            case 2:
                imgDrawable = R.drawable.ic_two_g;
                break;
            case 3:
                imgDrawable = R.drawable.ic_three_g;
                break;
            case 4:
                imgDrawable = R.drawable.ic_four_g;
                break;
            case 5:
                imgDrawable = R.drawable.ic_five_g;
                break;
            case 6:
                imgDrawable = R.drawable.ic_six_g;
                break;
            case 7:
                imgDrawable = R.drawable.ic_seven_g;
                break;
            case 8:
                imgDrawable = R.drawable.ic_eight_g;
                break;
            case 9:
                imgDrawable = R.drawable.ic_nine_g;
                break;
        }

        ((ImageView) findViewById(R.id.ic_mul_zero)).setImageResource(imgDrawable);
        ((ImageView) findViewById(R.id.ic_mul_one)).setImageResource(imgDrawable);
        ((ImageView) findViewById(R.id.ic_mul_two)).setImageResource(imgDrawable);
        ((ImageView) findViewById(R.id.ic_mul_three)).setImageResource(imgDrawable);
        ((ImageView) findViewById(R.id.ic_mul_four)).setImageResource(imgDrawable);
        ((ImageView) findViewById(R.id.ic_mul_five)).setImageResource(imgDrawable);
        ((ImageView) findViewById(R.id.ic_mul_six)).setImageResource(imgDrawable);
        ((ImageView) findViewById(R.id.ic_mul_seven)).setImageResource(imgDrawable);
        ((ImageView) findViewById(R.id.ic_mul_eight)).setImageResource(imgDrawable);
        ((ImageView) findViewById(R.id.ic_mul_nine)).setImageResource(imgDrawable);

    }

    public void clickFinish(View view) {
        if (dataTables.getData(10, indexTable))
            setResult(RESULT_OK, (new Intent()).putExtra(Constants.KEY_LEVEL, 2));
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (dataTables.getData(10, indexTable))
                setResult(1, (new Intent()).putExtra(Constants.KEY_LEVEL, 2));
        }
        return super.onKeyDown(keyCode, event);
    }
}
