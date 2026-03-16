package com.example.btl;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.adapters.DetailedMenuAdapter;
import com.example.btl.models.DetailedMenuModel;

import java.util.ArrayList;
import java.util.List;

public class DetailedMenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DetailedMenuModel> detailedDailyModelList;
    DetailedMenuAdapter dailyAdapter;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_menu);

        String type = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.detailed_rec);
        imageView = findViewById(R.id.detailed_img);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailedDailyModelList = new ArrayList<>();
        dailyAdapter = new DetailedMenuAdapter(detailedDailyModelList);
        recyclerView.setAdapter(dailyAdapter);

        if (type != null && type.equalsIgnoreCase("breakfast")) {

            detailedDailyModelList.add(new DetailedMenuModel(
                    R.drawable.fav1,
                    "Breakfast 1",
                    "description",
                    "5.0",
                    "50.0",
                    "10:00 - 21:00"
            ));

            detailedDailyModelList.add(new DetailedMenuModel(
                    R.drawable.fav2,
                    "Breakfast 2",
                    "description",
                    "4.5",
                    "30.0",
                    "10:00 - 21:00"
            ));

            detailedDailyModelList.add(new DetailedMenuModel(
                    R.drawable.fav3,
                    "Breakfast 3",
                    "description",
                    "4.8",
                    "45.0",
                    "10:00 - 21:00"
            ));

            dailyAdapter.notifyDataSetChanged();
        }

        if (type != null && type.equalsIgnoreCase("sweets")) {

            imageView.setImageResource(R.drawable.sweets);

            detailedDailyModelList.add(new DetailedMenuModel(
                    R.drawable.s1,
                    "Sweets 1",
                    "description",
                    "5.0",
                    "12.0",
                    "10:00 - 21:00"
            ));

            detailedDailyModelList.add(new DetailedMenuModel(
                    R.drawable.s2,
                    "Sweets 2",
                    "description",
                    "4.5",
                    "14.0",
                    "10:00 - 21:00"
            ));

            detailedDailyModelList.add(new DetailedMenuModel(
                    R.drawable.s3,
                    "Sweets 3",
                    "description",
                    "4.8",
                    "20.0",
                    "10:00 - 21:00"
            ));

            dailyAdapter.notifyDataSetChanged();
        }
    }
}