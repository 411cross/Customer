package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.Entity.User;
import com.example.peek_mapdemotest.nurseapp.R;

public class HomeActivity extends AppCompatActivity {

    private TextView tv1;
    private ImageButton ib1;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button NurseBt;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tv1 = (TextView)findViewById(R.id.textView);
        ib1 = (ImageButton)findViewById(R.id.imageButton);
        b1 = (Button)findViewById(R.id.button3);
        b2 = (Button)findViewById(R.id.button4);
        b3 = (Button)findViewById(R.id.button5);
        b4 = (Button)findViewById(R.id.button6);
        NurseBt = (Button) findViewById(R.id.NurseListBt);

        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("User");
        tv1.setText("用户名:"+user.getName());
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserHomeActivity.class);
                intent.putExtra("User",user);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, InternalMedicineActivity.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ChirurgeryActivity.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, GynaecologyActivity.class);
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
    }
}
