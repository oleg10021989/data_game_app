package com.example.gamedataapp;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel2> dataSet;
    private static OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView_url;
        TextView textViewGameName;
        TextView textViewRelease_date;
        TextView textViewGenre;




        public MyViewHolder(View itemView) {
            super(itemView);

            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
            this.textViewGameName = (TextView) itemView.findViewById(R.id.textViewNamegame);
            this.textViewRelease_date = (TextView) itemView.findViewById(R.id.textViewRelease_date);
            this.textViewGenre = (TextView) itemView.findViewById(R.id.textViewGenre);
            this.imageView_url=(ImageView) itemView.findViewById(R.id.imageView_url_card);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }

                    }
                }
            });

        }
    }

    public CustomAdapter(ArrayList<DataModel2> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        view.setOnTouchListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewGameName = holder.textViewGameName;
        TextView textViewRelease_date = holder.textViewRelease_date;
        TextView textViewGenre = holder.textViewGenre;
        ImageView imageView_url1 = holder.imageView_url;
        CardView cardView = holder.cardView;


//
//

        textViewGameName.setText(dataSet.get(listPosition).getGame_name());
        textViewRelease_date.setText(dataSet.get(listPosition).getRelease_date());
        textViewGenre.setText(dataSet.get(listPosition).getGenre());
        Picasso.with(imageView_url1.getContext()).load(dataSet.get(listPosition).getImg_utl()).into(imageView_url1);
//        Picasso.with(imageView.getContext()).load(dataSet.get(listPosition).getURL()).into(imageView);
        Log.d("FROM SERVER: " , dataSet.get(listPosition).getImg_utl());

//
//







//        imageView.setImageResource(dataSet.get(listPosition).getURL());


//        cardView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//
//                removeAt(listPosition);
//                return false;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

//    public void removeAt(int position) {
//        dataSet.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, dataSet.size());
//    }

}
