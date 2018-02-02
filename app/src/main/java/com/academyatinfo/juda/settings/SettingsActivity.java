package com.academyatinfo.juda.settings;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.academyatinfo.juda.R;
import com.academyatinfo.juda.databases.DataBaseInfo;
import com.academyatinfo.juda.ui.activitys.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity {


    @BindView(R.id.user_name_settings)
    EditText userName;
    @BindView(R.id.family_name_settings)
    EditText familyName;
    @BindView(R.id.male_settings)
    RadioButton male;
    @BindView(R.id.female_settings)
    RadioButton female;
    @BindView(R.id.avatar_settings)
    ImageView avatar;

    private DataBaseInfo dataBaseInfo;
    private String gender = "male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_settings);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);


        dataBaseInfo = new DataBaseInfo(this);

        userName.setText(dataBaseInfo.getName());

        familyName.setText(dataBaseInfo.getFamilyName());

        gender = dataBaseInfo.getGender();

        if (gender.equals("male"))
            male.setChecked(true);
        else if (gender.equals("female")) {
            avatar.setImageResource(R.drawable.ic_girl);
            female.setChecked(true);
        }

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

    }

    public void click_to_save(View view) {
        dataBaseInfo.setGender(gender);
        dataBaseInfo.setName(userName.getText().toString());
        dataBaseInfo.setFamilyName(familyName.getText().toString());

        toast(getString(R.string.save_done));
    }

    public void click_back(View view) {
        finish();
    }
}
