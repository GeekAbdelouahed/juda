package com.academyatinfo.juda.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.academyatinfo.juda.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by geek on 09/09/17.
 */

public class ResultHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.result_layout)
    public RelativeLayout layout;
    @BindView(R.id.result_date)
    public TextView date;
    @BindView(R.id.result_time)
    public TextView time;
    @BindView(R.id.result_avatar)
    public ImageView avatar;
    @BindView(R.id.result_name)
    public TextView name;
    @BindView(R.id.result_degree)
    public TextView degree;

    public ResultHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
