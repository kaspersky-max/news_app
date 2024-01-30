package com.example.news_app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_app.Adapters.Adapter;
import com.example.news_app.Model.ApiUtillities;
import com.example.news_app.Model.Response;
import com.example.news_app.Model.Vest;
import com.example.news_app.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class AktuelnoFragment extends Fragment {

    String api = "e671bbc1e1194c53bf238462250bbc12";
    ArrayList<Vest> vesti;
    Adapter adapter;
    String country = "us";
    private RecyclerView recyclerViewAktuelno;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aktuelno, null);

        recyclerViewAktuelno = view.findViewById(R.id.recylerViewAktuelno);
        vesti = new ArrayList<>();
        recyclerViewAktuelno.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), vesti);
        recyclerViewAktuelno.setAdapter(adapter);

        dobaviVesti();

        return view;
    }

    private void dobaviVesti(){

        ApiUtillities.getApiInterface().getNews(country, 10, api).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                    vesti.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
