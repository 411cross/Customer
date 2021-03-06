package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.Adapter.NurseAdapter;
import com.example.peek_mapdemotest.nurseapp.Adapter.TestArrayAdapter;
import com.example.peek_mapdemotest.nurseapp.Entity.Nurse;
import com.example.peek_mapdemotest.nurseapp.Operation.NurseOperation;
import com.example.peek_mapdemotest.nurseapp.Operation.UserOperation;
import com.example.peek_mapdemotest.nurseapp.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class NurseActivity extends AppCompatActivity {
    private NurseAdapter NuAdapter;
    private ListView NurseListView;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;

    private String[] spinner1Data;
    private String[] spinner2Data;
    private String[] spinner3Data;
    private String[] spinner4Data;

    private TextView tipsTv;

    private ArrayList<Nurse> list = new ArrayList<>();


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
                        list.clear();
                        list.addAll( UserOperation.nurseList);
                        NuAdapter.notifyDataSetChanged();
                        tipsTv.setText("");

                    }else{
                        list.clear();

                        tipsTv.setText("暂无订单");
                        NuAdapter.notifyDataSetChanged();
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
                        list.clear();
                        list.addAll( UserOperation.nurseList);
                        tipsTv.setText("");
                        NuAdapter.notifyDataSetChanged();

                    }else{
                        list.clear();

                        tipsTv.setText("暂无订单");
                        NuAdapter.notifyDataSetChanged();
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
                        list.clear();
                        list.addAll( UserOperation.nurseList);
                        tipsTv.setText("");
                        NuAdapter.notifyDataSetChanged();


                    }else{
                        list.clear();

                        tipsTv.setText("暂无订单");
                        NuAdapter.notifyDataSetChanged();
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
                        list.clear();
                        list.addAll( UserOperation.nurseList);
                        tipsTv.setText("");
                        NuAdapter.notifyDataSetChanged();

                    }else{
                        list.clear();

                        tipsTv.setText("暂无订单");
                        NuAdapter.notifyDataSetChanged();
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
        list.clear();
        list.addAll( UserOperation.nurseListAll);
        if(list.size()==0){
            tipsTv.setText("暂无订单");
        }else{
            tipsTv.setText("");
            NuAdapter = new NurseAdapter(NurseActivity.this,R.layout.nurse_item,list);
            NurseListView.setAdapter(NuAdapter);
        }

        NurseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nurse nurse = NuAdapter.getList().get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("Nurse_id", nurse.getNurseId());
                System.out.println("check nurse id" + nurse.getNurseId());
                bundle.putString("Nurse_name", nurse.getNurseName());
                bundle.putInt("Nurse_age", nurse.getNurseAge());
                bundle.putString("Nurse_Area", nurse.getNurseArea());
                bundle.putInt("Nurse_sex", nurse.getNurseSex());
                bundle.putInt("Nurse_price", nurse.getNursePrice());
                bundle.putInt("Nurse_work_age", nurse.getNurseWorkAge());
                bundle.putInt("Nurse_evaluate", nurse.getNurseEvaluate());
                bundle.putString("Nurse_phone", nurse.getNursePhone());
                bundle.putInt("Nurse_height", nurse.getNurseHeight());
                bundle.putInt("Nurse_weight", nurse.getNurseWeight());
                bundle.putString("Nurse_blood", nurse.getNurseBloodType());
                bundle.putString("Nurse_nation", nurse.getNurseNation());
                bundle.putString("Nurse_identity", nurse.getNurseIdentity());
                bundle.putString("Nurse_Constellation", nurse.getNurseConstellation());
                bundle.putString("Nurse_Animal", nurse.getNurseAnimal());
                bundle.putString("Nurse_Description", nurse.getNurseDescription());
                bundle.putIntegerArrayList("nurseProtectArea",nurse.getNurseProtectArea());
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
        tipsTv = (TextView) findViewById(R.id.tipsTv);

        NurseListView = (ListView) findViewById(R.id.NurseListview);
    }
}
