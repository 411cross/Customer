package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.R;

public class UserHomeActivity extends AppCompatActivity {

    private TextView tv1;
    private Button modify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        final Intent intent = getIntent();
        final String account = intent.getStringExtra("account").toString();
        tv1 = (TextView)findViewById(R.id.textView9);
        modify = (Button)findViewById(R.id.modify);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserHomeActivity.this,ModifyMessageActivity.class);
                startActivity(intent1);
            }
        });
        tv1.setText(account);
    }
}
