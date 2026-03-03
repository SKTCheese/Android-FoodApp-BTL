package com.example.btl.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.btl.R;
import com.example.btl.adapters.FeaturedVerAdapter;
import com.example.btl.models.FeaturedVerModel;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private FeaturedVerAdapter adapter;
    private List<FeaturedVerModel> popularList;

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        recyclerView = view.findViewById(R.id.popular_rec);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
        );

        // Data mẫu cho tab POPULAR
        popularList = new ArrayList<>();
        popularList.add(new FeaturedVerModel(R.drawable.ver1, "Popular 1", "Description 1", "4.9", "10:00 - 21:00"));
        popularList.add(new FeaturedVerModel(R.drawable.ver2, "Popular 2", "Description 2", "4.8", "09:00 - 22:00"));
        popularList.add(new FeaturedVerModel(R.drawable.ver3, "Popular 3", "Description 3", "4.7", "10:00 - 20:30"));
        popularList.add(new FeaturedVerModel(R.drawable.ver1, "Popular 4", "Description 4", "4.6", "10:30 - 21:30"));
        popularList.add(new FeaturedVerModel(R.drawable.ver2, "Popular 5", "Description 5", "4.5", "08:30 - 21:00"));

        adapter = new FeaturedVerAdapter(popularList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}