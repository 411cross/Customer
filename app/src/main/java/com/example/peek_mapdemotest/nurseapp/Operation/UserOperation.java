package com.example.peek_mapdemotest.nurseapp.Operation;

import com.example.peek_mapdemotest.nurseapp.Entity.Nurse;
import com.example.peek_mapdemotest.nurseapp.Entity.User;
import com.example.peek_mapdemotest.nurseapp.Okhttp_tools.okHttpTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2017/7/3.
 */

public class UserOperation {

    //客户端需要维护的User
    public static User user = null;
    private String data;
    public static ArrayList<Nurse> nurseList;

    /**
     *  用户登录
     * 传入 id 和 pass
     * 输出 状态码和返回信息
     *
     */

    public static ArrayList UserLogin(String id, String pass) throws JSONException, ExecutionException, InterruptedException {

        okHttpTools okht = new okHttpTools();
        String URL = "http://139.199.226.190:8080/api/v1/getInfo";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("password", pass);
        String json = jsonObject.toString();
        okht.postTools(URL, json);


        ArrayList responseList = okht.getResponse();
        if (Integer.parseInt((String) okht.getResponse().get(0)) == 200) {
            String data = (String) responseList.get(1);
            JSONObject object = new JSONObject(data);
            JSONObject respJsonObject = object.getJSONObject("data");
            String userId = respJsonObject.getString("id");
            String userPass = respJsonObject.getString("password");
            String userName = respJsonObject.getString("name");
            String userAvatar = respJsonObject.getString("avatar");
            user = new User(userId, userPass, userName, userAvatar);
        }

           return responseList;
    }


    /**
     *  用户注册
     *  输入 id password name
     *  输出 状态码和返回信息
     */
     public static  ArrayList register(String id,String pass,String name) throws JSONException, ExecutionException, InterruptedException {
         okHttpTools okhttpT = new okHttpTools();
         JSONObject jObject = new JSONObject();
         jObject.put("id", id);
         jObject.put("password", pass);
         jObject.put("name", name);
         String userRegJson = jObject.toString();
         String URL = "http://139.199.226.190:8080/api/v1/register";
         okhttpT.postTools(URL, userRegJson);
         return okhttpT.getResponse();
     }

    /**
     *  获取护工列表
     *  输入 无
     *  输出 状态码和返回信息
     */


    public static  ArrayList getNurse(String id,String pass,String name) throws JSONException, ExecutionException, InterruptedException {
        okHttpTools okhttpT = new okHttpTools();
        String URL = "http://139.199.226.190:8080/api/v1/register";
        okhttpT.postTools(URL,"");
        String data = (String) okhttpT.getResponse().get(1);
        JSONObject object = new JSONObject(data);
        JSONArray jsonArray = object.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject nursesData = (JSONObject) jsonArray.get(i);
            String nurseName = nursesData.getString("nurseName");
            int nurseId = nursesData.getInt("nurseId");
            int nurseSex = nursesData.getInt("nurseSex");
            int nurseAge = nursesData.getInt("nurseAge");
            int nurseWorkAge = nursesData.getInt("nurseWorkAge");
            String nurseArea = nursesData.getString("nurseArea");
            int nurseEvaluate = nursesData.getInt("nurseWorkAge");
            int nursePrice = nursesData.getInt("nurseWorkAge");
            JSONArray nurseProtectArea = nursesData.getJSONArray("nurseProtectArea");
            for (int j = 0; j < nurseProtectArea.length(); j++) {

            }



        }
        return okhttpT.getResponse();
    }



}
