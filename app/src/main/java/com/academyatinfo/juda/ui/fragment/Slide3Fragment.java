package com.academyatinfo.juda.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.academyatinfo.juda.R;
import com.academyatinfo.juda.data.DataProfile;
import com.academyatinfo.juda.utils.PlayerGender;
import com.github.paolorotolo.appintro.ISlidePolicy;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Slide3Fragment extends Fragment implements ISlidePolicy {


    @BindView(R.id.user_name)
    EditText etxUserName;
    @BindView(R.id.family_name)
    EditText etxFamilyName;
    @BindView(R.id.male)
    RadioButton rdbMale;
    @BindView(R.id.female)
    RadioButton rdbFemale;
    @BindView(R.id.avatar)
    ImageView imgAvatar;

    private DataProfile dataProfile;
    private String gender = PlayerGender.MALE.name();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slide3, container, false);
        ButterKnife.bind(this, view);

        dataProfile = new DataProfile(getContext());

        rdbMale.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                imgAvatar.setImageResource(R.drawable.ic_boy);
                gender = PlayerGender.MALE.name();
            }
        });

        rdbFemale.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                imgAvatar.setImageResource(R.drawable.ic_girl);
                gender = PlayerGender.FEMALE.name();
            }
        });

        return view;
    }

    @Override
    public boolean isPolicyRespected() {
        if (!etxUserName.getText().toString().isEmpty() && !etxFamilyName.getText().toString().isEmpty()) {
            dataProfile.setName(etxUserName.getText().toString());
            dataProfile.setFamilyName(etxFamilyName.getText().toString());
            dataProfile.setGender(gender);
            return true;
        }
        return false;
    }

    @Override
    public void onUserIllegallyRequestedNextPage() {

    }
}
