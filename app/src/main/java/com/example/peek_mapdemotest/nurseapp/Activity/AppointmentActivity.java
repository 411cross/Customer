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

import com.example.peek_mapdemotest.nurseapp.Entity.Nurse;
import com.example.peek_mapdemotest.nurseapp.Entity.Order;
import com.example.peek_mapdemotest.nurseapp.Entity.Patient;
import com.example.peek_mapdemotest.nurseapp.Entity.User;
import com.example.peek_mapdemotest.nurseapp.Operation.OrderOperation;
import com.example.peek_mapdemotest.nurseapp.Operation.UserOperation;
import com.example.peek_mapdemotest.nurseapp.R;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;


public class AppointmentActivity extends AppCompatActivity {

    private TextView NameTv;
    private TextView AgeTv;
    private TextView SexTv;
    private TextView AreaTv;
    private TextView PriceTv;
    private TextView EvaluateTv;
    private TextView ServerAreaTv;
    private TextView ChooseDate;
    private TextView protectedNameTv;
    private TextView AppointmentConNameTv;
    private TextView AppointmentConPhoneTv;
    private TextView AppiontmentMoneyTv;
    private TextView AppointmentMoney1Tv;

    private Button MinusBt;
    private Button AddBt;
    private EditText ET;

    private Button ConfirmButton;

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
        ServerAreaTv = (TextView) findViewById(R.id.ServerArea);
        protectedNameTv = (TextView) findViewById(R.id.protectedName);
        AppointmentConNameTv = (TextView) findViewById(R.id.AppointmentConName);
        AppointmentConPhoneTv = (TextView) findViewById(R.id.AppointmentConPhone);
        AppiontmentMoneyTv = (TextView) findViewById(R.id.AppiontmentMoney);
        AppointmentMoney1Tv = (TextView) findViewById(R.id.AppointmentMoney1);
        ConfirmButton = (Button) findViewById(R.id.ConfirmButton);

        MinusBt = (Button) findViewById(R.id.AppointmentMinusBt);
        AddBt = (Button) findViewById(R.id.AppointmentAddBt);
        ET = (EditText) findViewById(R.id.AppointmentEt);

        ChooseDate = (TextView) findViewById(R.id.AppointmentDate);

        final Bundle bundle = this.getIntent().getExtras();
        NameTv.setText(bundle.getString("Nurse_name"));
        AgeTv.setText(bundle.getInt("Nurse_age")+"岁");
        SexTv.setText(bundle.getInt("Nurse_sex")+"");
        AreaTv.setText(bundle.getString("Nurse_Area"));
        PriceTv.setText(bundle.getInt("Nurse_price")+"元/天");
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
        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalPrice = Integer.parseInt((String) AppointmentMoney1Tv.getText());
                String createTime = "111";
                String serviceTime = "122";
                int type = 1; //护理类型: 1.内科 2.外科 3.妇产科
                int situation = 1; //订单状态: 0.未付款 1.已付款 2.已取消 3.已完成 4.进行中 5.已提醒付款
                int choseNurse = 1;
                User user = UserOperation.user;
                Nurse nurse = new Nurse(bundle.getString("Nurse_name"),bundle.getInt("Nurse_sex"),bundle.getInt("Nurse_age"),bundle.getInt("Nurse_work_age"),bundle.getString("Nurse_Area"),bundle.getInt("Nurse_evaluate"),bundle.getInt("Nurse_price"),bundle.getIntegerArrayList("nurseProtectArea"),bundle.getInt("Nurse_height"),bundle.getInt("Nurse_weight"),bundle.getString("Nurse_blood"),bundle.getString("Nurse_nation"),bundle.getString("Nurse_identity"),bundle.getString("Nurse_Constellation"),bundle.getString("Nurse_Animal"),bundle.getString("Nurse_Description"),bundle.getString("Nurse_phone"));
                Patient patient = new Patient();
                Order order = new Order(totalPrice,createTime,serviceTime,type,situation,choseNurse,nurse,patient,user);
                try {
                    ArrayList resp = OrderOperation.createOrder(order);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }





}
