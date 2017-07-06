package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.Operation.UserOperation;
import com.example.peek_mapdemotest.nurseapp.R;

public class UserHomeActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tel;
    private Button modify;
    private ImageView imgv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        tv1 = (TextView)findViewById(R.id.textView9);
        tel = (TextView)findViewById(R.id.textView_tel);
        modify = (Button)findViewById(R.id.modify);
        tv1.setText(UserOperation.user.getName());
        tel.setText(UserOperation.user.getId()+"");
        imgv= (ImageView) findViewById(R.id.imageButton2);
        if(UserOperation.bitmap!=null){
            imgv.setImageBitmap(UserOperation.bitmap);
        }

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserHomeActivity.this,ModifyMessageActivity.class);
                startActivity(intent1);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        tv1.setText(UserOperation.user.getName());
        tel.setText(UserOperation.user.getId());
        if(UserOperation.bitmap!=null){
            imgv.setImageBitmap(UserOperation.bitmap);
        }

    }
}
