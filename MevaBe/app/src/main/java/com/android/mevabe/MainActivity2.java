package com.android.mevabe;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.mevabe.common.AppConfig;
import com.android.mevabe.dashboard.DashBoard;
import com.android.mevabe.lichsuthuoc.LichSuThuocMain;
import com.android.mevabe.lichtiem.LichTiemMain;
import com.android.mevabe.view.FragmentBase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_menu_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_menu_camera);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_menu_camera);
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                    private TabLayout.Tab selectedTab;

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        Log.i(AppConfig.LOG_TAG, "onTabSelected");
                        super.onTabSelected(tab);
                        selectedTab = tab;
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        Log.i(AppConfig.LOG_TAG, "onTabReselected");
                        super.onTabReselected(tab);

                        // Fire on toolbar click event
                        if (selectedTab == null || selectedTab.equals(tab)) {
                            ViewPagerAdapter adapter = (ViewPagerAdapter) viewPager.getAdapter();
                            FragmentBase screen = (FragmentBase) adapter.getItem(viewPager.getCurrentItem());
                            screen.onToolBarClicked(null);
                        }
                    }
                });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DashBoard(), getString(R.string.left_menu_dashboard));
        adapter.addFragment(new LichTiemMain(), getString(R.string.left_menu_lich_tiem));
        adapter.addFragment(new LichSuThuocMain(), getString(R.string.left_menu_su_dung_thuoc));
        adapter.addFragment(new LichSuThuocMain(), getString(R.string.left_menu_bac_sy));
        adapter.addFragment(new LichSuThuocMain(), getString(R.string.left_menu_profile));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}