package com.example.zeth32.mylibrary01.main.admin_view.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.admin_view.adminHomePage;
import com.example.zeth32.mylibrary01.main.entity.Book;
import com.example.zeth32.mylibrary01.main.entity.PinjamBuku;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Zeth32 on 11/06/2017.
 */

public class kembalikanBukuRecycleAdapter  extends RecyclerView.Adapter<kembalikanBukuRecycleAdapter.ViewHolder>{


    ArrayList<PinjamBuku> mItems;

    public kembalikanBukuRecycleAdapter(){
        super();
        mItems = new ArrayList<>();
        for(int i = 0;i<PinjamBuku.pinjamBukuList.size();i++){
            if(PinjamBuku.pinjamBukuList.get(i).getStatus().toLowerCase().equals("pinjam")){
                mItems.add(PinjamBuku.pinjamBukuList.get(i));
            }
        }

    }

    @Override
    public kembalikanBukuRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.admin_user_kembalikan_buku_list, viewGroup, false);
        ViewHolder viewHolder = new kembalikanBukuRecycleAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(kembalikanBukuRecycleAdapter.ViewHolder holder, int position) {
        PinjamBuku book = mItems.get(position);

        holder.email.setText(book.getEmail());
        holder.barcode.setText(book.getBarcode());
        String title;

        // Set Title
        for(int i = 0; i< Book.books.size(); i++){
            if(Book.books.get(i).getBarcode().equals(book.getBarcode())){
                title = Book.books.get(i).getNama();
                holder.title.setText(title);
                holder.genre.setText(Book.books.get(i).getGenre());
                break;
            }
        }
        // Set Denda
        holder.denda.setText(String.valueOf(book.hitungDenda()));
    }


    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView email;
        private TextView barcode;
        private TextView title;
        private TextView genre;
        private TextView denda;

        private Context context;
        public ViewHolder(final View itemView) {
            super(itemView);
            email = (TextView) itemView.findViewById(R.id.emailKembalikanBook);
            barcode = (TextView) itemView.findViewById(R.id.barcodeKembalikanBuku);
            title = (TextView) itemView.findViewById(R.id.titleKembalikanBuku);
            genre = (TextView) itemView.findViewById(R.id.genreBookList);
            denda = (TextView) itemView.findViewById(R.id.dendaBuku);


            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Success", Toast.LENGTH_SHORT).show();
                    PinjamBuku selected = mItems.get(getPosition());
                    // Ganti Status Book menjadi Pinjam
                    for(int i=0; i<PinjamBuku.pinjamBukuList.size();i++){
                        if(PinjamBuku.pinjamBukuList.get(i).getEmail().toLowerCase().equals(selected.getEmail())){
                            if(PinjamBuku.pinjamBukuList.get(i).getBarcode().equals(selected.getBarcode())){
                                if(PinjamBuku.pinjamBukuList.get(i).getStatus().toLowerCase().equals("pinjam")) {
                                    PinjamBuku.pinjamBukuList.remove(i);

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