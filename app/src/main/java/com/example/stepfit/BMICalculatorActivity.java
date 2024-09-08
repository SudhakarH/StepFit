package com.example.stepfit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BMICalculatorActivity extends AppCompatActivity {

    private EditText weightInput, heightInput;
    private TextView bmiResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        bmiResult = findViewById(R.id.bmiResult);
    }

    public void calculateBMI(View view) {
        String weightStr = weightInput.getText().toString();
        String heightStr = heightInput.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr) / 100; // Convert height to meters

            float bmi = weight / (height * height);
            bmiResult.setText(String.format("Your BMI: %.2f", bmi));
            SharedPreferences.Editor editor = getSharedPreferences("BMIPrefs", MODE_PRIVATE).edit();
            editor.putString("bmiResult", String.format("%.2f", bmi));
            editor.apply();

        } else {
            bmiResult.setText("Please enter valid inputs.");
        }
    }


}
