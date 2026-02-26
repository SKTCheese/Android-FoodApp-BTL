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
import com.example.btl.adapters.FeaturedVerAdapter;
import com.example.btl.models.FeaturedModel;
import com.example.btl.models.FeaturedVerModel;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {


    /// /////////Featured Hor RecyclerView
    List<FeaturedModel> featuredModelsList;
    RecyclerView recyclerView;
    FeaturedAdapter featuredAdapter;


    /// ////////Featured Ver RecyclerView
    List<FeaturedVerModel> featuredVerModelsList;
    RecyclerView recyclerView2;
    FeaturedVerAdapter featuredVerAdapter;
    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);



        /// ////////Featured Hor RecyclerView
        recyclerView = view.findViewById(R.id.featured_hor_rec);

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



        /// //////////Featured Ver RecyclerView

        recyclerView2 = view.findViewById(R.id.featured_ver_rec);

        recyclerView2.setLayoutManager(
                new LinearLayoutManager(
                        getContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );

        featuredVerModelsList = new ArrayList<>();

        featuredVerModelsList.add(
                new FeaturedVerModel(
                        R.drawable.ver1,
                        "Featured 1",
                        "Description 1",
                        "4.8",
                        "10:00 - 21:00"
                )
        );
        featuredVerModelsList.add(
                new FeaturedVerModel(
                        R.drawable.ver2,
                        "Featured 2",
                        "Description 2",
                        "4.9",
                        "10:00 - 21:00"
                )
        );
        featuredVerModelsList.add(
                new FeaturedVerModel(
                        R.drawable.ver3,
                        "Featured 3",
                        "Description 3",
                        "4.7",
                        "10:00 - 21:00"
                )
        );
        featuredVerModelsList.add(
                new FeaturedVerModel(
                        R.drawable.ver1,
                        "Featured 1",
                        "Description 1",
                        "4.8",
                        "10:00 - 21:00"
                )
        );
        featuredVerModelsList.add(
                new FeaturedVerModel(
                        R.drawable.ver2,
                        "Featured 2",
                        "Description 2",
                        "4.9",
                        "10:00 - 21:00"
                )
        );
        featuredVerModelsList.add(
                new FeaturedVerModel(
                        R.drawable.ver3,
                        "Featured 3",
                        "Description 3",
                        "4.7",
                        "10:00 - 21:00"
                )
        );

        featuredVerAdapter = new FeaturedVerAdapter(featuredVerModelsList);
        recyclerView2.setAdapter(featuredVerAdapter);
        return view;
    }
}