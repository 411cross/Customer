/**
* 查询结果界面
*/

package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.Adapter.CheckAdapter;
import com.example.peek_mapdemotest.nurseapp.R;

import java.util.ArrayList;



public class checkActivity extends AppCompatActivity {
    private ArrayList<textorders> arr_List;//查询结果数据适配器
    private ListView listView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkorder);
        listView = (ListView)findViewById(R.id.list1);
        textView = (TextView) findViewById(R.id.textView2);
        arr_List=new ArrayList<textorders>();
        Bundle bundle = this.getIntent().getExtras();
        String str = bundle.getString("status");
        textView.setText("查询结果");
        //构造未完成的测试数据的格式
        // public textorders( type1, status1, id1, date1, money1, patientname1, badnumber, connect, connectphone , caredate)
        if(!str.contains("未完成")) {
            arr_List.add(new textorders("精神", "已付款", "订单编号：201706280003", "创建日期：2017.06.28", "订单金额：88.00","牛上天","10087","牛欢喜","13666666666","2017.06.28 06:06-2017.06.29 06:06"));
            arr_List.add(new textorders("内科", "已付款", "订单编号：201706280004", "创建日期：2017.06.28", "订单金额：99.00","牛百万","10088","牛大力","13666666677","2017.07.01 06:06-2017.07.05 06:06"));
        }
        //构造已完成的测试数据
        if(!str.contains("已完成")) {
            arr_List.add(new textorders("妇科", "未付款", "订单编号：201605220003", "创建日期：2016.05.22", "订单金额：108.00","牛吃草","10089","牛喝水","13666666688","2017.07.01 06:06-2017.07.02 06:06"));
            arr_List.add(new textorders("外科", "未付款", "订单编号：201703100005", "创建日期：2017.03.10", "订单金额：60.00","牛回头","10090","牛睡觉","13666666699","2017.07.01 08:06-2017.07.02 08:06"));
        }
        CheckAdapter ccadapter =new CheckAdapter(checkActivity.this,R.layout.personalorder,arr_List);
       listView.setAdapter(ccadapter);
        //listView 点击事件，点击某一行获取当中的信息并且传递给订单详细信息界面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(checkActivity.this, OrderDetailActivity.class);
                textorders to1 = arr_List.get(i);
                intent.putExtra("textorders",to1);
                startActivity(intent);
            }
        });


    }
}
