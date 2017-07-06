/**
 * 订单详细信息界面
 **/

package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.R;

public class OrderDetailActivity extends AppCompatActivity {
    int ID;
    int money;
    int status;
    Button buttoncontact;
    Button buttonpay;
    AlertDialog.Builder builder2;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
//        Order od = (Order)getIntent().getSerializableExtra("order");
        int parentActivity = bundle.getInt("parentActivity");
        position = bundle.getInt("position");
        final int orderID = bundle.getInt("orderID");
        final int price = bundle.getInt("price");
        String time = bundle.getString("time");
        final int type = bundle.getInt("type");
        final int situation = bundle.getInt("situation");
        int notified = bundle.getInt("notified");
        int choseNurse = bundle.getInt("choseNurse");
        String patient = bundle.getString("patient");
        String bedNumber = bundle.getString("bed_number");
        String contact = bundle.getString("contact");
        String phone = bundle.getString("phone");
        String serviceTime = bundle.getString("service_time");
        String nurseName = bundle.getString("nurse_name");
        int height = bundle.getInt("height");
        int weight = bundle.getInt("weight");
        int evaluation = bundle.getInt("evaluation");
        String bloodType = bundle.getString("blood_type");

        setContentView(R.layout.activity_order_detail);
        TextView OrderStatus = (TextView) findViewById(R.id.order_status); //订单状态的Text，已付款，未付款
        TextView IDTv = (TextView) findViewById(R.id.order_id);//订单ID的Text
        TextView moneyTv = (TextView) findViewById(R.id.order_money);//订单金额的Text
        TextView patientTv = (TextView) findViewById(R.id.textPatient);//病人名字
        TextView bednumberTv = (TextView) findViewById(R.id.textBadNumber);//床位
        TextView connectTv = (TextView) findViewById(R.id.textLinkman);//联系人姓名
        TextView connectphone = (TextView) findViewById(R.id.textView10);//联系电话
        TextView typeTv = (TextView) findViewById(R.id.textView12);//护理类型
        TextView caredate = (TextView) findViewById(R.id.textView14);//护理时间
        TextView nurseNameTv = (TextView) findViewById(R.id.textView15);
        TextView evaluationTv = (TextView) findViewById(R.id.evaluation);
        TextView heightTv = (TextView) findViewById(R.id.textView17);
        TextView weightTv = (TextView) findViewById(R.id.textView18);
        TextView bloodTypeTv = (TextView) findViewById(R.id.textView19);
        buttonpay = (Button) findViewById(R.id.buttonPay);
        buttoncontact = (Button) findViewById(R.id.buttonContact);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Intent intent = getIntent();
        //获取传递的textorders并且设置各个TextView的内容
        try {

//            Order to1 = (Order) intent.getSerializableExtra("order");
//            IDTv.setText(to1.getId() + "");
//            ID = to1.getId();
//            moneyTv.setText(to1.getTotalPrice() + "元");
//            money = to1.getTotalPrice();
//            status = to1.getSituation();


//            OrderStatus.setText(status + "状态");
//            patientTv.setText(to1.getPatient().getName());
//            badnumber.setText(to1.getPatient().getBedNumber());
//            connect.setText(to1.getUser().getName());
//            connectphone.setText(to1.getUser().getId());
//            String[] strings = new String[to1.getNurse().getNurseProtectArea().size()];
//            String str = "";

            IDTv.setText(orderID + "");
            moneyTv.setText(price + "元");
            patientTv.setText(patient);
            bednumberTv.setText(bedNumber);
            connectTv.setText(contact);
            connectphone.setText(phone);
            caredate.setText(serviceTime);
            nurseNameTv.setText(nurseName);
            heightTv.setText(height + "cm");
            weightTv.setText(weight + "kg");
            evaluationTv.setText("评分："+evaluation );
            bloodTypeTv.setText(bloodType);

            switch (situation) {
                case 0:
                    OrderStatus.setText("未付款");
                    break;
                case 1:
                    OrderStatus.setText("已付款");
                    break;
                case 2:
                    OrderStatus.setText("已取消");
                    break;
                case 3:
                    OrderStatus.setText("已完成");
                    break;
                case 4:
                    OrderStatus.setText("进行中");
                    break;
                case 5:
                    OrderStatus.setText("已提醒付款");
                    break;
                default:
                    break;
            }

            switch (type) {
                case 1:
                    typeTv.setText("内科");
                    break;
                case 2:
                    typeTv.setText("外科");
                    break;
                case 3:
                    typeTv.setText("临时看护");
                    break;
                case 4:
                    typeTv.setText("标准护理");
                    break;
                case 5:
                    typeTv.setText("严重护理");
                    break;
                default:
                    break;
            }


//            for (int i = 0; i < to1.getNurse().getNurseProtectArea().size(); i++) {
//
//                if (to1.getNurse().getNurseProtectArea().get(i) == 1) {
//                    strings[i] = "内科";
//                }
//                if (to1.getNurse().getNurseProtectArea().get(i) == 2) {
//                    strings[i] = "外科";
//                }
//                if (to1.getNurse().getNurseProtectArea().get(i) == 3) {
//                    strings[i] = "临时看护";
//                }
//                if (to1.getNurse().getNurseProtectArea().get(i) == 4) {
//                    strings[i] = "天天护（标准）";
//                }
//                if (to1.getNurse().getNurseProtectArea().get(i) == 5) {
//                    strings[i] = "天天护（严重）";
//                }
//                str += "、" + strings[i];
//            }
//            typeTv.setText(str);

            //设置付款按钮能否被点击
            if (status == 1) {
                buttonpay.setText("已付款");
                buttonpay.setBackgroundColor(Color.parseColor("#cccccc"));
                buttonpay.setEnabled(false);
            } else {
                buttonpay.setText("付   款");
                buttonpay.setEnabled(true);
            }
        } catch (Exception e) {
            IDTv.setText(e.toString());
        }

        //点击支付按钮，进入支付界面
        buttonpay.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent intent = new Intent();
                                             intent.setClass(OrderDetailActivity.this, payActivity.class);
                                             Bundle bundle = new Bundle();

                                             bundle.putString("money", price+"");
                                             bundle.putString("id", orderID+"");
                                             intent.putExtras(bundle);
                                             startActivityForResult(intent, 0);
                                         }
                                     }
        );

        //点击联系我们 1
        buttoncontact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);

                Uri data = Uri.parse("tel:" + "15521107569");

                intent.setData(data);

                startActivity(intent);



            }
        });


    }

    //对支付返回进行确认，支付成功，支付失败，取消支付
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle = data.getExtras();
        AlertDialog.Builder builder = null;
        Button bt = (Button) findViewById(R.id.buttonPay);
        String text = bundle.getString("Status");
        bt.setText(text);

        try {
            if (text.equals("FAIL"))//支付失败
            {
                Toast.makeText(OrderDetailActivity.this, "测试作为成功", Toast.LENGTH_SHORT).show();
                bt.setText("已付款");
                bt.setBackgroundColor(Color.parseColor("#cccccc"));
                bt.setEnabled(false);
            }
            if (text.equals("CANCEL"))//取消支付
            {
                Toast.makeText(OrderDetailActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
            }
            if (text.equals("SUCCESS"))//支付成功
            {
                Toast.makeText(OrderDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                bt.setText("已付款");
                bt.setBackgroundColor(Color.parseColor("#cccccc"));
                bt.setEnabled(false);
            }
        } catch (Exception e) {

        }
    }
}
