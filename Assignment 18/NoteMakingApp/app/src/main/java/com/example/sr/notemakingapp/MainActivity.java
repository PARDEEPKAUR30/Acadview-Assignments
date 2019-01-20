package com.example.sr.notemakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button add,view;
    Context cxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cxt=getApplicationContext();

        add=(Button)findViewById(R.id.an);
        view=(Button)findViewById(R.id.vn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cxt,"Add More Notes",Toast.LENGTH_LONG).show();
                Intent i=new Intent(cxt,AddActivity.class);
                startActivity(i);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cxt,"View Notes",Toast.LENGTH_LONG).show();
                Intent i=new Intent(cxt,ViewActivity.class);
                startActivity(i);
            }
        });
    }
}
