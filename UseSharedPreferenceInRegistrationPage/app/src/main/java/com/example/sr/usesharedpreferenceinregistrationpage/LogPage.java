package com.example.sr.usesharedpreferenceinregistrationpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LogPage extends AppCompatActivity {
    String s,p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_page);

        TextView username=(TextView)findViewById(R.id.u);
        TextView password=(TextView)findViewById(R.id.p);

        s=MainActivity.getUsername();
        p=MainActivity.getPassword();

        username.setText(s);
        password.setText(p);
    }
}
