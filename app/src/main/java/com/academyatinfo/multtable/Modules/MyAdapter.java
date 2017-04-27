package com.academyatinfo.multtable.Modules;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.academyatinfo.multtable.R;

import java.util.ArrayList;

/**
 * Created by abde on 14/02/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Results> results;
    private Context context;
    private int lastPosition;

    public MyAdapter(Context context, ArrayList<Results> results) {
        this.context = context;
        this.results = results;
        this.lastPosition = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Results results = this.results.get(position);

        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom
                : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;

        holder.name.setText(results.getName());
        holder.result.setText(results.getResult() + "/90");
        holder.time.setText(results.getTime());
        holder.date.setText(results.getDate());
    }

    @Override
    public int getItemCount() {
        try {
            return results.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView result, time, date, name;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.result_name);
            result = (TextView) itemView.findViewById(R.id.text_result);
            time = (TextView) itemView.findViewById(R.id.text_time);
            date = (TextView) itemView.findViewById(R.id.text_date);
        }
    }
}