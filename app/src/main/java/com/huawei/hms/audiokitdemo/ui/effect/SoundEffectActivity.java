package com.huawei.hms.audiokitdemo.ui.effect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.huawei.hms.audiokitdemo.R;
import com.huawei.hms.audiokitdemo.equalizer.EqualizerFragment;
import com.huawei.hms.audiokitdemo.utils.ResUtils;

public class SoundEffectActivity extends AppCompatActivity {

    private ViewPager pager;

    private TabLayout tabLayout;

    MyViewPagerAdapter myViewPagerAdapter;

    private Fragment[] mFragmentArrays = new Fragment[3];

    private String[] mTabTitles = new String[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_effect);
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        pager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.table_layout);
        pager.setAdapter(myViewPagerAdapter);
        mTabTitles[0] = ResUtils.getString(R.string.featured);
        mTabTitles[1] = ResUtils.getString(R.string.common);
        mTabTitles[2] = ResUtils.getString(R.string.equilibrium);
        mFragmentArrays[0] = SoundEffectChosenFragment.newInstance();
        mFragmentArrays[1] = SoundEffectOrdinaryFragment.newInstance();
        mFragmentArrays[2] = EqualizerFragment.newInstance();
        tabLayout.setupWithViewPager(pager);
    }


    class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }


        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];

        }
    }


}