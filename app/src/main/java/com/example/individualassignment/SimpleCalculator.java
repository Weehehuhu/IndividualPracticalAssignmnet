package com.example.individualassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimpleCalculator extends AppCompatActivity {

    TextView NumInput;

    Button back;
    StringBuilder Input = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String bgColor = prefs.getString("bgColor", "#FFFFFF");
        View root = findViewById(android.R.id.content);
        root.setBackgroundColor(Color.parseColor(bgColor));

        NumInput = (TextView) findViewById(R.id.NumInput);
        back = (Button) findViewById(R.id.CalcReturnMain);
    }

    public void onButtonClick(View view) {
        String buttonText = ((TextView)view).getText().toString();
        if ( Input.length() == 1 &&  Input.charAt(0) == '0') {
             Input.setLength(0); // remove leading 0
        }
         Input.append(buttonText);
          NumInput.setText( Input.toString());
    }

    public void BtnClear(View view) {
         Input.setLength(0);
          NumInput.setText("0");
    }

    public void BtnDelete(View view) {
        if ( Input.length() > 0) {
             Input.deleteCharAt( Input.length() - 1);
              NumInput.setText( Input.length() > 0 ?  Input.toString() : "0");
        }
    }

    public void BtnEqual(View view) {
        try {
            String expression =  Input.toString()
                    .replace("×", "*")
                    .replace("÷", "/")
                    .replace("−", "-");

            double result = eval(expression);
              NumInput.setText(String.valueOf(result));
             Input.setLength(0);
             Input.append(result);
        } catch (Exception e) {
              NumInput.setText("Error");
             Input.setLength(0);
        }
    }

    public void Return(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Very basic expression evaluator (no parentheses or operator precedence)
    private double eval(String expr) {
        String[] tokens = expr.split("(?<=[-+*/])|(?=[-+*/])");
        double result = Double.parseDouble(tokens[0]);
        for (int i = 1; i < tokens.length - 1; i += 2) {
            String op = tokens[i];
            double num = Double.parseDouble(tokens[i + 1]);
            switch (op) {
                case "+": result += num; break;
                case "-": result -= num; break;
                case "*": result *= num; break;
                case "/": result /= num; break;
            }
        }
        return result;
    }
}