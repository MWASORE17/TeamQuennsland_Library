package com.example.zeth32.mylibrary01.main.admin_view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.admin_view.adminHomePage;
import com.example.zeth32.mylibrary01.main.admin_view.adminPage;
import com.example.zeth32.mylibrary01.main.bookDetails;
import com.example.zeth32.mylibrary01.main.entity.Book;
import com.example.zeth32.mylibrary01.main.entity.PinjamBuku;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Zeth32 on 11/06/2017.
 */

public class confirmBookedRecycleAdapter extends RecyclerView.Adapter<confirmBookedRecycleAdapter.ViewHolder>{


    ArrayList<PinjamBuku> mItems;

    public confirmBookedRecycleAdapter(){
        super();
        mItems = new ArrayList<>();
        for(int i = 0;i<PinjamBuku.pinjamBukuList.size();i++){
            if(PinjamBuku.pinjamBukuList.get(i).getStatus().toLowerCase().equals("book")){
                mItems.add(PinjamBuku.pinjamBukuList.get(i));
            }
        }

    }

    @Override
    public confirmBookedRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.admin_confirm_booked_list, viewGroup, false);
        ViewHolder viewHolder = new confirmBookedRecycleAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(confirmBookedRecycleAdapter.ViewHolder holder, int position) {
        PinjamBuku book = mItems.get(position);

        holder.emailConfirmBook.setText(book.getEmail());
        holder.barcodeConfirmBook.setText(book.getBarcode());
        String title;

        // Set Title
        for(int i=0;i<Book.books.size();i++){
            if(Book.books.get(i).getBarcode().equals(book.getBarcode())){
                title = Book.books.get(i).getNama();
                holder.titleConfirmBook.setText(title);
                holder.gender.setText(Book.books.get(i).getGenre());
                break;
            }
        }

    }


    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView emailConfirmBook;
        private TextView barcodeConfirmBook;
        private TextView titleConfirmBook;
        private TextView gender;

        private Context context;
        public ViewHolder(final View itemView) {
            super(itemView);
            emailConfirmBook = (TextView) itemView.findViewById(R.id.emailConfirmBook);
            barcodeConfirmBook = (TextView) itemView.findViewById(R.id.barcodeConfirmBook);
            titleConfirmBook = (TextView) itemView.findViewById(R.id.titleConfirmBook);
            gender = (TextView) itemView.findViewById(R.id.genreBookList);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    PinjamBuku selected = mItems.get(getPosition());
                    // Ganti Status Book menjadi Pinjam
                    for(int i=0; i<PinjamBuku.pinjamBukuList.size();i++){
                        if(PinjamBuku.pinjamBukuList.get(i).getEmail().toLowerCase().equals(selected.getEmail())){
                            if(PinjamBuku.pinjamBukuList.get(i).getBarcode().equals(selected.getBarcode())){
                                if(PinjamBuku.pinjamBukuList.get(i).getStatus().toLowerCase().equals("book".toLowerCase())){
                                    PinjamBuku.pinjamBukuList.get(i).setStatus("pinjam");
                                    Calendar c = Calendar.getInstance();
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    String strDate = sdf.format(c.getTime());

                                    PinjamBuku.pinjamBukuList.get(i).setTanggalAwalPinjam(strDate);
                                    Toast.makeText(v.getContext(), "Success", Toast.LENGTH_SHORT).show();
                                    adminHomePage myFragment = new adminHomePage();
                                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.adminFramePage, myFragment).addToBackStack(null).commit();
                                    break;
                                }
                            }
                        }
                    }

                }
            });
        }
    }
}

