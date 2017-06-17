package com.example.zeth32.mylibrary01.main.home_fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zeth32.mylibrary01.main.home_fragment.art;
import com.example.zeth32.mylibrary01.main.home_fragment.history;
import com.example.zeth32.mylibrary01.main.home_fragment.recommended;
import com.example.zeth32.mylibrary01.main.home_fragment.religion;
import com.example.zeth32.mylibrary01.main.home_fragment.science;
import com.example.zeth32.mylibrary01.main.home_fragment.travel;

/**
 * Created by Zeth32 on 25/05/2017.
 */

public class homePagerAdapter extends FragmentPagerAdapter {

    public homePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // Returning the current Tabs
        switch (position) {
            case 0:
                recommended tab1 = new recommended();
                return tab1;
            case 1:
                art tab2 = new art();
                return tab2;
            case 2:
                history tab3 = new history();
                return tab3;
            case 3:
                religion tab4 = new religion();
                return tab4;
            case 4:
                science tab5 = new science();
                return tab5;
            case 5:
                travel tab6 = new travel();
                return tab6;
        }
        return null;
    }


    @Override
    public int getCount() {
        // Show 6 total pages.
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Recommended";
            case 1:
                return "Art";
            case 2:
                return "History";
            case 3:
                return "Religion";
            case 4:
                return "Science";
            case 5:
                return "Travel";
        }
        return null;
    }
}