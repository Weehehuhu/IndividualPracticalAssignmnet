package com.example.individualassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.Random;

public class ComposeNumbers extends AppCompatActivity {

    TextView CompNum, Ans;
    EditText FirstNum, SecondNum;
    Button Check, NewNum, Back;
    int target;
    boolean isFirstActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_numbers);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String bgColor = prefs.getString("bgColor", "#FFFFFF");
        View root = findViewById(android.R.id.content);
        root.setBackgroundColor(Color.parseColor(bgColor));

        CompNum = (TextView) findViewById(R.id.ComposeNumber);
        Ans = (TextView) findViewById(R.id.AnsResult);
        FirstNum = (EditText) findViewById(R.id.edtFirstNumber);
        SecondNum = (EditText) findViewById(R.id.edtSecondNumber);
        Check = (Button) findViewById(R.id.btnCheck);
        NewNum = (Button) findViewById(R.id.btnNewNum);
        Back = (Button) findViewById(R.id.ComposeReturnMain);

        compNumGenerate();

        FirstNum.setOnClickListener(v -> isFirstActive = true);
        SecondNum.setOnClickListener(v -> isFirstActive = false);
    }

    public void compNumGenerate(View view) {
        compNumGenerate();
    }

    public void compNumGenerate() {
        Random random = new Random();
        target = 10 + random.nextInt(20); // Target between 10 and 30
        CompNum.setText("Try this number: " + target);
        Ans.setText("");
        FirstNum.setText("");
        SecondNum.setText("");
    }

    public void numberClicked(View view) {
        Button btn = (Button) view;
        String digit = btn.getText().toString();

        EditText target = isFirstActive ? FirstNum : SecondNum;
        target.setText(target.getText().toString() + digit);
    }

    public void deleteClicked(View view) {
        EditText target = isFirstActive ? FirstNum : SecondNum;
        String current = target.getText().toString();
        if (!current.isEmpty()) {
            target.setText(current.substring(0, current.length() - 1));
        }
    }

    public void compNumAns(View view) {
        try {
            int num1 = Integer.parseInt(FirstNum.getText().toString());
            int num2 = Integer.parseInt(SecondNum.getText().toString());

            if (num1 + num2 == target) {
                Ans.setText("Well done! ✅");
            } else {
                Ans.setText("Oops! Try again.");
            }
        } catch (NumberFormatException e) {
            Ans.setText("Please enter valid numbers.");
        }
    }

    public void Return(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}