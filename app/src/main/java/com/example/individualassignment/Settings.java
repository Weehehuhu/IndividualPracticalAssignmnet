package com.example.individualassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    public static final String PREFS_NAME = "AppPrefs";
    public static final String KEY_BG_COLOR = "bgColor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String bgColor = prefs.getString("bgColor", "#FFFFFF");
        View root = findViewById(android.R.id.content);
        root.setBackgroundColor(Color.parseColor(bgColor));

        findViewById(R.id.btnBlue).setOnClickListener(v -> {
            editor.putString(KEY_BG_COLOR, "#a0fffe");
            editor.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnPink).setOnClickListener(v -> {
            editor.putString(KEY_BG_COLOR, "#ff94f7");
            editor.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnYellow).setOnClickListener(v -> {
            editor.putString(KEY_BG_COLOR, "#fefd85");
            editor.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnWhite).setOnClickListener(v -> {
            editor.putString(KEY_BG_COLOR, "#ffffff");
            editor.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}
