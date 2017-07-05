package com.example.peek_mapdemotest.nurseapp.Activity;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.Entity.Nurse;
import com.example.peek_mapdemotest.nurseapp.Entity.Order;
import com.example.peek_mapdemotest.nurseapp.Entity.Patient;
import com.example.peek_mapdemotest.nurseapp.Entity.User;
import com.example.peek_mapdemotest.nurseapp.Operation.OrderOperation;
import com.example.peek_mapdemotest.nurseapp.Operation.PatientOperation;
import com.example.peek_mapdemotest.nurseapp.Operation.UserOperation;
import com.example.peek_mapdemotest.nurseapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private TextView AppointmentProtectAreaTv;


    private Button ConfirmButton;
    private RelativeLayout ChoosePatientLayout;

    private int type=0;
    private String serviceTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        NameTv = (TextView) findViewById(R.id.type);
        AgeTv = (TextView) findViewById(R.id.AppointmentNurseAge);
        SexTv = (TextView) findViewById(R.id.AppointmentNurseSex);
        AreaTv = (TextView) findViewById(R.id.AppointmentNurseArea);
        PriceTv = (TextView) findViewById(R.id.AppointmentNursePrice);
        EvaluateTv = (TextView) findViewById(R.id.AppointmentNurseEvaluate);
        ServerAreaTv = (TextView) findViewById(R.id.ServerArea);
        protectedNameTv = (TextView) findViewById(R.id.protectedName);
        AppointmentConNameTv = (TextView) findViewById(R.id.AppointmentConName);
        AppointmentConPhoneTv = (TextView) findViewById(R.id.AppointmentConPhone);
        AppiontmentMoneyTv = (TextView) findViewById(R.id.AppiontmentMoney);
        AppointmentMoney1Tv = (TextView) findViewById(R.id.AppointmentMoney1);
        ConfirmButton = (Button) findViewById(R.id.ConfirmButton);
        ChoosePatientLayout = (RelativeLayout) findViewById(R.id.relativeLayout4);
        AppointmentProtectAreaTv = (TextView) findViewById(R.id.AppointmentProtectArea);



        ChooseDate = (TextView) findViewById(R.id.AppointmentDate);

        final Bundle bundle = this.getIntent().getExtras();
        NameTv.setText(bundle.getString("Nurse_name"));
        AgeTv.setText(bundle.getInt("Nurse_age") + "岁");
        SexTv.setText(bundle.getInt("Nurse_sex") + "");
        AreaTv.setText(bundle.getString("Nurse_Area"));
        PriceTv.setText(bundle.getInt("Nurse_price") + "元/天");
        EvaluateTv.setText("好评率：" + bundle.getInt("Nurse_evaluate"));
        AppointmentConNameTv.setText(UserOperation.user.getName());
        AppointmentConPhoneTv.setText(UserOperation.user.getId());


        SimpleDateFormat formattery = new SimpleDateFormat("yyyy");
        final Date curDate = new Date(System.currentTimeMillis());
        String stry = formattery.format(curDate);
        final int y = Integer.parseInt(stry);
        SimpleDateFormat formatterm = new SimpleDateFormat("MM");
        String strM = formatterm.format(curDate);
        final int m = Integer.parseInt(strM);
        SimpleDateFormat formatterd = new SimpleDateFormat("dd");
        String strd = formatterd.format(curDate);
        final int d = Integer.parseInt(strd);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.DATE, 7);


        ChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker = new DatePickerDialog(AppointmentActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        ChooseDate.setText(String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                        ChooseDate.setTextColor(Color.rgb(0, 0, 0));
                        serviceTime = year + "-" + monthOfYear + "-" + dayOfMonth;
                    }

                }, y, m - 1, d);

                datePicker.getDatePicker().setMinDate(System.currentTimeMillis());
                datePicker.getDatePicker().setMaxDate(cal.getTimeInMillis());

                datePicker.show();

            }
        });



        ChoosePatientLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ArrayList resp = PatientOperation.getFamilyRelation();
                    if (Integer.parseInt((String) resp.get(0)) == 200) {
                        final ArrayList<String> single_list = new ArrayList<>();
                        for (int i = 0; i < UserOperation.patientList.size(); i++) {
                            single_list.add(UserOperation.patientList.get(i).getName());
                        }
                        String[] list_single = (String[]) single_list.toArray(new String[0]);
                        AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentActivity.this);
                        builder.setTitle("请选择病人");
