package com.zxzhang.leethub.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zxzhang.leethub.fragment.TabFragment;

import java.util.List;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter{
    private List<Fragment>mFragments;
    private List<String>mTitles;

    public FragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment>fragments, List<String>titles){
        super(fragmentManager);
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
