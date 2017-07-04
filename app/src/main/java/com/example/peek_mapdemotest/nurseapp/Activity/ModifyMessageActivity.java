package com.example.peek_mapdemotest.nurseapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.Operation.UserOperation;
import com.example.peek_mapdemotest.nurseapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ModifyMessageActivity extends AppCompatActivity {
    private TextView name;
    private TextView password;
    private Button ensure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_message);
        name = (EditText)findViewById(R.id.editText_name);
        password = (EditText)findViewById(R.id.editText_password);
        name.setText(UserOperation.user.getName());

        ensure = (Button)findViewById(R.id.ensure) ;

        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = name.getText().toString();
                String pass = password.getText().toString();
                try {
                    Toast.makeText(ModifyMessageActivity.this,UserOperation.user.getName() ,Toast.LENGTH_SHORT).show();
                    ArrayList resp = UserOperation.modifyUser(pass,nameStr,"avatar");
                    if(Integer.parseInt((String) resp.get(0))==200){
                        Toast.makeText(ModifyMessageActivity.this,UserOperation.user.getName() ,Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        String data = (String) resp.get(1);
                        JSONObject object = new JSONObject(data);
                        String respJsonObject = object.getString("message");
                        Toast.makeText(ModifyMessageActivity.this,respJsonObject ,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(ModifyMessageActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

}
