package com.example.news_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.news_app.DBHelper;
import com.example.news_app.Model.Korisnik;
import com.example.news_app.Model.KorisnikAdapter;
import com.example.news_app.R;

import java.util.List;
import java.util.zip.Inflater;

public class ViewKorisnikFragment extends Fragment {

    RecyclerView recyclerView;
    public ViewKorisnikFragment() {
        // Required empty public constructor
    }

    public static ViewKorisnikFragment newInstance() {
        ViewKorisnikFragment fragment = new ViewKorisnikFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_korisnik, container, false);

        recyclerView = view.findViewById(R.id.viewKorisnik);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        DBHelper db = new DBHelper(getActivity());
        List<Korisnik> listaKorisnika = db.getKorisnikLista();

        if(listaKorisnika.size() > 0){
            KorisnikAdapter adapter = new KorisnikAdapter(listaKorisnika, getActivity());
        }
        else{
            Toast.makeText(getActivity(), "Nema podataka o korisnicima!", Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}