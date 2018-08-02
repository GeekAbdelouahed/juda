package com.academyatinfo.juda.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.academyatinfo.juda.BaseActivity;
import com.academyatinfo.juda.R;
import com.academyatinfo.juda.data.DataTables;
import com.academyatinfo.juda.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.academyatinfo.juda.utils.Constants.DELAY_ANSWER;

public class OperationsActivity extends BaseActivity {

    @BindView(R.id.answer)
    TextView tvAnswer;
    @BindView(R.id.points_table)
    TextView tvPointsTable;

    private TextWatcher textWatcher;

    private final Handler answerHandler = new Handler();
    private final Runnable answerRunnable = new Runnable() {
        @Override
        public void run() {
            setResult(RESULT_OK, intent);
            finish();
        }
    };

    private MediaPlayer soundSuccess, soundWrong;
    private Intent intent;
    private int number, result, points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_operation);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        intent = getIntent();
        int index_table = intent.getIntExtra(Constants.KEY_INDEX_TABLE, 0);
        number = intent.getIntExtra(Constants.KEY_NUMBER, 0);
        result = index_table * number;
        setImageNumberTable(index_table, findViewById(R.id.first_number));
        setImageNumberMul(number, findViewById(R.id.second_number));

        DataTables dataTablesCheck = new DataTables(this);
        points = 0;
        for (int i = 1; i <= 10; i++) {
            if (dataTablesCheck.getData(i, index_table)) {
                points++;
            } else
                break;
        }

        String mPoints = getResources().getString(R.string.count_points) + " " + points;
        tvPointsTable.setText(mPoints);

        soundSuccess = MediaPlayer.create(this, R.raw.success);
        soundWrong = MediaPlayer.create(this, R.raw.wrong);

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (tvAnswer.getText().toString().length() == 2) {
                    if (Integer.parseInt(tvAnswer.getText().toString()) != result) {
                        tvAnswer.setBackgroundColor(getResources().getColor(R.color.background_wrong));
                        tvAnswer.setTextColor(getResources().getColor(R.color.text_wrong));
                        soundWrong.start();
                    }
                } else {
                    tvAnswer.setBackgroundColor(getResources().getColor(R.color.background_default));
                    tvAnswer.setTextColor(getResources().getColor(R.color.text_default));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        tvAnswer.addTextChangedListener(textWatcher);
    }

    private void checkAnswer(int result) {
        if (this.result == result) {
            String mPoints = getResources().getString(R.string.count_points) + " " + (points + 1);
            tvPointsTable.setText(mPoints);
            intent.putExtra(Constants.KEY_CHECK, true);
            intent.putExtra(Constants.KEY_MULT, number);
            tvAnswer.setBackgroundColor(getResources().getColor(R.color.background_success));
            tvAnswer.setTextColor(getResources().getColor(R.color.text_success));
            soundSuccess.start();

            answerHandler.postDelayed(answerRunnable, DELAY_ANSWER);
        }
    }

    public void clickNumberOne(View view) {
        String answer = tvAnswer.getText() + "1";
        tvAnswer.setText(answer);
        checkAnswer(Integer.parseInt(tvAnswer.getText().toString()));
    }

    public void clickNumberTwo(View view) {
        String answer = tvAnswer.getText() + "2";
        tvAnswer.setText(answer);
        checkAnswer(Integer.parseInt(tvAnswer.getText().toString()));
    }

    public void clickNumberThree(View view) {
        String answer = tvAnswer.getText() + "3";
        tvAnswer.setText(answer);
        checkAnswer(Integer.parseInt(tvAnswer.getText().toString()));
    }

    public void clickNumberFour(View view) {
        String answer = tvAnswer.getText() + "4";
        tvAnswer.setText(answer);
        checkAnswer(Integer.parseInt(tvAnswer.getText().toString()));
    }

    public void clickNumberFive(View view) {
        String answer = tvAnswer.getText() + "5";
        tvAnswer.setText(answer);
        checkAnswer(Integer.parseInt(tvAnswer.getText().toString()));
    }

    public void clickNumberSix(View view) {
        String answer = tvAnswer.getText() + "6";
        tvAnswer.setText(answer);
        checkAnswer(Integer.parseInt(tvAnswer.getText().toString()));
    }

    public void clickNumberSeven(View view) {
        String answer = tvAnswer.getText() + "7";
        tvAnswer.setText(answer);
        checkAnswer(Integer.parseInt(tvAnswer.getText().toString()));
    }

    public void clickNumberEight(View view) {
        String answer = tvAnswer.getText() + "8";
        tvAnswer.setText(answer);
        checkAnswer(Integer.parseInt(tvAnswer.getText().toString()));
    }

    public void clickNumberNine(View view) {
        String answer = tvAnswer.getText() + "9";
        tvAnswer.setText(answer);
        checkAnswer(Integer.parseInt(tvAnswer.getText().toString()));
    }

    public void clickNumberZero(View view) {
        String answer = tvAnswer.getText() + "0";
        tvAnswer.setText(answer);
        checkAnswer(Integer.parseInt(tvAnswer.getText().toString()));
    }

    public void clickDelete(View view) {
        if (tvAnswer.getText().toString().length() > 0)
            tvAnswer.setText(tvAnswer.getText().toString().substring(0, tvAnswer.getText().toString().length() - 1));
    }

    private void setImageNumberTable(int number, ImageView imageView) {
        switch (number) {
            case 0:
                imageView.setImageResource(R.drawable.ic_zero_g);
                break;
            case 1:
                imageView.setImageResource(R.drawable.ic_one_g);
                break;
            case 2:
                imageView.setImageResource(R.drawable.ic_two_g);
                break;
            case 3:
                imageView.setImageResource(R.drawable.ic_three_g);
                break;
            case 4:
                imageView.setImageResource(R.drawable.ic_four_g);
                break;
            case 5:
                imageView.setImageResource(R.drawable.ic_five_g);
                break;
            case 6:
                imageView.setImageResource(R.drawable.ic_six_g);
                break;
            case 7:
                imageView.setImageResource(R.drawable.ic_seven_g);
                break;
            case 8:
                imageView.setImageResource(R.drawable.ic_eight_g);
                break;
            case 9:
                imageView.setImageResource(R.drawable.ic_nine_g);
                break;
        }
    }

    private void setImageNumberMul(int number, ImageView imageView) {
        switch (number) {
            case 0:
                imageView.setImageResource(R.drawable.ic_zero_b);
                break;
            case 1:
                imageView.setImageResource(R.drawable.ic_one_b);
                break;
            case 2:
                imageView.setImageResource(R.drawable.ic_two_b);
                break;
            case 3:
                imageView.setImageResource(R.drawable.ic_three_b);
                break;
            case 4:
                imageView.setImageResource(R.drawable.ic_four_b);
                break;
            case 5:
                imageView.setImageResource(R.drawable.ic_five_b);
                break;
            case 6:
                imageView.setImageResource(R.drawable.ic_six_b);
                break;
            case 7:
                imageView.setImageResource(R.drawable.ic_seven_b);
                break;
            case 8:
                imageView.setImageResource(R.drawable.ic_eight_b);
                break;
            case 9:
                imageView.setImageResource(R.drawable.ic_nine_b);
                break;
        }
    }

    public void clickFinish(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        answerHandler.removeCallbacks(answerRunnable);
        tvAnswer.removeTextChangedListener(textWatcher);
    }

}
