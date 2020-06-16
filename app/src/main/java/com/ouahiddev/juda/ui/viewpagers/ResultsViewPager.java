package com.ouahiddev.juda.ui.viewpagers;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ouahiddev.juda.ui.fragments.FragmentCertificates;
import com.ouahiddev.juda.ui.fragments.FragmentResultsTables;

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
