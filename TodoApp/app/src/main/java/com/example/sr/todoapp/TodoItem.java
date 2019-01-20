package com.example.sr.todoapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoItem extends AppCompatActivity {
    ListView lv;
    ArrayList<String> al;
    ArrayAdapter<String> ad;
    SQLiteDatabase sd;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_item);

        back = (Button) findViewById(R.id.bb);
        lv = (ListView) findViewById(R.id.lv2);
        completeItems();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "BACK button is clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = ad.getItem(position);
                Toast.makeText(getApplicationContext(), "You clicked on " + s, Toast.LENGTH_LONG).show();
                ad.remove(s);
                deleteItem(s);
                ad.notifyDataSetChanged();

            }
        });


    }

    void completeItems() {
        al = new ArrayList<String>();
        boolean b = true;
        try {
            sd = this.openOrCreateDatabase("completeitems", MODE_PRIVATE, null);
            sd.execSQL("create table if not exists compitems(item VARCHAR);");

            String s = getData();
            if (s.equals("")) {
                b = false;
            }
            if (b==true) {
                sd.execSQL("insert into compitems values('"+s+"')");
                }

            addItem();
        } catch (Exception e) {
            e.getStackTrace();
          //  Toast.makeText(getApplicationContext(), "Error get in TodoItem class", Toast.LENGTH_SHORT).show();
            Log.e("error1", "error is here",e);
        } finally {
            ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
            lv.setAdapter(ad);
        }
    }

    String getData() {
        Intent i = getIntent();
        String s = i.getExtras().getString("Item");
        return s;
    }

    void addItem() {
        sd=this.openOrCreateDatabase("completeitems",MODE_PRIVATE,null);
        Cursor c = sd.rawQuery("select * from compitems", null);
        int index=c.getColumnIndex("item");
        c.moveToFirst();
        while ((c!=null)&&(c.getCount()!=0)) {
            al.add(c.getString(index));
            c.moveToNext();
        }
    }

    void deleteItem(String item) {
        try {
            sd = this.openOrCreateDatabase("completeitems", MODE_PRIVATE, null);
            String string="delete from compitems where item='"+item+"'";
            sd.execSQL(string);
        } catch (Exception e) {
            e.getStackTrace();
           // Toast.makeText(getApplicationContext(), "2Error get in TodoItem class", Toast.LENGTH_SHORT).show();
            Log.e("error2", "error2 is here",e);
        }
    }
}