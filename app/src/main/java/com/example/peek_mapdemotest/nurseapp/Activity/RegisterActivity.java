package com.example.peek_mapdemotest.nurseapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.R;

public class RegisterActivity extends AppCompatActivity {

    private Button b1;
    private EditText et1;
    private EditText et2;
    private EditText et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        b1 = (Button)findViewById(R.id.button);
        et1 = (EditText)findViewById(R.id.editText3);
        et2 = (EditText)findViewById(R.id.editText4);
        et3 = (EditText)findViewById(R.id.editText5);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = et2.getText().toString();
                String password2 = et3.getText().toString();
                if(password.isEmpty()||password2.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(password2)){
                        Toast.makeText(RegisterActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "密码不匹配！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
