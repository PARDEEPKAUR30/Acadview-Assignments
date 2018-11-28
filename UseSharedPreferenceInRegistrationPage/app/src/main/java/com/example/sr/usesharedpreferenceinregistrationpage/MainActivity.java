package com.example.sr.usesharedpreferenceinregistrationpage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   static SharedPreferences sp;
   static Context cxt;
    static String user;
    static String pass;

   public  void signup(String username, String password){
       String result=sp.getString(username,"");

       if(result.equals("")){
           Toast.makeText(cxt,"You should signup firstly",Toast.LENGTH_LONG).show();
            sp.edit().putString(username,password).apply();
           Intent i=new Intent(cxt,SignupPage.class);
            startActivity(i);
       }
       else{
           Toast.makeText(cxt,"User already exist",Toast.LENGTH_LONG).show();

       }

   }

   public void login(String username, String password){

       String result=sp.getString(username,"");

       if(result.equals("")){
           Toast.makeText(cxt,"Please sign up firstly",Toast.LENGTH_LONG).show();
           sp.edit().putString(username,password).apply();
           Intent i=new Intent(cxt,SignupPage.class);
           startActivity(i);
       }
       else{
           Toast.makeText(cxt,"You logged in",Toast.LENGTH_LONG).show();
           Intent i=new Intent(cxt,LogPage.class);
           startActivity(i);
       }
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cxt=getApplicationContext();

        Button sign= (Button)findViewById(R.id.signb);
        Button log=(Button)findViewById(R.id.logb);

        final EditText usernametext=(EditText)findViewById(R.id.user);
        final EditText passwordtext=(EditText)findViewById(R.id.pass);

        sp= this.getSharedPreferences(" com.example.sr.usesharedpreferenceinregistrationpage", Context.MODE_PRIVATE);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"sign up clicked", Toast.LENGTH_LONG).show();

               String username=usernametext.getText().toString();
               String password=passwordtext.getText().toString();

                signup(username, password);
            }
        });

             log.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(getApplicationContext(),"log in  clicked", Toast.LENGTH_LONG).show();

                    String username=usernametext.getText().toString();
                    user=username;
                     String password=passwordtext.getText().toString();
                     pass=password;

                     login(username, password);
                 }
             });
    }

    public static String getUsername(){
        return user;
    }
    public static String getPassword(){
        String p=sp.getString(user,"No data found");
        return p;
    }
}
