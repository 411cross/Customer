package com.example.peek_mapdemotest.nurseapp.Okhttp_tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/24.
 */
public class handler implements Callable {
    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    public String responseData = null;
    public String jsonData = null;
    public String URL = null;



    // 0:post   1: put   2: delete   3:get
    public handler(String URL, String jsonData) {
        this.jsonData = jsonData;
        this.URL = URL;


    }

    @Override
    public ArrayList call() throws Exception {
        OkHttpClient client = new OkHttpClient();
        ArrayList result = new ArrayList();
        RequestBody requestBody = RequestBody.create(JSON, jsonData);

                Request request = new Request.Builder().url(URL).addHeader("Content-Type", "application/json").post(requestBody).build();
                try {
                    Response response = client.newCall(request).execute();
                    responseData = response.body().string();
                    String res = responseData;
                    result.add(response.code() + "");
                    result.add(res);

                } catch (IOException e) {
                    e.printStackTrace();
                }



        return result;
    }

}
