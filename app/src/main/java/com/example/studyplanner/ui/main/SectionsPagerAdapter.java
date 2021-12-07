package com.example.studyplanner.ui.main;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.studyplanner.R;


import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {


    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.study_plan, R.string.assignments, R.string.exams, R.string.lectures};
    private static final List<String>list = new ArrayList<String>();

    //    private final Context mContext;

    public SectionsPagerAdapter( FragmentManager fm) {
        super(fm);
//        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
//        return mContext.getResources().getString(TAB_TITLES[position]);
        String title = null;
        if(position==0){
            title="STUDY PLAN";
        }
        if(position==1){
            title="ASSIGNMENTS";
        }
        if(position==2){
            title="Exams";
        }
        if(position==3){
            title="Lectures";
        }
        return title;
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}