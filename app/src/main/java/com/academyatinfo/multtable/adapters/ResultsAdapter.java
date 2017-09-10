package com.academyatinfo.multtable.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.holders.ResultHolder;
import com.academyatinfo.multtable.models.Result;

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

        holder.name.setText(result.getUserName());
        holder.result.setText(result.getDegree());
        /*holder.time.setText(result.getTime());
        holder.date.setText(result.getDate());*/
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