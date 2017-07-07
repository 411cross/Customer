package com.example.peek_mapdemotest.nurseapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        ImageView image=(ImageView) view.findViewById(R.id.imageView1);
        textid.setText(to.getId()+"");
        textmoney.setText(to.getTotalPrice()+"元");
        textdate.setText(to.getCreateTime());
        if(to.getSituation()==0){
            textstatus.setText("未付款");
        }else if (to.getSituation()==1){
            textstatus.setText("已付款");
        }else if (to.getSituation()==2){
            textstatus.setText("已取消");
        }else if (to.getSituation()==3){
            textstatus.setText("已完成");
        }else if (to.getSituation()==4){
            textstatus.setText("进行中");
        }
        else if (to.getSituation()==5){
            textstatus.setText("医院提醒您付款");
        }

        int image_id;
        switch (to.getType()) {
            case 1:
                image_id = R.mipmap.neike;
                break;
            case 2:
                image_id = R.mipmap.waike;
                break;
            case 3:
                image_id = R.mipmap.linshi;
                break;
            case 4:
                image_id = R.mipmap.biaozhun;
                break;
            case 5:
                image_id = R.mipmap.zhongzheng;
                break;
            default:
                image_id = R.mipmap.ic_launcher_round;
                break;
        }
        image.setImageResource(image_id);
        return view;
    }
}
