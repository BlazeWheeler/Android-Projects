package com.example.piggybank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import java.text.NumberFormat;
import java.util.Currency;


public class two extends AppCompatActivity { EditText e1,e2,e3,e4; Button b1; TextView tv1,tv2;
String c = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.savingorspending);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spendingSaving, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner.setAdapter(adapter);

         e1 = findViewById(R.id.txtQuarter);
         e2 = findViewById(R.id.txtDimes);
         e3 = findViewById(R.id.txtNickles);
         e4 = findViewById(R.id.txtPennies);

         b1 = findViewById(R.id.calculateBtn);
         tv1 = findViewById(R.id.result);
         b1.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v) {
                 double q, d, n, p, total;
                 final double QUARTER, DIME, NICKLE, PENNIE;
                 QUARTER = 0.25;
                 DIME = 0.10;
                 NICKLE = 0.05;
                 PENNIE = 0.01;
                 try {
                     q = Double.parseDouble(e1.getText().toString());
                     d = Double.parseDouble(e2.getText().toString());
                     n = Double.parseDouble(e3.getText().toString());
                     p = Double.parseDouble(e4.getText().toString());

                     q = q * QUARTER;
                     d = d * DIME;
                     n = n * NICKLE;
                     p = p * PENNIE;

                 } catch (NumberFormatException e) {
                     q = 0.00;
                     d = 0.00;
                     n = 0.00;
                     p = 0.00;
                 }
                 total = q + d + n + p;
                 NumberFormat format = NumberFormat.getCurrencyInstance();
                 format.setMaximumFractionDigits(0);
                 format.setCurrency(Currency.getInstance("USD"));
                 format.format(total);
                 if(adapter == adapter.getItem(0)){

                     tv1.setText("Total Spending "  +  format.format(total));
                 }else {
                     tv1.setText("Total Saving "  +  format.format(total));
                 }
             }
         });

        }
    }