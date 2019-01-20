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
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.TooManyListenersException;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    Cursor c;
    int nameindex;
    int descindex;
    ListView listview;
    ArrayList<String> arraylist;
    ArrayAdapter<String> adapter;
   Button button,complete,clear;

    void showData(){
        arraylist=new ArrayList<String>();

        try{
            database=this.openOrCreateDatabase("tododata",MODE_PRIVATE,null);
            c=database.rawQuery("select * from notedata",null );
            int index=c.getColumnIndex("ndata");
            c.moveToFirst();
            while(c!=null){
              arraylist.add(c.getString(index));
                c.moveToNext();
            }
        }
        catch(Exception  e){
            e.printStackTrace();
        }finally {
            addAdapter();
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview=(ListView)findViewById(R.id.lv);
        button=(Button)findViewById(R.id.b);
        complete=(Button)findViewById(R.id.cb);
        clear=(Button)findViewById(R.id.eb);

        showData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You can add new item",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),AddItemActivity.class);
                startActivity(i);
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You can view completed items",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),TodoItem.class);
                i.putExtra("Item","");
                startActivity(i);
            }
        });

       clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
                //Toast.makeText(getApplicationContext(),"You clear the item list",Toast.LENGTH_SHORT).show();
            }
        });

     listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item=(String)listview.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"You removed to "+item,Toast.LENGTH_SHORT).show();

             try{
            String query="delete from notedata where ndata='"+item+"'";

                 database.execSQL(query);}
                 catch(Exception e){
                // Toast.makeText(getApplicationContext(),"error in delete query",Toast.LENGTH_LONG).show();
                     Log.e("errorfind","delete",e);
             }
                adapter.remove(item);
             adapter.notifyDataSetChanged();

            }
        });



        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String item=(String)listview.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"You complete the item: "+item,Toast.LENGTH_SHORT).show();

                try{
                    String query="delete from notedata where ndata='"+item+"'";
                    database.execSQL(query);}
                catch(Exception e){
                    // Toast.makeText(getApplicationContext(),"error in delete query",Toast.LENGTH_LONG).show();
                    Log.e("errorfind2","delete",e);
                }
                adapter.remove(item);
                adapter.notifyDataSetChanged();

                Intent i=new Intent(getApplicationContext(),TodoItem.class);
                i.putExtra("Item",item);
                startActivity(i);
                return false;
            }
        });
    }

   void addAdapter(){
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arraylist);
        listview.setAdapter(adapter);

    }

    void clearData(){
        try{
            String s="drop table if exists notedata";
            database=this.openOrCreateDatabase("tododata",MODE_PRIVATE,null);
            database.execSQL(s);
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }catch(Exception e){
            e.getStackTrace();
            Log.e("clear","errorhere",e);
            //Toast.makeText(getApplicationContext(),"Error in clearing data",Toast.LENGTH_LONG).show();
        }
    }



}
