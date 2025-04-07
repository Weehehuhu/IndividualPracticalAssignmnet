package com.example.individualassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import android.widget.GridLayout;

public class OrderingNumbers extends AppCompatActivity {

    TextView OriginalNum, Ans, OrderTypeText;
    EditText edtAnswer;
    Button btnCheck, btnNewSet, back;
    String orderType = "ascending";
   List<Integer> numberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_numbers);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String bgColor = prefs.getString("bgColor", "#FFFFFF");
        View root = findViewById(android.R.id.content);
        root.setBackgroundColor(Color.parseColor(bgColor));

        OriginalNum = (TextView) findViewById(R.id.OriginalNumbers);
        Ans = (TextView) findViewById(R.id.Answer);
        edtAnswer = (EditText) findViewById(R.id.edtAnswer);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        btnNewSet = (Button) findViewById(R.id.btnNewSet);
        back = (Button) findViewById(R.id.ReorderReturnMain);

        edtAnswer.setShowSoftInputOnFocus(false);
        showOrderSelectionDialog();
        setupNumberButtons();

        generateSet();
    }

    public void showOrderSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Order Type")
                .setItems(new CharSequence[]{"Ascending", "Descending"}, (dialog, which) -> {
                    orderType = (which == 0) ? "ascending" : "descending";
                    generateSet(null);
                })
                .show();
    }

    private void setupNumberButtons() {
        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnComma, R.id.btnDelete
        };

        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            String currentText = edtAnswer.getText().toString();

            if (b.getId() == R.id.btnDelete) {
                if (!currentText.isEmpty()) {
                    edtAnswer.setText(currentText.substring(0, currentText.length() - 1));
                }
            } else {
                edtAnswer.setText(currentText + b.getText().toString());
            }
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(listener);
        }
    }



    public void generateSet(View view){
        generateSet();
    }
    public void generateSet() {
        numberList = new ArrayList<>();
        Random random = new Random();

        while (numberList.size() < 5) {
            int num = random.nextInt(999);
            if (!numberList.contains(num)) {
                numberList.add(num);
            }
        }

        OriginalNum.setText("Reordering Numbers (" + orderType + "):\n" + numberList.toString());
        Ans.setText("");
        edtAnswer.setText("");
    }

    public void Return(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void checkAnswer(View view) {
        String input = edtAnswer.getText().toString().trim();
        String[] inputParts = input.split(",");

        if (inputParts.length != numberList.size()) {
            Ans.setText("Please enter 5 numbers separated by commas.");
            return;
        }

        List<Integer> userInput = new ArrayList<>();
        try {
            for (String s : inputParts) {
                userInput.add(Integer.parseInt(s.trim()));
            }
        } catch (NumberFormatException e) {
            Ans.setText("Please enter only numbers.");
            return;
        }

        List<Integer> expected = new ArrayList<>(numberList);
        if (orderType.equals("ascending")) {
            Collections.sort(expected);
        } else {
            expected.sort(Collections.reverseOrder());
        }

        if (userInput.equals(expected)) {
            Ans.setText("Well Done!!!");
        } else {
            Ans.setText("Too Bad. Try again.");
        }
    }

}