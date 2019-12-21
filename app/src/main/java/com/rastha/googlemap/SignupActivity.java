package com.rastha.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity{
Button open_maps,submit;
EditText fname;
    EditText lname;
    EditText addr;
    EditText phone;
    EditText uname;
    EditText pass;
    EditText latshow;
    EditText lngshow;
double lng = 0.0;
    double lat=0.0;
private DBManager dbManager;
int phone1 = 0;

  /*  public SignupActivity(double lng, double lat) {
        lng=this.lng;
        lat=this.lat;
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        open_maps= findViewById(R.id.login_open_maps);
        submit= findViewById(R.id.login_submit);
        fname = findViewById(R.id.login_fname);
        lname = findViewById(R.id.login_lname);
        addr = findViewById(R.id.login_address);
        phone = findViewById(R.id.login_phone);
        uname = findViewById(R.id.login_usernm);
        pass = findViewById(R.id.login_pass);
        latshow = findViewById(R.id.latitude_show);
        lngshow = findViewById(R.id.longitude_show);
        dbManager = new DBManager(this);
        dbManager.open();
        open_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this,MapsActivity.class));
            }
        });

        Intent i = getIntent();
        lat=i.getDoubleExtra("Latitude",0.0);
        lng=i.getDoubleExtra("Longitude",0.0);
        latshow.setText(Double.toString(lat));
        lngshow.setText(Double.toString(lng));


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = fname.getText().toString();
                String last_name = lname.getText().toString();
                String address = addr.getText().toString();
                String username = uname.getText().toString();
                String password = pass.getText().toString();
                String phnone = phone.getText().toString();
                phone1 = Integer.parseInt(phnone);
                dbManager.insert(first_name, last_name, address, username, password, phone1, lat, lng);
                Toast.makeText(getApplicationContext(),"Added Successfully", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

}
