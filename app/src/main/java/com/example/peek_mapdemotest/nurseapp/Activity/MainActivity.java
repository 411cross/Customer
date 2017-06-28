package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.peek_mapdemotest.nurseapp.R;

public class MainActivity extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private EditText et1;
    private EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        b1.setOnClickListener(new View.OnClickListener() {
            String account;
            String password;
            @Override
            public void onClick(View v) {
                account = et1.getText().toString();
                password = et2.getText().toString();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("account",account);
                startActivity(intent);
//                Toast.makeText(MainActivity.this, password, Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
