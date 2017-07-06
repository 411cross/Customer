package com.example.peek_mapdemotest.nurseapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.peek_mapdemotest.nurseapp.Activity.CheckOrderActivity;
import com.example.peek_mapdemotest.nurseapp.Activity.addPatientActivity;
import com.example.peek_mapdemotest.nurseapp.Okhttp_tools.handler;
import com.example.peek_mapdemotest.nurseapp.R;

import static com.example.peek_mapdemotest.nurseapp.R.id.viewPager;


public class MyFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private boolean isLoop=true;
    private ViewPager viewPager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyFragment() {
        // Required empty public constructor
    }


    public static MyFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MyFragment newOrderFragment = new MyFragment();
        newOrderFragment.setArguments(args);
        return newOrderFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        Button CheckOrder_btn = (Button)view.findViewById(R.id.CheckOrder_btn);
        Button add_Patient_Btn = (Button)view.findViewById(R.id.add_Patient_Btn);
        // Inflate the layout for this fragment

        CheckOrder_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CheckOrderActivity.class);
                startActivityForResult(intent,1);
            }
        });
        add_Patient_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), addPatientActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }




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
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };
}
