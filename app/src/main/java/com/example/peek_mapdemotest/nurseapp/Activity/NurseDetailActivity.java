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
    private TextView WorkAgeTv;
    private TextView HeightTv;
    private TextView WeightTv;
    private TextView BloodTv;
    private TextView NationTv;
    private TextView IdentityTv;
    private TextView ConstellationTv;
    private TextView AnimalTv;
    private TextView DescriptionTv;
    private TextView PhoneTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_detail);
        NameTv = (TextView) findViewById(R.id.NurseNameI);
        AgeTv = (TextView) findViewById(R.id.NurseAgeD);
        SexTv = (TextView) findViewById(R.id.NurseSexD);
        AreaTv = (TextView) findViewById(R.id.NurseAreaD);
        PriceTv = (TextView) findViewById(R.id.NursePriceD);
        EvaluateTv = (TextView) findViewById(R.id.NurseEvaluateD);
        WorkAgeTv = (TextView) findViewById(R.id.Nursework);
        HeightTv = (TextView) findViewById(R.id.Nurseheight);
        WeightTv = (TextView) findViewById(R.id.NurseWeight);
        BloodTv = (TextView) findViewById(R.id.NurseBlood);
        NationTv = (TextView) findViewById(R.id.NurseNation);
        IdentityTv = (TextView) findViewById(R.id.NurseIDentity);
        ConstellationTv = (TextView) findViewById(R.id.NurseStar);
        AnimalTv = (TextView) findViewById(R.id.NurseAnimal);
        DescriptionTv = (TextView) findViewById(R.id.NurseDescription);
        PhoneTv = (TextView) findViewById(R.id.Nursephone);

        final Bundle bundle = this.getIntent().getExtras();
        NameTv.setText(bundle.getString("Nurse_name"));
        AgeTv.setText(bundle.getInt("Nurse_age") + "岁");
        SexTv.setText(bundle.getInt("Nurse_sex") + "");
        AreaTv.setText(bundle.getString("Nurse_Area"));
        PriceTv.setText(bundle.getInt("Nurse_price") + "元/天");
        EvaluateTv.setText("好评率：" + bundle.getInt("Nurse_evaluate"));
        WorkAgeTv.setText(bundle.getInt("Nurse_work_age")+"年");
        HeightTv.setText(bundle.getInt("Nurse_height")+"");
        WeightTv.setText(bundle.getInt("Nurse_weight")+"");
        BloodTv.setText(bundle.getString("Nurse_blood")+"型");
        NationTv.setText(bundle.getString("Nurse_nation"));
        IdentityTv.setText(bundle.getString("Nurse_identity"));
        ConstellationTv.setText(bundle.getString("Nurse_Constellation"));
        AnimalTv.setText(bundle.getString("Nurse_Animal"));
        DescriptionTv.setText(bundle.getString("Nurse_Description"));
        PhoneTv.setText(bundle.getString("Nurse_Area"));
        AppointmentButton = (Button) findViewById(R.id.appointmentbutton);
        AppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NurseDetailActivity.this, AppointmentActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

}
