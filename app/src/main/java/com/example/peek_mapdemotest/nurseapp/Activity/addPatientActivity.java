package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.Adapter.PatientAdapter;
import com.example.peek_mapdemotest.nurseapp.Entity.Patient;
import com.example.peek_mapdemotest.nurseapp.Operation.OrderOperation;
import com.example.peek_mapdemotest.nurseapp.Operation.PatientList;
import com.example.peek_mapdemotest.nurseapp.Operation.PatientOperation;
import com.example.peek_mapdemotest.nurseapp.Operation.UserOperation;
import com.example.peek_mapdemotest.nurseapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class addPatientActivity extends AppCompatActivity {

    private ArrayList<Patient> patientList = new ArrayList<>();
    private ArrayAdapter PatientAdapter;
    private ImageButton extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        extra = (ImageButton)findViewById(R.id.extra);
        extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extra.setImageDrawable(getResources().getDrawable(R.drawable.add_2));
                LayoutInflater layoutInflater = LayoutInflater.from(addPatientActivity.this);
                final View myLoginView = layoutInflater.inflate(R.layout.addpatient, null);
                AlertDialog alertDialog = new AlertDialog.Builder(addPatientActivity.this).
                        setTitle("手动关联病人").setView(myLoginView).
                        setPositiveButton("提交", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                extra.setImageDrawable(getResources().getDrawable(R.drawable.add));
                                EditText ED1 = (EditText) myLoginView.findViewById(R.id.patient_no);
                                int n = Integer.valueOf(ED1.getText().toString());
                                System.out.println("病人编号: " + n);
                                try {
                                    ArrayList resp = PatientOperation.AddFamilyRelation(n);
                                    if (Integer.parseInt((String) resp.get(0)) == 200) {
                                        Toast.makeText(addPatientActivity.this, "病人添加成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        JSONObject object = new JSONObject((String) resp.get(1));
                                        String message = object.getString("message");
                                        Toast.makeText(addPatientActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    ArrayList resp = PatientOperation.getFamilyRelation();
                                    if (Integer.parseInt((String) resp.get(0)) == 200) {
                                        patientList = UserOperation.patientList;

                                        PatientAdapter = new PatientAdapter(addPatientActivity.this, R.layout.patient_item,patientList);
                                        ListView listView1 = (ListView) findViewById(R.id.listview_patient);
                                        listView1.setAdapter(PatientAdapter);            } else {
                                        JSONObject object = new JSONObject((String) resp.get(1));
                                        String message = object.getString("message");
                                        Toast.makeText(addPatientActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                PatientAdapter.notifyDataSetChanged();
                                //提交病人编号
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        extra.setImageDrawable(getResources().getDrawable(R.drawable.add));
                        //取消操作
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        extra.setImageDrawable(getResources().getDrawable(R.drawable.add));
                    }
                }).create();
                alertDialog.show();
            }
        });
        try {
            ArrayList resp = PatientOperation.getFamilyRelation();
            if (Integer.parseInt((String) resp.get(0)) == 200) {
                patientList = UserOperation.patientList;

                PatientAdapter = new PatientAdapter(addPatientActivity.this, R.layout.patient_item,patientList);
                ListView listView1 = (ListView) findViewById(R.id.listview_patient);
                listView1.setAdapter(PatientAdapter);            } else {
                JSONObject object = new JSONObject((String) resp.get(1));
                String message = object.getString("message");
                Toast.makeText(addPatientActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
