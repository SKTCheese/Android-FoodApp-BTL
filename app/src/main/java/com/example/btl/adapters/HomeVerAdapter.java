package com.example.btl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.models.OrderModel;
import com.example.btl.models.HomeVerModel;
import com.example.btl.storage.CartStorage;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder> {


    private BottomSheetDialog bottomSheetDialog;
    Context context;
    List<HomeVerModel> list;

    public HomeVerAdapter(Context context, List<HomeVerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_vertical_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final String mName = list.get(position).getName();
        final String mTiming = list.get(position).getTiming();
        final String mRating = list.get(position).getRating();
        final String mPrice = list.get(position).getPrice();
        final int mImage = list.get(position).getImage();

        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.timing.setText(list.get(position).getTiming());
        holder.rating.setText(list.get(position).getRating());
        holder.price.setText(list.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetTheme);

                View sheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout, null);

                ImageView bottomImg = sheetView.findViewById(R.id.bottom_img);
                TextView bottomName = sheetView.findViewById(R.id.bottom_name);
                TextView bottomPrice = sheetView.findViewById(R.id.bottom_price);
                TextView bottomRating = sheetView.findViewById(R.id.bottom_rating);
                TextView quantityText = sheetView.findViewById(R.id.bottom_sheet_quantity);
                ImageButton btnMinus = sheetView.findViewById(R.id.bottom_sheet_btn_minus);
                ImageButton btnPlus = sheetView.findViewById(R.id.bottom_sheet_btn_plus);

                bottomName.setText(mName);
                bottomPrice.setText(mPrice);
                bottomRating.setText(mRating);
                bottomImg.setImageResource(mImage);
                quantityText.setText("1");

                btnMinus.setOnClickListener(btnView -> {
                    int qty = Integer.parseInt(quantityText.getText().toString());
                    if (qty > 1) {
                        qty--;
                        quantityText.setText(String.valueOf(qty));
                    }
                });
                btnPlus.setOnClickListener(btnView -> {
                    int qty = Integer.parseInt(quantityText.getText().toString());
                    if (qty < 999) {
                        qty++;
                        quantityText.setText(String.valueOf(qty));
                    }
                });

                sheetView.findViewById(R.id.add_to_cart).setOnClickListener(btnView -> {
                    int qty = Integer.parseInt(quantityText.getText().toString());
                    if (qty <= 0) qty = 1;
                    CartStorage.addToCart(
                            context,
                            new OrderModel(mImage, mName, mPrice, mRating, qty)
                    );
                    Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                });

                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, timing, rating, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ver_img);
            name = itemView.findViewById(R.id.name);
            timing = itemView.findViewById(R.id.timing);
            rating = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
        }
    }
}