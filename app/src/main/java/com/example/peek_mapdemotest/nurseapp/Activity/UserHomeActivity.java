package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.R;

public class UserHomeActivity extends AppCompatActivity {

    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        Intent intent = getIntent();
        final String account = intent.getStringExtra("account").toString();
        tv1 = (TextView)findViewById(R.id.textView9);
        tv1.setText(account);
    }
}
