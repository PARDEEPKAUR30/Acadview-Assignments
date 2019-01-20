package com.example.pari.lrpagewithsqlitedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText name,email,phone,pass;
    String string,s="",passw;
    SQLiteDatabase ud;
    Cursor cursor;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name=(EditText)findViewById(R.id.ln);
        email=(EditText)findViewById(R.id.lem);
        phone=(EditText)findViewById(R.id.lph);
        pass=(EditText)findViewById(R.id.lp);

        Intent i=getIntent();
        string =i.getExtras().getString("name");
         passw =i.getExtras().getString("password");

       /* name.setText(string);
        pass.setText(passw);*/
    data();}
 void data(){
        try {
            ud = this.openOrCreateDatabase("detail", MODE_PRIVATE, null);
            cursor = ud.rawQuery("select * from userdetails", null);

            cursor.moveToFirst();

           loop:while((cursor!=null)&&(cursor.getCount()!=0)){
               index=cursor.getColumnIndex("username");
               s=cursor.getString(index);
                if(s.equals(string)){
                   break loop;
                }
                else{
                    cursor.moveToNext();
                }
            }
            index = cursor.getColumnIndex("username");
            name.setText(cursor.getString(index));

            index=cursor.getColumnIndex("email");
            email.setText(cursor.getString(index));

            index=cursor.getColumnIndex("phone");
            phone.setText(cursor.getString(index));

            index=cursor.getColumnIndex("password");
            pass.setText(cursor.getString(index));

        }catch(Exception e){
           // Toast.makeText(getApplicationContext(), "Error in LogIn Activity ", Toast.LENGTH_LONG).show();
            e.getStackTrace();
            Log.e("logerror","exception in log Activity",e);
        }
    }
}
