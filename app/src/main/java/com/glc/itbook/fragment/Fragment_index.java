package com.glc.itbook.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.glc.itbook.MessageActivity;
import com.glc.itbook.R;
import com.glc.itbook.XiangQingActivity;
import com.glc.itbook.bean.Book;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
// 添加课程、查看课程（面向所有人）
public class Fragment_index extends Fragment {
    private ListView mlistView;
    private BaseAdapter adapter;
    private TextView tvShangye;
    private TextView tvNext;
    private EditText edtYeMa;
    private Button btnTiaozhuan;
    private Button comment;
    private int page = 1;
    private TextView tvCurrentPage;
    private int totalPage;
    private TextView bookName;
    private Button souSuo;
    private String role;
    private String name;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_index, null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        role = getArguments().getString("role");
        name = getArguments().getString("username");
        if(role.equals("Admin")) {
            Toast.makeText(getActivity(), "健身房有XX器材待报修，请及时查看", Toast.LENGTH_LONG).show();
        }
        else if (role.equals("Coach")){
            Toast.makeText(getActivity(), "您的下一节课将于x小时x分钟后开始，请您做好准备。", Toast.LENGTH_LONG).show();
        }
        else {
            if (name.equals("Member1"))
                Toast.makeText(getActivity(), "您今日有18:00开始的预约，请及时前往健身", Toast.LENGTH_LONG).show();
            else if (name.equals("Member2"))
                Toast.makeText(getActivity(), "您14:20的预约即将开始，请尽快前往健身", Toast.LENGTH_LONG).show();
            else if (name.equals("Member3"))
                Toast.makeText(getActivity(), "您12:00的预约已过期，资源已释放，积分-3", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getActivity(), "您尚未开始预约，运动起来吧！", Toast.LENGTH_LONG).show();
        }
        mlistView = view.findViewById(R.id.menu_list);
        tvNext = view.findViewById(R.id.tv_next);
        tvShangye = view.findViewById(R.id.tv_shangye);
        tvCurrentPage = view.findViewById(R.id.tv_currentPage);
        edtYeMa = view.findViewById(R.id.edt_yema);
        btnTiaozhuan = view.findViewById(R.id.btn_tiaozhuan);
        bookName=view.findViewById(R.id.edt_bookName);
        souSuo=view.findViewById(R.id.btn_imgSousuo);
        comment=view.findViewById(R.id.btn_post_comment);



        String bookNameStr = bookName.getText().toString().trim();
        selectFenYe(bookNameStr,1);
        //上一页
        tvShangye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page > 1) {
                    String bookNameStr = bookName.getText().toString().trim();
                    try {
                        String encode = URLEncoder.encode(bookNameStr, "utf-8");
                        selectFenYe(encode,--page);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), "当前第一页", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //下一页
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page < totalPage) {
                    String bookNameStr = bookName.getText().toString().trim();
                    try {
                        String encode = URLEncoder.encode(bookNameStr, "utf-8");
                        selectFenYe(encode,++page);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), "到达尾页", Toast.LENGTH_SHORT).show();
                }
            }
        });
    //跳转页码
        btnTiaozhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = Integer.parseInt(edtYeMa.getText().toString().trim());
                if (page > 0 && page <= totalPage) {

                    String bookNameStr = bookName.getText().toString().trim();
                    try {
                        String encode = URLEncoder.encode(bookNameStr, "utf-8");
                        selectFenYe(encode,page);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), "超过最大页数", Toast.LENGTH_SHORT).show();

                }
            }
        });

        //搜索按钮
        souSuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookNameStr = bookName.getText().toString().trim();
                try {
                    String encode = URLEncoder.encode(bookNameStr, "utf-8");
                    selectFenYe(encode,1);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });
//        comment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), MessageActivity.class));
//            }
//        });

    }

    //分页搜索显示的数据 -- 默认显示全部
    private void selectFenYe(String name,int page){
        JSONObject jsonObject = new JSONObject();
        String url = "http://10.0.2.2:8085/item/findByPageName?name="+name+"&currentPage="+page+"&pageSize=10";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                Gson gson = new Gson();
                final Book book = gson.fromJson(jsonObject.toString(), Book.class);
                int currentPage = book.getCurrentPage();
                tvCurrentPage.setText("第" + currentPage + "页");
                totalPage = book.getTotalPage();
                adapter = new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return book.getItems().size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }

                    @Override
                    public View getView(final int i, View view, ViewGroup viewGroup) {
                        view = View.inflate(getContext(), R.layout.item_booklist, null);
                        ImageView imageView = view.findViewById(R.id.item_image);
                        TextView name = view.findViewById(R.id.item_bookName);
                        final TextView user = view.findViewById(R.id.item_bookUser);
                        TextView info = view.findViewById(R.id.item_bookInfo);
                        TextView down = view.findViewById(R.id.item_bookDown);
                        name.setText("标题:"+book.getItems().get(i).getBook_name());
                        user.setText("用户:"+book.getItems().get(i).getBook_author());
                        info.setText("内容:"+book.getItems().get(i).getBook_info());

                        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent=new Intent(getActivity(), XiangQingActivity.class);
                                Bundle bundle=new Bundle();
                                bundle.putString("name",book.getItems().get(i).getBook_name());
                                bundle.putString("author",book.getItems().get(i).getBook_author());
                                bundle.putString("jianjie",book.getItems().get(i).getBook_info());
                                bundle.putString("down",book.getItems().get(i).getBook_download());
                                bundle.putString("img",book.getItems().get(i).getBook_img());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });

                        Glide.with(getContext()).load(book.getItems().get(i).getBook_img()).into(imageView);

                        return view;
                    }
                };
                mlistView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "网络出错", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
