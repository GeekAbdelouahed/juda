package com.abdelouahed.juda.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdelouahed.juda.R;
import com.abdelouahed.juda.models.Result;
import com.abdelouahed.juda.ui.adapter.ResultsAdapter;
import com.abdelouahed.juda.ui.common.EmptyRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class FragmentCertificates extends Fragment {

    @BindView(R.id.recyclerview)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.empty_view)
    View emptyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_certificates, container, false);

        ButterKnife.bind(this, view);

        recyclerView.setEmptyView(emptyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RealmResults<Result> results = Realm.getDefaultInstance().where(Result.class).findAll();

        if (results.size() > 0) {
            ResultsAdapter resultsAdapter = new ResultsAdapter(results);
            recyclerView.setAdapter(resultsAdapter);
        }

        return view;
    }
}
