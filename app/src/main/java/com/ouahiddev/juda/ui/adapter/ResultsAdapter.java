package com.ouahiddev.juda.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ouahiddev.juda.R;
import com.ouahiddev.juda.models.Result;
import com.ouahiddev.juda.ui.viewholders.ResultHolder;

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