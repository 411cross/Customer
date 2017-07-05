package com.example.peek_mapdemotest.nurseapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.Entity.Order;
import com.example.peek_mapdemotest.nurseapp.R;

import java.util.List;

/**
 * Created by DEMON on 2017/6/28.
 */

public class CheckAdapter extends ArrayAdapter<Order> {
    private int resource;
    public CheckAdapter(Context context,int resourceID,List<Order> objects)
    {
        super(context,resourceID,objects);
                resource=resourceID;
    }
    @Override
    public View getView(int position,View converView,ViewGroup parent)
    {
        Order to=getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource,parent,false);
        TextView textid=(TextView)view.findViewById(R.id.textViewId);
        TextView textmoney =(TextView)view.findViewById(R.id.textViewMoney);
        TextView textdate=(TextView)view.findViewById(R.id.textViewDate);
        TextView textstatus=(TextView)view.findViewById(R.id.textViewStatus);
      //  ImageView image=(ImageView) view.findViewById(R.id.imageView1);
        textid.setText(to.getId()+"");
        textmoney.setText(to.getTotalPrice()+"元");
        textdate.setText(to.getCreateTime());
        textstatus.setText(to.getSituation()+"状态");
        return view;
    }
}
