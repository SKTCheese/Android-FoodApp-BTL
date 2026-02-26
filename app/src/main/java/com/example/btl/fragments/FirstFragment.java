package com.example.btl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.adapters.FeaturedAdapter;
import com.example.btl.models.FeaturedModel;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    List<FeaturedModel> featuredModelsList;
    RecyclerView recyclerView;
    FeaturedAdapter featuredAdapter;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        recyclerView = view.findViewById(R.id.featured_hot_rec);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        recyclerView.setLayoutManager(
                new LinearLayoutManager(
                        getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                )
        );

        featuredModelsList = new ArrayList<>();

        featuredModelsList.add(
                new FeaturedModel(
                        R.drawable.fav1,
                        "Featured 1",
                        "Description 1"
                )
        );
        featuredModelsList.add(
                new FeaturedModel(
                        R.drawable.fav2,
                        "Featured 2",
                        "Description 2"
                )
        );
        featuredModelsList.add(
                new FeaturedModel(
                        R.drawable.fav3,
                        "Featured 3",
                        "Description 3"
                )
        );

        featuredAdapter = new FeaturedAdapter(featuredModelsList);
        recyclerView.setAdapter(featuredAdapter);

        return view;
    }
}