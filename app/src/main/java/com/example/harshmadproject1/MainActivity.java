package com.example.harshmadproject1;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromSpinner, toSpinner;
    Button convertButton;
    TextView resultText;

    String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });
    }

    private void convert() {
        String input = inputValue.getText().toString();
        if (input.isEmpty()) {
            resultText.setText("Please enter a value");
            return;
        }

        double value = Double.parseDouble(input);
        String fromUnit = fromSpinner.getSelectedItem().toString();
        String toUnit = toSpinner.getSelectedItem().toString();

        double result = convertLength(value, fromUnit, toUnit);
        resultText.setText(String.format("%.4f %s", result, toUnit));
    }

    private double convertLength(double value, String from, String to) {
        double meters = toMeters(value, from);
        return fromMeters(meters, to);
    }

    private double toMeters(double value, String from) {
        switch (from) {
            case "Feet": return value * 0.3048;
            case "Inches": return value * 0.0254;
            case "Centimeters": return value * 0.01;
            case "Meters": return value;
            case "Yards": return value * 0.9144;
        }
        return value;
    }

    private double fromMeters(double value, String to) {
        switch (to) {
            case "Feet": return value / 0.3048;
            case "Inches": return value / 0.0254;
            case "Centimeters": return value / 0.01;
            case "Meters": return value;
            case "Yards": return value / 0.9144;
        }
        return value;
    }
}
}