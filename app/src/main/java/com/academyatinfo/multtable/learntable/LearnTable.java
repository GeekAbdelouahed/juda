package com.academyatinfo.multtable.learntable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.levels.LevelsContract;
import com.academyatinfo.multtable.tools.BaseActivity;

public class LearnTable extends BaseActivity implements LevelsContract.View {

    private Intent intent;
    private int table;
    private TextView result_zero, result_one, result_two, result_three, result_four, result_five, result_six, result_seven, result_eight, result_nine,
            second_zero, second_one, second_two, second_three, second_four, second_five, second_six, second_seven, second_eight, second_nine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learntable);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        intent = getIntent();
        table = intent.getIntExtra("table", 0);

        result_zero = (TextView) findViewById(R.id.zero_result);
        result_one = (TextView) findViewById(R.id.one_result);
        result_two = (TextView) findViewById(R.id.two_result);
        result_three = (TextView) findViewById(R.id.three_result);
        result_four = (TextView) findViewById(R.id.four_result);
        result_five = (TextView) findViewById(R.id.five_result);
        result_six = (TextView) findViewById(R.id.six_result);
        result_seven = (TextView) findViewById(R.id.seven_result);
        result_eight = (TextView) findViewById(R.id.eight_result);
        result_nine = (TextView) findViewById(R.id.nine_result);

        second_zero = (TextView) findViewById(R.id.zero_second);
        second_one = (TextView) findViewById(R.id.one_second);
        second_two = (TextView) findViewById(R.id.two_second);
        second_three = (TextView) findViewById(R.id.three_second);
        second_four = (TextView) findViewById(R.id.four_second);
        second_five = (TextView) findViewById(R.id.five_second);
        second_six = (TextView) findViewById(R.id.six_second);
        second_seven = (TextView) findViewById(R.id.seven_second);
        second_eight = (TextView) findViewById(R.id.eight_second);
        second_nine = (TextView) findViewById(R.id.nine_second);

        second_zero.setText(table + "");
        second_one.setText(table + "");
        second_two.setText(table + "");
        second_three.setText(table + "");
        second_four.setText(table + "");
        second_five.setText(table + "");
        second_six.setText(table + "");
        second_seven.setText(table + "");
        second_eight.setText(table + "");
        second_nine.setText(table + "");

        result_zero.setText((table * 0) + "");
        result_one.setText((table * 1) + "");
        result_two.setText((table * 2) + "");
        result_three.setText((table * 3) + "");
        result_four.setText((table * 4) + "");
        result_five.setText((table * 5) + "");
        result_six.setText((table * 6) + "");
        result_seven.setText((table * 7) + "");
        result_eight.setText((table * 8) + "");
        result_nine.setText((table * 9) + "");

    }

    public void back_to_learn(View view) {
        setResult(1, (new Intent()).putExtra("level", 1));
        finish();
    }
}
