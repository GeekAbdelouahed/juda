package com.academyatinfo.multtable.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.academyatinfo.multtable.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by geek on 09/09/17.
 */

public class ResultHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_result)
    public TextView result;
    @BindView(R.id.text_time)
    public TextView time;
    @BindView(R.id.text_date)
    public TextView date;
    @BindView(R.id.result_name)
    public TextView name;

    public ResultHolder(View view) {
        super(view);

        ButterKnife.bind(true, view);
    }
}
