package com.example.peek_mapdemotest.nurseapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.peek_mapdemotest.nurseapp.Entity.Patient;
import com.example.peek_mapdemotest.nurseapp.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */

public class PatientAdapter extends ArrayAdapter<Patient> {
    private int resource;
    public PatientAdapter(Context context, int resourceID, List<Patient> objects) {
        super(context, resourceID, objects);
        resource = resourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Patient pg = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        TextView name = (TextView)view.findViewById(R.id.textView7);
        TextView no = (TextView)view.findViewById(R.id.textView4);
        name.setText(pg.getName());
        no.setText(pg.getNo());
        return view;
    }
}
