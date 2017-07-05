package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.peek_mapdemotest.nurseapp.R;

public class ByAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_area);

        Bundle bundle = getIntent().getExtras();
        int area = bundle.getInt("area");



    }



}
