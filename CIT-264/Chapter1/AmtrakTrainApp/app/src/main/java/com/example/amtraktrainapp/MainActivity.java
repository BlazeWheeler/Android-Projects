package com.example.amtraktrainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText timeHours, timeMinuets, tripInMin;
    Button calcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcBtn = findViewById(R.id.calcBtn);
        timeHours = findViewById(R.id.hoursTxt);
        timeMinuets = findViewById(R.id.minuetsTxt);
        tripInMin = findViewById(R.id.tripInMinuets);

        SharedPreferences sharePref = PreferenceManager.getDefaultSharedPreferences(this);


        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intTimeHours = Integer.parseInt(timeHours.getText().toString());
                int intTimeMinuets = Integer.parseInt(timeMinuets.getText().toString());
                int intTripInMin = Integer.parseInt(tripInMin.getText().toString());

                SharedPreferences.Editor editor = sharePref.edit();
                editor.putInt("key1", intTimeHours);
                editor.putInt("key2", intTimeMinuets);
                editor.putInt("key3", intTripInMin);
                editor.commit();

                startActivity(new Intent(MainActivity.this, output.class));


            }
        });



    }
}