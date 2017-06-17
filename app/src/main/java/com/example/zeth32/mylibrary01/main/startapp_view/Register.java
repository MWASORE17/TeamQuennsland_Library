package com.example.zeth32.mylibrary01.main.startapp_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.entity.TemporaryUser;
import com.example.zeth32.mylibrary01.main.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Zeth32 on 02/06/2017.
 */

public class Register extends Fragment {

    public static Register newInstance() {
        Register fragment = new Register();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.start_register, container, false);
        init(_view);
        event();

        if(TemporaryUser.temporaryUsers.size() != 0){
            registerEmail.setText(TemporaryUser.temporaryUsers.get(0).getEmail().toString());
            TemporaryUser.temporaryUsers.clear();
        }

        return _view;
    }

    private TextInputLayout registerEmailContainer;
    private EditText registerEmail;
    private TextInputLayout registerPasswordContainer;
    private EditText registerPassword;
    private TextInputLayout registerRePasswordContainer;
    private EditText registerRePassword;
    private TextView toLogin;
    private Button btnRegister;
    private void init(View view){
        registerEmailContainer = (TextInputLayout) view.findViewById(R.id.registerEmailContainer);
        registerEmail = (EditText) view.findViewById(R.id.registerEmail);
        registerPasswordContainer = (TextInputLayout) view.findViewById(R.id.registerPasswordContainer);
        registerPassword = (EditText) view.findViewById(R.id.registerPassword);
        registerRePasswordContainer = (TextInputLayout) view.findViewById(R.id.registerRePasswordContainer);
        registerRePassword = (EditText) view.findViewById(R.id.registerRePassword);
        toLogin = (TextView) view.findViewById(R.id.toLogin);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);

    }

    private void event(){
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ke Tampilan Login
                Fragment firstSelectedFragment = Login.newInstance();
                FragmentTransaction firstTransaction = getFragmentManager().beginTransaction();
                firstTransaction.replace(R.id.frameLoginRegister, firstSelectedFragment);
                firstTransaction.commit();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Tampilan Pertama
//                Fragment firstSelectedFragment = input_sambutan.newInstance();
//                FragmentTransaction firstTransaction = getFragmentManager().beginTransaction();
//                firstTransaction.replace(R.id.frameLoginRegister, firstSelectedFragment);
//                firstTransaction.commit();
                boolean _isvalid = true;
                registerEmailContainer.setErrorEnabled(false);
                registerPasswordContainer.setErrorEnabled(false);
                registerRePasswordContainer.setErrorEnabled(false);


                if (TextUtils.isEmpty(registerEmail.getText())) {
                    _isvalid = false;
                    registerEmailContainer.setErrorEnabled(true);
                    registerEmailContainer.setError("Email is required");
                } else if (registerPassword.getText().length() < 7) {
                    _isvalid = false;
                    registerPasswordContainer.setErrorEnabled(true);
                    registerPasswordContainer.setError("Password minimal 7");
                } else if (TextUtils.isEmpty(registerEmail.getText())) {
                    _isvalid = false;
                    registerEmailContainer.setErrorEnabled(true);
                    registerEmailContainer.setError("Email is required");
                } else if (!isemailvalid(registerEmail.getText().toString())) {
                    _isvalid = false;
                    registerEmailContainer.setErrorEnabled(true);
                    registerEmailContainer.setError("Email is not valid");
                } else if (TextUtils.isEmpty(registerPassword.getText())) {
                    _isvalid = false;
                    registerPasswordContainer.setErrorEnabled(true);
                    registerPasswordContainer.setError("Password is required");
                } else if (TextUtils.isEmpty(registerRePassword.getText())) {
                    _isvalid = false;
                    registerRePasswordContainer.setErrorEnabled(true);
                    registerRePasswordContainer.setError("Re-Password is required");
                } else if (!registerRePassword.getText().toString().equals(registerPassword.getText().toString())) {
                    _isvalid = false;
                    registerRePasswordContainer.setErrorEnabled(true);
                    registerRePasswordContainer.setError("Password not match");
                }

                Boolean _isregistered = false;
                for (User item : User.users) {
                    if (item.getEmail().equals(registerEmail.getText().toString())) {
                        _isregistered = true;
                        break;
                    }
                }

                if(_isregistered){
                    _isvalid = false;
                    registerEmailContainer.setErrorEnabled(true);
                    registerEmailContainer.setError("Email is already registered as a user.");
                }

                if (_isvalid) {
                    // Simpan Sementara
                    TemporaryUser userNew = new TemporaryUser(registerEmail.getText().toString(), registerPassword.getText().toString());
                    TemporaryUser.temporaryUsers.add(userNew);
                    // Change Tampilan ke StartRegisterInfo
                    Fragment firstSelectedFragment = StartRegisterInfo.newInstance();
                    FragmentTransaction firstTransaction = getFragmentManager().beginTransaction();
                    firstTransaction.replace(R.id.frameLoginRegister, firstSelectedFragment);
                    firstTransaction.commit();
                }
            }
        });
    }

    // Validasi Register (Email)
    public static boolean isemailvalid(String email) {
        //String _expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        String _expression = "^[a-z]([a-z0-9-\\.]+)?+@[a-z]+\\.[a-z]{2,4}+(\\.[a-z]{2,4})?$";
        CharSequence _email = email;
        Pattern _pattern = Pattern.compile(_expression, Pattern.CASE_INSENSITIVE);
        Matcher _mathcer = _pattern.matcher(_email);

        if (_mathcer.matches()) {
            return true;
        }
        return false;
    }
}
