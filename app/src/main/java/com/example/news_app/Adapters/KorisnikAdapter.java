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
        final Korisnik korisnik = korisnici.get(position);

        holder.textViewID.setText(Integer.toString(korisnik.getId()));
        holder.editText_ime.setText(korisnik.getIme());
        holder.editText_prezime.setText(korisnik.getPrezime());
        holder.editText_username.setText(korisnik.getUsername());
        holder.editText_password.setText(korisnik.getPassword());
        holder.editText_tip_korisnika.setText(korisnik.getTipKorisnika());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newIme = holder.editText_ime.getText().toString();
                String newPrezime = holder.editText_prezime.getText().toString();
                String newUsername = holder.editText_username.getText().toString();
                String newPassword = holder.editText_password.getText().toString();
                String newTip_korisnika = holder.editText_tip_korisnika.getText().toString();

                db.izmeniKorisnika(new Korisnik(korisnik.getId(), newIme, newPrezime, newUsername, newPassword, newTip_korisnika));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.obrisiKorisnika(korisnik.getId());
                korisnici.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return korisnici.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_ime;
        EditText editText_prezime;
        EditText editText_username;
        EditText editText_password;
        EditText editText_tip_korisnika;
        Button btnEdit;
        Button btnDelete;
        public ViewHolder(@NonNull View view){
            super(view);

            textViewID = view.findViewById(R.id.editKorisnikId);
            editText_ime = view.findViewById(R.id.editKorisnikIme);
            editText_prezime = view.findViewById(R.id.editKorisnikPrezime);
            editText_username = view.findViewById(R.id.editKorisnikUsername);
            editText_password = view.findViewById(R.id.editKorisnikPassword);
            editText_tip_korisnika = view.findViewById(R.id.editKorisnikTip);
            btnEdit = view.findViewById(R.id.editKorisnikBtn);
            btnDelete = view.findViewById(R.id.deleteKorisnikBtn);
        }
    }
}
