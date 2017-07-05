package com.example.peek_mapdemotest.nurseapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.peek_mapdemotest.nurseapp.R;
import com.example.peek_mapdemotest.nurseapp.fragment.MyFragmentPagerAdapter;
import com.example.peek_mapdemotest.nurseapp.fragment.NewOrderFragment;
import com.example.peek_mapdemotest.nurseapp.fragment.OldOrderFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckOrderActivity extends AppCompatActivity {

    Button button;
    String string;
    Spinner spinner;
    private String[] tabTitles = {"未完成", "已完成"};
    private MyFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_order);
//        button = (Button) findViewById(R.id.button01);
//        spinner = (Spinner) findViewById(R.id.spinner01);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CheckOrderActivity.this, checkActivity.class);
//                //  intent.setClass(MainActivity.this,checkActivity.class);
//                string = spinner.getSelectedItem().toString();
//                Bundle bundle = new Bundle();
//                bundle.putString("status", string);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });

        initLayoutView();

    }

    private void initLayoutView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("未完成"));
        tabLayout.addTab(tabLayout.newTab().setText("已完成"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("TEST","onTabSelected:"+tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(NewOrderFragment.newInstance(1));
        fragments.add(OldOrderFragment.newInstance(2));

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments, Arrays.asList(tabTitles));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("TEST","select page:"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}


