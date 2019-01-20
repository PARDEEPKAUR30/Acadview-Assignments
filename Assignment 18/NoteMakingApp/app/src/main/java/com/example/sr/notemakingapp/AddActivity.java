package com.example.sr.notemakingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class AddActivity extends AppCompatActivity {
   static Button add;
   static EditText et;
   SQLiteDatabase data;
   String s;

    void addNote(String s) {
        try {
            data = this.openOrCreateDatabase("mydata", MODE_PRIVATE, null);
            data.execSQL("Create table if not exists notesdata(fullnote VARCAHR);");
            data.execSQL("insert into notesdata values('" +s+ "')");

           // Log.i("Note","Your note is added successfully");
            Toast.makeText(getApplicationContext(),"Notes added successfully",Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.getStackTrace();
            Toast.makeText(getApplicationContext(),"Error is occurred",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        add=(Button)findViewById(R.id.addbutton);
       et=(EditText)findViewById(R.id.etnote);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s=et.getText().toString();
                addNote(s);

                Toast.makeText(getApplicationContext(),"You added one new note",Toast.LENGTH_LONG).show();

               Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}
