package com.academyatinfo.juda.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.academyatinfo.juda.R;
import com.academyatinfo.juda.databases.DataBaseInfo;
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

        return view;
    }

    @Override
    public boolean isPolicyRespected() {
        if (!userName.getText().toString().isEmpty() && !familyName.getText().toString().isEmpty()) {
            dataBaseInfo.setName(userName.getText().toString());
            dataBaseInfo.setFamilyName(familyName.getText().toString());
            dataBaseInfo.setGender(gender);
            return true;
        }
        return false;
    }

    @Override
    public void onUserIllegallyRequestedNextPage() {

    }

    public interface OnSaveDataListener {
        void onSaveData();
    }
}
