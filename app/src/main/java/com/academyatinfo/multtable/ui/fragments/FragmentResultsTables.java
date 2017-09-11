package com.academyatinfo.multtable.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.databases.DataTableLevel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentResultsTables extends Fragment {

    @BindView(R.id.progress_one)
    TextView progressOne;
    @BindView(R.id.progressBar_one)
    ProgressBar barOne;
    @BindView(R.id.progress_two)
    TextView progressTwo;
    @BindView(R.id.progressBar_two)
    ProgressBar barTwo;
    @BindView(R.id.progress_three)
    TextView progressThree;
    @BindView(R.id.progressBar_three)
    ProgressBar barThree;
    @BindView(R.id.progress_four)
    TextView progressFour;
    @BindView(R.id.progressBar_four)
    ProgressBar barFour;
    @BindView(R.id.progress_five)
    TextView progressFive;
    @BindView(R.id.progressBar_five)
    ProgressBar barFive;
    @BindView(R.id.progress_six)
    TextView progressSix;
    @BindView(R.id.progressBar_six)
    ProgressBar barSix;
    @BindView(R.id.progress_seven)
    TextView progressSeven;
    @BindView(R.id.progressBar_seven)
    ProgressBar barSeven;
    @BindView(R.id.progress_eight)
    TextView progressEight;
    @BindView(R.id.progressBar_eight)
    ProgressBar barEight;
    @BindView(R.id.progress_nine)
    TextView progressNine;
    @BindView(R.id.progressBar_nine)
    ProgressBar barNine;

    private DataTableLevel dataTableLevel;
    private int progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_results_tables, container, false);

        ButterKnife.bind(this, view);

        dataTableLevel = new DataTableLevel(getContext());

        progress = getProgress(1);
        progressOne.setText(progress + "/6");
        barOne.setProgress(progress);

        progress = getProgress(2);
        progressTwo.setText(progress + "/6");
        barTwo.setProgress(progress);

        progress = getProgress(3);
        progressThree.setText(progress + "/6");
        barThree.setProgress(progress);

        progress = getProgress(4);
        progressFour.setText(progress + "/6");
        barFour.setProgress(progress);

        progress = getProgress(5);
        progressFive.setText(progress + "/6");
        barFive.setProgress(progress);

        progress = getProgress(6);
        progressSix.setText(progress + "/6");
        barSix.setProgress(progress);

        progress = getProgress(7);
        progressSeven.setText(progress + "/6");
        barSeven.setProgress(progress);

        progress = getProgress(8);
        progressEight.setText(progress + "/6");
        barEight.setProgress(progress);

        progress = getProgress(9);
        progressNine.setText(progress + "/6");
        barNine.setProgress(progress);

        return view;
    }


    private int getProgress(int table) {
        int somme = 0;
        for (int i = 1; i <= 6; i++) {
            if (dataTableLevel.getData(table, i))
                somme++;
        }

        return somme;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentResultsTables() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentResultsTables.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentResultsTables newInstance(String param1, String param2) {
        FragmentResultsTables fragment = new FragmentResultsTables();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
