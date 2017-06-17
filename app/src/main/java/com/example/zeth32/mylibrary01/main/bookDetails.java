package com.example.zeth32.mylibrary01.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.entity.Book;
import com.example.zeth32.mylibrary01.main.entity.PinjamBuku;
import com.example.zeth32.mylibrary01.main.entity.TemporaryUser;
import com.example.zeth32.mylibrary01.main.entity.User;
import com.example.zeth32.mylibrary01.main.entity.Wish_Booked_List;
import com.example.zeth32.mylibrary01.main.home_fragment.customClass;
import com.example.zeth32.mylibrary01.main.loginned_view.books;
import com.example.zeth32.mylibrary01.main.startapp_view.Login;
import com.example.zeth32.mylibrary01.main.startapp_view.Register;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Zeth32 on 08/06/2017.
 */

public class bookDetails extends AppCompatActivity{
    private boolean book;
    private boolean wishlist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.book_detail);
        init();
        setText();
        event();
        Toolbar toolbar = (Toolbar) findViewById(R.id.bookDetailToolbar);
        toolbar.setTitle("Book Detail");

        // Set Navigation Back
        Drawable iconBack = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        iconBack.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        toolbar.setNavigationIcon(iconBack);

        this.setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);
    }

    private TextView statusBookDetail;
    private TextView ratingBookDetail;
    private TextView stockBookDetail;
    private TextView namaBookDetail;
    private TextView authorBookDetail;
    private TextView categoryBookDetail;
    private Button btnBooking;
    private Button btnAddWishList;
    private TextView deskripsiBookList;
    private TextView denda;
    private void init(){
        statusBookDetail = (TextView) findViewById(R.id.statusBookDetail);
        ratingBookDetail = (TextView) findViewById(R.id.ratingBookDetail);
        stockBookDetail = (TextView) findViewById(R.id.stockBookDetail);
        namaBookDetail = (TextView) findViewById(R.id.namaBookDetail);
        authorBookDetail = (TextView) findViewById(R.id.authorBookDetail);
        categoryBookDetail = (TextView) findViewById(R.id.categoryBookDetail);
        btnBooking = (Button) findViewById(R.id.btnBooking);
        btnAddWishList = (Button) findViewById(R.id.btnAddWishList);
        deskripsiBookList = (TextView) findViewById(R.id.deskripsiBookList);
        denda = (TextView) findViewById(R.id.bookListDenda);
    }

    private void setText(){
        ratingBookDetail.setText("Rating: "+String.valueOf(customClass.detailBuku.getRating()));
        stockBookDetail.setText("Stock: "+String.valueOf(customClass.detailBuku.getStock()));
        namaBookDetail.setText(String.valueOf(customClass.detailBuku.getNama()));
        authorBookDetail.setText(customClass.detailBuku.getAuthor());
        categoryBookDetail.setText(customClass.detailBuku.getGenre());
        deskripsiBookList.setText(customClass.detailBuku.getDeskripsi());

        wishlist = false;
        book = false;
        // Check WistList
        // Jika WishList Berisi
        if(Wish_Booked_List.wishBookedList.size() != 0){
            for(int i=0; i<Wish_Booked_List.wishBookedList.size(); i++){
                // Cari isi WishList User
                if(Wish_Booked_List.wishBookedList.get(i).getEmail().toLowerCase().equals(customClass.email.toLowerCase())){
                    // Check ID Detail Buku(Tampil) dengan WishList(Sama/Tidak)
                    if(customClass.detailBuku.getBarcode().equals(Wish_Booked_List.wishBookedList.get(i).getBarcode())){
                        wishlist = true;
                        btnAddWishList.setText("Delete From WishList");
                    }
                }
            }
        }

        // Check Status
        if(PinjamBuku.pinjamBukuList.size()!=0){
            for(int i=0; i<PinjamBuku.pinjamBukuList.size(); i++){
                // Cari isi BookedList User
                if(PinjamBuku.pinjamBukuList.get(i).getEmail().toLowerCase().equals(customClass.email.toLowerCase())){
                    // Check ID Detail Buku(Tampil) dengan Wishlist (Sama / tidak)
                    if(customClass.detailBuku.getBarcode().equals(PinjamBuku.pinjamBukuList.get(i).getBarcode().toLowerCase())){
                        if(PinjamBuku.pinjamBukuList.get(i).getStatus().toLowerCase().equals("done")) {
                            statusBookDetail.setText("Status: Selesai pinjam");
                            btnBooking.setEnabled(true);
                        } else if(PinjamBuku.pinjamBukuList.get(i).getStatus().toLowerCase().equals("pinjam")){
                            btnBooking.setEnabled(false);
                            statusBookDetail.setText("Status: "+ PinjamBuku.pinjamBukuList.get(i).getStatus());
                            btnBooking.setText("Dipinjam");
                            denda.setText("Denda: "+String.valueOf(PinjamBuku.pinjamBukuList.get(i).hitungDenda()));
                        } else{
                            btnBooking.setEnabled(true);
                            book = true;
                            statusBookDetail.setText("Status: "+ PinjamBuku.pinjamBukuList.get(i).getStatus());
                            btnBooking.setText("Cancel Book");
                        }
                    }
                }
            }
        }
    }

    private void event(){
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mau Book
                if(book == false){
                    // Cek Stock
                    if(customClass.detailBuku.getStock() != 0){
                        book = true;
                        // Kurangi Stock Buku
                        for(int i=0; i< Book.books.size(); i++){
                            if(Book.books.get(i).getBarcode().equals(customClass.detailBuku.getBarcode())){
                                int stockSekarang = Book.books.get(i).getStock();
                                Book.books.get(i).setStock(stockSekarang-1);
                                stockBookDetail.setText(String.valueOf("Stock:"+Book.books.get(i).getStock()));
                                statusBookDetail.setText("Status: book");
                                break;
                            }
                        }

                        // Tanggal Pinjam
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String strDate = sdf.format(c.getTime());
                        PinjamBuku book = new PinjamBuku(customClass.email, customClass.detailBuku.getBarcode(), strDate,"book");
                        PinjamBuku.pinjamBukuList.add(book);
                        btnBooking.setText("Cancel Book");
                    } else{
                        Toast.makeText(bookDetails.this, "Stock Habis", Toast.LENGTH_SHORT).show();
                    }
                } else if(book == true){
                    book = false;
                    // Hapus Booked Dari List
                    for(int i=0; i<PinjamBuku.pinjamBukuList.size(); i++){
                        if(PinjamBuku.pinjamBukuList.get(i).getEmail().toLowerCase().equals(customClass.email.toLowerCase())){
                            if(PinjamBuku.pinjamBukuList.get(i).getBarcode() == customClass.detailBuku.getBarcode()){
                                if(PinjamBuku.pinjamBukuList.get(i).getStatus().toLowerCase().equals("book")){
                                    PinjamBuku.pinjamBukuList.remove(i);
                                    btnBooking.setText("Booking");
                                    statusBookDetail.setText("Status: -");
                                    break;
                                }
                            }
                        }
                    }
                    // Tambah Stock Buku
                    for(int i=0; i< Book.books.size(); i++){
                        if(Book.books.get(i).getBarcode().equals(customClass.detailBuku.getBarcode())){
                            int stockSekarang = Book.books.get(i).getStock();
                            Book.books.get(i).setStock(stockSekarang+1);
                            stockBookDetail.setText(String.valueOf("Stock:"+Book.books.get(i).getStock()));
                            break;
                        }
                    }

                }

            }
        });
        btnAddWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Mau Tambahkan ke Wish List
                if(wishlist == false){
                    wishlist = true;
//                    Calendar c = Calendar.getInstance();
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String strDate = sdf.format(c.getTime());
                    Wish_Booked_List dataBuku = new Wish_Booked_List(customClass.email, customClass.detailBuku.getBarcode());
                    Wish_Booked_List.wishBookedList.add(dataBuku);
                    btnAddWishList.setText("Delete From WishList");
                }
                // Hapus dari Wish List
                else if(wishlist == true){
                    wishlist = false;
                    for(int i=0; i<Wish_Booked_List.wishBookedList.size(); i++){
                        if(Wish_Booked_List.wishBookedList.get(i).getEmail().toLowerCase().equals(customClass.email.toLowerCase())){
                            if(Wish_Booked_List.wishBookedList.get(i).getBarcode() == customClass.detailBuku.getBarcode()){
                                Wish_Booked_List.wishBookedList.remove(i);
                                btnAddWishList.setText("Add to Wish List");
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                Intent intent = new Intent(bookDetails.this, Logined.class);
                startActivityForResult(intent,0);
                this.finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
