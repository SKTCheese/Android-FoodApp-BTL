package com.example.btl.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.adapters.OrderAdapter;
import com.example.btl.models.OrderModel;
import com.example.btl.storage.CartStorage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrentOrderFragment extends Fragment implements OrderAdapter.OnCartItemListener {

    List<OrderModel> list;
    OrderAdapter cartAdapter;
    RecyclerView recyclerView;
    TextView totalText;
    TextView tvSelectedTable;

    private static final Pattern PRICE_PATTERN = Pattern.compile("(\\d+(?:\\.\\d+)?)");

    public CurrentOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_current_order, container, false);

        recyclerView = view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        totalText = view.findViewById(R.id.textView3);
        tvSelectedTable = view.findViewById(R.id.tvSelectedTable);

        // Hiển thị mã bàn từ SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("app_data", Context.MODE_PRIVATE);
        String tableCode = sharedPreferences.getString("selected_table", "B01");
        tvSelectedTable.setText("Table: " + tableCode);

        list = new ArrayList<>();
        list.addAll(CartStorage.getCart(requireContext()));
        updateTotal();

        cartAdapter = new OrderAdapter(list, this);
        recyclerView.setAdapter(cartAdapter);

        return view;
    }

    @Override
    public void onQuantityChanged(int position, int newQuantity) {
        CartStorage.updateQuantity(requireContext(), position, newQuantity);
        list.clear();
        list.addAll(CartStorage.getCart(requireContext()));
        cartAdapter.notifyDataSetChanged();
        updateTotal();
    }

    @Override
    public void onRemove(int position) {
        CartStorage.removeItem(requireContext(), position);
        list.remove(position);
        cartAdapter.notifyItemRemoved(position);
        updateTotal();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (list != null && cartAdapter != null && getContext() != null) {
            list.clear();
            list.addAll(CartStorage.getCart(requireContext()));
            cartAdapter.notifyDataSetChanged();
            updateTotal();

            // Cập nhật lại mã bàn khi quay lại fragment
            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("app_data", Context.MODE_PRIVATE);
            String tableCode = sharedPreferences.getString("selected_table", "B01");
            if (tvSelectedTable != null) {
                tvSelectedTable.setText("Table: " + tableCode);
            }
        }
    }

    private void updateTotal() {
        if (totalText == null || list == null) return;

        double total = 0d;
        for (OrderModel item : list) {
            if (item == null) continue;
            double unitPrice = parsePrice(item.getPrice());
            total += unitPrice * item.getQuantity();
        }
        totalText.setText("$" + formatMoney(total));
    }

    private static double parsePrice(String raw) {
        if (raw == null) return 0d;
        Matcher matcher = PRICE_PATTERN.matcher(raw);
        String lastNumber = null;
        while (matcher.find()) {
            lastNumber = matcher.group(1);
        }
        if (lastNumber == null) return 0d;
        try {
            return Double.parseDouble(lastNumber);
        } catch (NumberFormatException e) {
            return 0d;
        }
    }

    private static String formatMoney(double value) {
        // Nếu là số nguyên thì hiển thị gọn (vd 180 thay vì 180.00)
        if (Math.abs(value - Math.rint(value)) < 1e-9) {
            return String.valueOf((long) Math.rint(value));
        }
        return new DecimalFormat("#0.00").format(value);
    }
}
