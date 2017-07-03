package com.example.peek_mapdemotest.nurseapp.Operation;

import com.example.peek_mapdemotest.nurseapp.Entity.Patient;

import java.util.ArrayList;

/**
 * Created by chf on 2017/7/3.
 */

public class PatientList {
    private Patient p1= new Patient(1233,"张三","123",1,"有病","小张","13318311111");
    private Patient p2 = new Patient(1234,"李四","124",1,"有病","小李","13318311122");
    private Patient p3 = new Patient(1235,"赵五","125",0,"有病","小李","13318311122");
    private Patient p4 = new Patient(1236,"王五","126",0,"有病","小李","13318311122");
    private ArrayList<Patient> patientList = new ArrayList<>();

    public PatientList(){
        patientList.add(p1);
        patientList.add(p2);
    }
    public void addPatirnt(int id){
            if(id == p3.getId()){
                patientList.add(p3);
            }
            else patientList.add(p4);
    }

    public ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(ArrayList<Patient> patientList) {
        this.patientList = patientList;
    }
}
