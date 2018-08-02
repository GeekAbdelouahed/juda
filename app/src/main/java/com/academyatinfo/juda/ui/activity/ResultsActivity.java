package com.academyatinfo.juda.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.academyatinfo.juda.BaseActivity;
import com.academyatinfo.juda.R;
import com.academyatinfo.juda.ui.viewpager.ResultsViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultsActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_results);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        ResultsViewPager resultsViewPager = new ResultsViewPager(getSupportFragmentManager());

        viewPager.setAdapter(resultsViewPager);
        viewPager.setOffscreenPageLimit(2);

        TabLayout.Tab tabCertificates = createTabWithTitle(getResources().getString(R.string.certifications));
        tabLayout.addTab(tabCertificates, 0);

        TabLayout.Tab tabTables = createTabWithTitle(getResources().getString(R.string.tables));
        tabLayout.addTab(tabTables, 1);

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

    private TabLayout.Tab createTabWithTitle(String title) {
        TabLayout.Tab tab = tabLayout.newTab();
        View view = tab.setCustomView(R.layout.custom_tab).getCustomView();
        if (view != null) {
            TextView tv = view.findViewById(R.id.text_tab);
            tv.setText(title);
        }
        return tab;
    }

    public void clickFinish(View view) {
        finish();
    }
}
