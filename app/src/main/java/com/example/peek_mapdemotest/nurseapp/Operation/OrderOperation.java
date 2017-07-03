package com.example.peek_mapdemotest.nurseapp.Operation;

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
    /**
     * 通过订单状态筛选订单列表
     * 输入  状态
     * 输出 状态码和返回信息
     */
    public static ArrayList getOrder(String situation) throws JSONException, ExecutionException, InterruptedException {
        okHttpTools okhttpT = new okHttpTools();
        JSONObject jObject = new JSONObject();
        jObject.put("id", UserOperation.user.getId());
        jObject.put("situation", situation);
        String Json = jObject.toString();
        String URL = "http://139.199.226.190:8080/api/v1/register";
        okhttpT.postTools(URL, Json);
        String data = (String) okhttpT.getResponse().get(1);
        JSONObject object = new JSONObject(data);
        JSONArray jsonArray = object.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject ordersData = (JSONObject) jsonArray.get(i);
//            String nurseName = nursesData.getString("nurseName");
//            int nurseSex = nursesData.getInt("nurseSex");

        }

        return okhttpT.getResponse();

    }
}
