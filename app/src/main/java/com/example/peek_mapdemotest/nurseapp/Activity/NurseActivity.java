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
import com.example.peek_mapdemotest.nurseapp.Operation.NurseOperation;
import com.example.peek_mapdemotest.nurseapp.Operation.UserOperation;
import com.example.peek_mapdemotest.nurseapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class NurseActivity extends AppCompatActivity {
     NurseAdapter NuAdapter;
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
        spinner3Data = this.getResources().getStringArray(R.array.sex);
        spinner4Data = this.getResources().getStringArray(R.array.evaluation);
        try {
            getdata();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int filter = 1;
                try {
                    ArrayList resp = NurseOperation.filterNurseList(filter,position);
                    if(Integer.parseInt((String) resp.get(0))==200){
                        NuAdapter = new NurseAdapter(NurseActivity.this,R.layout.nurse_item, UserOperation.nurseList);
                        NurseListView.setAdapter(NuAdapter);

                    }else{
                        String data = (String) resp.get(1);
                        JSONObject object = new JSONObject(data);
                        String respJsonObject = object.getString("message");
                        Toast.makeText(NurseActivity.this,"筛选"+respJsonObject ,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int filter = 2;
                try {
                    ArrayList resp = NurseOperation.filterNurseList(filter,position);
                    if(Integer.parseInt((String) resp.get(0))==200){
                        NuAdapter = new NurseAdapter(NurseActivity.this,R.layout.nurse_item, UserOperation.nurseList);
                        NuAdapter.notifyDataSetChanged();
                        NurseListView.setAdapter(NuAdapter);

                    }else{
                        String data = (String) resp.get(1);
                        JSONObject object = new JSONObject(data);
                        String respJsonObject = object.getString("message");
                        Toast.makeText(NurseActivity.this,"筛选"+respJsonObject ,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int filter = 3;
                try {
                    ArrayList resp = NurseOperation.filterNurseList(filter,position);
                    if(Integer.parseInt((String) resp.get(0))==200){
                        NuAdapter = new NurseAdapter(NurseActivity.this,R.layout.nurse_item, UserOperation.nurseList);
                        NurseListView.setAdapter(NuAdapter);


                    }else{
                        String data = (String) resp.get(1);
                        JSONObject object = new JSONObject(data);
                        String respJsonObject = object.getString("message");
                        Toast.makeText(NurseActivity.this,"筛选"+respJsonObject ,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int filter = 4;
                try {
                    ArrayList resp = NurseOperation.filterNurseList(filter,position);
                    if(Integer.parseInt((String) resp.get(0))==200){
                        NuAdapter = new NurseAdapter(NurseActivity.this,R.layout.nurse_item, UserOperation.nurseList);

                        NurseListView.setAdapter(NuAdapter);

                    }else{
                        String data = (String) resp.get(1);
                        JSONObject object = new JSONObject(data);
                        String respJsonObject = object.getString("message");
                        Toast.makeText(NurseActivity.this,"筛选"+respJsonObject ,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        NuAdapter = new NurseAdapter(NurseActivity.this,R.layout.nurse_item, UserOperation.nurseListAll);
        NurseListView.setAdapter(NuAdapter);
        NurseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nurse nurse = NuAdapter.getList().get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("Nurse_id", nurse.getNurseId());
                bundle.putString("Nurse_name", nurse.getNurseName());
                bundle.putInt("Nurse_age", nurse.getNurseAge());
                bundle.putString("Nurse_Area", nurse.getNurseArea());
                bundle.putInt("Nurse_sex", nurse.getNurseSex());
                bundle.putInt("Nurse_price", nurse.getNursePrice());
                bundle.putInt("Nurse_evaluate", nurse.getNurseEvaluate());
                Intent intent = new Intent(NurseActivity.this,NurseDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }



    public void getdata() throws InterruptedException, ExecutionException, JSONException {
        ArrayList resp =NurseOperation.getNurseList();


        TestArrayAdapter adapter1=new TestArrayAdapter(this,spinner1Data);
        spinner1.setAdapter(adapter1);
        TestArrayAdapter adapter2=new TestArrayAdapter(this,spinner2Data);
        spinner2.setAdapter(adapter2);
        TestArrayAdapter adapter3=new TestArrayAdapter(this,spinner3Data);
        spinner3.setAdapter(adapter3);
        TestArrayAdapter adapter4=new TestArrayAdapter(this,spinner4Data);
        spinner4.setAdapter(adapter4);
        spinner1.setSelection(0, true);
        spinner2.setSelection(0, true);
        spinner3.setSelection(0, true);
        spinner4.setSelection(0, true);

    }

    public void initFindByID(){
        spinner1 = (Spinner) findViewById(R.id.Spinner1);
        spinner2 = (Spinner) findViewById(R.id.Spinner2);
        spinner3 = (Spinner) findViewById(R.id.Spinner3);
        spinner4 = (Spinner) findViewById(R.id.Spinner4);

        NurseListView = (ListView) findViewById(R.id.NurseListview);
    }
}
