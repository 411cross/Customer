package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.peek_mapdemotest.nurseapp.Adapter.PatientAdapter;
import com.example.peek_mapdemotest.nurseapp.Entity.Patient;
import com.example.peek_mapdemotest.nurseapp.R;

import java.util.ArrayList;

public class addPatientActivity extends AppCompatActivity {

    private ListView listview;
    private Button add_Button;
    private ArrayList<Patient> patientList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        add_Button = (Button)findViewById(R.id.button12);
        patientList.add(new Patient("张","1233"));
        patientList.add(new Patient("赵","1234"));
        PatientAdapter PatientAdapter = new PatientAdapter(addPatientActivity.this, R.layout.patient_item,patientList);
        ListView listView1 = (ListView) findViewById(R.id.listview_patient);
        listView1.setAdapter(PatientAdapter);
        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(addPatientActivity.this);
                final View myLoginView = layoutInflater.inflate(R.layout.addpatient, null);
                AlertDialog alertDialog = new AlertDialog.Builder(addPatientActivity.this).
                        setTitle("手动关联病人").setView(myLoginView).
                        setPositiveButton("提交", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                EditText ED1 = (EditText) myLoginView.findViewById(R.id.patient_no);
                                //提交病人编号
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                }).
                        create();
                alertDialog.show();
            }
        });
    }
}
