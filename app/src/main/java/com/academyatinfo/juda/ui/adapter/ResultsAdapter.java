package com.academyatinfo.juda.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.academyatinfo.juda.R;
import com.academyatinfo.juda.ui.viewholder.ResultHolder;
import com.academyatinfo.juda.model.Result;

import io.realm.RealmResults;

public class ResultsAdapter extends RecyclerView.Adapter<ResultHolder> {

    private RealmResults<Result> results;

    public ResultsAdapter(RealmResults<Result> results) {
        this.results = results;
    }

    @Override
    public ResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item, parent, false);
        return new ResultHolder(view);
    }

    @Override
    public void onViewDetachedFromWindow(ResultHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(final ResultHolder holder, final int position) {
        final Result result = this.results.get(position);
        holder.bind(result);
    }

    @Override
    public int getItemCount() {
        if (results != null)
            return results.size();
        return 0;
    }
}