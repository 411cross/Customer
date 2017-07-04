package com.example.peek_mapdemotest.nurseapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.Entity.Nurse;
import com.example.peek_mapdemotest.nurseapp.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class NurseAdapter  extends ArrayAdapter<Nurse> {

     private int resource;
     private List<Nurse> list;
    public NurseAdapter(Context context, int resourceID,List<Nurse> objects) {
        super(context, resourceID, objects);
        resource = resourceID;
        list=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Nurse nurse = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        TextView nurse_name = (TextView) view.findViewById(R.id.NurseNameD);
        ImageView nurse_icon = (ImageView) view.findViewById(R.id.NurseIcon);
        TextView nurse_age = (TextView) view.findViewById(R.id.NurseAge);
        TextView nurse_area = (TextView) view.findViewById(R.id.NurseArea);
        TextView nurse_Sex = (TextView) view.findViewById(R.id.NurseSex);
        TextView nurse_evaluate = (TextView) view.findViewById(R.id.NurseEvaluateD);
        TextView nurse_price = (TextView) view.findViewById(R.id.NursePrice);
        nurse_name.setText(nurse.getNurseName());
        nurse_icon.setImageResource(R.mipmap.ic_launcher);
        nurse_age.setText(nurse.getNurseAge()+"岁");
        nurse_area.setText(nurse.getNurseArea());
        nurse_Sex.setText(nurse.getNurseSex()+"");
        nurse_price.setText(nurse.getNursePrice()+"元/每天");
        nurse_evaluate.setText("好评率："+nurse.getNurseEvaluate());
        return view;

    }

    public List<Nurse> getList() {
        return list;
    }

    public void setList(List<Nurse> list) {
        this.list = list;
    }
}
