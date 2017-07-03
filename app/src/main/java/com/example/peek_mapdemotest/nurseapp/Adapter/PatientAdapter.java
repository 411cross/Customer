package com.example.peek_mapdemotest.nurseapp.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.Activity.addPatientActivity;
import com.example.peek_mapdemotest.nurseapp.Entity.Patient;
import com.example.peek_mapdemotest.nurseapp.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */

public class PatientAdapter extends ArrayAdapter<Patient> {
    private int resource;
    private Context con;
    private List<Patient> list;
    public PatientAdapter(Context context, int resourceID, List<Patient> objects) {
        super(context, resourceID, objects);
        resource = resourceID;
        this.con=context;
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final Patient pt = getItem(position);
        final View view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        TextView name = (TextView)view.findViewById(R.id.textView7);
        TextView no = (TextView)view.findViewById(R.id.textView4);
        name.setText(pt.getName());
        no.setText(pt.getId()+"");
        final String fuck = pt.getName();

        Button untie = (Button)view.findViewById(R.id.button_untie);
        untie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(con);
                builder.setTitle("确认解绑吗？");
                builder.setPositiveButton("确认",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for(int i = 0;i<list.size();i++){
                                    if(pt.getId()==list.get(i).getId()){
                                        list.remove(i);
                                        notifyDataSetChanged();
                                        break;
                                    }
                                }
                                //确认解绑逻辑
                            }
                        });
                builder.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消解绑操作
                            }
                        });

                builder.show();

            }
        });
        return view;
    }
}
