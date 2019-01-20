package com.example.pari.lrpagewithsqlitedatabase;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    SQLiteDatabase ud;
    Button rbutton;
    EditText name,email,phone,password;
    String un,em,ph,pw,s;
    Boolean b=false;
    Cursor cursor;
    int index;
    Context context;

    void register(String uname){
        try {
            ud=this.openOrCreateDatabase("detail", MODE_PRIVATE,null);
            ud.execSQL("create table if not exists userdetails(username VARCHAR, email VARCHAR, phone VARCHAR, password VARCHAR);");

           cursor = ud.rawQuery("select * from userdetails",null);

            index = cursor.getColumnIndex("username");
            cursor.moveToFirst();
            b = false;
            outer: while((cursor!= null)&&(cursor.getCount()!=0)){
                String count=Integer.toString(cursor.getCount());
                //Toast.makeText(getApplicationContext(), count,Toast.LENGTH_LONG).show();
                        s = cursor.getString(index);
               // Toast.makeText(getApplicationContext(), s,Toast.LENGTH_LONG).show();
                        if (s.equals(uname)) {
                            Toast.makeText(context, "User already exists", Toast.LENGTH_LONG).show();
                            Intent i=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                            b = true;
                            break outer;
                        } else {
                            cursor.moveToNext();
                        }
                    }

        }catch (Exception e){
           // Toast.makeText(context, "Error in Registeration Activity", Toast.LENGTH_LONG).show();
            Log.e("myapp","exception",e);
            e.getStackTrace();}
    finally{
        if (b==false) {
            ud.execSQL("insert into userdetails values('"+un+"','"+em+"','"+ph+"','"+pw+"');");

            Toast.makeText(context, "You Registered Successfully", Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, MainActivity.class);
            startActivity(i);
        }}
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        context=getApplicationContext();

        name=(EditText)findViewById(R.id.rn);
        email=(EditText)findViewById(R.id.rem);
        phone=(EditText)findViewById(R.id.rph);
        password=(EditText)findViewById(R.id.rp);
        rbutton=(Button)findViewById(R.id.rrb);

        rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                un = name.getText().toString();
                em = email.getText().toString();
                ph = phone.getText().toString();
                pw = password.getText().toString();
                Toast.makeText(context, "You clicked on Register button ", Toast.LENGTH_SHORT).show();
                 register(un);
            }
                });
    }
}
