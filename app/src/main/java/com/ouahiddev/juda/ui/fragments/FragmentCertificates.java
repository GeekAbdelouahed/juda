package com.ouahiddev.juda.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ouahiddev.juda.R;
import com.ouahiddev.juda.models.Result;
import com.ouahiddev.juda.ui.adapter.ResultsAdapter;
import com.ouahiddev.juda.ui.common.EmptyRecyclerView;

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
