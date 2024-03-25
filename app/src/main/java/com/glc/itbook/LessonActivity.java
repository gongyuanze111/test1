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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.glc.itbook.bean.Book;
import com.glc.itbook.fragment.Fragment_dongtai;
import com.glc.itbook.fragment.Fragment_index;
import com.glc.itbook.fragment.Fragment_wode;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LessonActivity extends AppCompatActivity{
    private ImageView dumbbell;
    private TextView dumbbell_num;

    private ImageView treadmill;
    private TextView treadmill_num;

    private ImageView exercisebike;
    private TextView exercisebike_num;

    private ImageView yoga_mat;
    private TextView yoga_mat_num;
    private TextView tvCurrentPage;
    private int totalPage;
    private ImageView resistance_band;
    private TextView resistance_band_num;

    private ImageView machine;
    private ImageView machine_clicked;
    private TextView machine_num;
    private Button btn_choose;
    private TextView time_1500;
    private TextView time_1800;
    private ListView coachList;
    private BaseAdapter adapter;
    private Button confirm;
    private Button confirm2;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_list);
//        getWindow().addFlags(WindowManager.LayoutParams.);
//        getWindow().addFlags(WindowManager.LayoutParams.);
        time_1500 = findViewById(R.id.time_1500);
        time_1800 = findViewById(R.id.time_1800);
        time_1500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_coach_list1);
                //透明状态栏          
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                confirm = findViewById(R.id.confirm);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(LessonActivity.this, "课程预约成功", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
//                machine.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view){
//                        machine.setVisibility(View.INVISIBLE);
//                        machine_clicked.setVisibility(View.VISIBLE);
//                        String text = machine_num.getText().toString();
//                        int currentNum = 0;
//                        Pattern pattern = Pattern.compile("\\d+");
//                        Matcher matcher = pattern.matcher(text);
//                        if (matcher.find()) {
//                            currentNum = Integer.parseInt(matcher.group());
//                            currentNum --;
//                            machine_num.setText("剩余 " + currentNum + " 个");
//
//                        }
//                    };
//                });
//
//                btn_choose.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        int currentNum = 0;
//                        Pattern pattern = Pattern.compile("\\d+");
//                        String text = machine_num.getText().toString();
//                        Matcher matcher = pattern.matcher(text);
//                        currentNum = Integer.parseInt(matcher.group());
//                        String url="http://10.0.2.2:8085/lesson/add";
//                        JSONObject jsonObject = new JSONObject();
//                        try {
//                            jsonObject.put("num", currentNum);
//                            jsonObject.put("member_id", 1);
//                            jsonObject.put("coach_id", 1);
//                            jsonObject.put("time", 1400);
//                            jsonObject.put("status", "待上课");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        RequestQueue requestQueue = Volley.newRequestQueue(LessonActivity.this);
//                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject jsonObject) {
//                                try {
//                                    String msg = jsonObject.getString("msg");
//                                    if(msg.equals("预约成功")){
//                                        Toast.makeText(LessonActivity.this, msg, Toast.LENGTH_SHORT).show();
//                                    }
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError volleyError) {
//                                Toast.makeText(LessonActivity.this, "预约出错", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        requestQueue.add(jsonObjectRequest);
//                    }
//                });
//
//            }
//        });
//    }
//    private  void selectFenYe(String name,int page){
//        JSONObject jsonObject = new JSONObject();
//        String url = "http://10.0.2.2:8085/lesson/findByPageName?name="+name+"&currentPage="+page+"&pageSize=10";
//        RequestQueue requestQueue = Volley.newRequestQueue(LessonActivity.this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//
//                Gson gson = new Gson();
//                final Book book = gson.fromJson(jsonObject.toString(), Book.class);
//                int currentPage = book.getCurrentPage();
//                tvCurrentPage.setText("第" + currentPage + "页");
//                totalPage = book.getTotalPage();
//                adapter = new BaseAdapter() {
//                    @Override
//                    public int getCount() {
//                        return book.getItems().size();
//                    }
//
//                    @Override
//                    public Object getItem(int i) {
//                        return null;
//                    }
//
//                    @Override
//                    public long getItemId(int i) {
//                        return 0;
//                    }
//
//                    @Override
//                    public View getView(final int i, View view, ViewGroup viewGroup) {
//                        view = View.inflate(UpdateActity.this, R.layout.item_booklist2, null);
//                        ImageView imageView = view.findViewById(R.id.item_image);
//                        TextView name = view.findViewById(R.id.item_bookName);
//                        final TextView user = view.findViewById(R.id.item_bookUser);
//                        TextView info = view.findViewById(R.id.item_bookInfo);
//                        TextView update = view.findViewById(R.id.item_bookUpdate);
//                        final TextView delete = view.findViewById(R.id.item_bookDelete);
//                        name.setText("书名:"+book.getItems().get(i).getBook_name());
//                        user.setText("作者:"+book.getItems().get(i).getBook_author());
//                        info.setText("简介:"+book.getItems().get(i).getBook_info());
//                        update.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                int id = book.getItems().get(i).getId();
//                                String name= book.getItems().get(i).getBook_name();
//                                String shutu= book.getItems().get(i).getBook_img();
//                                String author=book.getItems().get(i).getBook_author();
//                                String info=book.getItems().get(i).getBook_info();
//                                String lianjie=book.getItems().get(i).getBook_download();
//                                Intent intent=new Intent(UpdateActity.this,UpdateItemActivity.class);
//                                Bundle bundle=new Bundle();
//                                bundle.putString("name",name);
//                                bundle.putString("id", String.valueOf(id));
//                                bundle.putString("shutu",shutu);
//                                bundle.putString("author",author);
//                                bundle.putString("info",info);
//                                bundle.putString("lianjie",lianjie);
//                                intent.putExtras(bundle);
//                                startActivity(intent);
//                            }
//                        });
//                        delete.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                int id = book.getItems().get(i).getId();
//                                deleteInfo(id);
//                                book.getItems().remove(i);
//                                adapter.notifyDataSetChanged();
//
//                            }
//                        });
//                        Glide.with(UpdateActity.this).load(book.getItems().get(i).getBook_img()).into(imageView);
//
//                        return view;
//                    }
//                };
//                mlistView.setAdapter(adapter);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(UpdateActity.this, "网络出错", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//    }
//
//    //删除请求
//    private void  deleteInfo(int id){
//        JSONObject jsonObject = new JSONObject();
//        String url="http://10.0.2.2:8085/item/deleteItem/?id="+id+"";
//        RequestQueue requestQueue=Volley.newRequestQueue(UpdateActity.this);
//        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                try {
//                    String info = jsonObject.getString("info");
//                    if(info.equals("删除成功")){
//                        Toast.makeText(UpdateActity.this, "删除成功", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(UpdateActity.this, "删除失败", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//    }