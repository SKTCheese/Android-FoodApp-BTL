package com.example.btl.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.adapters.HomeHorAdapter;
import com.example.btl.adapters.HomeVerAdapter;
import com.example.btl.adapters.UpdateVerticalRec;
import com.example.btl.models.HomeHorModel;
import com.example.btl.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements UpdateVerticalRec {

    RecyclerView homeHorizontalRec, homeVerticalRec;

    ArrayList<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;

    ArrayList<HomeVerModel> homeVerModelList;
    HomeVerAdapter homeVerAdapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        View root = inflater.inflate(R.layout.home_fragment, container, false);

        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
        homeVerticalRec = root.findViewById(R.id.home_ver_rec);

        // ===== Horizontal =====
        homeHorModelList = new ArrayList<>();

        homeHorModelList.add(new HomeHorModel(R.drawable.pizza, "Pizza"));
        homeHorModelList.add(new HomeHorModel(R.drawable.hamburger, "HamBurger"));
        homeHorModelList.add(new HomeHorModel(R.drawable.fried_potatoes, "Fries"));
        homeHorModelList.add(new HomeHorModel(R.drawable.ice_cream, "Ice Cream"));
        homeHorModelList.add(new HomeHorModel(R.drawable.sandwich, "Sandwich"));

        homeHorAdapter = new HomeHorAdapter(this, getActivity(), homeHorModelList);

        homeHorizontalRec.setLayoutManager(
                new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false)
        );
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);
        homeHorizontalRec.setAdapter(homeHorAdapter);

        // ===== Vertical =====
        homeVerModelList = new ArrayList<>();
        homeVerAdapter = new HomeVerAdapter(getActivity(), homeVerModelList);

        homeVerticalRec.setLayoutManager(
                new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false)
        );
        homeVerticalRec.setHasFixedSize(true);
        homeVerticalRec.setNestedScrollingEnabled(false);
        homeVerticalRec.setAdapter(homeVerAdapter);

        // ===== Load mặc định Pizza =====
        ArrayList<HomeVerModel> defaultList = new ArrayList<>();

        defaultList.add(new HomeVerModel(R.drawable.pizza1,"Pizza 1","10:00 - 23:00","4.9","Min - $30"));
        defaultList.add(new HomeVerModel(R.drawable.pizza2,"Pizza 2","10:00 - 23:00","4.8","Min - $28"));
        defaultList.add(new HomeVerModel(R.drawable.pizza3,"Pizza 3","10:00 - 23:00","4.8","Min - $28"));
        defaultList.add(new HomeVerModel(R.drawable.pizza4,"Pizza 4","10:00 - 23:00","4.8","Min - $28"));
        homeVerModelList.addAll(defaultList);
        homeVerAdapter.notifyDataSetChanged();

        return root;
    }

    // ===== Callback từ Horizontal =====
    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {

        homeVerModelList.clear();        // Xoá data cũ
        homeVerModelList.addAll(list);  // Thêm data mới
        homeVerAdapter.notifyDataSetChanged(); // Refresh
    }
}