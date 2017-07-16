package com.abbisqq.myapplication.adapters;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.abbisqq.myapplication.fragments.FishDetailsFragment;

/**
 * Created by chart on 17/6/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private Cursor mCursor;

    public ViewPagerAdapter(FragmentManager fm,Cursor cursor) {
        super(fm);
        mCursor = cursor;
    }

    @Override
    public Fragment getItem(int position) {
        return FishDetailsFragment.newInstance(mCursor,position);
    }

    @Override
    public int getCount() {
        return mCursor.getCount();
    }
}
