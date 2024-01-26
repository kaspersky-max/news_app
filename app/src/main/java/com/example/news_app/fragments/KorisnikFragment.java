package com.example.news_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.news_app.DBHelper;
import com.example.news_app.Model.Korisnik;
import com.example.news_app.Model.TipKorisnika;
import com.example.news_app.R;
import com.example.news_app.Views.ViewKorisnikActivity;

public class KorisnikFragment extends Fragment {

    EditText ime, prezime, username, password, tip_korisnika;
    Button dodajKorisnika, pregledKorisnika;
    public KorisnikFragment() {
        // Required empty public constructor
    }
    public static KorisnikFragment newInstance() {
        KorisnikFragment fragment = new KorisnikFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_korisnik, container, false);

        ime = view.findViewById(R.id.imeKorisnik);
        prezime = view.findViewById(R.id.prezimeKorisnik);
        username = view.findViewById(R.id.usernameKorisnik);
        password = view.findViewById(R.id.passwordKorisnik);
        tip_korisnika = view.findViewById(R.id.tip_korisnikaKorisnik);

        dodajKorisnika = view.findViewById(R.id.btnDodajKorisnika);
        pregledKorisnika = view.findViewById(R.id.btnPregledKorisnika);
        
        dodajKorisnika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imeKorisnika = ime.getText().toString();
                String prezimeKorisnika = prezime.getText().toString();
                String usernameKorisnika = username.getText().toString();
                String passwordKorisnika = password.getText().toString();
                String tipKorisnika = tip_korisnika.getText().toString();

                if(tipKorisnika.equals("") || imeKorisnika.equals("") || prezimeKorisnika.equals("") || usernameKorisnika.equals("") || passwordKorisnika.equals(""))
                    Toast.makeText(getActivity(), "Popunite sva polja!", Toast.LENGTH_SHORT).show();
                else{
                    DBHelper db = new DBHelper(getActivity());
                    Korisnik korisnik = new Korisnik(0, imeKorisnika,prezimeKorisnika,usernameKorisnika, passwordKorisnika, tipKorisnika);
                    Boolean result = db.dodajKorisnika(korisnik);
                    if(result)
                        Toast.makeText(getActivity(), "Korisnik uspesno dodat!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "Korisnik nije dodat", Toast.LENGTH_SHORT).show();
                    ime.setText("");
                    prezime.setText("");
                    tip_korisnika.setText("");
                    username.setText("");
                    password.setText("");
                }
            }
        });

        pregledKorisnika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewKorisnikActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}