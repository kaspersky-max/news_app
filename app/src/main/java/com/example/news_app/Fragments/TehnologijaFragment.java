package com.example.news_app.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_app.Adapters.Adapter;
import com.example.news_app.Model.Vest;
import com.example.news_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class TehnologijaFragment extends Fragment {

    String api = "e671bbc1e1194c53bf238462250bbc12";
    ArrayList<Vest> vesti;
    Adapter adapter;
    int brArtikala = 10;
    String drzava = "us";
    private RecyclerView recyclerViewTech;
    private String kategorija = "technology";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tehnologija, null);

        recyclerViewTech = view.findViewById(R.id.recylerViewTehnologija);
        vesti = new ArrayList<>();
        recyclerViewTech.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), vesti);
        recyclerViewTech.setAdapter(adapter);

        dobaviVesti();

        return view;
    }

    private void dobaviVesti(){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("http://192.168.0.29:5000/vesti/"+drzava+"/"+brArtikala+"/"+kategorija).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                Log.e("OkHTTP", "Request Failed: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        JSONArray articles = jsonObject.getJSONArray("articles");

                        for(int i = 0;i < articles.length();i++){
                            JSONObject article = articles.getJSONObject(i);
                            String author = article.optString("author");
                            String title = article.optString("title");
                            String description = article.optString("descritpion");
                            String url = article.optString("url");
                            String urlToImage = article.optString("urlToImage");
                            String publishedAt = article.optString("publishedAt");

                            Vest vest = new Vest(0, author,title,description,url,urlToImage,publishedAt);
                            vesti.add(vest);
                            //Toast.makeText(getContext(), author, Toast.LENGTH_SHORT).show();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    } catch (JSONException e) {
                        Log.e("JSON Exception", "Error", e);
                        throw new RuntimeException(e);
                    }
                }
                else{
                    Log.e("ReponseError", "Response Failed" + response.code());
                }
            }
        });
    }
}
