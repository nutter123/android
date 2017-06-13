package com.example.nutter.dongruancp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by nutter on 2017/5/27.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;
    private List<String> mName;

    public PagerAdapter(FragmentManager fm, List<String> name, List<Fragment> list) {
        super(fm);
        this.list = list;
        this.mName = name;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list == null ? 0:list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mName.get(position);
    }
}
