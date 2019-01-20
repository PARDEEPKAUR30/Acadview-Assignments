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

public class MainActivity extends AppCompatActivity {

    EditText uname,password;
    Button lb,rb,rsb;

    SQLiteDatabase ud;
    Context cxt;
    String s,name,pass;
    boolean b=false;
    int index;
    Cursor cr;

    public void logIn(String username, String password){
        getDatabase(username);
       if(b==true){
            Toast.makeText(cxt, "Account is exist", Toast.LENGTH_LONG).show();
          Intent i=new Intent(cxt, LoginActivity.class);
            i.putExtra("name",username);
            i.putExtra("password",password);
           startActivity(i);
        }
        else{
            Toast.makeText(cxt,"You should Registered first",Toast.LENGTH_LONG).show();
            Intent i=new Intent(cxt, RegistrationActivity.class);
            startActivity(i);
        }
    }

    public void registerAccount(String username, String password){
        getDatabase(username);
        if(username.equals(s)){
            Toast.makeText(cxt, "User Already exist", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(cxt,"You can Registered here",Toast.LENGTH_LONG).show();
            Intent i=new Intent(cxt, RegistrationActivity.class);
            startActivity(i);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cxt=getApplicationContext();

        lb=(Button)findViewById(R.id.log);
        rb=(Button)findViewById(R.id.reg);
        rsb=(Button)findViewById(R.id.reset);

        uname=(EditText)findViewById(R.id.n);
        password=(EditText)findViewById(R.id.p);


        lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=uname.getText().toString();
                pass=password.getText().toString();
                //Toast.makeText(cxt,"Login button is clicked",Toast.LENGTH_SHORT).show();
                logIn(name,pass);
            }
        });

        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=uname.getText().toString();
                pass=password.getText().toString();
                Toast.makeText(cxt,"You can Register here",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(cxt,RegistrationActivity.class);
                startActivity(i);
              // registerAccount(name,pass);
            }
        });

        rsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=uname.getText().toString();
                pass=password.getText().toString();
                Toast.makeText(cxt, "Page is Reset", Toast.LENGTH_LONG).show();
                Intent i=new Intent(cxt, MainActivity.class);
                startActivity(i);

            }
        });

    }
    void getDatabase(String name){
        try{
            //name=uname.getText().toString();
            ud=this.openOrCreateDatabase("detail",MODE_PRIVATE,null);
           cr =ud.rawQuery("select * from userdetails",null);
                index=cr.getColumnIndex("username");
               // if((cr.isNull(index))==false){
            String count=Integer.toString(cr.getCount());
               cr.moveToFirst();
            outer: while((cr!= null)&&(cr.getCount()!=0)){
                   b=false;
                  // Toast.makeText(getApplicationContext(),count,Toast.LENGTH_LONG).show();
                s=cr.getString(index);
               if(s.equals(name)){
                   // name=s;
                    b=true;
                    break outer;
                }
                else{
                    cr.moveToNext();
                }
            }

        }catch (Exception e){
            Toast.makeText(cxt, "Error in Main Activity", Toast.LENGTH_LONG).show();
            e.getStackTrace();
            Log.e("mainerror","exception in main Activity",e);
        }
    }
}
