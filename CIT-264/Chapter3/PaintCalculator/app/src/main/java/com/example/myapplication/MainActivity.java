package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
public class MainActivity extends AppCompatActivity {
String c = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Spinner colors;
        final EditText ftString, hString;
        Button calcBtn;
        TextView calcLbl, color;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         colors = findViewById(R.id.colorSpinner);
       ftString = findViewById(R.id.ftTxt);
       color = findViewById(R.id.colorsTxt);
        hString = findViewById(R.id.hTxt);
        calcLbl = findViewById(R.id.textView2);
        calcBtn = findViewById(R.id.calculateBtn);
        colors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        List<String> col = new ArrayList<>();
        col.add("Red");
        col.add("Blue");
        col.add("Green");
        col.add("Yellow");
        col.add("Orange");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, col);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colors.setAdapter(dataAdapter);

        calcBtn.setOnClickListener(v -> {
            double h, d, g;
            try {
                h = Double.parseDouble(hString.getText().toString());
            } catch (NumberFormatException e) {
                h = 0.0;
            }
            try {
                d = Double.parseDouble(ftString.getText().toString());
            } catch (NumberFormatException e) {
                d = 0;
            }
            g = (h * d / 250);
            calcLbl.setText(String.format("Gallons Required : %2f", g));
            color.setText("Colors Chosen : "+ c);
        });
    }
}