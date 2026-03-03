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

public class ThirdFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private FeaturedVerAdapter adapter;
    private List<FeaturedVerModel> newList;

    public ThirdFragment() {
        // Required empty public constructor
    }

    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
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
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        recyclerView = view.findViewById(R.id.new_rec);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
        );

        // Data mẫu cho tab NEW
        newList = new ArrayList<>();
        newList.add(new FeaturedVerModel(R.drawable.ver3, "New 1", "Description 1", "5.0", "11:00 - 23:00"));
        newList.add(new FeaturedVerModel(R.drawable.ver2, "New 2", "Description 2", "4.9", "10:00 - 22:30"));
        newList.add(new FeaturedVerModel(R.drawable.ver1, "New 3", "Description 3", "4.8", "09:30 - 21:30"));
        newList.add(new FeaturedVerModel(R.drawable.ver3, "New 4", "Description 4", "4.7", "10:00 - 21:00"));

        adapter = new FeaturedVerAdapter(newList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}