package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.R;

public class NurseDetailActivity extends AppCompatActivity {
    private Button AppointmentButton;

    private TextView NameTv;
    private TextView AgeTv;
    private TextView SexTv;
    private TextView AreaTv;
    private TextView PriceTv;
    private TextView EvaluateTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_detail);
        NameTv= (TextView) findViewById(R.id.NurseNameD);
        AgeTv = (TextView) findViewById(R.id.NurseAgeD);
        SexTv= (TextView) findViewById(R.id.NurseSexD);
        AreaTv= (TextView) findViewById(R.id.NurseAreaD);
        PriceTv= (TextView) findViewById(R.id.NursePriceD);
        EvaluateTv= (TextView) findViewById(R.id.NurseEvaluateD);
        final Bundle bundle = this.getIntent().getExtras();
        NameTv.setText(bundle.getString("Nurse_name"));
        AgeTv.setText(bundle.getInt("Nurse_age")+"岁");
        SexTv.setText(bundle.getString("Nurse_sex"));
        AreaTv.setText(bundle.getString("Nurse_Area"));
        PriceTv.setText(bundle.getInt("Nurse_price")+"元/每天");
        EvaluateTv.setText("好评率："+bundle.getInt("Nurse_evaluate"));

        AppointmentButton = (Button) findViewById(R.id.appointmentbutton);
        AppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NurseDetailActivity.this,AppointmentActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

}
