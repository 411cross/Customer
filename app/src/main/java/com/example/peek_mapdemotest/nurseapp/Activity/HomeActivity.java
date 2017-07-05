package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.Entity.User;
import com.example.peek_mapdemotest.nurseapp.Operation.UserOperation;
import com.example.peek_mapdemotest.nurseapp.R;

import static com.example.peek_mapdemotest.nurseapp.Operation.UserOperation.user;

public class HomeActivity extends AppCompatActivity {

    private TextView tv1;
    private ImageButton ib1;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button standardBtn;
    private Button seriousBtn;
    private Button b4;
    private Button NurseBt;
    private Button OrderBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tv1 = (TextView)findViewById(R.id.textView);
        ib1 = (ImageButton)findViewById(R.id.imageButton);
        b1 = (Button)findViewById(R.id.button3);
        b2 = (Button)findViewById(R.id.button4);
        b3 = (Button)findViewById(R.id.button5);
        standardBtn = (Button) findViewById(R.id.standard_btn);
        seriousBtn = (Button) findViewById(R.id.serious_btn);
        b4 = (Button)findViewById(R.id.button6);
        NurseBt = (Button) findViewById(R.id.NurseListBt);
        OrderBt = (Button) findViewById(R.id.CheckOrder);
        tv1.setText("用户名:"+ UserOperation.user.getName());
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserHomeActivity.class);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("area", 1);
                Intent intent = new Intent(HomeActivity.this, ByAreaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("area", 2);
                Intent intent = new Intent(HomeActivity.this, ByAreaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("area", 3);
                Intent intent = new Intent(HomeActivity.this, ByAreaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        standardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("area", 4);
                Intent intent = new Intent(HomeActivity.this, ByAreaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        seriousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("area", 5);
                Intent intent = new Intent(HomeActivity.this, ByAreaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, addPatientActivity.class);
                startActivity(intent);
            }
        });
        NurseBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NurseActivity.class);
                startActivity(intent);
            }
        });
        OrderBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CheckOrderActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
    protected void onRestart() {
        super.onRestart();
        tv1.setText("用户名:"+ UserOperation.user.getName());

    }

}
