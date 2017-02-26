package com.avinashiyer.allergent.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.avinashiyer.allergent.fragments.AllergiesFragment;
import com.avinashiyer.allergent.fragments.RecordFragment;

/**
 * Created by avinashiyer on 2/25/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AllergiesFragment tab1 = new AllergiesFragment();
                return tab1;
            case 1:
                RecordFragment tab2 = new RecordFragment();
                return tab2;
            case 2:

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
