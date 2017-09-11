package com.academyatinfo.multtable.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.certification.CertificationActivity;
import com.academyatinfo.multtable.holders.ResultHolder;
import com.academyatinfo.multtable.models.Result;
import com.academyatinfo.multtable.tools.Constants;

import io.realm.RealmResults;

/**
 * Created by abde on 14/02/17.
 */

public class ResultsAdapter extends RecyclerView.Adapter<ResultHolder> {

    private RealmResults<Result> results;
    private Context context;

    public ResultsAdapter(Context context, RealmResults<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item, parent, false);
        ResultHolder holder = new ResultHolder(view);
        return holder;
    }

    @Override
    public void onViewDetachedFromWindow(ResultHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(final ResultHolder holder, final int position) {

        final Result result = this.results.get(position);

        holder.date.setText(result.getDate());
        holder.time.setText("المدة  " + result.getTime());

        if (result.getGender().equals("male"))
            holder.avatar.setImageResource(R.drawable.ic_boy);
        else
            holder.avatar.setImageResource(R.drawable.ic_girl);

        holder.name.setText(result.getUserName() + " " + result.getFamilyName());
        holder.degree.setText(result.getDegree());

        holder.layout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("هل تريد إستخراج الشهادة");
            builder.setCancelable(false);
            builder.setPositiveButton("نعم", (dialog, which) -> {
                Intent intent = new Intent(context, CertificationActivity.class);
                intent.putExtra(Constants.KEY_NAME, result.getUserName());
                intent.putExtra(Constants.KEY_FAMILY_NAME, result.getFamilyName());
                intent.putExtra(Constants.KEY_DEGREE, result.getDegree());
                intent.putExtra(Constants.KEY_GENDER, result.getGender());
                intent.putExtra(Constants.KEY_DATE, result.getDate());
                dialog.dismiss();
                context.startActivity(intent);
            });
            builder.setNegativeButton("لا", (dialog, which) -> dialog.dismiss());
            builder.show();
        });

    }

    @Override
    public int getItemCount() {
        try {
            return results.size();
        } catch (Exception e) {
            return 0;
        }
    }
}