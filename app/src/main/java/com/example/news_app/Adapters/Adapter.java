package com.example.news_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news_app.Model.Vest;
import com.example.news_app.R;
import com.example.news_app.Views.WebActivity;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Vest> vesti;

    public Adapter(Context context, ArrayList<Vest> vesti) {
        this.context = context;
        this.vesti = vesti;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url", vesti.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.mtime.setText("Published At: "+vesti.get(position).getPublishedAt());
        holder.mauthor.setText(vesti.get(position).getAuthor());
        holder.mheading.setText(vesti.get(position).getTitle());
        holder.mcontent.setText(vesti.get(position).getDescription());
        Glide.with(context).load(vesti.get(position).getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return vesti.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mheading, mcontent, mauthor, mtime;
        CardView cardView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mheading = itemView.findViewById(R.id.mainheading);
            mcontent = itemView.findViewById(R.id.content);
            mauthor = itemView.findViewById(R.id.author);
            mtime = itemView.findViewById(R.id.time);
            cardView = itemView.findViewById(R.id.cardview);
            imageView = itemView.findViewById(R.id.imageview);
        }
    }
}
