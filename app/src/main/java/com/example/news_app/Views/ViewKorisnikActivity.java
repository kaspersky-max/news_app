package com.example.news_app.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.news_app.DBHelper;
import com.example.news_app.Model.Korisnik;
import com.example.news_app.Model.KorisnikAdapter;
import com.example.news_app.R;

import java.util.List;

public class ViewKorisnikActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_korisnik);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        DBHelper db = new DBHelper(this);
        List<Korisnik> listaKorisnika = db.getKorisnikLista();

        if(listaKorisnika.size() > 0){
            KorisnikAdapter adapter = new KorisnikAdapter(listaKorisnika, this);
            recyclerView.setAdapter(adapter);
        }
        else{
            Toast.makeText(this, "Nema podataka o korisnicima!", Toast.LENGTH_SHORT).show();
        }

    }
}