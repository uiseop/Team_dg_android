package com.course.capstone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;

import com.course.capstone.adapter.BoardTabPageAdapter;
import com.google.android.material.tabs.TabLayout;


public class Frag2 extends Fragment {
    private ViewPager mViewPager;

    public static int getCurrentTab() {
        return currentTab;
    }

    private static int currentTab;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        currentTab=0;
        View view = (ViewGroup) inflater.inflate(R.layout.frag2, container, false);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.board_tab);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.addTab(tabLayout.newTab().setText("자유게시판"));
        tabLayout.addTab(tabLayout.newTab().setText("hot게시판"));
        tabLayout.addTab(tabLayout.newTab().setText("금융공모전"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mViewPager = (ViewPager) view.findViewById(R.id.board_view_pager);
        BoardTabPageAdapter boardTabPageAdapter = new BoardTabPageAdapter(getFragmentManager(), tabLayout.getTabCount());
        mViewPager.setAdapter(boardTabPageAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                currentTab = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    }
