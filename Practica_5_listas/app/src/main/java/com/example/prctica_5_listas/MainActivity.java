package com.example.prctica_5_listas;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recView);

        Legos[] legos = new Legos[9];
        legos[0] = new Legos("Lego Batman", "legobatman");
        legos[1] = new Legos("Lego Joker", "legojoker");
        legos[2] = new Legos("Lego Hulk", "legohulk");
        legos[3] = new Legos("Lego Nightwing", "legonightwing");
        legos[4] = new Legos("Lego Han solo", "legohansolo");
        legos[5] = new Legos("Lego Darth Vader", "legodarthvader");
        legos[6] = new Legos("Lego Gonk", "legogonk");
        legos[7] = new Legos("Lego Bobba fett", "legobobbafett");
        legos[8] = new Legos("Lego Wicket", "legowicket");

        AdaptadorLegos adapter = new AdaptadorLegos(legos);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

    }
    ;
}
