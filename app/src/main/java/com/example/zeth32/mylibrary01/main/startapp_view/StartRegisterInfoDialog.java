package com.example.zeth32.mylibrary01.main.startapp_view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.entity.TemporaryUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeth32 on 04/06/2017.
 */

public class StartRegisterInfoDialog extends DialogFragment implements View.OnClickListener{
    private CheckBox FavCategoryArt, FavCategoryHistory, FavCategoryReligion, FavCategoryScience, FavCategoryTravel;
    private Button FavCategorySave, FavCategoryCancel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setCancelable(false);

        View view = inflater.inflate(R.layout.start_register_info_dialog, null);
        FavCategorySave = (Button) view.findViewById(R.id.FavCategorySave);
        FavCategorySave.setOnClickListener(this);
        FavCategoryCancel = (Button) view.findViewById(R.id.FavCategoryCancel);
        FavCategoryCancel.setOnClickListener(this);

        FavCategoryArt = (CheckBox) view.findViewById(R.id.FavCategoryArt);
        FavCategoryHistory = (CheckBox) view.findViewById(R.id.FavCategoryHistory);
        FavCategoryReligion = (CheckBox) view.findViewById(R.id.FavCategoryReligion);
        FavCategoryScience = (CheckBox) view.findViewById(R.id.FavCategoryScience);
        FavCategoryTravel = (CheckBox) view.findViewById(R.id.FavCategoryTravel);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.FavCategorySave){
            List<String> FavBook = new ArrayList<String>();
            boolean FavBookTerisi = false;
            if(FavCategoryArt.isChecked()){
                FavBook.add(FavCategoryArt.getText().toString());
                FavBookTerisi = true;
            }
            if(FavCategoryHistory.isChecked()){
                FavBook.add(FavCategoryHistory.getText().toString());
                FavBookTerisi = true;
            }
            if(FavCategoryReligion.isChecked()){
                FavBook.add(FavCategoryReligion.getText().toString());
                FavBookTerisi = true;
            }
            if(FavCategoryScience.isChecked()){
                FavBook.add(FavCategoryScience.getText().toString());
                FavBookTerisi = true;
            }
            if(FavCategoryTravel.isChecked()){
                FavBook.add(FavCategoryTravel.getText().toString());
                FavBookTerisi = true;
            }

            if(FavBookTerisi == false){
                FavBook.add("kosong");
            }
            else{
                Toast.makeText(getActivity(), "Saved",Toast.LENGTH_SHORT).show();
            }
            TemporaryUser.temporaryUsers.get(0).setFavCategory(FavBook);
            dismiss();
        }
        if(v.getId() == R.id.FavCategoryCancel){
            dismiss();
        }
    }
}
