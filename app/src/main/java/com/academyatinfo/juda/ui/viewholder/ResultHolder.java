package com.academyatinfo.juda.ui.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.academyatinfo.juda.R;
import com.academyatinfo.juda.ui.activity.CertificationActivity;
import com.academyatinfo.juda.model.Result;
import com.academyatinfo.juda.utils.Constants;
import com.academyatinfo.juda.utils.PlayerGender;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.result_layout)
    public RelativeLayout layout;
    @BindView(R.id.result_date)
    public TextView tvDate;
    @BindView(R.id.result_time)
    public TextView tvTime;
    @BindView(R.id.result_name)
    public TextView tvName;
    @BindView(R.id.result_degree)
    public TextView tvDegree;
    @BindView(R.id.result_avatar)
    public ImageView avatar;

    private final Context context;

    public ResultHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        context = itemView.getContext();
    }

    public void bind(Result result) {
        tvDate.setText(result.getDate());
        tvDegree.setText(result.getDegree());
        String time = context.getString(R.string.duration) + result.getTime();
        tvTime.setText(time);
        String name = result.getUserName() + " " + result.getFamilyName();
        tvName.setText(name);

        if (result.getGender().equals(PlayerGender.MALE.name()))
            avatar.setImageResource(R.drawable.ic_boy);
        else if (result.getGender().equals(PlayerGender.FEMALE.name()))
            avatar.setImageResource(R.drawable.ic_girl);

        layout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(context.getString(R.string.ask_get_certificate));
            builder.setCancelable(false);
            builder.setPositiveButton(context.getString(R.string.yes), (dialog, which) -> {
                Intent intent = new Intent(context, CertificationActivity.class);
                intent.putExtra(Constants.KEY_NAME, result.getUserName());
                intent.putExtra(Constants.KEY_FAMILY_NAME, result.getFamilyName());
                intent.putExtra(Constants.KEY_DEGREE, result.getDegree());
                intent.putExtra(Constants.KEY_GENDER, result.getGender());
                intent.putExtra(Constants.KEY_DATE, result.getDate());
                dialog.dismiss();
                context.startActivity(intent);
            });
            builder.setNegativeButton(context.getString(R.string.no), (dialog, which) -> dialog.dismiss());
            builder.show();
        });
    }
}
