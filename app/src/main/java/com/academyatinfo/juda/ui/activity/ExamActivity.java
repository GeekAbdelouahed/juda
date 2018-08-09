package com.academyatinfo.juda.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.academyatinfo.juda.BaseActivity;
import com.academyatinfo.juda.R;
import com.academyatinfo.juda.data.DataProfile;
import com.academyatinfo.juda.model.Result;
import com.academyatinfo.juda.utils.Constants;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.academyatinfo.juda.utils.Constants.DELAY_ANSWER;

public class ExamActivity extends BaseActivity {

    @BindView(R.id.text_card_one)
    TextView tvAnswer1;
    @BindView(R.id.text_card_two)
    TextView tvAnswer2;
    @BindView(R.id.text_card_three)
    TextView tvAnswer3;
    @BindView(R.id.text_card_four)
    TextView tvAnswer4;
    @BindView(R.id.points_table_exam)
    TextView tvPoints;
    @BindView(R.id.full_points)
    TextView tvFullPoints;
    @BindView(R.id.first_number_exam)
    ImageView imgFirst;
    @BindView(R.id.second_number_exam)
    ImageView imgSecond;
    @BindView(R.id.chrono)
    Chronometer chronometer;

    private TextView answerText;
    private final Handler answerHandler = new Handler();
    private final Runnable answerRunnable = new Runnable() {
        @Override
        public void run() {
            if (canPlayAgain()) {
                playAgain();
            } else {
                endPlaying();
            }
            section = false;
            answerText.setBackgroundColor(getResources().getColor(R.color.background_default));
            answerText.setTextColor(getResources().getColor(R.color.text_default));
        }
    };

