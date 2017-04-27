package com.academyatinfo.multtable.LearnTable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.academyatinfo.multtable.Exam;
import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.databases.DataBaseLearn;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LearnTable0 extends AppCompatActivity {

    private Intent intent;
    private ImageView lock_two, lock_three, lock_four, lock_five, lock_six, lock_seven, lock_eight, lock_nine, img_lock_exam;
    private DataBaseLearn dataBaseLearn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learntable0);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        lock_two = (ImageView) findViewById(R.id.lock_two);
        lock_three = (ImageView) findViewById(R.id.lock_three);
        lock_four = (ImageView) findViewById(R.id.lock_four);
        lock_five = (ImageView) findViewById(R.id.lock_five);
        lock_six = (ImageView) findViewById(R.id.lock_six);
        lock_seven = (ImageView) findViewById(R.id.lock_seven);
        lock_eight = (ImageView) findViewById(R.id.lock_eight);
        lock_nine = (ImageView) findViewById(R.id.lock_nine);
        img_lock_exam = (ImageView) findViewById(R.id.img_lock_certification);

        dataBaseLearn = new DataBaseLearn(this);

        intent = new Intent(this, LearnTable1.class);
    }

    public void one(View view) {
        intent.putExtra("index_table", 1);
        startActivity(intent);
    }

    public void two(View view) {
        if (check_done_table(1)) {
            intent.putExtra("index_table", 2);
            startActivity(intent);
        }
    }

    public void three(View view) {
        if (check_done_table(2)) {
            intent.putExtra("index_table", 3);
            startActivity(intent);
        }
    }

    public void four(View view) {
        if (check_done_table(3)) {
            intent.putExtra("index_table", 4);
            startActivity(intent);
        }
    }

    public void five(View view) {
        if (check_done_table(4)) {
            intent.putExtra("index_table", 5);
            startActivity(intent);
        }
    }

    public void six(View view) {
        if (check_done_table(5)) {
            intent.putExtra("index_table", 6);
            startActivity(intent);
        }
    }

    public void seven(View view) {
        if (check_done_table(6)) {
            intent.putExtra("index_table", 7);
            startActivity(intent);
        }
    }

    public void eight(View view) {
        if (check_done_table(7)) {
            intent.putExtra("index_table", 8);
            startActivity(intent);
        }
    }

    public void nine(View view) {
        if (check_done_table(8)) {
            intent.putExtra("index_table", 9);
            startActivity(intent);
        }
    }

    public void click_exam(View view) {
        if (check_done_table(9)) {
            intent = new Intent(this, Exam.class);
            intent.putExtra("long_table", 9);
            intent.putExtra("level", 7);
            startActivity(intent);
        } else {
            Toast.makeText(this, "يجب إكمال جميع مراحل التعليم", Toast.LENGTH_LONG).show();
        }
    }

    public boolean check_done_table(int id_table) {
        return dataBaseLearn.getData(id_table , 6);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (check_done_table(1)) {
            lock_two.setVisibility(View.GONE);
            if (check_done_table(2)) {
                lock_three.setVisibility(View.GONE);
                if (check_done_table(3)) {
                    lock_four.setVisibility(View.GONE);
                    if (check_done_table(4)) {
                        lock_five.setVisibility(View.GONE);
                        if (check_done_table(5)) {
                            lock_six.setVisibility(View.GONE);
                            if (check_done_table(6)) {
                                lock_seven.setVisibility(View.GONE);
                                if (check_done_table(7)) {
                                    lock_eight.setVisibility(View.GONE);
                                    if (check_done_table(8)) {
                                        lock_nine.setVisibility(View.GONE);
                                        if (check_done_table(9)) {
                                            img_lock_exam.setVisibility(View.GONE);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