//                    builder.setIcon(R.mipmap.ic_launcher);
                        builder.setSingleChoiceItems(list_single, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String str = single_list.get(which);
                                Toast.makeText(AppointmentActivity.this, "成功选择" + str, Toast.LENGTH_SHORT).show();
                                UserOperation.patient = UserOperation.patientList.get(which);
                                protectedNameTv.setText(UserOperation.patient.getName());
                                ServerAreaTv.setText(UserOperation.patient.getBedNumber());

                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    } else {
                        String data = (String) resp.get(1);
                        JSONObject object = new JSONObject(data);
                        String respJsonObject = object.getString("message");
                        Toast.makeText(AppointmentActivity.this, respJsonObject, Toast.LENGTH_SHORT).show();
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

        AppointmentProtectAreaTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<Integer> al = bundle.getIntegerArrayList("nurseProtectArea");
                final String[] list_single = new String[al.size()];
                for (int i = 0; i < al.size(); i++) {
                    if (al.get(i) == 1) {
                        list_single[i] = "内科";
                    }
                    if (al.get(i) == 2) {
                        list_single[i] = "外科";
                    }
                    if (al.get(i) == 3) {
                        list_single[i] = "临时看护";
                    }
                    if (al.get(i) == 4) {
                        list_single[i] = "天天护（标准）";
                    }
                    if (al.get(i) == 5) {
                        list_single[i] = "天天护（严重）";
                    }

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentActivity.this);
                builder.setTitle("选择服务类型");
//                    builder.setIcon(R.mipmap.ic_launcher);
                builder.setSingleChoiceItems(list_single, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        type = al.get(which);
                        AppointmentProtectAreaTv.setText(list_single[which]);
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalPrice = bundle.getInt("Nurse_price");
                SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyy-M-d");
                String createTime = currentDateFormat.format(curDate);
                int situation = 0; //订单状态: 0.未付款 1.已付款 2.已取消 3.已完成 4.进行中 5.已提醒付款
                int choseNurse = 1;
                User user = UserOperation.user;
                Nurse nurse = new Nurse(bundle.getString("Nurse_name"), bundle.getInt("Nurse_id"), bundle.getInt("Nurse_sex"), bundle.getInt("Nurse_age"), bundle.getInt("Nurse_work_age"), bundle.getString("Nurse_Area"), bundle.getInt("Nurse_evaluate"), bundle.getInt("Nurse_price"), bundle.getIntegerArrayList("nurseProtectArea"), bundle.getInt("Nurse_height"), bundle.getInt("Nurse_weight"), bundle.getString("Nurse_blood"), bundle.getString("Nurse_nation"), bundle.getString("Nurse_identity"), bundle.getString("Nurse_Constellation"), bundle.getString("Nurse_Animal"), bundle.getString("Nurse_Description"), bundle.getString("Nurse_phone"));
                Patient patient = UserOperation.patient;
                Order order = new Order(totalPrice, createTime, serviceTime, type, situation, choseNurse, nurse, patient, user);
                try {
                    ArrayList resp = OrderOperation.createOrder(order);
                    if (Integer.parseInt((String) resp.get(0)) == 200) {
                        Toast.makeText(AppointmentActivity.this, "订单创建成功", Toast.LENGTH_SHORT).show();
                    } else {
                        JSONObject object = new JSONObject((String) resp.get(1));
                        String message = object.getString("data");
                        Toast.makeText(AppointmentActivity.this, message, Toast.LENGTH_SHORT).show();
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
    }


}
