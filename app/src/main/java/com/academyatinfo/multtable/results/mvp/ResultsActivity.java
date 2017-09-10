package com.academyatinfo.multtable.results.mvp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.ui.activitys.BaseActivity;
import com.academyatinfo.multtable.ui.viewpagers.ResultsViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultsActivity extends BaseActivity implements ResultContract.View {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ResultsViewPager resultsViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_results);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        resultsViewPager = new ResultsViewPager(getSupportFragmentManager());

        viewPager.setAdapter(resultsViewPager);
        viewPager.setOffscreenPageLimit(2);


        TabLayout.Tab tab;

        tab = tabLayout.newTab();
        ((TextView) tab.setCustomView(R.layout.custom_tab).getCustomView().findViewById(R.id.text_tab))
                .setText("الشهادات");
        tabLayout.addTab(tab, 0);

        tab = tabLayout.newTab();
        ((TextView) tab.setCustomView(R.layout.custom_tab).getCustomView().findViewById(R.id.text_tab))
                .setText("الجداول");
        tabLayout.addTab(tab, 1);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(2);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }

    public void click_back(View view) {
        finish();
    }
}
