package com.example.lostandfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createAdvertButton = findViewById(R.id.createAdvertButton);
        Button showItemsButton = findViewById(R.id.showItemsButton);

        createAdvertButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateAdvertActivity.class);
            startActivity(intent);
        });

        showItemsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShowItemsActivity.class);
            startActivity(intent);
        });
    }
}
