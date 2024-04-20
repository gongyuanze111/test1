package com.glc.itbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class BanBenActivity extends AppCompatActivity {
private ImageView fanhui;
private TextView lianZhang;
private TextView lianGao;
private Button sign;
private String username;
private Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_ben);
        sign=findViewById(R.id.btn_check_in);
        username = getIntent().getStringExtra("username");
//        fanhui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.equals("Member1")) {
//                    setContentView(R.layout.activity_signup);
//                    signup = findViewById(R.id.equipmentSign);
//                    signup.setText("签到");
                    setContentView(R.layout.activity_signup_2);
                    signup = findViewById(R.id.lessonSign);
                    signup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            Toast.makeText(BanBenActivity.this, "签到成功！积分+5", Toast.LENGTH_SHORT).show();
                            signup.setText("已签到");
                        }
                    });
//                    signup.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view1) {
//                            Toast.makeText(BanBenActivity.this, "签到成功！积分+3", Toast.LENGTH_SHORT).show();
//                            signup.setText("已签到");
//                        }
//                    });
                } else if (username.equals("Member2")) {
                    setContentView(R.layout.activity_signup_2);
                    signup = findViewById(R.id.lessonSign);
                    signup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            Toast.makeText(BanBenActivity.this, "签到成功！积分+5", Toast.LENGTH_SHORT).show();
                            signup.setText("已签到");
                        }
                    });
                } else
                    Toast.makeText(BanBenActivity.this, "签到成功！积分+1", Toast.LENGTH_SHORT).show();
                sign.setText("已签到");

            }
        });
//        lianGao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                //intent 动作
//                intent.setAction("android.intent.action.VIEW");
//                //打开的网址
//                intent.setData(Uri.parse("http://www.gaolicong.xyz/"));
//                startActivity(intent);
//            }
//        });
    }
}
