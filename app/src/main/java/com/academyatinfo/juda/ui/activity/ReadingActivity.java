package com.academyatinfo.juda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.academyatinfo.juda.BaseActivity;
import com.academyatinfo.juda.R;
import com.academyatinfo.juda.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadingActivity extends BaseActivity {

    @BindView(R.id.zero_result)
    TextView tvResultZero;
    @BindView(R.id.one_result)
    TextView tvResultOne;
    @BindView(R.id.two_result)
    TextView tvResultTwo;
    @BindView(R.id.three_result)
    TextView tvResultThree;
    @BindView(R.id.four_result)
    TextView tvResultFour;
    @BindView(R.id.five_result)
    TextView tvResultFive;
    @BindView(R.id.six_result)
    TextView tvResultSix;
    @BindView(R.id.seven_result)
    TextView tvResultSeven;
    @BindView(R.id.eight_result)
    TextView tvResultEight;
    @BindView(R.id.nine_result)
    TextView tvResultNine;
    
    @BindView(R.id.zero_second)
    TextView tvSecondZero;
    @BindView(R.id.one_second)
    TextView tvSecondOne;
    @BindView(R.id.two_second)
    TextView tvSecondTwo;
    @BindView(R.id.three_second)
    TextView tvSecondThree;
    @BindView(R.id.four_second)
    TextView tvSecondFour;
    @BindView(R.id.five_second)
    TextView tvSecondFive;
    @BindView(R.id.six_second)
    TextView tvSecondSix;
    @BindView(R.id.seven_second)
    TextView tvSecondSeven;
    @BindView(R.id.eight_second)
    TextView tvSecondEight;
    @BindView(R.id.nine_second)
    TextView tvSecondNine;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_reading);

        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        intent = getIntent();

        int table = intent.getIntExtra(Constants.KEY_TABLE, 0);

        tvSecondZero.setText(String.valueOf(table));
        tvSecondOne.setText(String.valueOf(table));
        tvSecondTwo.setText(String.valueOf(table));
        tvSecondThree.setText(String.valueOf(table));
        tvSecondFour.setText(String.valueOf(table));
        tvSecondFive.setText(String.valueOf(table));
        tvSecondSix.setText(String.valueOf(table));
        tvSecondSeven.setText(String.valueOf(table));
        tvSecondEight.setText(String.valueOf(table));
        tvSecondNine.setText(String.valueOf(table));

        tvResultZero.setText(String.valueOf((0)));
        tvResultOne.setText(String.valueOf((table)));
        tvResultTwo.setText(String.valueOf((table * 2)));
        tvResultThree.setText(String.valueOf((table * 3)));
        tvResultFour.setText(String.valueOf((table * 4)));
        tvResultFive.setText(String.valueOf((table * 5)));
        tvResultSix.setText(String.valueOf((table * 6)));
        tvResultSeven.setText(String.valueOf((table * 7)));
        tvResultEight.setText(String.valueOf((table * 8)));
        tvResultNine.setText(String.valueOf((table * 9)));

    }

    public void clickFinish(View view) {
        intent.putExtra(Constants.KEY_LEVEL, 1);
        setResult(RESULT_OK, intent);
        finish();
    }
}
