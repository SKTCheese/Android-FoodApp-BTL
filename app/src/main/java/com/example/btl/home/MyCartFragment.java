package com.example.btl.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.adapters.CartAdapter;
import com.example.btl.models.CartModel;
import com.example.btl.storage.CartStorage;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    List<CartModel> list;
    CartAdapter cartAdapter;
    RecyclerView recyclerView;

    public MyCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        recyclerView = view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        list.addAll(CartStorage.getCart(requireContext()));

        cartAdapter = new CartAdapter(list);
        recyclerView.setAdapter(cartAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (list != null && cartAdapter != null && getContext() != null) {
            list.clear();
            list.addAll(CartStorage.getCart(requireContext()));
            cartAdapter.notifyDataSetChanged();
        }
    }
}
