package com.example.zeth32.mylibrary01.main.startapp_view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
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
import android.widget.Toast;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.Logined;
import com.example.zeth32.mylibrary01.main.admin_view.adminPage;
import com.example.zeth32.mylibrary01.main.alarmNotificationReceiver;
import com.example.zeth32.mylibrary01.main.entity.Book;
import com.example.zeth32.mylibrary01.main.entity.User;
import com.example.zeth32.mylibrary01.main.home_fragment.customClass;
import com.example.zeth32.mylibrary01.main.session.LoginSession;

import java.util.Calendar;

/**
 * Created by Zeth32 on 02/06/2017.
 */

public class Login extends Fragment {

    public static Login newInstance() {
        Login fragment = new Login();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.start_login, container, false);
        init(_view);
        event();
        return _view;
    }

    private TextInputLayout loginEmailContainer;
    private EditText loginEmail;
    private TextInputLayout loginPasswordContainer;
    private EditText loginPassword;
    private TextView toRegister;
    private Button btnLogin;
    private void init(View view) {
        loginEmailContainer = (TextInputLayout) view.findViewById(R.id.loginEmailContainer);
        loginEmail = (EditText) view.findViewById(R.id.loginEmail);
        loginPasswordContainer = (TextInputLayout) view.findViewById(R.id.loginPasswordContainer);
        loginPassword = (EditText) view.findViewById(R.id.loginPassword);
        toRegister = (TextView) view.findViewById(R.id.toRegister);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
    }

    private void event(){
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tampilan Pertama

                Fragment firstSelectedFragment = Register.newInstance();
                FragmentTransaction firstTransaction = getFragmentManager().beginTransaction();
                firstTransaction.replace(R.id.frameLoginRegister, firstSelectedFragment);
                firstTransaction.commit();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tampilan Pertama
                boolean _isvalid = true;
                loginEmailContainer.setErrorEnabled(false);
                loginPasswordContainer.setErrorEnabled(false);

                if(loginEmail.getText().toString().toLowerCase().equals("admin")){
                    if(loginPassword.getText().toString().equals("admin")){
                        Intent intent = new Intent(getActivity(), adminPage.class);
                        startActivity(intent);
                        getActivity().finish();
                        _isvalid = false;
                    }
                }

                //Validasi FrontEnd
                if (TextUtils.isEmpty(loginEmail.getText())) {
                    _isvalid = false;
                    loginEmailContainer.setErrorEnabled(true);
                    loginEmailContainer.setError("Email is required");
                } else if (!Register.isemailvalid(loginEmail.getText().toString())) {
                    _isvalid = false;
                    loginEmailContainer.setErrorEnabled(true);
                    loginEmailContainer.setError("Email is not valid");
                } else if (TextUtils.isEmpty(loginPassword.getText())) {
                    _isvalid = false;
                    loginPasswordContainer.setErrorEnabled(true);
                    loginPasswordContainer.setError("Password is required");
                }

                //Check Account
                if (_isvalid) {
                    Boolean _isregistered = false, _ismatch = false;
                    User _user = new User();
                    for (User item : User.users) {
                        if (item.getEmail().toLowerCase().equals(loginEmail.getText().toString().toLowerCase())) {
                            if (item.getPassword().equals(loginPassword.getText().toString())) {
                                _ismatch = true;
                                _user = item;
                            }
                            _isregistered = true;
                            break;
                        }
                    }

                    if (!_isregistered) {
                        loginEmailContainer.setErrorEnabled(true);
                        loginEmailContainer.setError("Email is not registered as a user.");
                    } else if (!_ismatch) {
                        loginPasswordContainer.setErrorEnabled(true);
                        loginPasswordContainer.setError("Password is wrong.");
                    }

                    if (_isregistered && _ismatch) {
                        LoginSession.with(getContext()).createsession(_user);
                        Toast.makeText(getActivity(), "Welcome "+ _user.getName(),Toast.LENGTH_SHORT).show();
                        customClass.favCategory = _user.getFavCategory();
                        customClass.email = _user.getEmail();

                        Intent intent = new Intent(getActivity(), Logined.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
            }
        });
    }
}