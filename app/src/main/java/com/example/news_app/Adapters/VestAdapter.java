package com.example.news_app.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_app.Database.DBHelper;
import com.example.news_app.Model.Korisnik;
import com.example.news_app.Model.Vest;
import com.example.news_app.R;

import java.util.List;

public class VestAdapter extends RecyclerView.Adapter<VestAdapter.ViewHolder>{

    List<Vest> vesti;
    Context context;
    DBHelper db;

    public VestAdapter(List<Vest> vesti, Context context) {
        this.vesti = vesti;
        this.context = context;
        db = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.vest_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Vest vest = vesti.get(position);

        holder.textViewID.setText(Integer.toString(vest.getId()));
        holder.editTextAutor.setText(vest.getAutor());
        holder.editTextNaslov.setText(vest.getNaslov());
        holder.editTextSadrzaj.setText(vest.getSadrzaj());
        holder.editTextUrl.setText(vest.getUrl());
        holder.editTextDatum.setText(vest.getDatumPostavljanja());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newAutor = holder.editTextAutor.getText().toString();
                String newNaslov = holder.editTextNaslov.getText().toString();
                String newSadrzaj = holder.editTextSadrzaj.getText().toString();
                String newUrl = holder.editTextUrl.getText().toString();
                String newDatum = holder.editTextDatum.getText().toString();

                db.izmeniVest(new Vest(vest.getId(), newAutor, newNaslov, newSadrzaj, newUrl, null,  newDatum));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.obrisiVest(vest.getId());
                vesti.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return vesti.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editTextAutor;
        EditText editTextNaslov;
        EditText editTextSadrzaj;
        EditText editTextUrl;
        EditText editTextDatum;
        Button btnEdit;
        Button btnDelete;
        public ViewHolder(@NonNull View view){
            super(view);

            textViewID = view.findViewById(R.id.editVestId);
            editTextAutor = view.findViewById(R.id.editVestAutor);
            editTextNaslov = view.findViewById(R.id.editVestNaslov);
            editTextSadrzaj = view.findViewById(R.id.editVestSadrzaj);
            editTextUrl = view.findViewById(R.id.editVestUrl);
            editTextDatum = view.findViewById(R.id.editVestDatum);
            btnEdit = view.findViewById(R.id.editVestBtn);
            btnDelete = view.findViewById(R.id.deleteVestBtn);
        }
    }
}
