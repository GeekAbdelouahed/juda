package com.academyatinfo.juda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.academyatinfo.juda.R;
import com.academyatinfo.juda.data.DataProfile;
import com.academyatinfo.juda.utils.PlayerGender;
import com.academyatinfo.juda.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity {


    @BindView(R.id.user_name_settings)
    EditText etxUserName;
    @BindView(R.id.family_name_settings)
    EditText etxFamilyName;
    @BindView(R.id.male_settings)
    RadioButton rdbMale;
    @BindView(R.id.female_settings)
    RadioButton rdbFemale;
    @BindView(R.id.avatar_settings)
    ImageView imgAvatar;

    private DataProfile dataProfile;
    private String gender = PlayerGender.MALE.name();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_settings);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        dataProfile = new DataProfile(this);

        etxUserName.setText(dataProfile.getName());
        etxFamilyName.setText(dataProfile.getFamilyName());
        gender = dataProfile.getGender();

        if (gender.equals(PlayerGender.MALE.name()))
            rdbMale.setChecked(true);
        else if (gender.equals(PlayerGender.FEMALE.name())) {
            imgAvatar.setImageResource(R.drawable.ic_girl);
            rdbFemale.setChecked(true);
        }

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

    }

    public void clickSave(View view) {
        dataProfile.setGender(gender);
        dataProfile.setName(etxUserName.getText().toString());
        dataProfile.setFamilyName(etxFamilyName.getText().toString());

        makeToast(getString(R.string.save_done));
    }

    public void clickFinish(View view) {
        finish();
    }
}
