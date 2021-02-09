package com.course.capstone.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.course.capstone.DailyFragment;
import com.course.capstone.freeFragment;
import com.course.capstone.hotFragment;

public class BoardTabPageAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public BoardTabPageAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new freeFragment();

            case 1:
                return new hotFragment();

            case 2:
                return new DailyFragment();


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
