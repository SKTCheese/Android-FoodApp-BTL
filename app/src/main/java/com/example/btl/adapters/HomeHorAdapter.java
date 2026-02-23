package com.example.btl.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.models.HomeHorModel;
import com.example.btl.models.HomeVerModel;

import java.util.ArrayList;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {

    UpdateVerticalRec updateVerticalRec;
    Activity activity;
    ArrayList<HomeHorModel> list;

    int selectedPosition = 0;

    public HomeHorAdapter(UpdateVerticalRec updateVerticalRec,
                          Activity activity,
                          ArrayList<HomeHorModel> list) {

        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_horizontal_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        holder.cardView.setBackgroundResource(
                selectedPosition == position
                        ? R.drawable.change_bg
                        : R.drawable.default_bg
        );

        holder.cardView.setOnClickListener(v -> {

            int pos = holder.getAdapterPosition();
            if (pos == RecyclerView.NO_POSITION) return;

            if (selectedPosition == pos) return;

            selectedPosition = pos;
            notifyDataSetChanged();

            updateVerticalRec.callBack(pos, getVerticalData(pos));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private ArrayList<HomeVerModel> getVerticalData(int position) {

        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

        switch (position) {

            case 0:
                homeVerModels.add(new HomeVerModel(R.drawable.pizza1,"Pizza 1","10:00 - 23:00","4.9","Min - $30"));
                homeVerModels.add(new HomeVerModel(R.drawable.pizza2,"Pizza 2","10:00 - 23:00","4.8","Min - $28"));
                homeVerModels.add(new HomeVerModel(R.drawable.pizza3,"Pizza 3","10:00 - 23:00","4.8","Min - $28"));
                homeVerModels.add(new HomeVerModel(R.drawable.pizza4,"Pizza 4","10:00 - 23:00","4.8","Min - $28"));
                break;

            case 1:
                homeVerModels.add(new HomeVerModel(R.drawable.burger2,"Burger 1","10:00 - 23:00","4.7","Min - $25"));
                homeVerModels.add(new HomeVerModel(R.drawable.burger4,"Burger 2","10:00 - 23:00","4.6","Min - $27"));
                break;

            case 2:
                homeVerModels.add(new HomeVerModel(R.drawable.fries1,"Fries 1","10:00 - 23:00","4.5","Min - $15"));
                homeVerModels.add(new HomeVerModel(R.drawable.fries2,"Fries 2","10:00 - 23:00","4.4","Min - $18"));
                homeVerModels.add(new HomeVerModel(R.drawable.fries3,"Fries 3","10:00 - 23:00","4.4","Min - $18"));
                homeVerModels.add(new HomeVerModel(R.drawable.fries4,"Fries 4","10:00 - 23:00","4.4","Min - $18"));
                break;

            case 3:
                homeVerModels.add(new HomeVerModel(R.drawable.icecream2,"Ice Cream 1","10:00 - 23:00","4.9","Min - $20"));
                homeVerModels.add(new HomeVerModel(R.drawable.icecream3,"Ice Cream 2","10:00 - 23:00","4.9","Min - $20"));
                homeVerModels.add(new HomeVerModel(R.drawable.icecream4,"Ice Cream 3","10:00 - 23:00","4.9","Min - $20"));
                break;

            case 4:
                homeVerModels.add(new HomeVerModel(R.drawable.sandwich1,"Sandwich 1","10:00 - 23:00","4.3","Min - $22"));
                homeVerModels.add(new HomeVerModel(R.drawable.sandwich2,"Sandwich 2","10:00 - 23:00","4.3","Min - $22"));
                homeVerModels.add(new HomeVerModel(R.drawable.sandwich3,"Sandwich 3","10:00 - 23:00","4.3","Min - $22"));
                break;
        }

        return homeVerModels;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.hor_img);
            name = itemView.findViewById(R.id.hor_text);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}