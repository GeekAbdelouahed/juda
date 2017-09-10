package com.academyatinfo.multtable.operation;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.databases.DataTables;
import com.academyatinfo.multtable.tools.Constants;
import com.academyatinfo.multtable.ui.activitys.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OperationActivity extends BaseActivity implements OperationContract.View {

    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.soccer_table)
    TextView soccer_table;
    private int index_table, mult_in, result, soccer;
    private Intent intent;
    private DataTables dataTablesCheck;
    private MediaPlayer success, wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_operation);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        intent = getIntent();
        index_table = intent.getIntExtra(Constants.KEY_INDEX_TABLE, 0);
        mult_in = intent.getIntExtra(Constants.KEY_NUMBER, 0);
        result = index_table * mult_in;
        setImageNumberTable(index_table, (ImageView) findViewById(R.id.first_number));
        setImageNumberMul(mult_in, (ImageView) findViewById(R.id.second_number));

        dataTablesCheck = new DataTables(this);
        soccer = 0;
        for (int i = 1; i <= 10; i++) {
            if (dataTablesCheck.getData(i, index_table)) {
                soccer++;
            }
        }

        soccer_table.setText(getResources().getString(R.string.count_points) + " " + soccer);

        success = MediaPlayer.create(this, R.raw.success);
        wrong = MediaPlayer.create(this, R.raw.wrong);

        answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (answer.getText().toString().length() == 2) {
                    if (Integer.parseInt(answer.getText().toString()) != result) {
                        answer.setBackgroundColor(Color.parseColor("#FFFF4046"));
                        answer.setTextColor(Color.parseColor("#FFF1F1F1"));
                        wrong.start();
                    }
                } else {
                    answer.setBackgroundColor(Color.parseColor("#00000000"));
                    answer.setTextColor(Color.parseColor("#30af94"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void check_answer(int result) {
        if (this.result == result) {
            soccer_table.setText(getResources().getString(R.string.count_points) + " " + (soccer + 1));
            intent.putExtra(Constants.KEY_CHECK, true);
            intent.putExtra(Constants.KEY_MULT, mult_in);

            setResult(1, intent);

            answer.setBackgroundColor(Color.parseColor("#03d1bd"));
            answer.setTextColor(Color.parseColor("#FFF1F1F1"));
            success.start();
            AsyncTask task = new AsyncTask() {
                @Override
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    try {
                        Thread.sleep(900);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected Object doInBackground(Object[] objects) {
                    finish();
                    return null;
                }
            };
            task.execute();
        }
    }

    public void answer_one(View view) {
        answer.setText(answer.getText() + "1");
        check_answer(Integer.parseInt(answer.getText().toString()));
    }

    public void answer_two(View view) {
        answer.setText(answer.getText() + "2");
        check_answer(Integer.parseInt(answer.getText().toString()));
    }

    public void answer_three(View view) {
        answer.setText(answer.getText() + "3");
        check_answer(Integer.parseInt(answer.getText().toString()));
    }

    public void answer_four(View view) {
        answer.setText(answer.getText() + "4");
        check_answer(Integer.parseInt(answer.getText().toString()));
    }

    public void answer_five(View view) {
        answer.setText(answer.getText() + "5");
        check_answer(Integer.parseInt(answer.getText().toString()));
    }

    public void answer_six(View view) {
        answer.setText(answer.getText() + "6");
        check_answer(Integer.parseInt(answer.getText().toString()));
    }

    public void answer_seven(View view) {
        answer.setText(answer.getText() + "7");
        check_answer(Integer.parseInt(answer.getText().toString()));
    }

    public void answer_eight(View view) {
        answer.setText(answer.getText() + "8");
        check_answer(Integer.parseInt(answer.getText().toString()));
    }

    public void answer_nine(View view) {
        answer.setText(answer.getText() + "9");
        check_answer(Integer.parseInt(answer.getText().toString()));
    }

    public void answer_zero(View view) {
        answer.setText(answer.getText() + "0");
        check_answer(Integer.parseInt(answer.getText().toString()));
    }

    public void click_delete(View view) {
        if (answer.getText().toString().length() > 0)
            answer.setText(answer.getText().toString().substring(0, answer.getText().toString().length() - 1));
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

    public void click_back(View view) {
        finish();
    }
}
