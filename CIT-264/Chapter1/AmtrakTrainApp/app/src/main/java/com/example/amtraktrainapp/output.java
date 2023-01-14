package com.example.amtraktrainapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.Format;
import java.time.LocalTime;

public class output extends AppCompatActivity {
    TextView tripArrival, redEye;
    ImageView redEyePic;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);


        tripArrival = findViewById(R.id.tripArrivaltxt);
        redEye = findViewById(R.id.redEye);
        redEyePic = findViewById(R.id.redEyePic);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);


        int hours = sharedPref.getInt("key1", 0);
        int min = sharedPref.getInt("key2",0);
        int tripInMin = sharedPref.getInt("key3",0);


        DecimalFormat decimalFormat = new DecimalFormat(".##");

       double total;
        int totalTrip;




        LocalTime time = LocalTime.of(hours, min);
        LocalTime newTime = time.plusMinutes(tripInMin);


       tripArrival.setText("Your Arrival Time is "+ newTime.toString());

       int x = newTime.getHour();

       if(x >= 12){
           redEye.setText("This is a Red-Eye Arrival");
           redEyePic.setImageResource(R.drawable.redeye);

       }









    }

}