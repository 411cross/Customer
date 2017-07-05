package com.example.peek_mapdemotest.nurseapp.Operation;

import com.example.peek_mapdemotest.nurseapp.Entity.Patient;
import com.example.peek_mapdemotest.nurseapp.Okhttp_tools.okHttpTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2017/7/3.
 */

public class PatientOperation {

    /**
     * 添加用户家属关系
     * 输入  p_id  病人的ID
     * 输出 状态码和返回信息
     */
    public static ArrayList AddFamilyRelation(int p_id) throws JSONException, ExecutionException, InterruptedException {


        okHttpTools okhttpT = new okHttpTools();
        JSONObject jObject = new JSONObject();
        jObject.put("u_id", UserOperation.user.getId());
        jObject.put("p_id", p_id);
        String Json = jObject.toString();
        String URL = "http://139.199.226.190:8888/NurseApp/addrelation";
        okhttpT.postTools(URL, Json);

        return okhttpT.getResponse();

    }

    /**
     * 查看用户家属关系
     * 输入  无
     * 输出 状态码和返回信息
     */
    public static ArrayList getFamilyRelation() throws JSONException, ExecutionException, InterruptedException {
        ArrayList<Patient> list = new ArrayList<Patient>();
        okHttpTools okhttpT = new okHttpTools();
        JSONObject jObject = new JSONObject();
        jObject.put("id", UserOperation.user.getId());
        String Json = jObject.toString();
        String URL = "http://139.199.226.190:8888/NurseApp/getpatient";
        okhttpT.postTools(URL, Json);
        if(Integer.parseInt((String) okhttpT.getResponse().get(0))==200){
            String data = (String) okhttpT.getResponse().get(1);
            JSONObject object = new JSONObject(data);
            JSONArray jsonArray = object.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject patientsData = (JSONObject) jsonArray.get(i);
                int id = patientsData.getInt("id");
                String bedNumber = patientsData.getString("bedNumber");
                String name = patientsData.getString("name");
                int sex = patientsData.getInt("sex");
                String disease = patientsData.getString("disease");
                String contactName = patientsData.getString("contactName");
                String contactPhone = patientsData.getString("contactPhone");
                Patient patient = new Patient(id, name, bedNumber, sex, disease, contactName, contactPhone);
                list.add(patient);


            }
            UserOperation.patientList = list;
        }

        return okhttpT.getResponse();


    }
}