    private MediaPlayer soundSuccess, soundWrong, soundEnd;
    private Random random = new Random();
    private ArrayList<Integer> table1 = new ArrayList<>(), table2 = new ArrayList<>();
    private Intent intent;
    private int level, table, answer, answerIndex, points, fullPoints;
    private boolean section = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_exam);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        intent = getIntent();
        level = intent.getIntExtra(Constants.KEY_LEVEL, 0);
        table = intent.getIntExtra(Constants.KEY_TABLE, 0);

        soundSuccess = MediaPlayer.create(this, R.raw.success);
        soundWrong = MediaPlayer.create(this, R.raw.wrong);
        soundEnd = MediaPlayer.create(this, R.raw.finish);

        createTable(table);

        answerIndex = generateRandomIndex();
        answer = prepareGame(table1, table2);
        addOptionToAnswers(answerIndex, answer);

        points = 0;
        String sPoints = getString(R.string.points) + " " + points;
        tvPoints.setText(sPoints);
    }

    private void createTable(int answersNumber) {

        if (level >= 6) {
            for (int i = 0; i <= 9; i++) {
                for (int j = 1; j <= answersNumber; j++) {
                    table1.add(j);
                }
            }
            for (int i = 1; i <= answersNumber; i++) {
                for (int j = 0; j <= 9; j++) {
                    table2.add(j);
                }
            }
            fullPoints = 10 * answersNumber;
        } else {
            if (level == 3) {
                for (int j = 0; j <= 4; j++) {
                    table1.add(answersNumber);
                    table2.add(j);
                }
                fullPoints = 5;
            } else if (level == 4) {
                for (int j = 5; j <= 9; j++) {
                    table1.add(answersNumber);
                    table2.add(j);
                }
                fullPoints = 5;
            } else {
                for (int j = 0; j <= 9; j++) {
                    table1.add(answersNumber);
                    table2.add(j);
                }
                fullPoints = 10;
            }
        }

        String points = getString(R.string.count_questions) + " " + fullPoints;
        tvFullPoints.setText(points);
    }

    private int generateRandomIndex() {
        int val = table * 9;
        int value_random1 = generateRandomInt(val / 2 + 1, val);
        int value_random2 = generateRandomInt((val / 10 + 2), val / 2);
        int value_random3 = generateRandomInt(0, val / 10 + 1);

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i <= 3; i++)
            arrayList.add(i);
        int index = random.nextInt(arrayList.size());
        addOptionToAnswers(arrayList.get(index), value_random1);
        arrayList.remove(index);
        index = random.nextInt(arrayList.size());
        addOptionToAnswers(arrayList.get(index), value_random2);
        arrayList.remove(index);
        index = random.nextInt(arrayList.size());
        addOptionToAnswers(arrayList.get(index), value_random3);
        arrayList.remove(index);
        return arrayList.get(arrayList.size() - 1);
    }

    private int prepareGame(ArrayList<Integer> t1, ArrayList<Integer> t2) {
        int index;
        index = random.nextInt(t1.size());
        int first = t1.get(index);
        setRandomImage(first, imgFirst);
        t1.remove(index);
        index = random.nextInt(t2.size());
        int second = t2.get(index);
        setRandomImage(second, imgSecond);
        t2.remove(index);
        return first * second;
    }

    public void addOptionToAnswers(int index, int value) {
        switch (index) {
            case 0:
                tvAnswer1.setText(String.valueOf(value));
                break;
            case 1:
                tvAnswer2.setText(String.valueOf(value));
                break;
            case 2:
                tvAnswer3.setText(String.valueOf(value));
                break;
            case 3:
                tvAnswer4.setText(String.valueOf(value));
                break;
        }
    }

    private void playGame(boolean isWine) {

        if (!chronometer.isEnabled()) {
            chronometer.setEnabled(true);
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        }

        if (isWine) {
            points++;
            String sc = getString(R.string.points) + " " + points;
            tvPoints.setText(sc);
            answerText.setBackgroundColor(getResources().getColor(R.color.background_success));
            answerText.setTextColor(getResources().getColor(R.color.text_success));
            soundSuccess.start();
        } else {
            answerText.setBackgroundColor(getResources().getColor(R.color.background_wrong));
            answerText.setTextColor(getResources().getColor(R.color.text_wrong));
            soundWrong.start();
        }

        checkAnswerGame(answerText);
    }

    private void checkAnswerGame(TextView text) {
        answerText = text;
        answerHandler.postDelayed(answerRunnable, DELAY_ANSWER);
    }

    private boolean canPlayAgain() {
        if (table1.size() > 0 && table2.size() > 0)
            return true;
        else
            return false;
    }

    private void playAgain() {
        answerIndex = generateRandomIndex();
        answer = prepareGame(table1, table2);
        addOptionToAnswers(answerIndex, answer);
    }

    private void endPlaying() {

        String resultMessage = resultExamMessage(points, fullPoints);

        new AwesomeSuccessDialog(ExamActivity.this)
                .setTitle(R.string.text_result)
                .setMessage(resultMessage)
                .setColoredCircle(R.color.colorPrimary)
                .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                .setCancelable(false)
                .setPositiveButtonText(getString(R.string.agree))
                .setPositiveButtonbackgroundColor(R.color.colorAccent)
                .setPositiveButtonTextColor(R.color.white)
                .setPositiveButtonClick(() -> {
                    // last Exam Certification
                    if (table == 9 && level == 7) {

                        DataProfile dataProfile = new DataProfile(ExamActivity.this);
                        String userName = dataProfile.getName();
                        String familyName = dataProfile.getFamilyName();
                        String gender = dataProfile.getGender();

                        String date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());

                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        Result resultCertification = new Result();
                        int id = 0;
                        Number number = realm.where(Result.class).max("id");
                        if (number != null)
                            id = number.intValue() + 1;

                        String result = resultExam(points, fullPoints);

                        resultCertification.setId(id);
                        resultCertification.setUserName(userName);
                        resultCertification.setFamilyName(familyName);
                        resultCertification.setDate(date);
                        resultCertification.setDegree(result);
                        resultCertification.setGender(gender);
                        resultCertification.setTime(chronometer.getText().toString());

                        realm.insert(resultCertification);
                        realm.commitTransaction();
                        realm.close();

                        intent = new Intent(ExamActivity.this, CertificationActivity.class);
                        intent.putExtra(Constants.KEY_NAME, userName);
                        intent.putExtra(Constants.KEY_FAMILY_NAME, familyName);
                        intent.putExtra(Constants.KEY_DEGREE, result);
                        intent.putExtra(Constants.KEY_GENDER, gender);
                        intent.putExtra(Constants.KEY_DATE, date);

                        startActivity(intent);


                    } else {
                        double ratio = calculateRatioPoints(points, fullPoints);
                        if (ratio >= 0.75)
                            setResult(RESULT_OK, intent);
                        else {
                            setResult(RESULT_CANCELED);
                            makeToast(getString(R.string.alert_excellent));
                        }
                    }
                    finish();
                })
                .show();

        soundEnd.start();
        chronometer.stop();
    }

    private String resultExam(int points, int fullPoints) {
        double ratio = calculateRatioPoints(points, fullPoints);
        if (ratio >= 0.75) {
            return getString(R.string.result_excellent);
        } else if (ratio < 0.75 && ratio >= 0.5) {
            return getString(R.string.result_medium);
        } else if (ratio < 0.5) {
            return getString(R.string.result_low);
        }
        return null;
    }

    private String resultExamMessage(int points, int fullPoints) {
        double ratio = calculateRatioPoints(points, fullPoints);
        if (ratio >= 0.75) {
            return getString(R.string.excellent);
        } else if (ratio < 0.75 && ratio >= 0.5) {
            return getString(R.string.medium);
        } else if (ratio < 0.5) {
            return getString(R.string.low);
        }
        return null;
    }

    private double calculateRatioPoints(int points, int fullPoints) {
        return (double) points / (double) fullPoints;
    }

    public void clickAnswerOne(View view) {
        if (!section) {
            section = true;
            boolean isWine = (answer == Integer.parseInt(tvAnswer1.getText().toString()));
            answerText = tvAnswer1;
            playGame(isWine);
        }
    }

    public void clickAnswerTwo(View view) {
        if (!section) {
            section = true;
            boolean isWine = (answer == Integer.parseInt(tvAnswer2.getText().toString()));
            answerText = tvAnswer2;
            playGame(isWine);
        }
    }

    public void clickAnswerThree(View view) {
        if (!section) {
            section = true;
            boolean isWine = (answer == Integer.parseInt(tvAnswer3.getText().toString()));
            answerText = tvAnswer3;
            playGame(isWine);
        }
    }

    public void clickAnswerFour(View view) {
        if (!section) {
            section = true;
            boolean isWine = (answer == Integer.parseInt(tvAnswer4.getText().toString()));
            answerText = tvAnswer4;
            playGame(isWine);
        }
    }

    private void setRandomImage(int value, ImageView img) {
        switch (value) {
            case 0:
                img.setImageResource(R.drawable.ic_zero_b);
                break;
            case 1:
                img.setImageResource(R.drawable.ic_one_b);
                break;
            case 2:
                img.setImageResource(R.drawable.ic_two_b);
                break;
            case 3:
                img.setImageResource(R.drawable.ic_three_b);
                break;
            case 4:
                img.setImageResource(R.drawable.ic_four_b);
                break;
            case 5:
                img.setImageResource(R.drawable.ic_five_b);
                break;
            case 6:
                img.setImageResource(R.drawable.ic_six_b);
                break;
            case 7:
                img.setImageResource(R.drawable.ic_seven_b);
                break;
            case 8:
                img.setImageResource(R.drawable.ic_eight_b);
                break;
            case 9:
                img.setImageResource(R.drawable.ic_nine_b);
                break;
        }
    }

    private int generateRandomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public void clickFinish(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (answerHandler != null && answerRunnable != null) {
            answerHandler.removeCallbacks(answerRunnable);
        }
    }
}
