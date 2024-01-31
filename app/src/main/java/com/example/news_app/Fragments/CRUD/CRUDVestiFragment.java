package com.example.news_app.Fragments.CRUD;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.news_app.Database.DBHelper;
import com.example.news_app.Model.Korisnik;
import com.example.news_app.Model.Vest;
import com.example.news_app.R;
import com.example.news_app.Views.ViewKorisnikActivity;
import com.example.news_app.Views.ViewVestActivity;

public class CRUDVestiFragment extends Fragment {

    EditText mautor, mnaslov, msadrzaj, murl, mdatum;
    Button dodajVest, pregledVesti;
    public CRUDVestiFragment() {
        // Required empty public constructor
    }
    public static CRUDVestiFragment newInstance() {
        CRUDVestiFragment fragment = new CRUDVestiFragment();
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
        View view = inflater.inflate(R.layout.fragment_vesti, container, false);

        mautor = view.findViewById(R.id.autor);
        mnaslov = view.findViewById(R.id.naslov);
        msadrzaj = view.findViewById(R.id.sadrzaj);
        murl = view.findViewById(R.id.url);
        mdatum = view.findViewById(R.id.datum);

        dodajVest = view.findViewById(R.id.btnDodajVest);
        pregledVesti = view.findViewById(R.id.btnPregledVesti);

        dodajVest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String autor = mautor.getText().toString();
                String naslov = mnaslov.getText().toString();
                String sadrzaj = msadrzaj.getText().toString();
                String url =  murl.getText().toString();
                String datum = mdatum.getText().toString();

                if(autor.equals("") || naslov.equals("") || sadrzaj.equals("") || url.equals("") || datum.equals(""))
                    Toast.makeText(getActivity(), "Popunite sva polja!", Toast.LENGTH_SHORT).show();
                else{
                    DBHelper db = new DBHelper(getActivity());
                    Vest vest = new Vest(0, autor,naslov,sadrzaj, url, null, datum);
                    Boolean result = db.dodajVest(vest);
                    if(result)
                        Toast.makeText(getActivity(), "Dodavanje Vesti Uspesno!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "Neuspesno Dodavanje", Toast.LENGTH_SHORT).show();
                    mautor.setText("");
                    mnaslov.setText("");
                    msadrzaj.setText("");
                    murl.setText("");
                    mdatum.setText("");
                }
            }
        });

        pregledVesti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewVestActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}