package com.example.peek_mapdemotest.nurseapp.Activity;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AppointmentActivity extends AppCompatActivity {

    private TextView NameTv;
    private TextView AgeTv;
    private TextView SexTv;
    private TextView AreaTv;
    private TextView PriceTv;
    private TextView EvaluateTv;

    private TextView ChooseDate;

    private Button MinusBt;
    private Button AddBt;
    private EditText ET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        NameTv= (TextView) findViewById(R.id.AppointmentNurseName);
        AgeTv = (TextView) findViewById(R.id.AppointmentNurseAge);
        SexTv= (TextView) findViewById(R.id.AppointmentNurseSex);
        AreaTv= (TextView) findViewById(R.id.AppointmentNurseArea);
        PriceTv= (TextView) findViewById(R.id.AppointmentNursePrice);
        EvaluateTv= (TextView) findViewById(R.id.AppointmentNurseEvaluate);

        MinusBt = (Button) findViewById(R.id.AppointmentMinusBt);
        AddBt = (Button) findViewById(R.id.AppointmentAddBt);
        ET = (EditText) findViewById(R.id.AppointmentEt);

        ChooseDate = (TextView) findViewById(R.id.AppointmentDate);

        final Bundle bundle = this.getIntent().getExtras();
        NameTv.setText(bundle.getString("Nurse_name"));
        AgeTv.setText(bundle.getInt("Nurse_age")+"岁");
        SexTv.setText(bundle.getString("Nurse_sex"));
        AreaTv.setText(bundle.getString("Nurse_Area"));
        PriceTv.setText(bundle.getInt("Nurse_price")+"元/每天");
        EvaluateTv.setText("好评率："+bundle.getInt("Nurse_evaluate"));


        SimpleDateFormat formattery = new SimpleDateFormat ("yyyy");
        Date curDate = new Date(System.currentTimeMillis());
        String stry = formattery.format(curDate);
        final int y = Integer.parseInt(stry);
        SimpleDateFormat formatterm = new SimpleDateFormat ("MM");
        String strM = formatterm.format(curDate);
        final int m = Integer.parseInt(strM);
        SimpleDateFormat formatterd = new SimpleDateFormat ("dd");
        String strd = formatterd.format(curDate);
        final int d = Integer.parseInt(strd);

        ChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker=new DatePickerDialog(AppointmentActivity.this, new DatePickerDialog.OnDateSetListener() {

                        @Override
                 public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                            ChooseDate.setText(String.format("%d-%d-%d", year, monthOfYear+1,dayOfMonth));
                            ChooseDate.setTextColor(Color.rgb(0, 0, 0));
                                        }
             }, y, m-1, d);
                             datePicker.show();

            }
        });

        MinusBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(ET.getText().toString())!=0){
                    ET.setText((Integer.parseInt(ET.getText().toString())-1)+"");
                }
            }
        });

        AddBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ET.setText((Integer.parseInt(ET.getText().toString())+1)+"");
            }
        });

    }





}
