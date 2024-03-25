package com.glc.itbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.glc.itbook.fragment.Fragment_dongtai;
import com.glc.itbook.fragment.Fragment_index;
import com.glc.itbook.fragment.Fragment_wode;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeActivity extends AppCompatActivity{
    private ImageView dumbbell;
    private TextView dumbbell_num;

    private ImageView treadmill;
    private TextView treadmill_num;

    private ImageView exercisebike;
    private TextView exercisebike_num;

    private ImageView yoga_mat;
    private TextView yoga_mat_num;

    private ImageView resistance_band;
    private TextView resistance_band_num;

    private ImageView machine;
    private ImageView machine_clicked;
    private TextView machine_num;
    private Button btn_choose;
    private TextView time_1420;
    private TextView time_1800;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_list);
//        getWindow().addFlags(WindowManager.LayoutParams.);
//        getWindow().addFlags(WindowManager.LayoutParams.);
        time_1420 = findViewById(R.id.time_1420);
        time_1800 = findViewById(R.id.time_1800);
        time_1420.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_equipment_layout);
                //透明状态栏          
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

                machine = findViewById(R.id.machine);
                machine_clicked = findViewById(R.id.machine_clicked);
                machine_num = findViewById(R.id.machine_num);
                btn_choose = findViewById(R.id.buttonChooseEquipment);

                machine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view){
                        machine.setVisibility(View.INVISIBLE);
                        machine_clicked.setVisibility(View.VISIBLE);
                        String text = machine_num.getText().toString();
                        int currentNum = 0;
                        Pattern pattern = Pattern.compile("\\d+");
                        Matcher matcher = pattern.matcher(text);
                        if (matcher.find()) {
                            currentNum = Integer.parseInt(matcher.group());
                            currentNum --;
                            machine_num.setText("剩余 " + currentNum + " 个");

                        }
                    };
                });

                btn_choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int currentNum = 0;
                        Pattern pattern = Pattern.compile("\\d+");
                        String text = machine_num.getText().toString();
                        Matcher matcher = pattern.matcher(text);
                        currentNum = Integer.parseInt(matcher.group());
                        String url="http://10.0.2.2:8085/equipment/register";
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("num", currentNum);
                            jsonObject.put("username", "zhangsan");
                            jsonObject.put("time", 1400);
                            jsonObject.put("status", true);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RequestQueue requestQueue = Volley.newRequestQueue(TimeActivity.this);
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                try {
                                    String msg = jsonObject.getString("msg");
                                    if(msg.equals("预约成功")){
                                        Toast.makeText(TimeActivity.this, msg, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(TimeActivity.this, "预约出错", Toast.LENGTH_SHORT).show();
                            }
                        });
                        requestQueue.add(jsonObjectRequest);
                    }
                });

            }
        });
    }
}
