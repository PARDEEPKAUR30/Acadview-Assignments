package com.example.sr.notemakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        EditText et=(EditText)findViewById(R.id.etn);
        String n;

            Intent i=getIntent();
            n=i.getExtras().getString("Your Note");
            et.setText(n);
        }
    }

