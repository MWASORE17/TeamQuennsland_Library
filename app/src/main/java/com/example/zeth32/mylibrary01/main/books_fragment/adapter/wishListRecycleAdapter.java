package com.example.zeth32.mylibrary01.main.books_fragment.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.bookDetails;
import com.example.zeth32.mylibrary01.main.entity.Book;
import com.example.zeth32.mylibrary01.main.entity.Wish_Booked_List;
import com.example.zeth32.mylibrary01.main.home_fragment.customClass;

import java.util.ArrayList;

/**
 * Created by Zeth32 on 11/06/2017.
 */

public class wishListRecycleAdapter extends RecyclerView.Adapter<wishListRecycleAdapter.ViewHolder>{


    ArrayList<Book> mItems;

    public wishListRecycleAdapter(){
        super();
        mItems = new ArrayList<>();
        for(int i = 0; i< Wish_Booked_List.wishBookedList.size(); i++){
            if(Wish_Booked_List.wishBookedList.get(i).getEmail().toLowerCase() == customClass.email.toLowerCase()){
                String bCode = Wish_Booked_List.wishBookedList.get(i).getBarcode();
                for(int j =0; j<Book.books.size(); j++){
                    if(bCode.equals(Book.books.get(j).getBarcode())){
                        mItems.add(Book.books.get(j));
                    }
                }
            }
        }

    }

    @Override
    public wishListRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.home_book_list, viewGroup, false);
        ViewHolder viewHolder = new wishListRecycleAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(wishListRecycleAdapter.ViewHolder holder, int position) {
        Book book = mItems.get(position);

        String deskripsi = "";
        for(int i =0; i<book.getDeskripsi().length(); i++){
            deskripsi+= book.getDeskripsi().charAt(i);

            if(i==100){
                deskripsi+=". . . . . ( Read Me )";
                break;
            }
        }

        String namaBuku ="";
        for(int i =0; i<book.getNama().length();i++){
            namaBuku+= book.getNama().charAt(i);

            if(i==15){
                namaBuku+="...";
                break;
            }
        }

        holder.deskripsiHomeBookList.setText(deskripsi);
        holder.authorHomeBookList.setText(book.getAuthor());
        holder.titleHomeBookList.setText(namaBuku);
        holder.stockHomeBookList.setText("stock: "+String.valueOf(book.getStock()));
        holder.genreBookList.setText(book.getGenre());
    }


    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {
        private ImageView imgHomeBookList;
        private TextView titleHomeBookList;
        private TextView authorHomeBookList;
        private TextView deskripsiHomeBookList;
        private TextView stockHomeBookList;
        private TextView genreBookList;

        public ViewHolder(final View itemView) {
            super(itemView);
            imgHomeBookList = (ImageView) itemView.findViewById(R.id.imgHomeBookList);
            titleHomeBookList = (TextView) itemView.findViewById(R.id.titleHomeBookList);
            authorHomeBookList = (TextView) itemView.findViewById(R.id.authorHomeBookList);
            deskripsiHomeBookList = (TextView) itemView.findViewById(R.id.deskripsiHomeBookList);
            stockHomeBookList = (TextView) itemView.findViewById(R.id.stockHomeBookList);
            genreBookList = (TextView) itemView.findViewById(R.id.genreBookList);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    customClass.detailBuku = mItems.get(getPosition());

                    Intent intent = new Intent(v.getContext(), bookDetails.class);
                    v.getContext().startActivity(intent);
                    ((Activity)v.getContext()).finish();
                }
            });
        }
    }
}
