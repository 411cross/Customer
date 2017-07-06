package com.example.peek_mapdemotest.nurseapp.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.R;
import com.example.peek_mapdemotest.nurseapp.fragment.HomeFragment;
import com.example.peek_mapdemotest.nurseapp.fragment.MyFragmentPagerAdapter;
import com.example.peek_mapdemotest.nurseapp.fragment.NurseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewHomeActivity extends AppCompatActivity {

    private Button nurseManageBtn;
    private Button newOrderBtn;
    private Button oldOrderBtn;
    public ArrayList list = new ArrayList();
    private ImageView avatarImage;
    private TextView accountNickName;


    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;

    private String[] tabTitles ={"首页", "护工", "我的"};
    private ListView drawList;
    private RelativeLayout drawerList;

    private MyFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabLayout.Tab tab_one;
    private TabLayout.Tab tab_two;
    private TabLayout.Tab tab_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);

//        // 初始化控件
//        initView();
//
//        // 初始化数据
//        initData();

        initLayoutView();

    }

//    // 初始化Toolbar、DrawerLayout，生成相应的对象
//    private void initView() {
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//
//        //抽屉部分的 ListView
//        drawList = (ListView) findViewById(R.id.lv_title);
//        MyAdapter myAdapter = new MyAdapter();
//        drawList.setAdapter(myAdapter);
//
//
//    }
//
//    // 设置应用title
//    private void initData() {
//        // 设置Toolbar标题，需在setSupportActionBar之前，不然会失效
//        mToolbar.setTitle("Tea Mall");
//        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
//
//        // 设置toolbar支持actionbar
//        setSupportActionBar(mToolbar);
//
//        // 实现按钮开关的显示及打开关闭功能并同步动画
//        initDrawerToggle();
//    }
//
//    private void initDrawerToggle() {
//        // 参数：开启抽屉的activity、DrawerLayout的对象、toolbar按钮打开关闭的对象、描述open drawer、描述close drawer
//        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
//        // 添加抽屉按钮，通过点击按钮实现打开和关闭功能; 如果不想要抽屉按钮，只允许在侧边边界拉出侧边栏，可以不写此行代码
//        mDrawerToggle.syncState();
//        // 设置按钮的动画效果; 如果不想要打开关闭抽屉时的箭头动画效果，可以不写此行代码
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//    }
//

    private void initLayoutView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("订单"));
        tabLayout.addTab(tabLayout.newTab().setText("护工"));

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
        fragments.add(HomeFragment.newInstance(1));
        fragments.add(NurseFragment.newInstance(2));
        fragments.add(HomeFragment.newInstance(3));


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

//private Button newOrderBtn;
//private Button oldOrderBtn;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        newOrderBtn = (Button) findViewById(R.id.new_order);
//        oldOrderBtn = (Button) findViewById(R.id.old_order);
//
//        newOrderBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent toNewOrder = new Intent(MainActivity.this, NewOrderActivity.class);
//                startActivity(toNewOrder);
//            }
//        });
//
//        oldOrderBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent toOldOrder = new Intent(MainActivity.this, OldOrderActivity.class);
//                startActivity(toOldOrder);
//            }
//        });
//
//    }