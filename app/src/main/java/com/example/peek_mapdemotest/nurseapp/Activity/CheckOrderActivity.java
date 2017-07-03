package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.peek_mapdemotest.nurseapp.R;

public class CheckOrderActivity extends AppCompatActivity {
    Button  button;
    String string;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main1);
        button = (Button)findViewById(R.id.button01);
        spinner=(Spinner)findViewById(R.id.spinner01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CheckOrderActivity.this,checkActivity.class);
              //  intent.setClass(MainActivity.this,checkActivity.class);
                string= spinner.getSelectedItem().toString();
                Bundle bundle = new Bundle();
                bundle.putString("status",string);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        }
    }


