package com.academyatinfo.juda.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.academyatinfo.juda.R;
import com.academyatinfo.juda.model.Result;
import com.academyatinfo.juda.ui.adapter.ResultsAdapter;
import com.academyatinfo.juda.ui.customview.EmptyRecyclerView;

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
