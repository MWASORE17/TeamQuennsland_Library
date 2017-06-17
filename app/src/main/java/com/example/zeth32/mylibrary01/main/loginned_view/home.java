package com.example.zeth32.mylibrary01.main.loginned_view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.bookDetails;
import com.example.zeth32.mylibrary01.main.entity.Book;
import com.example.zeth32.mylibrary01.main.home_fragment.adapter.searchRecycleAdapter;
import com.example.zeth32.mylibrary01.main.home_fragment.customClass;
import com.example.zeth32.mylibrary01.main.home_fragment.adapter.homePagerAdapter;
import com.example.zeth32.mylibrary01.main.scanBarcode;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.ArrayList;

/**
 * Created by Zeth32 on 25/05/2017.
 */

public class home extends Fragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener{
    RecyclerView mRecycleView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    public static home newInstance() {
        home fragment = new home();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        mSectionsPagerAdapter = new homePagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);


        tabLayout.setupWithViewPager(mViewPager);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.homeToolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_activity, container, false);

        // Isi Data Buku

        mRecycleView = (RecyclerView) v.findViewById(R.id.homeSearchRecycleView);
        mRecycleView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this.getActivity(),1);
        mRecycleView.setLayoutManager(mLayoutManager);

        ArrayList<Book> mItems = Book.books;

        mAdapter = new searchRecycleAdapter(mItems);
        mRecycleView.setAdapter(mAdapter);
        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.menu_home, menu);
        MenuItem item = menu.findItem(R.id.homeSearch);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                tabLayout.setVisibility(View.VISIBLE);
                homeSearchRecycleView.setVisibility(View.GONE);
                mViewPager.setVisibility(View.VISIBLE);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private homePagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private RecyclerView homeSearchRecycleView;
    private void init(View view){
        tabLayout = (TabLayout) view.findViewById(R.id.homeTabs);
        homeSearchRecycleView = (RecyclerView) view.findViewById(R.id.homeSearchRecycleView);
        mViewPager = (ViewPager) view.findViewById(R.id.homeContainer);
    }

    private void event(){

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeSearchBarcode:
                Intent intent = new Intent(getActivity(), scanBarcode.class);
                startActivityForResult(intent, 0);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0){
            if(resultCode == CommonStatusCodes.SUCCESS){
                if(data!=null){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    boolean found = false;
                    for(int i =0; i<Book.books.size();i++){
                        if(barcode.displayValue.equals(Book.books.get(i).getBarcode())){
                            customClass.detailBuku = Book.books.get(i);
                            Intent intent = new Intent(getContext(), bookDetails.class);
                            getContext().startActivity(intent);
                            found = true;
                            break;
                       }
                    }
                    if(found == false){
                        Toast.makeText(getActivity(), "Not Found", Toast.LENGTH_SHORT).show();
                    }

                } else{
                    Toast.makeText(getActivity(), "Ooppss..", Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        //ChangeColor HomeSearch
        MenuItem homeSearch = menu.findItem(R.id.homeSearch);
        Drawable iconHomeSearch = getResources().getDrawable(R.drawable.ic_search_black_24dp);
        iconHomeSearch.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        homeSearch.setIcon(iconHomeSearch);

        //ChangeColor HomeBarcodeSearch
        MenuItem homeBarcodeSearch = menu.findItem(R.id.homeSearchBarcode);
        Drawable iconHomeBarcodeSearch = getResources().getDrawable(R.drawable.ic_code_black_24dp);
        iconHomeBarcodeSearch.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        homeBarcodeSearch.setIcon(iconHomeBarcodeSearch);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return false;
    }



    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        tabLayout.setVisibility(View.GONE);
        homeSearchRecycleView.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.GONE);


        if (newText == null || newText.trim().isEmpty()) {
            ArrayList<Book> mItems = Book.books;

            mAdapter = new searchRecycleAdapter(mItems);
            mRecycleView.setAdapter(mAdapter);

        }
        else {
            ArrayList<Book> filter = new ArrayList<>();
            ArrayList<Book> mItems = Book.books;

            for (int i = 0; i < mItems.size() ; i++){
                String title = String.valueOf(mItems.get(i).getNama().toLowerCase());

                if (title.contains(newText.toLowerCase())){

                    filter.add(mItems.get(i));
                }

                mAdapter = new searchRecycleAdapter(filter);
                mRecycleView.setAdapter(mAdapter);
            }
        }
        return false;
    }

}
