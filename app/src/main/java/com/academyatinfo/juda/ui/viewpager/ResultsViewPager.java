package com.academyatinfo.juda.ui.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.academyatinfo.juda.ui.fragment.FragmentCertificates;
import com.academyatinfo.juda.ui.fragment.FragmentResultsTables;

public class ResultsViewPager extends FragmentPagerAdapter {

    public ResultsViewPager(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new FragmentCertificates();
        return new FragmentResultsTables();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
