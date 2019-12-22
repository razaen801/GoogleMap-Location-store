package com.rastha.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowDetails extends AppCompatActivity {
TextView t1,t2,t3,t4,t5;
Button openmaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        t1=findViewById(R.id.fname);
        t2=findViewById(R.id.lname);
        t3=findViewById(R.id.addr);
        t4=findViewById(R.id.phone);
        t5=findViewById(R.id.coordinates);

        Intent i=getIntent();
        final String fname1= i.getStringExtra("firstname");
        String lname= i.getStringExtra("lastname");
        String addr= i.getStringExtra("address");
        String phone= i.getStringExtra("phone");
        final String lat1 = i.getStringExtra("latitude");

        final String lng1 = i.getStringExtra("longitude");

        t1.setText(fname1);
        t2.setText(lname);
        t3.setText(addr);
        t4.setText(phone);
        t5.setText("Your coordinates are: Latitude "+ lat1 +" and Longitude "+lng1);

        openmaps=findViewById(R.id.open_my);
        openmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i = new Intent(ShowDetails.this, ShowMap.class);
            i.putExtra("latitude",lat1);
            i.putExtra("longitude",lng1);
            i.putExtra("name",fname1);
            startActivity(i);
            }
        });



    }

}
