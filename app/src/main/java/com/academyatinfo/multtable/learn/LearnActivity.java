package com.academyatinfo.multtable.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LearnActivity extends BaseActivity {

    @BindView(R.id.zero_result)
    TextView result_zero;
    @BindView(R.id.one_result)
    TextView result_one;
    @BindView(R.id.two_result)
    TextView result_two;
    @BindView(R.id.three_result)
    TextView result_three;
    @BindView(R.id.four_result)
    TextView result_four;
    @BindView(R.id.five_result)
    TextView result_five;
    @BindView(R.id.six_result)
    TextView result_six;
    @BindView(R.id.seven_result)
    TextView result_seven;
    @BindView(R.id.eight_result)
    TextView result_eight;
    @BindView(R.id.nine_result)
    TextView result_nine;
    @BindView(R.id.zero_second)
    TextView second_zero;
    @BindView(R.id.one_second)
    TextView second_one;
    @BindView(R.id.two_second)
    TextView second_two;
    @BindView(R.id.three_second)
    TextView second_three;
    @BindView(R.id.four_second)
    TextView second_four;
    @BindView(R.id.five_second)
    TextView second_five;
    @BindView(R.id.six_second)
    TextView second_six;
    @BindView(R.id.seven_second)
    TextView second_seven;
    @BindView(R.id.eight_second)
    TextView second_eight;
    @BindView(R.id.nine_second)
    TextView second_nine;

    private Intent intent;
    private int table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_learn);

        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        intent = getIntent();
        table = intent.getIntExtra("table", 0);

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
