package com.academyatinfo.multtable.appintro.slides;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.databases.DataBaseInfo;
import com.github.paolorotolo.appintro.ISlidePolicy;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Slide3Fragment extends Fragment implements ISlidePolicy {


    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.family_name)
    EditText familyName;
    @BindView(R.id.male)
    RadioButton male;
    @BindView(R.id.female)
    RadioButton female;
    @BindView(R.id.avatar)
    ImageView avatar;

    private DataBaseInfo dataBaseInfo;
    private String gender = "male";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slide3, container, false);
        ButterKnife.bind(this, view);
        dataBaseInfo = new DataBaseInfo(getContext());

        male.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                avatar.setImageResource(R.drawable.ic_boy);
                gender = "male";
            }
        });

        female.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                avatar.setImageResource(R.drawable.ic_girl);
                gender = "female";
            }
        });

        userName.setText("عبد الواحد");
        familyName.setText("مجوجة");
        return view;
    }


    @Override
    public boolean isPolicyRespected() {
        if (!userName.getText().toString().isEmpty() && !familyName.getText().toString().isEmpty()) {
            dataBaseInfo.setName(userName + " " + familyName);
            dataBaseInfo.setGender(gender);
            return true;
        }
        return false;
    }

    @Override
    public void onUserIllegallyRequestedNextPage() {

    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Slide3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Slide3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Slide3Fragment newInstance(String param1, String param2) {
        Slide3Fragment fragment = new Slide3Fragment();
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
