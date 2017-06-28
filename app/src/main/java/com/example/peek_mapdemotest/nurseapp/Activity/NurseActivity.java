package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.Adapter.NurseAdapter;
import com.example.peek_mapdemotest.nurseapp.Adapter.TestArrayAdapter;
import com.example.peek_mapdemotest.nurseapp.Entity.Nurse;
import com.example.peek_mapdemotest.nurseapp.R;

import java.util.ArrayList;



public class NurseActivity extends AppCompatActivity {
    private ArrayList<Nurse> NurseList = new ArrayList<Nurse>();
    private ListView NurseListView;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;

    private String[] spinner1Data;
    private String[] spinner2Data;
    private String[] spinner3Data;
    private String[] spinner4Data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nurse_activity);
        initFindByID();
        spinner1Data = this.getResources().getStringArray(R.array.protectArea);
        spinner2Data = this.getResources().getStringArray(R.array.protectYear);
        spinner3Data = this.getResources().getStringArray(R.array.level);
        spinner4Data = this.getResources().getStringArray(R.array.choose);
        getdata();
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] protectArea = getResources().getStringArray(R.array.protectArea);
                Toast.makeText(NurseActivity.this, "你点击的是:"+protectArea[position], Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] protectYear = getResources().getStringArray(R.array.protectYear);
                Toast.makeText(NurseActivity.this, "你点击的是:"+protectYear[position], Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] level = getResources().getStringArray(R.array.level);
                Toast.makeText(NurseActivity.this, "你点击的是:"+level[position], Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] choose = getResources().getStringArray(R.array.choose);
                Toast.makeText(NurseActivity.this, "你点击的是:"+choose[position], Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        NurseAdapter NuAdapter = new NurseAdapter(NurseActivity.this,R.layout.nurse_item,NurseList);
        NurseListView.setAdapter(NuAdapter);
        NurseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nurse nurse = NurseList.get(position);

                Bundle bundle = new Bundle();
                bundle.putString("Nurse_name", nurse.getNurseName());
                bundle.putInt("Nurse_age", nurse.getNurseAge());
                bundle.putString("Nurse_Area", nurse.getNurseArea());
                bundle.putString("Nurse_sex", nurse.getNurseSex());
                bundle.putInt("Nurse_price", nurse.getNursePrice());
                bundle.putInt("Nurse_evaluate", nurse.getNurseEvaluate());
                Intent intent = new Intent(NurseActivity.this,NurseDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }



    public void getdata(){
        for(int i=0;i<2;i++){
            Nurse nurse1 = new Nurse("春梅",18,"女","汕头",80,220);
            NurseList.add(nurse1);
            Nurse nurse2 = new Nurse("牛大春",28,"男","湖南",90,210);
            NurseList.add(nurse2);
            Nurse nurse3 = new Nurse("牛欢喜",42,"女","河南",78,440);
            NurseList.add(nurse3);

        }

        TestArrayAdapter adapter1=new TestArrayAdapter(this,spinner1Data);
        spinner1.setAdapter(adapter1);
        TestArrayAdapter adapter2=new TestArrayAdapter(this,spinner2Data);
        spinner2.setAdapter(adapter2);
        TestArrayAdapter adapter3=new TestArrayAdapter(this,spinner3Data);
        spinner3.setAdapter(adapter3);
        TestArrayAdapter adapter4=new TestArrayAdapter(this,spinner4Data);
        spinner4.setAdapter(adapter4);

    }

    public void initFindByID(){
        spinner1 = (Spinner) findViewById(R.id.Spinner1);
        spinner2 = (Spinner) findViewById(R.id.Spinner2);
        spinner3 = (Spinner) findViewById(R.id.Spinner3);
        spinner4 = (Spinner) findViewById(R.id.Spinner4);

        NurseListView = (ListView) findViewById(R.id.NurseListview);
    }
}
