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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_ben);
        sign=findViewById(R.id.btn_check_in);
//        fanhui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BanBenActivity.this, "签到成功！积分+2", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.fragment_dongtai);
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
