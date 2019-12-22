package com.rastha.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
   Button b1;
   TextView t1;
   DBManager dbUser;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.login_click);
        final EditText txtUserName = (EditText)findViewById(R.id.txtUsername);
        final EditText txtPassword = (EditText)findViewById(R.id.txtPassword);
        dbUser = new DBManager(this);
        dbUser.open();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this,LoginActivity  .class);
                //startActivity(intent);
                String username = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();

                try{
                    if(username.length() > 0 && password.length() >0)
                    {


                        if(dbUser.Login(username, password))
                        {
                            Toast.makeText(MainActivity.this,"Successfully Logged In", Toast.LENGTH_LONG).show();
                            Intent intent= new Intent(MainActivity.this,ShowDetails.class);
                             Cursor cursor = dbUser.fetch_data(username,password);
                             String fname = cursor.getString(cursor.getColumnIndex(DBHelper.F_NAME));
                             intent.putExtra("firstname",fname);
                            String lname = cursor.getString(cursor.getColumnIndex(DBHelper.L_NAME));
                            intent.putExtra("lastname",lname);
                            String addr = cursor.getString(cursor.getColumnIndex(DBHelper.ADDRESS));
                            intent.putExtra("address",addr);
                            String phone = cursor.getString(cursor.getColumnIndex(DBHelper.PHONE));
                            intent.putExtra("phone",phone);
                            String lat = cursor.getString(cursor.getColumnIndex(DBHelper.LATITUDE));
                            intent.putExtra("latitude",lat);
                            String lng = cursor.getString(cursor.getColumnIndex(DBHelper.LONGITUDE));
                            intent.putExtra("longitude",lng);
                            cursor.close();
                            startActivity(intent);
                        }
                       else{
                            Toast.makeText(MainActivity.this,"Invalid Username/Password", Toast.LENGTH_LONG).show();
                        }

                    }

                }catch(Exception e)
                {
                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        t1=findViewById(R.id.signup_form);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignupActivity.class));

            }
        });

    }
}
