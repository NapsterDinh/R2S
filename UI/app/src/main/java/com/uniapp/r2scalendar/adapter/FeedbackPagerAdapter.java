package com.uniapp.r2scalendar.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.ListFragment;

import com.uniapp.r2scalendar.ui.FeedbackCommentFragment;
import com.uniapp.r2scalendar.ui.FeedbackDetailFragment;
import com.uniapp.r2scalendar.ui.FeedbackOverviewLeftFragment;
import com.uniapp.r2scalendar.ui.FeedbackOverviewRightFragment;

import java.util.ArrayList;

public class FeedbackPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 4;
    private ArrayList<Fragment> fragments=new ArrayList<Fragment>();
    private FragmentManager fragMan;

    public FeedbackPagerAdapter(FragmentManager fragmentManager)
    {
        super(fragmentManager);
        fragMan = fragmentManager;
        fragments.add(new FeedbackOverviewLeftFragment());
        fragments.add(new FeedbackOverviewRightFragment());
        fragments.add(new FeedbackDetailFragment());
        fragments.add(new FeedbackCommentFragment());
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return fragments.get(0);
            case 1: // Fragment # 0 - This will show FirstFragment
                return fragments.get(1);
            case 2: // Fragment # 0 - This will show FirstFragment
                return fragments.get(2);
            case 3: // Fragment # 0 - This will show FirstFragment
                return fragments.get(3);
        }
        return null;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }




}
