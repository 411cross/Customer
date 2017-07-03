package com.example.peek_mapdemotest.nurseapp.Operation;

import com.example.peek_mapdemotest.nurseapp.Entity.Nurse;
import com.example.peek_mapdemotest.nurseapp.Entity.Order;
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

public class OrderOperation {

    ArrayList<Order> orderArrayList = null;
    /**
     * 通过订单状态筛选订单列表
     * 输入  状态
     * 输出 状态码和返回信息
     */
    public static ArrayList getOrder(String situation1) throws JSONException, ExecutionException, InterruptedException {
        ArrayList<Order> list = new ArrayList<>();
        okHttpTools okhttpT = new okHttpTools();
        JSONObject jObject = new JSONObject();
        jObject.put("id", UserOperation.user.getId());
        jObject.put("situation", situation1);
        String Json = jObject.toString();
        String URL = "http://139.199.226.190:8080/api/v1/register";
        okhttpT.postTools(URL, Json);
        String data = (String) okhttpT.getResponse().get(1);
        JSONObject object = new JSONObject(data);
        JSONArray jsonArray = object.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject ordersData = (JSONObject) jsonArray.get(i);
            int id = ordersData.getInt("id");
            int totalPrice = ordersData.getInt("totalPrice");
            String createTime = ordersData.getString("createTime");
            String serviceTime = ordersData.getString("serviceTime");
            int type = ordersData.getInt("type");
            int situation = ordersData.getInt("situation");
            int choseNurse = ordersData.getInt("choseNurse");
            Nurse nurse = new Nurse();
            JSONObject nurseJsonArray = object.getJSONObject("Nurse");
            String nurseName = nurseJsonArray.getString("nurseName");
            String nursePhone = nurseJsonArray.getString("nursePhone");
            int nurseEvaluate = nurseJsonArray.getInt("nurseEvaluate");
            nurse.setNurseName(nurseName);
            nurse.setNursePhone(nursePhone);
            nurse.setNurseEvaluate(nurseEvaluate);
            Patient patient = new Patient();
            JSONObject patientJsonArray = object.getJSONObject("Patient");
            String patientName = patientJsonArray.getString("name");
            String bedNumber = patientJsonArray.getString("bedNumber");
            patient.setName(patientName);
            patient.setBedNumber(bedNumber);
            Order order = new Order(id,totalPrice,createTime,serviceTime,type,situation,choseNurse,nurse,patient);
            list.add(order);
        }
        return okhttpT.getResponse();

    }

    /**
     * 通过创建订单
     * 输入  p_id u_id n_id
     * 输出 状态码和返回信息
     */
    public static ArrayList createOrder(Order order) throws JSONException, ExecutionException, InterruptedException {
        okHttpTools okhttpT = new okHttpTools();
        JSONObject jObject = new JSONObject();
        jObject.put("order", order);
        String Json = jObject.toString();
        String URL = "http://139.199.226.190:8080/api/v1/register";
        okhttpT.postTools(URL, Json);
        return okhttpT.getResponse();

    }
}
