package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.peek_mapdemotest.nurseapp.R;
import com.example.peek_mapdemotest.nurseapp.alipayjar.OrderInfoUtil2_0;
import com.example.peek_mapdemotest.nurseapp.alipayjar.PayResult;

import java.util.Map;


public class payActivity extends AppCompatActivity implements View.OnClickListener  {

    Button btnPay;
    String money; //交易金额
    String subject;//商品名称
    String body;//商品描述
    TextView textView;
    Bundle bundle;
    private static final int SDK_PAY_FLAG = 1001;
    private String RSA_PRIVATE ="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCai+0S8c8SejQw8NZAAZvMskkYisL3uVkXBFB9BlymwMd9pn+dY40+Q+CaI7+PAdI33SNPch5ue7ievs05SLEedx7j3S6vl7avuKolNF5S9juJ8IWEtVf0InO2qKg/SjpaswY+4QXkXJ34IY9qYIEoLm+pPugNQHOFQ9aZI/iPhSa2jRcKE7UXE6L35XA+HWx3VVpN4TPSWxi8sOimG32unoqM2r1gFRIBAhDJHO5FvgjGt3+jSQhtPRRhrQhwovPKGKc7ah40rdGhX2UxwbmiEtMOfdSfsivCNXBe4fDgCgb9sNyFu8bttEzXI6Du8QUaUq/jebXJ9AI1NEooTjAzAgMBAAECggEAcZNxp+GDP14Fqw3jNmi4OeewJlQb0ngpS+wExsz9HCNCuEm7MUxvCZpSnpS0+4DoH9vpTkJcsO9Fj4xDP9z3JZ9/WRA8dhj1zg/VIsBorWl9+fKD/TJUL/t9tHggJVsEpZE5fs5aSSfzg94OCqxfXss5jlTHrq11+DMRq7uuuLGI6t62g0OTRw92NOwc0cwZf6+ccqv4x9h2OqppJDiS8hGqNiIHdyx6ELi2uQilN2uDmnU8zFxqyejwHSB2RV7QVoC4BKHCbC6ehsfWq8VZPwOJn0VxsgSAirN/XXoBnf9exK2yOB2C/9KmCcPM6QyCyTr2GprFFb0Avq/3zaYtYQKBgQDdTtH3nW6MWPDPIlYukYySH1OXnn5gHv+yKUeY7saDdwxxF4FvFiUQN47JxLkHYvBrYasyqtrJCoq5QwMEIMkzmtznYT/025n8R2Qv43RK+flDCVx4caEZpW60Q1dMELePYDenY69UIlWTBWe9LlvX6BmmBaHAhyw843EIQSxngwKBgQCyxfJBk0FBabLm8ijRAwZQVAkonWLiDYePRjbP2SupK+k54JwohHAWjXwEyOTx7OoOiTbOPYHGcJj9xGGHu7J+W7zrLjdph8LqQnsATc7b/BqO9/glQo5ZWMULT44SV4ViMxiifD6/D8eTbL6R/k6O/lNidsqiYH9lMw7Pw7YFkQKBgAY0CGSlcAzVDWLw9nN1tvf9ks1ELwyoscgfqhcaUkwrERk2bHVdrIpI1GLJddpEJrML2M2hxmIuPnc07qe1943PwHNn4XAOm8Fbiu5gtMjJOvhrEcF6heZ/IZxB6veB3HNTYhv6ClcUY0usshy2aPKjWdyyebc40r+eLHPrx7/bAoGASmB0c7fSFGbnWZIyTRMXE2CDFvSz6Rx7463wq/TMZ2uvcrbyASFoiNmAWXVDWyuYDUglFxUb4vWBkUKc2R3MGyoLpUTrJOqou1HBXtUAVSHD4utjwAy6sI1wHU6R6yuigMKFY0SRzmfAPqOlfZPyFyfUhUiq8Xd4SSA+702bcyECgYAe/W8eExMO4/AlAJFvwmcCPb81ZX2BP9yy9r5M6PHjnxlCU2csTiTbONSKVJ2c7/MGHaDY3rOaxJjqgR4fgkfx5RsqWauzc+Pz5ccgLaMI8HcGoigm9Dra3uA3/FRqVabO5i4oebGcx086z8mUo6zkhkgz92oOM2BJnCPPicmOVQ==";
    public static final String APPID = "2017062907602331";


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //同步获取结果
                    String resultInfo = payResult.getResult();
                    Log.i("Pay", "Pay:" + resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    Intent intent =getIntent();
                    Bundle bundle = new Bundle();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(payActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        bundle.putString("Status","success");
                    } else {
                        Toast.makeText(payActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        bundle.putString("Status","fail");
                    }
                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);
                    finish();
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        textView=(TextView) findViewById(R.id.textMoney);
        bundle = this.getIntent().getExtras();
        money= bundle.getString("money");
        textView.setText("¥ "+money);
        Button button5=(Button) findViewById(R.id.buttonCancel);
        button5.setOnClickListener( new View.OnClickListener()
        {
            public void onClick( View view)
            {
                Intent intent =getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("Status","cancel");
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        initView();
    }

    private void initView() {
        btnPay = (Button) findViewById(R.id.button);
        btnPay.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        subject="仁爱护工费用";
        body="订单编号 "+bundle.getString("id");
        switch (v.getId()) {
            case R.id.button:
                //秘钥验证的类型 true:RSA2 false:RSA
                boolean rsa = false;
                //构造支付订单参数列表 参数1：支付宝上APP的ID 参数2：false 参数3：金额 参数4：商品名称  参数5：商品描述
                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa,money,subject,body);
                //构造支付订单参数信息
                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
                //对支付参数信息进行签名
                String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE, rsa);
                //订单信息
                final String orderInfo = orderParam + "&" + sign;
                //异步处理
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        //新建任务
                        PayTask alipay = new PayTask(payActivity.this);

                        //获取支付结果
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
                break;
        }
    }




}
