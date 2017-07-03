package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.Entity.User;
import com.example.peek_mapdemotest.nurseapp.R;

public class UserHomeActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tel;
    private Button modify;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        final Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("User");
        tv1 = (TextView)findViewById(R.id.textView9);
        tel = (TextView)findViewById(R.id.textView_tel);
        modify = (Button)findViewById(R.id.modify);
        tv1.setText(user.getName());
        tel.setText(user.getId()+"");
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserHomeActivity.this,ModifyMessageActivity.class);
                intent1.putExtra("User",user);
                startActivity(intent1);
            }
        });

    }
}
