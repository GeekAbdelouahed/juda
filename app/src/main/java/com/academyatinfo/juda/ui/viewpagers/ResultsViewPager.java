package com.academyatinfo.juda.ui.viewpagers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.academyatinfo.juda.ui.fragments.FragmentCertificates;
import com.academyatinfo.juda.ui.fragments.FragmentResultsTables;

/**
 * Created by geek on 10/09/17.
 */

public class ResultsViewPager extends FragmentPagerAdapter {

    public ResultsViewPager(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new FragmentCertificates();
        else
            return new FragmentResultsTables();

    }

    @Override
    public int getCount() {
        return 2;
    }
}
