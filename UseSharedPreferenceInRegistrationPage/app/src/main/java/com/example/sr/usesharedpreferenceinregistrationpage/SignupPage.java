package com.example.sr.usesharedpreferenceinregistrationpage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SignupPage extends AppCompatActivity {
    Button signb;
    SharedPreferences sp;

    public void setsp(){
   sp=this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        signb=(Button)findViewById(R.id.signbutton);

        signb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You are signned up successfully and can log in now",Toast.LENGTH_LONG).show();
                setsp();
                Intent i=new Intent (getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}
