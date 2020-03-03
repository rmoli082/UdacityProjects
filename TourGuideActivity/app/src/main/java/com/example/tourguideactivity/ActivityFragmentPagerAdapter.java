package com.example.tourguideactivity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ActivityFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ActivityFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            // Welcome to the App Fragment
            return new MainFragment();
        } else if (position == 1){
            // Places to Go
            return new PlacesToGoFragment();
        } else if (position == 2) {
            // Places to Eat
            return new PlacesToEatFragment();
        } else if (position == 3) {
            // Places to Drink
            return new PlacesToDrinkFragment();
        } else  {
            // Places to Sleep
            return new PlacesToSleepFragment();
        }


    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.home);
        } else if (position == 1) {
            return mContext.getString(R.string.go);
        } else if (position == 2) {
            return mContext.getString(R.string.eat);
        } else if (position == 3) {
            return mContext.getString(R.string.drink);
        } else {
            return mContext.getString(R.string.sleep);
        }
    }
}
