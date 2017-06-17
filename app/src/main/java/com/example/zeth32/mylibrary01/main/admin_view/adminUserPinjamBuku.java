package com.example.zeth32.mylibrary01.main.admin_view;

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
import android.widget.Toast;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.entity.Book;
import com.example.zeth32.mylibrary01.main.entity.PinjamBuku;
import com.example.zeth32.mylibrary01.main.entity.User;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Zeth32 on 11/06/2017.
 */

public class adminUserPinjamBuku extends Fragment {
    public static adminUserPinjamBuku newInstance() {
        adminUserPinjamBuku fragment = new adminUserPinjamBuku();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.admin_user_pinjam_buku, container, false);
        init(_view);
        event();
        return _view;
    }

    private Button back;
    private Button save;
    private TextInputLayout emailContainer;
    private EditText email;
    private TextInputLayout barcodeContainer;
    private EditText barcode;

    public void init(View view) {
        back = (Button) view.findViewById(R.id.backAdminUserPinjamBuku);
        save = (Button) view.findViewById(R.id.btnSavePinjamBuku);
        emailContainer = (TextInputLayout) view.findViewById(R.id.pinjamBukuEmailContainer);
        email = (EditText) view.findViewById(R.id.pinjamBukuEmail);
        barcodeContainer = (TextInputLayout) view.findViewById(R.id.pinjamBukuBarcodeContainer);
        barcode = (EditText) view.findViewById(R.id.pinjamBukuBarcode);
    }

    public void event() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment firstSelectedFragment = adminHomePage.newInstance();
                FragmentTransaction firstTransaction = getFragmentManager().beginTransaction();
                firstTransaction.replace(R.id.adminFramePage, firstSelectedFragment);
                firstTransaction.commit();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean email_valid = true;
                boolean barcode_valid = true;
                if (TextUtils.isEmpty(email.getText())) {
                    email_valid = false;
                    emailContainer.setErrorEnabled(true);
                    emailContainer.setError("Email is required");
                } else if (TextUtils.isEmpty(barcode.getText())) {
                    barcode_valid = false;
                    barcodeContainer.setErrorEnabled(true);
                    barcodeContainer.setError("Barcode is required");
                } else if (checkEmail(email.getText().toString())) {
                    email_valid = false;
                    emailContainer.setErrorEnabled(true);
                    emailContainer.setError("Email belum terdaftar");
                } else if (checkBarcode(barcode.getText().toString())) {
                    barcode_valid = false;
                    barcodeContainer.setErrorEnabled(true);
                    barcodeContainer.setError("Barcode tidak terdaftar");
                } else if (checkStatusBooking(email.getText().toString(), barcode.getText().toString())){
                    barcode_valid = false;
                    barcodeContainer.setErrorEnabled(true);
                    barcodeContainer.setError("Buku sudah diBooking");
                } else if(checkStatusPinjam(email.getText().toString(), barcode.getText().toString())){
                    barcode_valid = false;
                    barcodeContainer.setErrorEnabled(true);
                    barcodeContainer.setError("Buku sedang dipinjam");
                }

                if (barcode_valid == true && email_valid == true) {
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String strDate = sdf.format(c.getTime());
                    PinjamBuku dataPinjam = new PinjamBuku(email.getText().toString().toLowerCase(),
                            barcode.getText().toString().toLowerCase(), strDate, "pinjam");
                    PinjamBuku.pinjamBukuList.add(dataPinjam);
                    // Kurangi Stock
                    for (int i = 0; i < Book.books.size(); i++) {
                        if (barcode.getText().toString().equals(Book.books.get(i).getBarcode())) {
                            Book.books.get(i).setStock(Book.books.get(i).getStock() - 1);
                            break;
                        }
                    }
                    Fragment firstSelectedFragment = adminHomePage.newInstance();
                    FragmentTransaction firstTransaction = getFragmentManager().beginTransaction();
                    firstTransaction.replace(R.id.adminFramePage, firstSelectedFragment);
                    firstTransaction.commit();


                    Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkEmail(String email) {
        for (int i = 0; i < User.users.size(); i++) {
            if (email.toLowerCase().equals(User.users.get(i).getEmail().toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBarcode(String barcode){
        for (int i = 0; i < Book.books.size(); i++) {
            if (barcode.toLowerCase().equals(Book.books.get(i).getBarcode().toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    private boolean checkStatusPinjam(String email, String Barcode){
        for(int i =0; i < PinjamBuku.pinjamBukuList.size(); i++){
            if(email.toLowerCase().equals(PinjamBuku.pinjamBukuList.get(i).getEmail().toLowerCase())){
                if(Barcode.toLowerCase().equals(PinjamBuku.pinjamBukuList.get(i).getBarcode().toLowerCase())){
                    if(PinjamBuku.pinjamBukuList.get(i).getStatus().toLowerCase().equals("pinjam")){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkStatusBooking(String email, String Barcode){
        for(int i =0; i < PinjamBuku.pinjamBukuList.size(); i++){
            if(email.toLowerCase().equals(PinjamBuku.pinjamBukuList.get(i).getEmail().toLowerCase())){
                if(Barcode.toLowerCase().equals(PinjamBuku.pinjamBukuList.get(i).getBarcode().toLowerCase())){
                    if(PinjamBuku.pinjamBukuList.get(i).getStatus().toLowerCase().equals("book")){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}