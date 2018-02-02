package com.academyatinfo.juda.exam;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.academyatinfo.juda.R;
import com.academyatinfo.juda.certification.CertificationActivity;
import com.academyatinfo.juda.databases.DataBaseInfo;
import com.academyatinfo.juda.models.Result;
import com.academyatinfo.juda.tools.Constants;
import com.academyatinfo.juda.ui.activitys.BaseActivity;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class ExamActivity extends BaseActivity {

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

    private int long_table, level, answer, index_empty, valueSoccer, fullSoccer;
    private int value_random1, value_random2, value_random3, first, second;
    private Random random;
    private MediaPlayer success, wrong, end;
    private ArrayList<Integer> table1, table2;
    private boolean section = false;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_exam);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        intent = getIntent();
        long_table = intent.getIntExtra(Constants.KEY_LONG_TABLE, 0);
        level = intent.getIntExtra(Constants.KEY_LEVEL, 0);

        table1 = new ArrayList<>();
        table2 = new ArrayList<>();

        success = MediaPlayer.create(this, R.raw.success);
        wrong = MediaPlayer.create(this, R.raw.wrong);
        end = MediaPlayer.create(this, R.raw.finish);

        createTable(long_table);

        random = new Random();

        index_empty = generateRandom();

        answer = prepare_game(table1, table2);

        inputByIndex(index_empty, answer);

        valueSoccer = 0;

        soccer.setText(getString(R.string.points) + " 0");
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
            fullSoccer = 10 * long_table;
        } else {
            if (level == 3) {
                for (int j = 0; j <= 4; j++) {
                    table1.add(long_table);
                    table2.add(j);
                }
                fullSoccer = 5;
            } else if (level == 4) {
                for (int j = 5; j <= 9; j++) {
                    table1.add(long_table);
                    table2.add(j);
                }
                fullSoccer = 5;
            } else {
                for (int j = 0; j <= 9; j++) {
                    table1.add(long_table);
                    table2.add(j);
                }
                fullSoccer = 10;
            }
        }

        full_soccer.setText(getString(R.string.count_questions) + fullSoccer);
    }

    private int randInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public int generateRandom() {
        int val = long_table * 9;
        value_random1 = randInt(val / 2 + 1, val);
        value_random2 = randInt((val / 10 + 2), val / 2);
        value_random3 = randInt(0, val / 10 + 1);

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

    private void play_game(boolean wine, final TextView text) {
        if (!chronometer.isEnabled()) {
            chronometer.setEnabled(true);
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        }

        if (wine) {
            valueSoccer++;
            soccer.setText(getString(R.string.points) + valueSoccer);
            text.setBackgroundColor(Color.parseColor("#03d1bd"));
            text.setTextColor(Color.parseColor("#FFF1F1F1"));
            success.start();
        } else {
            text.setBackgroundColor(Color.parseColor("#FFFF4046"));
            text.setTextColor(Color.parseColor("#FFF1F1F1"));
            wrong.start();
        }

        TaskClass taskClass = new TaskClass(text);
        taskClass.execute();
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


    public void click_back(View view) {
        finish();
    }


    private class TaskClass extends AsyncTask {

        private TextView text;

        private TaskClass(TextView text) {
            this.text = text;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (table1.size() > 0 && table2.size() > 0) {
                index_empty = generateRandom();
                answer = prepare_game(table1, table2);
                inputByIndex(index_empty, answer);
            } else {

                String resultExam = null, result = null;

                if (valueSoccer >= (fullSoccer * 0.75)) {
                    resultExam = getString(R.string.result_excellent);
                    result = getString(R.string.excellent);
                } else if (valueSoccer < (fullSoccer * 0.75) && valueSoccer >= (fullSoccer * 0.5)) {
                    resultExam = getString(R.string.result_medium);
                    result = getString(R.string.medium);
                } else if (valueSoccer < (fullSoccer * 0.5)) {
                    resultExam = getString(R.string.result_weak);
                    result = getString(R.string.weak);
                }

                String degree = result;

                new AwesomeSuccessDialog(ExamActivity.this)
                        .setTitle(R.string.text_result)
                        .setMessage(resultExam)
                        .setColoredCircle(R.color.colorPrimary)
                        .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                        .setCancelable(false)
                        .setPositiveButtonText(getString(R.string.agree))
                        .setPositiveButtonbackgroundColor(R.color.colorAccent)
                        .setPositiveButtonTextColor(R.color.white)
                        .setPositiveButtonClick(() -> {
                            // last Exam Certification
                            if (long_table == 9 && level == 7) {

                                DataBaseInfo dataBaseInfo = new DataBaseInfo(ExamActivity.this);
                                String userName = dataBaseInfo.getName();
                                String familyName = dataBaseInfo.getFamilyName();
                                String gender = dataBaseInfo.getGender();

                                String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

                                Realm realm = Realm.getDefaultInstance();
                                realm.beginTransaction();
                                Result resultCertification = new Result();
                                int id = 0;
                                Number number = realm.where(Result.class).max("id");
                                if (number != null)
                                    id = number.intValue() + 1;

                                resultCertification.setId(id);
                                resultCertification.setUserName(userName);
                                resultCertification.setFamilyName(familyName);
                                resultCertification.setDate(date);
                                resultCertification.setDegree(degree);
                                resultCertification.setGender(gender);
                                resultCertification.setTime(chronometer.getText().toString());

                                realm.insert(resultCertification);
                                realm.commitTransaction();
                                realm.close();

                                intent = new Intent(ExamActivity.this, CertificationActivity.class);
                                intent.putExtra(Constants.KEY_NAME, userName);
                                intent.putExtra(Constants.KEY_FAMILY_NAME, familyName);
                                intent.putExtra(Constants.KEY_DEGREE, degree);
                                intent.putExtra(Constants.KEY_GENDER, gender);
                                intent.putExtra(Constants.KEY_DATE, date);

                                startActivity(intent);


                            } else {
                                if (valueSoccer >= (long_table * 0.75))
                                    setResult(1, intent.putExtra(Constants.KEY_LEVEL, level));
                                else
                                    toast(getString(R.string.alert_excellent));
                            }
                            finish();
                        })
                        .show();

                end.start();
                chronometer.stop();
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
    }

}
