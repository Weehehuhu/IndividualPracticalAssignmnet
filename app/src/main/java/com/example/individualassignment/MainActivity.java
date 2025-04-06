package com.example.individualassignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String bgColor = prefs.getString("bgColor", "#FFFFFF");
        View root = findViewById(android.R.id.content);
        root.setBackgroundColor(Color.parseColor(bgColor));
    }

    public void openCompareNumbers(View view) {
        Intent intent = new Intent(this, CompareNumbers.class);
        startActivity(intent);
    }

    public void openOrderingNumbers(View view) {
        Intent intent = new Intent(this, OrderingNumbers.class);
        startActivity(intent);
    }

    public void openComposeNumbers(View view) {
        Intent intent = new Intent(this, ComposeNumbers.class);
        startActivity(intent);
    }

    public void openSimpleCalculator(View view) {
        Intent intent = new Intent(this, SimpleCalculator.class);
        startActivity(intent);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}