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

public class AddPasswordActivity extends AppCompatActivity {
private EditText addLessonName;
private EditText addCoachName;
private EditText addLessonContent;
private EditText addInfo;
private Button addBtn;
private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
//        addCoachName=findViewById(R.id.addCoachName);
//        addLessonName=findViewById(R.id.edt_addLessonName);
//        addLessonContent=findViewById(R.id.edt_addLessonContent);
//        addInfo=findViewById(R.id.edt_addLessonInfo);
        addBtn = findViewById(R.id.lessonSign);
//        img=findViewById(R.id.img_fanhuiAdd);

//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               finish();
//            }
//        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddPasswordActivity.this, "添加成功", Toast.LENGTH_LONG).show();
            }
        });
    }




}
