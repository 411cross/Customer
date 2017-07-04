package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.Entity.User;
import com.example.peek_mapdemotest.nurseapp.R;

public class ModifyMessageActivity extends AppCompatActivity {
    private TextView name;
    private TextView password;
    private Button ensure;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_message);

        final Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("User");
        name = (EditText)findViewById(R.id.editText_name);
        password = (EditText)findViewById(R.id.editText_password);
        name.setText(user.getName());

        ensure = (Button)findViewById(R.id.ensure) ;

        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setName(name.getText().toString());
                if(password.getText().toString().isEmpty());
                else
                user.setPassword(password.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("User",user);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

}
