package com.example.btl.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.models.OrderModel;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    public interface OnCartItemListener {
        void onQuantityChanged(int position, int newQuantity);
        void onRemove(int position);
    }

    private final List<OrderModel> list;
    private final OnCartItemListener listener;

    public OrderAdapter(List<OrderModel> list, OnCartItemListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.current_order_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        OrderModel item = list.get(position);
        holder.imageView.setImageResource(item.getImage());
        holder.name.setText(item.getName());
        holder.rating.setText(item.getRating());
        holder.price.setText(item.getPrice());
        holder.quantityText.setText(String.valueOf(item.getQuantity()));

        holder.btnMinus.setOnClickListener(v -> {
            if (listener == null) return;
            int qty = item.getQuantity();
            if (qty > 1) listener.onQuantityChanged(position, qty - 1);
        });
        holder.btnPlus.setOnClickListener(v -> {
            if (listener == null) return;
            listener.onQuantityChanged(position, item.getQuantity() + 1);
        });
        holder.btnRemove.setOnClickListener(v -> {
            if (listener != null) listener.onRemove(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, rating, price, quantityText;
        ImageButton btnMinus, btnPlus, btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.detailed_img);
            name = itemView.findViewById(R.id.detailed_name);
            rating = itemView.findViewById(R.id.detailed_rating);
            price = itemView.findViewById(R.id.textView13);
            quantityText = itemView.findViewById(R.id.cart_item_quantity);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            btnPlus = itemView.findViewById(R.id.btn_plus);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }
}
