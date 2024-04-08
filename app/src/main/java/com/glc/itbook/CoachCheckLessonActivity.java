package com.glc.itbook;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CoachCheckLessonActivity extends AppCompatActivity{


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);
//        getWindow().addFlags(WindowManager.LayoutParams.);
//        getWindow().addFlags(WindowManager.LayoutParams.);
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