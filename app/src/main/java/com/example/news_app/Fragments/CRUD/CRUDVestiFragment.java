package com.example.news_app.Fragments.CRUD;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.news_app.R;

public class CRUDVestiFragment extends Fragment {
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
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vesti, container, false);
    }
}