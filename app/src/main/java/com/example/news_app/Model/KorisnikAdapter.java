package com.example.news_app.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_app.DBHelper;
import com.example.news_app.R;

import java.util.List;

public class KorisnikAdapter extends RecyclerView.Adapter<KorisnikAdapter.ViewHolder>{

    List<Korisnik> korisnici;
    Context context;
    DBHelper db;

    public KorisnikAdapter(List<Korisnik> korisnici, Context context) {
        this.korisnici = korisnici;
        this.context = context;
        db = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lista_korisnika, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View view){
            super(view);
        }
    }
}
