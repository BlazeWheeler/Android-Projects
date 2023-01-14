package com.example.myapplication;


 import androidx.appcompat.app.AppCompatActivity;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.ArrayAdapter;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.Spinner;
 import android.widget.TextView;
 public class BillActivity extends AppCompatActivity { EditText e1,e2; Button b1; TextView tv1,tv2;
 @Override protected void onCreate(Bundle savedInstanceState)
 { super.onCreate(savedInstanceState);
 setContentView(R.layout.billactivity);
 Spinner spinner = (Spinner) findViewById(R.id.spinner);

 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this, R.array.quality, android.R.layout.simple_spinner_item);

 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 spinner.setAdapter(adapter);
 e1=findViewById(R.id.bill);

 e2=findViewById(R.id.people);
 b1=findViewById(R.id.splitbill);
 tv1=findViewById(R.id.result);
 tv2=findViewById(R.id.tip);
 b1.setOnClickListener(new View.OnClickListener()
 {
  @Override
 public void onClick(View v)
  {
  int a = Integer.parseInt(e1.getText().toString());
  int b = Integer.parseInt(e2.getText().toString());
  //here calculating the tip amount
  float tip = (float) (0.18 * a);
  float share = (float) ((a + tip) / b);
  String s = share + "";
  //here displaying the tip amount
  tv2.setText("Tip : " + tip);
  tv1.setText("Individual share : " + share);
       }
    });
   }
 }