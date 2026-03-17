package com.example.btl.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.SelectTableActivity;
import com.example.btl.adapters.OrderAdapter;
import com.example.btl.models.OrderModel;
import com.example.btl.storage.CartStorage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrentOrderFragment extends Fragment implements OrderAdapter.OnCartItemListener {

    List<OrderModel> list;
    OrderAdapter cartAdapter;
    RecyclerView recyclerView;
    TextView totalText;
    TextView tvSelectedTable;
    Button btnCreateOrder;

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
        btnCreateOrder = view.findViewById(R.id.btnCreateOrder);

        // Hiển thị mã bàn từ SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("app_data", Context.MODE_PRIVATE);
        String tableCode = sharedPreferences.getString("selected_table", "B01");
        tvSelectedTable.setText("Table: " + tableCode);

        list = new ArrayList<>();
        list.addAll(CartStorage.getCart(requireContext()));
        updateTotal();

        cartAdapter = new OrderAdapter(list, this);
        recyclerView.setAdapter(cartAdapter);

        btnCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.isEmpty()) {
                    Toast.makeText(getContext(), "Your order is empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                showConfirmDialog();
            }
        });

        return view;
    }

    private void showConfirmDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Confirm Order")
                .setMessage("Are you sure you want to create this order?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        createOrder();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    private void createOrder() {
        // 1. Lấy thông tin bàn
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("app_data", Context.MODE_PRIVATE);
        String tableCode = sharedPreferences.getString("selected_table", "Unknown");

        // 2. Lấy thông tin nhân viên (Sử dụng Firebase Auth nếu đã có, hoặc từ SharedPreferences)
        String staffEmail = "Unknown Staff";
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null && currentUser.getEmail() != null) {
            staffEmail = currentUser.getEmail();
        } else {
            // Backup nếu chưa dùng Firebase Auth thì lấy từ SharedPreferences bạn đã lưu lúc Login
            staffEmail = sharedPreferences.getString("user_email", "staff@restaurant.com");
        }

        // 3. Tham chiếu đến Firebase Database
        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance("https://btl-staff-order-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .getReference("Orders");

        String orderId = databaseReference.push().getKey();

        // 4. Đóng gói dữ liệu (Thêm staffEmail để quản lý)
        Map<String, Object> orderData = new HashMap<>();
        orderData.put("tableCode", tableCode);
        orderData.put("staffEmail", staffEmail); // <--- THÊM DÒNG NÀY ĐỂ TRUY VẾT
        orderData.put("totalPrice", totalText.getText().toString());
        orderData.put("timestamp", ServerValue.TIMESTAMP);
        orderData.put("status", "Pending");
        orderData.put("items", list);

        // 5. Gửi lên Firebase
        if (orderId != null) {
            databaseReference.child(orderId).setValue(orderData)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Order Created Successfully!", Toast.LENGTH_SHORT).show();

                                CartStorage.clearCart(requireContext());
                                list.clear();
                                cartAdapter.notifyDataSetChanged();
                                updateTotal();

                                if (getActivity() != null) {
                                    Intent intent = new Intent(getActivity(), SelectTableActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            } else {
                                String errorMsg = task.getException() != null ? task.getException().getMessage() : "Unknown error";
                                Toast.makeText(getContext(), "Error: " + errorMsg, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
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
        if (Math.abs(value - Math.rint(value)) < 1e-9) {
            return String.valueOf((long) Math.rint(value));
        }
        return new DecimalFormat("#0.00").format(value);
    }
}
