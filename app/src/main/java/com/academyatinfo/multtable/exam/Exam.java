package com.academyatinfo.multtable.exam;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.certification.mvp.Certification;
import com.academyatinfo.multtable.ui.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Exam extends BaseActivity implements ExamContract.View {

    @BindView(R.id.text_card_one)
    TextView text1;
    @BindView(R.id.text_card_two)
    TextView text2;
    @BindView(R.id.text_card_three)
    TextView text3;
    @BindView(R.id.text_card_four)
    TextView text4;
    @BindView(R.id.soccer_table_exam)
    TextView soccer;
    @BindView(R.id.full_soccer)
    TextView full_soccer;
    @BindView(R.id.first_number_exam)
    ImageView firstImage;
    @BindView(R.id.second_number_exam)
    ImageView secondImage;
    @BindView(R.id.chrono)
    Chronometer chronometer;

    private TextView result_exam;
    private Dialog dialog;
    private Button button;
    private int long_table, level, value_random1, value_random2, value_random3, first, second, answer, index_empty, value_soccer;
    private Random random;
    private Calendar calendar;
    private MediaPlayer success, wrong, end;
    private ArrayList<Integer> table1, table2;
    private boolean section = false;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        intent = getIntent();
        long_table = intent.getIntExtra("long_table", 0);
        level = intent.getIntExtra("level", 0);
        table1 = new ArrayList<>();
        table2 = new ArrayList<>();

        success = MediaPlayer.create(this, R.raw.success);
        wrong = MediaPlayer.create(this, R.raw.wrong);
        end = MediaPlayer.create(this, R.raw.finish);

        createTable(long_table);
        chronometer.start();
        random = new Random();
        index_empty = generateRandom();
        answer = prepare_game(table1, table2);
        inputByIndex(index_empty, answer);
        value_soccer = 0;

        soccer.setText("النقاط 0");
    }

    private void createTable(int long_table) {
        if (level >= 6) {
            for (int i = 0; i <= 9; i++) {
                for (int j = 1; j <= long_table; j++) {
                    table1.add(j);
                }
            }
            for (int i = 1; i <= long_table; i++) {
                for (int j = 0; j <= 9; j++) {
                    table2.add(j);
                }
            }
            full_soccer.setText("عدد الأسئلة " + (long_table * 10));
        } else {
            if (level == 3) {
                for (int j = 0; j <= 4; j++) {
                    table1.add(long_table);
                    table2.add(j);
                }
                full_soccer.setText("عدد الأسئلة 5");
            } else if (level == 4) {
                for (int j = 5; j <= 9; j++) {
                    table1.add(long_table);
                    table2.add(j);
                }
                full_soccer.setText("عدد الأسئلة 5");
            } else {
                for (int j = 0; j <= 9; j++) {
                    table1.add(long_table);
                    table2.add(j);
                }
                full_soccer.setText("عدد الأسئلة 10");
            }
        }
    }

    public int generateRandom() {
        value_random1 = random.nextInt(long_table * 9);
        value_random2 = random.nextInt((long_table * 9) / 2);
        value_random3 = random.nextInt((long_table * 9) / 10 + 1);

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i <= 3; i++)
            arrayList.add(i);
        int index = random.nextInt(arrayList.size());
        inputByIndex(arrayList.get(index), value_random1);
        arrayList.remove(index);
        index = random.nextInt(arrayList.size());
        inputByIndex(arrayList.get(index), value_random2);
        arrayList.remove(index);
        index = random.nextInt(arrayList.size());
        inputByIndex(arrayList.get(index), value_random3);
        arrayList.remove(index);
        return arrayList.get(arrayList.size() - 1);
    }

    public int prepare_game(ArrayList<Integer> t1, ArrayList<Integer> t2) {
        int index;
        index = random.nextInt(t1.size());
        first = t1.get(index);
        setImageRandom(first, firstImage);
        t1.remove(index);
        index = random.nextInt(t2.size());
        second = t2.get(index);
        setImageRandom(second, secondImage);
        t2.remove(index);
        return first * second;
    }


    public void inputByIndex(int i, int value) {
        switch (i) {
            case 0:
                text1.setText(value + "");
                break;
            case 1:
                text2.setText(value + "");
                break;
            case 2:
                text3.setText(value + "");
                break;
            case 3:
                text4.setText(value + "");
                break;
        }
    }

    public void play_game(boolean wine, final TextView text) {
        if (wine) {
            value_soccer++;
            soccer.setText("النقاط " + value_soccer);
            text.setBackgroundColor(Color.parseColor("#03d1bd"));
            text.setTextColor(Color.parseColor("#FFF1F1F1"));
            success.start();
        } else {
            text.setBackgroundColor(Color.parseColor("#FFFF4046"));
            text.setTextColor(Color.parseColor("#FFF1F1F1"));
            wrong.start();
        }

        final AsyncTask task = new AsyncTask() {
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                if (table1.size() > 0 && table2.size() > 0) {
                    index_empty = generateRandom();
                    answer = prepare_game(table1, table2);
                    inputByIndex(index_empty, answer);
                } else {
                    dialog = new Dialog(Exam.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    DisplayMetrics metrics = getResources().getDisplayMetrics();
                    int width = metrics.widthPixels;
                    dialog.getWindow().setLayout(width - 10, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                    dialog.setContentView(R.layout.custom_dialog_result);
                    dialog.setCancelable(false);
                    result_exam = (TextView) dialog.findViewById(R.id.text_result_exam);


                    if (value_soccer >= (Integer.parseInt(full_soccer.getText().toString()) * 0.7))
                        result_exam.setText("ممتاز لقد حصلت على معدل\n" + value_soccer + " من " + full_soccer.getText().toString() + "\nفي وقت قدره " + chronometer.getText() +
                                "\nواصل على هذا المنوال");
                    else if (value_soccer < (Integer.parseInt(full_soccer.getText().toString()) * 0.7) && value_soccer >= (Integer.parseInt(full_soccer.getText().toString()) * 0.5))
                        result_exam.setText("لقد حصلت على معدل\n" + value_soccer + " من " + full_soccer.getText().toString() + "\nفي وقت قدره " + chronometer.getText() +
                                "\nأعد المراجعة للحصول على علامة ممتاز");
                    else if (value_soccer < (Integer.parseInt(full_soccer.getText().toString()) * 0.5))
                        result_exam.setText("لقد حصلت على معدل\n" + value_soccer + " من " + full_soccer.getText().toString() + "\nفي وقت قدره " + chronometer.getText() +
                                "\nيمكنك تحسين مهاراتك بالتعلم أكثر");

                    button = (Button) dialog.findViewById(R.id.click_done);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (Integer.parseInt(full_soccer.getText().toString()) == 90 && level == 7) {
                                intent = new Intent(Exam.this, Certification.class);
                                intent.putExtra("time", chronometer.getText().toString());
                                intent.putExtra("soccer", value_soccer + "");
                                calendar = Calendar.getInstance();
                                intent.putExtra("date", calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH)
                                        + "/" + calendar.get(Calendar.DAY_OF_MONTH));
                                DisplayMetrics metrics = getResources().getDisplayMetrics();
                                int width = metrics.widthPixels;
                                dialog.getWindow().setLayout(width - 10, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                                dialog.setContentView(R.layout.layout_name);
                                final EditText text_name = (EditText) dialog.findViewById(R.id.text_name);
                                (dialog.findViewById(R.id.button_name)).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (text_name.getText().toString().length() > 3) {
                                            intent.putExtra("name", text_name.getText().toString());
                                            dialog.hide();
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                            } else {
                                if (value_soccer >= (Integer.parseInt(full_soccer.getText().toString()) * 0.7))
                                    setResult(1, intent.putExtra("level", level));
                                dialog.hide();
                                finish();
                            }
                        }
                    });
                    end.start();
                    chronometer.stop();
                    dialog.show();
                }
                section = false;
                text.setBackgroundColor(Color.parseColor("#00000000"));
                text.setTextColor(Color.parseColor("#30af94"));
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task.execute();
    }


    public void click_card_one(View view) {
        if (!section) {
            section = true;
            if (answer == Integer.parseInt(text1.getText().toString())) {
                play_game(true, text1);
            } else {
                play_game(false, text1);
            }
        }
    }

    public void click_card_two(View view) {
        if (!section) {
            section = true;
            if (answer == Integer.parseInt(text2.getText().toString())) {
                play_game(true, text2);
            } else {
                play_game(false, text2);
            }
        }
    }

    public void click_card_three(View view) {
        if (!section) {
            section = true;
            if (answer == Integer.parseInt(text3.getText().toString())) {
                play_game(true, text3);
            } else {
                play_game(false, text3);
            }
        }
    }

    public void click_card_four(View view) {
        if (!section) {
            section = true;
            if (answer == Integer.parseInt(text4.getText().toString())) {
                play_game(true, text4);
            } else {
                play_game(false, text4);
            }
        }
    }


    public void setImageRandom(int value, View view) {
        switch (value) {
            case 0:
                ((ImageView) view).setImageResource(R.drawable.ic_zero_b);
                break;
            case 1:
                ((ImageView) view).setImageResource(R.drawable.ic_one_b);
                break;
            case 2:
                ((ImageView) view).setImageResource(R.drawable.ic_two_b);
                break;
            case 3:
                ((ImageView) view).setImageResource(R.drawable.ic_three_b);
                break;
            case 4:
                ((ImageView) view).setImageResource(R.drawable.ic_four_b);
                break;
            case 5:
                ((ImageView) view).setImageResource(R.drawable.ic_five_b);
                break;
            case 6:
                ((ImageView) view).setImageResource(R.drawable.ic_six_b);
                break;
            case 7:
                ((ImageView) view).setImageResource(R.drawable.ic_seven_b);
                break;
            case 8:
                ((ImageView) view).setImageResource(R.drawable.ic_eight_b);
                break;
            case 9:
                ((ImageView) view).setImageResource(R.drawable.ic_nine_b);
                break;
        }
    }

    public void click_finish_exam(View view) {
    }
}
