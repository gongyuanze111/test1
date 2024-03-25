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

public class AddCoachActivity extends AppCompatActivity {
private EditText addCoachName;
private EditText addGender;
private EditText addAge;
private EditText addSpecialty;
private EditText addInfo;
private Button addBtn;
private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coach);
        addCoachName=findViewById(R.id.edt_addCoachName);
        addGender=findViewById(R.id.edt_addCoachGender);
        addAge=findViewById(R.id.edt_addCoachAge);
        addSpecialty=findViewById(R.id.edt_addCoachSpecialty);
        addInfo=findViewById(R.id.edt_addCoachInfo);
        addBtn=findViewById(R.id.btn_Add);
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
               String name = addCoachName.getText().toString();
               String gender = addGender.getText().toString();
               String age= addAge.getText().toString();
               String specialty = addSpecialty.getText().toString();
               String info = addInfo.getText().toString();
                if(name.equals("")||gender.equals("")||age.equals("")||info.equals("")||specialty.equals("")){
                    Toast.makeText(AddCoachActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        String name1 = URLEncoder.encode(name, "utf-8");
                        String gender1 = URLEncoder.encode(gender, "utf-8");
                        int age1= Integer.parseInt(URLEncoder.encode(age, "utf-8"));
                        String specialty1 = URLEncoder.encode(specialty, "utf-8");
                        String info1 = URLEncoder.encode(info, "utf-8");
                        Add(name1,gender1,age1,specialty1,info1);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

           }
        });




    }
    private void Add(String name,String gender,int age,String specialty,String info){
        JSONObject jsonObject=new JSONObject();
        String url="http://10.0.2.2:8085/coach/insertCoach?name="+name+"&gender="+gender+"&age="+age+"&specialty="+specialty+"&info="+info+"";
        RequestQueue requestQueue= Volley.newRequestQueue(AddCoachActivity.this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String info1 = jsonObject.getString("info");
                    if(info1.equals("添加成功")){
                        Toast.makeText(AddCoachActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(AddCoachActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("错误", volleyError.toString());
                Toast.makeText(AddCoachActivity.this, "网络失败", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}
