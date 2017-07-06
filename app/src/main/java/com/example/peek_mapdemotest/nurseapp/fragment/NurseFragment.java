package com.example.peek_mapdemotest.nurseapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.Activity.NurseActivity;
import com.example.peek_mapdemotest.nurseapp.Activity.NurseDetailActivity;
import com.example.peek_mapdemotest.nurseapp.Activity.OrderDetailActivity;
import com.example.peek_mapdemotest.nurseapp.Adapter.CheckAdapter;
import com.example.peek_mapdemotest.nurseapp.Adapter.NurseAdapter;
import com.example.peek_mapdemotest.nurseapp.Adapter.TestArrayAdapter;
import com.example.peek_mapdemotest.nurseapp.Entity.Nurse;
import com.example.peek_mapdemotest.nurseapp.Entity.Order;
import com.example.peek_mapdemotest.nurseapp.Operation.NurseOperation;
import com.example.peek_mapdemotest.nurseapp.Operation.OrderOperation;
import com.example.peek_mapdemotest.nurseapp.Operation.UserOperation;
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

public class NurseFragment extends android.support.v4.app.Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private NurseAdapter NuAdapter;
    private ListView NurseListView;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;

    private String[] spinner1Data;
    private String[] spinner2Data;
    private String[] spinner3Data;
    private String[] spinner4Data;

    private TextView tipsTv;

    private View view;

    private ArrayList<Nurse> list = new ArrayList<>();

    public static NurseFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        NurseFragment newOrderFragment = new NurseFragment();
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
        view = inflater.inflate(R.layout.layout_nurse_fragment, container, false);

        initFindByID();
        spinner1Data = this.getResources().getStringArray(R.array.protectArea);
        spinner2Data = this.getResources().getStringArray(R.array.protectYear);
        spinner3Data = this.getResources().getStringArray(R.array.sex);
        spinner4Data = this.getResources().getStringArray(R.array.evaluation);
        try {
            getdata();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int filter = 1;
                try {
                    ArrayList resp = NurseOperation.filterNurseList(filter, position);
                    if (Integer.parseInt((String) resp.get(0)) == 200) {
                        list.clear();
                        list.addAll(UserOperation.nurseList);
                        NuAdapter.notifyDataSetChanged();
                        tipsTv.setText("");

                    } else {
                        list.clear();

                        tipsTv.setText("无符合条件的护工");
                        NuAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int filter = 2;
                try {
                    ArrayList resp = NurseOperation.filterNurseList(filter, position);
                    if (Integer.parseInt((String) resp.get(0)) == 200) {
                        list.clear();
                        list.addAll(UserOperation.nurseList);
                        tipsTv.setText("");
                        NuAdapter.notifyDataSetChanged();

                    } else {
                        list.clear();

                        tipsTv.setText("无符合条件的护工");
                        NuAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int filter = 3;
                try {
                    ArrayList resp = NurseOperation.filterNurseList(filter, position);
                    if (Integer.parseInt((String) resp.get(0)) == 200) {
                        list.clear();
                        list.addAll(UserOperation.nurseList);
                        tipsTv.setText("");
                        NuAdapter.notifyDataSetChanged();


                    } else {
                        list.clear();

                        tipsTv.setText("无符合条件的护工");
                        NuAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int filter = 4;
                try {
                    ArrayList resp = NurseOperation.filterNurseList(filter, position);
                    if (Integer.parseInt((String) resp.get(0)) == 200) {
                        list.clear();
                        list.addAll(UserOperation.nurseList);
                        tipsTv.setText("");
                        NuAdapter.notifyDataSetChanged();

                    } else {
                        list.clear();

                        tipsTv.setText("无符合条件的护工");
                        NuAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        list.clear();
        list.addAll(UserOperation.nurseListAll);
        if (list.size() == 0) {
            tipsTv.setText("无符合条件的护工");
        } else {
            tipsTv.setText("");
            NuAdapter = new NurseAdapter(getContext(), R.layout.nurse_item, list);
            NurseListView.setAdapter(NuAdapter);
        }

        NurseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nurse nurse = NuAdapter.getList().get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("Nurse_id", nurse.getNurseId());
                System.out.println("check nurse id" + nurse.getNurseId());
                bundle.putString("Nurse_name", nurse.getNurseName());
                bundle.putInt("Nurse_age", nurse.getNurseAge());
                bundle.putString("Nurse_Area", nurse.getNurseArea());
                bundle.putInt("Nurse_sex", nurse.getNurseSex());
                bundle.putInt("Nurse_price", nurse.getNursePrice());
                bundle.putInt("Nurse_work_age", nurse.getNurseWorkAge());
                bundle.putInt("Nurse_evaluate", nurse.getNurseEvaluate());
                bundle.putString("Nurse_phone", nurse.getNursePhone());
                bundle.putInt("Nurse_height", nurse.getNurseHeight());
                bundle.putInt("Nurse_weight", nurse.getNurseWeight());
                bundle.putString("Nurse_blood", nurse.getNurseBloodType());
                bundle.putString("Nurse_nation", nurse.getNurseNation());
                bundle.putString("Nurse_identity", nurse.getNurseIdentity());
                bundle.putString("Nurse_Constellation", nurse.getNurseConstellation());
                bundle.putString("Nurse_Animal", nurse.getNurseAnimal());
                bundle.putString("Nurse_Description", nurse.getNurseDescription());
                bundle.putIntegerArrayList("nurseProtectArea", nurse.getNurseProtectArea());
                Intent intent = new Intent(getContext(), NurseDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }


    public void getdata() throws InterruptedException, ExecutionException, JSONException {
        ArrayList resp = NurseOperation.getNurseList();


        TestArrayAdapter adapter1 = new TestArrayAdapter(getContext(), spinner1Data);
        spinner1.setAdapter(adapter1);
        TestArrayAdapter adapter2 = new TestArrayAdapter(getContext(), spinner2Data);
        spinner2.setAdapter(adapter2);
        TestArrayAdapter adapter3 = new TestArrayAdapter(getContext(), spinner3Data);
        spinner3.setAdapter(adapter3);
        TestArrayAdapter adapter4 = new TestArrayAdapter(getContext(), spinner4Data);
        spinner4.setAdapter(adapter4);
        spinner1.setSelection(0, true);
        spinner2.setSelection(0, true);
        spinner3.setSelection(0, true);
        spinner4.setSelection(0, true);

    }


    public void initFindByID() {
        spinner1 = (Spinner) view.findViewById(R.id.Spinner1);
        spinner2 = (Spinner) view.findViewById(R.id.Spinner2);
        spinner3 = (Spinner) view.findViewById(R.id.Spinner3);
        spinner4 = (Spinner) view.findViewById(R.id.Spinner4);
        tipsTv = (TextView) view.findViewById(R.id.tipsTv);

        NurseListView = (ListView) view.findViewById(R.id.NurseListview);
    }

}
