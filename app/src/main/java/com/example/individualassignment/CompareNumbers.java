package com.example.individualassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.Random;

public class CompareNumbers extends AppCompatActivity {

    TextView RndNum, Result;
    int num1, num2;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_numbers);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String bgColor = prefs.getString("bgColor", "#FFFFFF");
        View root = findViewById(android.R.id.content);
        root.setBackgroundColor(Color.parseColor(bgColor));

        RndNum = (TextView)findViewById(R.id.RandomNumbers);
        Result = (TextView) findViewById(R.id.AnsResult);
        back = (Button) findViewById(R.id.CompareReturnMain);

        generateNumbers();
    }

    public void generateNumbers(View view){
        generateNumbers();
    }

    public void generateNumbers() {
        Random random = new Random();
        num1 = random.nextInt(999) ;
        num2 = random.nextInt(999) ;
        RndNum.setText(num1 + "  ?  " + num2);
        Result.setText(" ");
    }

    public void Return(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void checkAns(View view) {
        String tag = (String) view.getTag();

        if (tag.equals("greater")) {
            if (num1 > num2) {
                Result.setText("Correct!");
            } else {
                Result.setText("Try Again");
            }
        } else if (tag.equals("lesser")) {
            if (num1 < num2) {
                Result.setText("Correct!");
            } else {
                Result.setText("Try Again");
            }
        } else if (tag.equals("equal")) {
            if (num1 == num2) {
                Result.setText("Correct!");
            } else {
                Result.setText("Try Again");
            }
        }
    }
}