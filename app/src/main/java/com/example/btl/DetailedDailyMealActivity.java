package com.example.btl;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.adapters.DetailedDailyAdapter;
import com.example.btl.models.DetailedDailyModel;

import java.util.ArrayList;
import java.util.List;

public class DetailedDailyMealActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DetailedDailyModel> detailedDailyModelList;
    DetailedDailyAdapter dailyAdapter;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_daily_meal);

        String type = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.detailed_rec);
        imageView = findViewById(R.id.detailed_img);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailedDailyModelList = new ArrayList<>();
        dailyAdapter = new DetailedDailyAdapter(detailedDailyModelList);
        recyclerView.setAdapter(dailyAdapter);

        if (type != null && type.equalsIgnoreCase("breakfast")) {

            detailedDailyModelList.add(new DetailedDailyModel(
                    R.drawable.fav1,
                    "Breakfast 1",
                    "description",
                    "5.0",
                    "50.0",
                    "10:00 - 21:00"
            ));

            detailedDailyModelList.add(new DetailedDailyModel(
                    R.drawable.fav2,
                    "Breakfast 2",
                    "description",
                    "4.5",
                    "30.0",
                    "10:00 - 21:00"
            ));

            detailedDailyModelList.add(new DetailedDailyModel(
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

            detailedDailyModelList.add(new DetailedDailyModel(
                    R.drawable.s1,
                    "Sweets 1",
                    "description",
                    "5.0",
                    "12.0",
                    "10:00 - 21:00"
            ));

            detailedDailyModelList.add(new DetailedDailyModel(
                    R.drawable.s2,
                    "Sweets 2",
                    "description",
                    "4.5",
                    "14.0",
                    "10:00 - 21:00"
            ));

            detailedDailyModelList.add(new DetailedDailyModel(
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