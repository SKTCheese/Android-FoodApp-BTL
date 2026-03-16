package com.example.btl.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.adapters.MenuAdapter;
import com.example.btl.models.MenuModel;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment {

    RecyclerView recyclerView;
    List<MenuModel> dailyMealModels;
    MenuAdapter dailyMealAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.menu_fragment, container, false);

        recyclerView = root.findViewById(R.id.daily_meal_rec);
        recyclerView.setLayoutManager (new LinearLayoutManager(getContext()));
        dailyMealModels = new ArrayList<>();

        dailyMealModels.add(new MenuModel(R.drawable.breakfast, "Breakfast", "30% OFF", "Description Description","breakfast"));
        dailyMealModels.add(new MenuModel(R.drawable.lunch, "Lunch", "20% OFF", "Description Description","lunch"));
        dailyMealModels.add(new MenuModel(R.drawable.dinner, "Dinner", "10% OFF", "Description Description","dinner"));
        dailyMealModels.add(new MenuModel(R.drawable.sweets, "Sweets", "20% OFF", "Description Description","sweets"));
        dailyMealModels.add(new MenuModel(R.drawable.coffe, "Coffee", "25% OFF", "Description Description","coffee"));

        dailyMealAdapter = new MenuAdapter(getContext(), dailyMealModels);
        recyclerView.setAdapter(dailyMealAdapter);
        dailyMealAdapter.notifyDataSetChanged();

        return root;
    }
}