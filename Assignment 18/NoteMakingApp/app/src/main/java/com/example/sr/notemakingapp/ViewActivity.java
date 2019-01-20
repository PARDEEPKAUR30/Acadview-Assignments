package com.example.sr.notemakingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    SQLiteDatabase data;
     ArrayList<String> notes;
    ArrayAdapter<String> ad;
    ListView lv;
    Cursor c;
    int index;

    void getNote(){
            notes=new ArrayList<String>();

        try{
            data = this.openOrCreateDatabase("mydata", MODE_PRIVATE, null);
            c=data.rawQuery("select * from notesdata",null);
            index=c.getColumnIndex("fullnote");

            c.moveToFirst();
            while(c!=null){
                //Log.i("New Note", c.getString(index));
                notes.add(c.getString(index));
                c.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
            //Toast.makeText(getApplicationContext(),"error find in view note",Toast.LENGTH_LONG).show();
            Log.d("error here","error in view notes",e);
        }finally {
            ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,notes);
            lv.setAdapter(ad);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

       lv=(ListView)findViewById(R.id.ntv);
       getNote();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String p=(String)lv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),p,Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),NoteActivity.class);
               i.putExtra("Your Note",p);
               // i.putExtra("Your Note",notes.get(position));
                startActivity(i);
            }
        });
    }
}
