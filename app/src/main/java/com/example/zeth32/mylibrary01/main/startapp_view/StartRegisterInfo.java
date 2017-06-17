package com.example.zeth32.mylibrary01.main.startapp_view;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.Logined;
import com.example.zeth32.mylibrary01.main.StartApp;
import com.example.zeth32.mylibrary01.main.entity.TemporaryUser;
import com.example.zeth32.mylibrary01.main.entity.User;
import com.example.zeth32.mylibrary01.main.home_fragment.customClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeth32 on 04/06/2017.
 */

public class StartRegisterInfo  extends Fragment {

    public static StartRegisterInfo newInstance() {
        StartRegisterInfo fragment = new StartRegisterInfo();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        List<String> FavBook = new ArrayList<String>();
        FavBook.add("kosong");
        TemporaryUser.temporaryUsers.get(0).setFavCategory(FavBook);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.start_register_info, container, false);
        init(_view);
        event();
        return _view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.startRegisterInfoToolbar);
        toolbar.setTitle("Toolbar");

        // Set Navigation Back
        Drawable iconBack = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        iconBack.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        toolbar.setNavigationIcon(iconBack);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    // Create Menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.menu_register_info, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private String gender;
    private TextInputLayout registerNameContainer;
    private EditText registerName;
    private TextInputLayout registerAlamatContainer;
    private EditText registerAlamat;
    private TextInputLayout registerPhoneNumberContainer;
    private EditText registerPhoneNumber;
    private Button selectFavoriteCategory;
    private RadioButton genderMale;
    private RadioButton genderFemale;
    private TextView registerWelcome;
    private void init(View view){
        registerNameContainer = (TextInputLayout) view.findViewById(R.id.registerNameContainer);
        registerName = (EditText) view.findViewById(R.id.registerName);
        registerAlamatContainer = (TextInputLayout) view.findViewById(R.id.registerAlamatContainer);
        registerAlamat = (EditText) view.findViewById(R.id.registerAlamat);
        registerPhoneNumberContainer = (TextInputLayout) view.findViewById(R.id.registerPhoneNumberContainer);
        registerPhoneNumber = (EditText) view.findViewById(R.id.registerPhoneNumber);
        selectFavoriteCategory = (Button) view.findViewById(R.id.selectFavoriteCategory);
        genderMale = (RadioButton) view.findViewById(R.id.genderMale);
        genderFemale = (RadioButton) view.findViewById(R.id.genderFemale);
        registerWelcome = (TextView) view.findViewById(R.id.registerWelcome);
    }

    public void event(){
        selectFavoriteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    public void showDialog(){
        FragmentManager manager = getActivity().getFragmentManager();

        StartRegisterInfoDialog mDialog = new StartRegisterInfoDialog();
        mDialog.show(manager, "StartRegisterInfoDialog");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case android.R.id.home:
                Fragment firstSelectedFragment = Register.newInstance();
                FragmentTransaction firstTransaction = getFragmentManager().beginTransaction();
                firstTransaction.replace(R.id.frameLoginRegister, firstSelectedFragment);
                firstTransaction.commit();
                break;
            case R.id.startRegisterNext:
                boolean _isvalid = true;
                // Check Gender
                if(genderMale.isChecked()){
                    gender = "Male";
                }else if(genderFemale.isChecked()){
                    gender = "Female";
                }else{
                    _isvalid = false;
                    Toast.makeText(getActivity(), "Error Select Gender", Toast.LENGTH_SHORT).show();
                }
                // End of Check Gender
                // Validasi Data Front End
                if (TextUtils.isEmpty(registerName.getText())) {
                    _isvalid = false;
                    registerNameContainer.setErrorEnabled(true);
                    registerNameContainer.setError("Name is required");
                } else if(TextUtils.isEmpty(registerAlamat.getText())) {
                    _isvalid = false;
                    registerAlamatContainer.setErrorEnabled(true);
                    registerAlamatContainer.setError("Alamat is required");
                } else if(TextUtils.isEmpty(registerPhoneNumber.getText())) {
                    _isvalid = false;
                    registerPhoneNumberContainer.setErrorEnabled(true);
                    registerPhoneNumberContainer.setError("PhoneNumber is required");
                } else if (registerName.getText().length() < 3) {
                    _isvalid = false;
                    registerNameContainer.setErrorEnabled(true);
                    registerNameContainer.setError("Name minimal 3");
                } else if (registerPhoneNumber.getText().length() < 7) {
                    _isvalid = false;
                    registerPhoneNumberContainer.setErrorEnabled(true);
                    registerPhoneNumberContainer.setError("PhoneNumber minimal 7");
                } else if(TemporaryUser.temporaryUsers.get(0).getFavCategory().get(0).toString() == "kosong"){
                    _isvalid = false;
                    Toast.makeText(getActivity(), "Please Select Your Favorite Category",Toast.LENGTH_SHORT).show();
                } else if(TemporaryUser.temporaryUsers.get(0).getFavCategory().get(0) == null){
                    _isvalid = false;
                    Toast.makeText(getActivity(), "Please Select Your Favorite Category",Toast.LENGTH_SHORT).show();
                }
                // End of Validasi

                if(_isvalid){
                    User userNew = new User(
                            registerName.getText().toString(),
                            TemporaryUser.temporaryUsers.get(0).getEmail().toString(),
                            TemporaryUser.temporaryUsers.get(0).getPassword().toString(),
                            gender,
                            registerAlamat.getText().toString(),
                            registerPhoneNumber.getText().toString(),
                            TemporaryUser.temporaryUsers.get(0).getFavCategory());

                    User.users.add(userNew);
                    customClass.favCategory = TemporaryUser.temporaryUsers.get(0).getFavCategory();
                    //Toast.makeText(getActivity(), DaftarSementara.daftarSementara.get(0).getSambutan().get(0),Toast.LENGTH_SHORT).show();
                    TemporaryUser.temporaryUsers.clear();
                    // Ke Tampilan Login
                    firstSelectedFragment = Login.newInstance();
                    firstTransaction = getFragmentManager().beginTransaction();
                    firstTransaction.replace(R.id.frameLoginRegister, firstSelectedFragment);
                    firstTransaction.commit();
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
