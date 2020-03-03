package com.example.tourguideactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager activityPager = findViewById(R.id.viewpager);
        ActivityFragmentPagerAdapter fragmentPager = new ActivityFragmentPagerAdapter(this, getSupportFragmentManager());

        activityPager.setAdapter(fragmentPager);

        TabLayout activityTabs = findViewById(R.id.tab_layout);
        activityTabs.setupWithViewPager(activityPager);
    }
}