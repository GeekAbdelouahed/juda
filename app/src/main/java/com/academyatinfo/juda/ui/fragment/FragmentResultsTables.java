package com.academyatinfo.juda.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.academyatinfo.juda.R;
import com.academyatinfo.juda.data.DataTableLevel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentResultsTables extends Fragment {

    @BindView(R.id.progress_one)
    TextView progressOne;
    @BindView(R.id.progressBar_one)
    ProgressBar barOne;
    @BindView(R.id.progress_two)
    TextView progressTwo;
    @BindView(R.id.progressBar_two)
    ProgressBar barTwo;
    @BindView(R.id.progress_three)
    TextView progressThree;
    @BindView(R.id.progressBar_three)
    ProgressBar barThree;
    @BindView(R.id.progress_four)
    TextView progressFour;
    @BindView(R.id.progressBar_four)
    ProgressBar barFour;
    @BindView(R.id.progress_five)
    TextView progressFive;
    @BindView(R.id.progressBar_five)
    ProgressBar barFive;
    @BindView(R.id.progress_six)
    TextView progressSix;
    @BindView(R.id.progressBar_six)
    ProgressBar barSix;
    @BindView(R.id.progress_seven)
    TextView progressSeven;
    @BindView(R.id.progressBar_seven)
    ProgressBar barSeven;
    @BindView(R.id.progress_eight)
    TextView progressEight;
    @BindView(R.id.progressBar_eight)
    ProgressBar barEight;
    @BindView(R.id.progress_nine)
    TextView progressNine;
    @BindView(R.id.progressBar_nine)
    ProgressBar barNine;

    private DataTableLevel dataTableLevel;
    private int progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_results_tables, container, false);

        ButterKnife.bind(this, view);

        dataTableLevel = new DataTableLevel(getContext());

        progress = getProgress(1);
        progressOne.setText(progress + "/6");
        barOne.setProgress(progress);

        progress = getProgress(2);
        progressTwo.setText(progress + "/6");
        barTwo.setProgress(progress);

        progress = getProgress(3);
        progressThree.setText(progress + "/6");
        barThree.setProgress(progress);

        progress = getProgress(4);
        progressFour.setText(progress + "/6");
        barFour.setProgress(progress);

        progress = getProgress(5);
        progressFive.setText(progress + "/6");
        barFive.setProgress(progress);

        progress = getProgress(6);
        progressSix.setText(progress + "/6");
        barSix.setProgress(progress);

        progress = getProgress(7);
        progressSeven.setText(progress + "/6");
        barSeven.setProgress(progress);

        progress = getProgress(8);
        progressEight.setText(progress + "/6");
        barEight.setProgress(progress);

        progress = getProgress(9);
        progressNine.setText(progress + "/6");
        barNine.setProgress(progress);

        return view;
    }


    private int getProgress(int table) {
        int somme = 0;
        for (int i = 1; i <= 6; i++) {
            if (dataTableLevel.getData(table, i))
                somme++;
            else
                break;
        }

        return somme;
    }
}
