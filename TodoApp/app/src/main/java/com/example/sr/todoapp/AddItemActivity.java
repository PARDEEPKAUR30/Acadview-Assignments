package com.example.sr.todoapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    SQLiteDatabase database;
    EditText n,d;
    Button s,r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        n=(EditText)findViewById(R.id.name);
        d=(EditText)findViewById(R.id.desc);
        s=(Button)findViewById(R.id.save);
        r=(Button)findViewById(R.id.reset);

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String name=n.getText().toString();
                String desc=d.getText().toString();

               String data= name+": "+desc;
                saveData(data);
                Toast.makeText(getApplicationContext(),"Item is saved",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Page is Reset",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),AddItemActivity.class);
                startActivity(i);
            }
        });
    }

    void saveData(String data){
        try{
            database=this.openOrCreateDatabase("tododata",MODE_PRIVATE,null);

            database.execSQL("create table if not exists notedata(ndata VARCHAR);");
            database.execSQL("insert into notedata values('"+data+"')");
        }catch(Exception e){
            e.printStackTrace();
            Log.e("insert","error insert",e);

        }
    }

}
