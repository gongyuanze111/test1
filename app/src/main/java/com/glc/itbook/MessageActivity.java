package com.glc.itbook;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MessageActivity extends AppCompatActivity {
    private EditText addName;
    private EditText addimg;
    private EditText addInfo;
    private EditText addLianjie;
    private Button addBtn;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);
        addName=findViewById(R.id.edt_addTitle);
        addInfo =findViewById(R.id.edt_addInfo);
        addLianjie=findViewById(R.id.edt_addLianjie);
        addimg=findViewById(R.id.edt_addImg);
        addBtn=findViewById(R.id.btn_post_comment);
        img=findViewById(R.id.img_fanhuiAdd);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addName.getText().toString();
                String img = addimg.getText().toString();
                String author= "大宝";
                String info = addInfo.getText().toString();
                String lianjie = addLianjie.getText().toString();
                if(name.equals("")||img.equals("")||author.equals("")||info.equals("")){
                    Toast.makeText(MessageActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        String name1 = URLEncoder.encode(name, "utf-8");
                        String img1 = URLEncoder.encode(img, "utf-8");
                        String  author1= URLEncoder.encode(author, "utf-8");
                        String info1 = URLEncoder.encode(info, "utf-8");
                        String lianjie1 = URLEncoder.encode(lianjie, "utf-8");
                        Add(name1,img1,author1,info1,lianjie1);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

            }
        });




    }
    private void Add(String name,String img,String author,String info,String lianjie){
        JSONObject jsonObject=new JSONObject();
        String url="http://10.0.2.2:8085/item/insertItem?book_name="+name+"&book_img="+img+"&book_author="+author+"&book_info="+info+"&book_download="+lianjie+"";
        RequestQueue requestQueue= Volley.newRequestQueue(MessageActivity.this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String info1 = jsonObject.getString("info");
                    if(info1.equals("添加成功")){
                        Toast.makeText(MessageActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MessageActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("错误", volleyError.toString());
                Toast.makeText(MessageActivity.this, "网络失败", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}
