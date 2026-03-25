package com.example.naturewalkstracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Walk> walks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        walks = new ArrayList<>();

        walks.add(new Walk(1, "Beach", "Daytona beach", R.drawable.beach_walk, false));
        walks.add(new Walk(2, "Canyon walk", "Grand Canyon", R.drawable.canyon_walk,false));
        walks.add(new Walk(3, "Forest walk", "Stanly Park", R.drawable.forest_walk,false));
        walks.add(new Walk(4, "Lake walk", "10-mile lake", R.drawable.lake_walk,false));
        walks.add(new Walk(5, "Meadow walk", "The meadow", R.drawable.meadow_walk,false));
        walks.add(new Walk(6, "Mountain walk", "Mt Everest", R.drawable.mountain_walk,false));
        walks.add(new Walk(7, "River walk", "Fraser River", R.drawable.river_walk,false));
        walks.add(new Walk(8, "Woodland walk", "The great woods", R.drawable.woodland_walk,false));

        RecyclerView recyclerView = findViewById(R.id.walksRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        WalksAdapter adapter = new WalksAdapter(walks, MainActivity.this);
        recyclerView.setAdapter(adapter);

        startForegroundService(new Intent(this, AmbientSoundService.class));


    }
}