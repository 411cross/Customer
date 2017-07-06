package com.example.peek_mapdemotest.nurseapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.Activity.OrderDetailActivity;
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

public class NewOrderFragment extends android.support.v4.app.Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private ArrayList<Order> orderList = new ArrayList<>();
    Order tempOrder = new Order();
    private TextView tips1Tv;

    public static NewOrderFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        NewOrderFragment newOrderFragment = new NewOrderFragment();
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
        View view = inflater.inflate(R.layout.checkorder, container, false);

        tips1Tv = (TextView) view.findViewById(R.id.tips1Tv);
        try {
            ArrayList responseList = OrderOperation.getOrder("new");
            if (Integer.parseInt((String) responseList.get(0)) == 200) {
                JSONObject object = new JSONObject((String) responseList.get(1));
                String message = object.getString("data");
                System.out.println(message);
                JSONArray jsonArray = new JSONArray(message);
                JSONObject jsonObject;
                String jsonString = "";
                Gson gson = new Gson();
                System.out.println("jsonArray length: " + jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = (JSONObject) jsonArray.get(i);
                    jsonString = jsonObject.toString();
                    tempOrder = gson.fromJson(jsonString, Order.class);
                    System.out.println("new order id: " + tempOrder.getId());
                    orderList.add(tempOrder);
                }
                tips1Tv.setText("");

            } else {
                if (orderList.size() == 0) {
                    tips1Tv.setText("暂无订单");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        CheckAdapter checkAdapter = new CheckAdapter(view.getContext(), R.layout.personalorder, orderList);
        ListView orderListView = (ListView) view.findViewById(R.id.new_order_list);
        orderListView.setAdapter(checkAdapter);
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Order goodsClass = orderList.get(i);
//                Toast.makeText(buy_Activity.this, goods.getGoods_name(), Toast.LENGTH_SHORT).show();
                Order order = orderList.get(i);
                Bundle bundle = new Bundle();

                bundle.putInt("parentActivity", 0);
                bundle.putInt("position", i);
                bundle.putInt("orderID", order.getId());
                bundle.putInt("price", order.getTotalPrice());
                bundle.putString("time", order.getCreateTime());
                bundle.putInt("type", order.getType());
                bundle.putInt("situation", order.getSituation());
                bundle.putInt("choseNurse", order.getChoseNurse());

                bundle.putString("patient", order.getPatient().getName());
                bundle.putString("bed_number", order.getPatient().getBedNumber());
                bundle.putString("contact", order.getUser().getName());
                bundle.putString("phone", order.getUser().getId());
                bundle.putString("service_time", order.getServiceTime());

                bundle.putString("nurse_name", order.getNurse().getNurseName());
                bundle.putInt("evaluation", order.getNurse().getNurseEvaluate());
                bundle.putInt("height", order.getNurse().getNurseHeight());
                bundle.putInt("weight", order.getNurse().getNurseWeight());
                bundle.putString("blood_type", order.getNurse().getNurseBloodType());

                Intent intent = new Intent(view.getContext(), OrderDetailActivity.class);
                intent.putExtras(bundle);
                System.out.println(order.getId());
                startActivityForResult(intent, 1);
            }
        });

        return view;
    }

}
