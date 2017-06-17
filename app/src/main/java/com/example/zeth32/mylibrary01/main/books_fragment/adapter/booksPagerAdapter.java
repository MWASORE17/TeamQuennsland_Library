package com.example.zeth32.mylibrary01.main.books_fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zeth32.mylibrary01.main.books_fragment.booksHistory;
import com.example.zeth32.mylibrary01.main.books_fragment.wishList;

/**
 * Created by Zeth32 on 26/05/2017.
 */

public class booksPagerAdapter extends FragmentPagerAdapter {

    public booksPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // Returning the current Tabs
        switch (position) {
            case 0:
                wishList tab1 = new wishList();
                return tab1;
            case 1:
                booksHistory tab2 = new booksHistory();
                return tab2;
        }
        return null;
    }


    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Wish List";
            case 1:
                return "History";
        }
        return null;
    }
}
