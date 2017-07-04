package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.Operation.UserOperation;
import com.example.peek_mapdemotest.nurseapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {

    private Button b1;
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        b1 = (Button)findViewById(R.id.button);
        et1 = (EditText)findViewById(R.id.editText3);
        et2 = (EditText)findViewById(R.id.editText4);
        et3 = (EditText)findViewById(R.id.editText5);
        et4 = (EditText)findViewById(R.id.editText6);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = et1.getText().toString();
                String password = et2.getText().toString();
                String password2 = et3.getText().toString();
                String name = et4.getText().toString();
                if(password.isEmpty()||password2.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else if (name.isEmpty())   Toast.makeText(RegisterActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                else if (ID.isEmpty()) Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                else if (password.equals(password2)){
                    try {
                        ArrayList resp =  UserOperation.register(ID,password,name);
                        if(Integer.parseInt((String) resp.get(0))==200){
                            String data = (String) resp.get(1);
                            JSONObject object = new JSONObject(data);
                            String respJsonObject = object.getString("message");
                            Toast.makeText(RegisterActivity.this,respJsonObject ,Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            String data = (String) resp.get(1);
                            JSONObject object = new JSONObject(data);
                            String respJsonObject = object.getString("message");
                            Toast.makeText(RegisterActivity.this,respJsonObject ,Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                    else{
                        Toast.makeText(RegisterActivity.this, "密码不匹配！", Toast.LENGTH_SHORT).show();
                    }
                }

        });
    }
}

