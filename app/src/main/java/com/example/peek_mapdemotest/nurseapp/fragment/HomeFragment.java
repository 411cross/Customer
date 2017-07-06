package com.example.peek_mapdemotest.nurseapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.Activity.ByAreaActivity;
import com.example.peek_mapdemotest.nurseapp.Activity.CheckOrderActivity;
import com.example.peek_mapdemotest.nurseapp.Activity.HomeActivity;
import com.example.peek_mapdemotest.nurseapp.Activity.NurseActivity;
import com.example.peek_mapdemotest.nurseapp.Activity.OrderDetailActivity;
import com.example.peek_mapdemotest.nurseapp.Activity.addPatientActivity;
import com.example.peek_mapdemotest.nurseapp.Adapter.CheckAdapter;
import com.example.peek_mapdemotest.nurseapp.Entity.Order;
import com.example.peek_mapdemotest.nurseapp.Operation.OrderOperation;

import com.example.peek_mapdemotest.nurseapp.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by derrickJ on 2017/5/28.
 */

public class HomeFragment extends android.support.v4.app.Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private ArrayList<Order> orderList = new ArrayList<>();
    Order tempOrder = new Order();

    private TextView tv1;
    private ImageView ib1;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button standardBtn;
    private Button seriousBtn;
    private Button b4;
    private Button NurseBt;
    private Button OrderBt;
    private boolean isLoop=true;
    private int itemnumber;
    /**
     * ViewPager
     */
    private ViewPager viewPager;

    /**
     * 装点点的ImageView数组
     */
    private ImageView[] tips;

    /**
     * 装ImageView数组
     */
    private ImageView[] mImageViews;

    /**
     * 图片资源id
     */
    private int[] imgIdArray ;

//    public boolean dispatchTouchEvent(MotionEvent ev) {
//           switch (ev.getAction())
//           {
//               case MotionEvent.ACTION_UP:
//                   isLoop=true;
//                   break;
//           }
//           return false;
//    }

    public static HomeFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        HomeFragment newOrderFragment = new HomeFragment();
        newOrderFragment.setArguments(args);
        return newOrderFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home_fragment, container, false);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        tv1 = (TextView) view.findViewById(R.id.textView);
//        ib1 = (ImageView)findViewById(R.id.imageButton);
        b1 = (Button) view.findViewById(R.id.button3);
        b2 = (Button) view.findViewById(R.id.button4);
        b3 = (Button) view.findViewById(R.id.button5);
        standardBtn = (Button) view.findViewById(R.id.standard_btn);
        seriousBtn = (Button) view.findViewById(R.id.serious_btn);
        b4 = (Button) view.findViewById(R.id.button6);
        NurseBt = (Button) view.findViewById(R.id.NurseListBt);
        OrderBt = (Button) view.findViewById(R.id.CheckOrder);
//        tv1.setText("用户名:"+ UserOperation.user.getName());


        setView();
/**
 *
 *
 */
        ViewGroup group = (ViewGroup) view.findViewById(R.id.viewGroup);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        //载入图片资源ID
        imgIdArray = new int[]{R.mipmap.alipay, R.mipmap.button1, R.mipmap.hartbat, R.mipmap.background,
                R.mipmap.blank,R.mipmap.lovelogo, R.mipmap.shenjingneike, R.mipmap.erbihouke};

//获得选择第几幅图
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            int flage =0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        tv1.setText(""+itemnumber);
                        break;
                }
                return true;
            }

        });
//        ib1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomeActivity.this, UserHomeActivity.class);
//                startActivity(intent);
//            }
//        });
        //点点
        tips = new ImageView[imgIdArray.length];
        for(int i=0; i<tips.length; i++){
            ImageView imageView = new ImageView(getContext());
            // LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            LinearLayout.LayoutParams paramsarams = new LinearLayout.LayoutParams(10,10);
            imageView.setLayoutParams(paramsarams);
            tips[i] = imageView;
            if(i == 0){
                tips[i].setBackgroundResource(R.mipmap.focused1);
            }else{
                tips[i].setBackgroundResource(R.mipmap.unfocused1);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.WRAP_CONTENT,
                    ViewPager.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group.addView(imageView, layoutParams);
        }
        //将图片装载到数组中
        mImageViews = new ImageView[imgIdArray.length];
        for(int i=0; i<mImageViews.length; i++){
            ImageView imageView = new ImageView(getContext());
            mImageViews[i] = imageView;
            imageView.setBackgroundResource(imgIdArray[i]);
        }

        //设置Adapter
        viewPager.setAdapter(new MyAdapter());
        //设置监听，主要是设置点点的背景
//        viewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        viewPager.setCurrentItem((mImageViews.length) * 100);
        /**
         *
         *
         */

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("area", 1);
                Intent intent = new Intent(getContext(), ByAreaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("area", 2);
                Intent intent = new Intent(getContext(), ByAreaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("area", 3);
                Intent intent = new Intent(getContext(), ByAreaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        standardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("area", 4);
                Intent intent = new Intent(getContext(), ByAreaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        seriousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("area", 5);
                Intent intent = new Intent(getContext(), ByAreaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), addPatientActivity.class);
                startActivity(intent);
            }
        });
        NurseBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NurseActivity.class);
                startActivity(intent);
            }
        });
        OrderBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CheckOrderActivity.class);
                startActivityForResult(intent,1);
            }
        });
    return view;
    }
    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);

        }

        /**
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
         */
        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);
            return mImageViews[position % mImageViews.length];
        }



    }

//    @Override
//    public void onPageScrollStateChanged(int arg0) {
//
//    }
//
//    @Override
//    public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//    }
//
//    @Override
//    public void onPageSelected(int arg0) {
//
//        setImageBackground(arg0 % mImageViews.length);
//    }

    /**
     * 设置选中的tip的背景
     //  * @param selectItems
     */
    private void setImageBackground(int selectItems){
        for(int i=0; i<tips.length; i++){
            itemnumber =selectItems;
            if(i == selectItems){
                tips[i].setBackgroundResource(R.mipmap.focused1);
            }else{
                tips[i].setBackgroundResource(R.mipmap.unfocused1);
            }
        }
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };

    public void setView() {
        // setContentView(R.layout.activity_splash_viewpager);

        // 自动切换页面功能
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (isLoop) {
                    //  while (true) {
                    SystemClock.sleep(4000);
                    handler.sendEmptyMessage(0);
                    //  NurseBt.setText("true");
                }
            }
        }).start();
    }

//    protected void onRestart() {
//        super.onRestart();
////        tv1.setText("用户名:"+ UserOperation.user.getName());
//
//    }

}
