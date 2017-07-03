package com.example.peek_mapdemotest.nurseapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.R;

public class ModifyMessageActivity extends AppCompatActivity {
    private TextView name;
    private TextView tel;
    private Button ensure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_message);

        ensure = (Button)findViewById(R.id.ensure) ;

        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ModifyMessageActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

}
