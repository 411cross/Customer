package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.Entity.User;
import com.example.peek_mapdemotest.nurseapp.Operation.UserOperation;
import com.example.peek_mapdemotest.nurseapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private EditText et1;
    private EditText et2;
    private User user;
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
                try {
                    ArrayList resp = UserOperation.UserLogin(account,password);

                    if(Integer.parseInt((String) resp.get(0))==200){
                        Intent intent = new Intent(MainActivity.this, NewHomeActivity.class);
                        intent.putExtra("User",UserOperation.user);
                        startActivity(intent);
                    }else{
                        String data = (String) resp.get(1);
                        JSONObject object = new JSONObject(data);
                        String respJsonObject = object.getString("message");
                        Toast.makeText(MainActivity.this,respJsonObject ,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

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
