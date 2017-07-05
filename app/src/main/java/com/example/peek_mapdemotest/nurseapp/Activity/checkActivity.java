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
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.Adapter.CheckAdapter;
import com.example.peek_mapdemotest.nurseapp.Entity.Order;
import com.example.peek_mapdemotest.nurseapp.Operation.OrderOperation;
import com.example.peek_mapdemotest.nurseapp.Operation.UserOperation;
import com.example.peek_mapdemotest.nurseapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class checkActivity extends AppCompatActivity {
    private ListView listView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkorder);
        listView = (ListView)findViewById(R.id.list1);
        textView = (TextView) findViewById(R.id.textView2);

        Bundle bundle = this.getIntent().getExtras();
        String situation = bundle.getString("status");
        textView.setText("查询结果");
        try {
            ArrayList resp = OrderOperation.getOrder(situation);
            if(Integer.parseInt((String) resp.get(0))==200){

            }else{
                String data = (String) resp.get(1);
                JSONObject object = new JSONObject(data);
                String respJsonObject = object.getString("message");
                Toast.makeText(checkActivity.this,respJsonObject ,Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //构造未完成的测试数据的格式
        // public textorders( type1, status1, id1, date1, money1, patientname1, badnumber, connect, connectphone , caredate)

        CheckAdapter ccadapter =new CheckAdapter(checkActivity.this,R.layout.personalorder, UserOperation.orderList);
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
