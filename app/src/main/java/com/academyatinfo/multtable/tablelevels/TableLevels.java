package com.academyatinfo.multtable.tablelevels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.academyatinfo.multtable.operation.Operation;
import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.databases.DataBaseLearnTable;
import com.academyatinfo.multtable.ui.BaseActivity;

public class TableLevels extends BaseActivity implements TableLevelsContract.View {

    private Intent intent;
    private int index_table;
    private DataBaseLearnTable dataBaseLearnTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_levels);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        index_table = getIntent().getIntExtra("index_table", 0);
        setImageNumber(index_table);
        intent = new Intent(TableLevels.this, Operation.class);
        intent.putExtra("index_table", index_table);
        dataBaseLearnTable = new DataBaseLearnTable(this);
        for (int i = 1; i <= 10; i++) {
            if (dataBaseLearnTable.getData(i, index_table))
                add_image_check(i - 1);
        }

    }

    public void click_zero(View view) {
        startAnotherActivity(0);
    }

    public void click_one(View view) {
        if (dataBaseLearnTable.getData(1, index_table))
            startAnotherActivity(1);
    }

    public void click_two(View view) {
        if (dataBaseLearnTable.getData(2, index_table))
            startAnotherActivity(2);
    }

    public void click_three(View view) {
        if (dataBaseLearnTable.getData(3, index_table))
            startAnotherActivity(3);
    }

    public void click_four(View view) {
        if (dataBaseLearnTable.getData(4, index_table))
            startAnotherActivity(4);
    }

    public void click_five(View view) {
        if (dataBaseLearnTable.getData(5, index_table))
            startAnotherActivity(5);
    }

    public void click_six(View view) {
        if (dataBaseLearnTable.getData(6, index_table))
            startAnotherActivity(6);
    }

    public void click_seven(View view) {
        if (dataBaseLearnTable.getData(7, index_table))
            startAnotherActivity(7);
    }

    public void click_eight(View view) {
        if (dataBaseLearnTable.getData(8, index_table))
            startAnotherActivity(8);
    }

    public void click_nine(View view) {
        if (dataBaseLearnTable.getData(9, index_table)) {
            startAnotherActivity(9);
        }
    }

    public void startAnotherActivity(int index) {
        intent.putExtra("number", index);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 1 && data.getBooleanExtra("check", false)) {
                add_image_check(data.getIntExtra("index_mult", -1));
                dataBaseLearnTable.updateData(data.getIntExtra("index_mult", -1) + 1, index_table);
            }
        }
    }

    public void add_image_check(int index) {
        switch (index) {
            case 0:
                ((ImageView) findViewById(R.id.check_zero)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearnTable.getData(2, index_table))
                    ((ImageView) findViewById(R.id.check_one)).setImageResource(R.drawable.ic_check_false);
                break;
            case 1:
                ((ImageView) findViewById(R.id.check_one)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearnTable.getData(3, index_table))
                    ((ImageView) findViewById(R.id.check_two)).setImageResource(R.drawable.ic_check_false);
                break;
            case 2:
                ((ImageView) findViewById(R.id.check_two)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearnTable.getData(4, index_table))
                    ((ImageView) findViewById(R.id.check_three)).setImageResource(R.drawable.ic_check_false);
                break;
            case 3:
                ((ImageView) findViewById(R.id.check_three)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearnTable.getData(5, index_table))
                    ((ImageView) findViewById(R.id.check_four)).setImageResource(R.drawable.ic_check_false);
                break;
            case 4:
                ((ImageView) findViewById(R.id.check_four)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearnTable.getData(6, index_table))
                    ((ImageView) findViewById(R.id.check_five)).setImageResource(R.drawable.ic_check_false);
                break;
            case 5:
                ((ImageView) findViewById(R.id.check_five)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearnTable.getData(7, index_table))
                    ((ImageView) findViewById(R.id.check_six)).setImageResource(R.drawable.ic_check_false);
                break;
            case 6:
                ((ImageView) findViewById(R.id.check_six)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearnTable.getData(8, index_table))
                    ((ImageView) findViewById(R.id.check_seven)).setImageResource(R.drawable.ic_check_false);
                break;
            case 7:
                ((ImageView) findViewById(R.id.check_seven)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearnTable.getData(9, index_table))
                    ((ImageView) findViewById(R.id.check_eight)).setImageResource(R.drawable.ic_check_false);
                break;
            case 8:
                ((ImageView) findViewById(R.id.check_eight)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearnTable.getData(10, index_table))
                    ((ImageView) findViewById(R.id.check_nine)).setImageResource(R.drawable.ic_check_false);
                break;
            case 9:
                ((ImageView) findViewById(R.id.check_nine)).setImageResource(R.drawable.ic_check_true);
                break;
        }
    }

    private void setImageNumber(int number) {
        switch (number) {
            case 1:
                ((ImageView) findViewById(R.id.ic_mul_zero)).setImageResource(R.drawable.ic_one_g);
                ((ImageView) findViewById(R.id.ic_mul_one)).setImageResource(R.drawable.ic_one_g);
                ((ImageView) findViewById(R.id.ic_mul_two)).setImageResource(R.drawable.ic_one_g);
                ((ImageView) findViewById(R.id.ic_mul_three)).setImageResource(R.drawable.ic_one_g);
                ((ImageView) findViewById(R.id.ic_mul_four)).setImageResource(R.drawable.ic_one_g);
                ((ImageView) findViewById(R.id.ic_mul_five)).setImageResource(R.drawable.ic_one_g);
                ((ImageView) findViewById(R.id.ic_mul_six)).setImageResource(R.drawable.ic_one_g);
                ((ImageView) findViewById(R.id.ic_mul_seven)).setImageResource(R.drawable.ic_one_g);
                ((ImageView) findViewById(R.id.ic_mul_eight)).setImageResource(R.drawable.ic_one_g);
                ((ImageView) findViewById(R.id.ic_mul_nine)).setImageResource(R.drawable.ic_one_g);
                break;
            case 2:
                ((ImageView) findViewById(R.id.ic_mul_zero)).setImageResource(R.drawable.ic_two_g);
                ((ImageView) findViewById(R.id.ic_mul_one)).setImageResource(R.drawable.ic_two_g);
                ((ImageView) findViewById(R.id.ic_mul_two)).setImageResource(R.drawable.ic_two_g);
                ((ImageView) findViewById(R.id.ic_mul_three)).setImageResource(R.drawable.ic_two_g);
                ((ImageView) findViewById(R.id.ic_mul_four)).setImageResource(R.drawable.ic_two_g);
                ((ImageView) findViewById(R.id.ic_mul_five)).setImageResource(R.drawable.ic_two_g);
                ((ImageView) findViewById(R.id.ic_mul_six)).setImageResource(R.drawable.ic_two_g);
                ((ImageView) findViewById(R.id.ic_mul_seven)).setImageResource(R.drawable.ic_two_g);
                ((ImageView) findViewById(R.id.ic_mul_eight)).setImageResource(R.drawable.ic_two_g);
                ((ImageView) findViewById(R.id.ic_mul_nine)).setImageResource(R.drawable.ic_two_g);
                break;
            case 3:
                ((ImageView) findViewById(R.id.ic_mul_zero)).setImageResource(R.drawable.ic_three_g);
                ((ImageView) findViewById(R.id.ic_mul_one)).setImageResource(R.drawable.ic_three_g);
                ((ImageView) findViewById(R.id.ic_mul_two)).setImageResource(R.drawable.ic_three_g);
                ((ImageView) findViewById(R.id.ic_mul_three)).setImageResource(R.drawable.ic_three_g);
                ((ImageView) findViewById(R.id.ic_mul_four)).setImageResource(R.drawable.ic_three_g);
                ((ImageView) findViewById(R.id.ic_mul_five)).setImageResource(R.drawable.ic_three_g);
                ((ImageView) findViewById(R.id.ic_mul_six)).setImageResource(R.drawable.ic_three_g);
                ((ImageView) findViewById(R.id.ic_mul_seven)).setImageResource(R.drawable.ic_three_g);
                ((ImageView) findViewById(R.id.ic_mul_eight)).setImageResource(R.drawable.ic_three_g);
                ((ImageView) findViewById(R.id.ic_mul_nine)).setImageResource(R.drawable.ic_three_g);
                break;
            case 4:
                ((ImageView) findViewById(R.id.ic_mul_zero)).setImageResource(R.drawable.ic_four_g);
                ((ImageView) findViewById(R.id.ic_mul_one)).setImageResource(R.drawable.ic_four_g);
                ((ImageView) findViewById(R.id.ic_mul_two)).setImageResource(R.drawable.ic_four_g);
                ((ImageView) findViewById(R.id.ic_mul_three)).setImageResource(R.drawable.ic_four_g);
                ((ImageView) findViewById(R.id.ic_mul_four)).setImageResource(R.drawable.ic_four_g);
                ((ImageView) findViewById(R.id.ic_mul_five)).setImageResource(R.drawable.ic_four_g);
                ((ImageView) findViewById(R.id.ic_mul_six)).setImageResource(R.drawable.ic_four_g);
                ((ImageView) findViewById(R.id.ic_mul_seven)).setImageResource(R.drawable.ic_four_g);
                ((ImageView) findViewById(R.id.ic_mul_eight)).setImageResource(R.drawable.ic_four_g);
                ((ImageView) findViewById(R.id.ic_mul_nine)).setImageResource(R.drawable.ic_four_g);
                break;
            case 5:
                ((ImageView) findViewById(R.id.ic_mul_zero)).setImageResource(R.drawable.ic_five_g);
                ((ImageView) findViewById(R.id.ic_mul_one)).setImageResource(R.drawable.ic_five_g);
                ((ImageView) findViewById(R.id.ic_mul_two)).setImageResource(R.drawable.ic_five_g);
                ((ImageView) findViewById(R.id.ic_mul_three)).setImageResource(R.drawable.ic_five_g);
                ((ImageView) findViewById(R.id.ic_mul_four)).setImageResource(R.drawable.ic_five_g);
                ((ImageView) findViewById(R.id.ic_mul_five)).setImageResource(R.drawable.ic_five_g);
                ((ImageView) findViewById(R.id.ic_mul_six)).setImageResource(R.drawable.ic_five_g);
                ((ImageView) findViewById(R.id.ic_mul_seven)).setImageResource(R.drawable.ic_five_g);
                ((ImageView) findViewById(R.id.ic_mul_eight)).setImageResource(R.drawable.ic_five_g);
                ((ImageView) findViewById(R.id.ic_mul_nine)).setImageResource(R.drawable.ic_five_g);
                break;
            case 6:
                ((ImageView) findViewById(R.id.ic_mul_zero)).setImageResource(R.drawable.ic_six_g);
                ((ImageView) findViewById(R.id.ic_mul_one)).setImageResource(R.drawable.ic_six_g);
                ((ImageView) findViewById(R.id.ic_mul_two)).setImageResource(R.drawable.ic_six_g);
                ((ImageView) findViewById(R.id.ic_mul_three)).setImageResource(R.drawable.ic_six_g);
                ((ImageView) findViewById(R.id.ic_mul_four)).setImageResource(R.drawable.ic_six_g);
                ((ImageView) findViewById(R.id.ic_mul_five)).setImageResource(R.drawable.ic_six_g);
                ((ImageView) findViewById(R.id.ic_mul_six)).setImageResource(R.drawable.ic_six_g);
                ((ImageView) findViewById(R.id.ic_mul_seven)).setImageResource(R.drawable.ic_six_g);
                ((ImageView) findViewById(R.id.ic_mul_eight)).setImageResource(R.drawable.ic_six_g);
                ((ImageView) findViewById(R.id.ic_mul_nine)).setImageResource(R.drawable.ic_six_g);
                break;
            case 7:
                ((ImageView) findViewById(R.id.ic_mul_zero)).setImageResource(R.drawable.ic_seven_g);
                ((ImageView) findViewById(R.id.ic_mul_one)).setImageResource(R.drawable.ic_seven_g);
                ((ImageView) findViewById(R.id.ic_mul_two)).setImageResource(R.drawable.ic_seven_g);
                ((ImageView) findViewById(R.id.ic_mul_three)).setImageResource(R.drawable.ic_seven_g);
                ((ImageView) findViewById(R.id.ic_mul_four)).setImageResource(R.drawable.ic_seven_g);
                ((ImageView) findViewById(R.id.ic_mul_five)).setImageResource(R.drawable.ic_seven_g);
                ((ImageView) findViewById(R.id.ic_mul_six)).setImageResource(R.drawable.ic_seven_g);
                ((ImageView) findViewById(R.id.ic_mul_seven)).setImageResource(R.drawable.ic_seven_g);
                ((ImageView) findViewById(R.id.ic_mul_eight)).setImageResource(R.drawable.ic_seven_g);
                ((ImageView) findViewById(R.id.ic_mul_nine)).setImageResource(R.drawable.ic_seven_g);
                break;
            case 8:
                ((ImageView) findViewById(R.id.ic_mul_zero)).setImageResource(R.drawable.ic_eight_g);
                ((ImageView) findViewById(R.id.ic_mul_one)).setImageResource(R.drawable.ic_eight_g);
                ((ImageView) findViewById(R.id.ic_mul_two)).setImageResource(R.drawable.ic_eight_g);
                ((ImageView) findViewById(R.id.ic_mul_three)).setImageResource(R.drawable.ic_eight_g);
                ((ImageView) findViewById(R.id.ic_mul_four)).setImageResource(R.drawable.ic_eight_g);
                ((ImageView) findViewById(R.id.ic_mul_five)).setImageResource(R.drawable.ic_eight_g);
                ((ImageView) findViewById(R.id.ic_mul_six)).setImageResource(R.drawable.ic_eight_g);
                ((ImageView) findViewById(R.id.ic_mul_seven)).setImageResource(R.drawable.ic_eight_g);
                ((ImageView) findViewById(R.id.ic_mul_eight)).setImageResource(R.drawable.ic_eight_g);
                ((ImageView) findViewById(R.id.ic_mul_nine)).setImageResource(R.drawable.ic_eight_g);
                break;
            case 9:
                ((ImageView) findViewById(R.id.ic_mul_zero)).setImageResource(R.drawable.ic_nine_g);
                ((ImageView) findViewById(R.id.ic_mul_one)).setImageResource(R.drawable.ic_nine_g);
                ((ImageView) findViewById(R.id.ic_mul_two)).setImageResource(R.drawable.ic_nine_g);
                ((ImageView) findViewById(R.id.ic_mul_three)).setImageResource(R.drawable.ic_nine_g);
                ((ImageView) findViewById(R.id.ic_mul_four)).setImageResource(R.drawable.ic_nine_g);
                ((ImageView) findViewById(R.id.ic_mul_five)).setImageResource(R.drawable.ic_nine_g);
                ((ImageView) findViewById(R.id.ic_mul_six)).setImageResource(R.drawable.ic_nine_g);
                ((ImageView) findViewById(R.id.ic_mul_seven)).setImageResource(R.drawable.ic_nine_g);
                ((ImageView) findViewById(R.id.ic_mul_eight)).setImageResource(R.drawable.ic_nine_g);
                ((ImageView) findViewById(R.id.ic_mul_nine)).setImageResource(R.drawable.ic_nine_g);
                break;
        }
    }

    public void back_learn(View view) {
        if (dataBaseLearnTable.getData(10, index_table))
            setResult(1, (new Intent()).putExtra("level", 2));
        finish();
    }
}
