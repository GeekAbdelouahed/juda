package com.academyatinfo.juda.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.academyatinfo.juda.R;
import com.academyatinfo.juda.adapters.ResultsAdapter;
import com.academyatinfo.juda.models.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class FragmentCertificates extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.text_checkdata)
    TextView text_check;

    private ResultsAdapter resultsAdapter;
    private RealmResults<Result> results;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_certificates, container, false);

        ButterKnife.bind(this, view);

        results = Realm.getDefaultInstance().where(Result.class).findAll();

        if (results.size() > 0) {
            resultsAdapter = new ResultsAdapter(getContext(), results);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(resultsAdapter);
            recyclerView.setVisibility(View.VISIBLE);
            text_check.setVisibility(View.GONE);
        }

        return view;
    }
}
