package com.example.peek_mapdemotest.nurseapp.Operation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import Decoder.BASE64Encoder;


/**
 * Created by derrickJ on 2017/5/26.
 */

public class Base64Tool {

    public static String imageToBase(String imagePath) {

        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imagePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        String baseString = encoder.encode(data);

        return baseString;//返回Base64编码过的字节数组字符串
    }

//    public static String baseToImage(String baseString) throws IOException {
//
//
//        //对字节数组字符串进行Base64解码并生成图片
//        if (baseString == null) //图像数据为空
//            return "Blank Image";
//
//        BASE64Decoder decoder = new BASE64Decoder();
//            //Base64解码
//            byte[] b = decoder.decodeBuffer(baseString);
//            for (int i = 0; i < b.length; ++i) {
//                if (b[i] < 0) {//调整异常数据
//                    b[i] += 256;
//                }
//            }
//            //生成jpeg图片
//            String imagePath = getApplicationContext().getFilesDir().getAbsolutePath() + "/pic.jpg";//新生成的图片
//            OutputStream out = new FileOutputStream(imagePath);
//            out.write(b);
//            out.flush();
//            out.close();
//
//            return imagePath;
//
//    }




}
