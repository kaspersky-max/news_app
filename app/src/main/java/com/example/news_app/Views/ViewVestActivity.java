package com.example.news_app.Views;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_app.Adapters.KorisnikAdapter;
import com.example.news_app.Adapters.VestAdapter;
import com.example.news_app.Database.DBHelper;
import com.example.news_app.Model.Korisnik;
import com.example.news_app.Model.Vest;
import com.example.news_app.R;

import java.util.List;

public class ViewVestActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vest);

        recyclerView = findViewById(R.id.recyclerViewVest);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        DBHelper db = new DBHelper(this);
        List<Vest> listaVesti = db.getVestLista();

        if(listaVesti.size() > 0){
            VestAdapter adapter = new VestAdapter(listaVesti, this);
            recyclerView.setAdapter(adapter);
        }
        else{
            Toast.makeText(this, "Nema podataka o vestima!", Toast.LENGTH_SHORT).show();
        }

    }
}