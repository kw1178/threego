package com.threego.loginactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static androidx.core.view.GravityCompat.START;

public class NoticeActivity extends AppCompatActivity {
    ImageView iv_menu, iv_close;
    ImageButton ibtn_close;
    ArrayList<String> notice = new ArrayList<>();
    ListView listView;
    TextView tv_1, tv_2, tv_3, tv_4, tv_admin, tv_num,tv_n_title, tv_n_date, tv_contents, tv_date_n, tv_title_n, tv_content, tv_hidden;
    DrawerLayout drawerLayout;
    Button btn_delivery, btn_mypage, btn_ad, btn_money, btn_notice, btn_home;
    StringRequest stringRequest, stringRequest2;
    RequestQueue requestQueue, requestQueue2;
    JSONArray jarr;
    String[] nn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        listView = findViewById(R.id.list);

        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);
        tv_4 = findViewById(R.id.tv_4);
        tv_admin = findViewById(R.id.tv_admin);
        tv_num = findViewById(R.id.tv_num);
        tv_hidden = findViewById(R.id.tv_hidden);
        
        // 내용 숨기기
        tv_n_title = findViewById(R.id.tv_n_title);
        tv_n_title.setVisibility(View.INVISIBLE);
        tv_n_date = findViewById(R.id.tv_n_date);
        tv_n_date.setVisibility(View.INVISIBLE);
        tv_contents = findViewById(R.id.tv_contents);
        tv_contents.setVisibility(View.INVISIBLE);
        tv_date_n = findViewById(R.id.tv_date_n);
        tv_date_n.setVisibility(View.INVISIBLE);
        tv_title_n = findViewById(R.id.tv_title_n);
        tv_title_n.setVisibility(View.INVISIBLE);
        tv_content = findViewById(R.id.tv_content);
        tv_content.setVisibility(View.INVISIBLE);
        iv_close = findViewById(R.id.iv_close);
        iv_close.setVisibility(View.INVISIBLE);

        // 네비게이션 id 값 찾기
        iv_menu = findViewById(R.id.iv_menu);
        ibtn_close = findViewById(R.id.ibtn_close);
        drawerLayout = findViewById(R.id.drawer_layout);
        btn_delivery = findViewById(R.id.btn_delivery);
        btn_mypage = findViewById(R.id.btn_mypage);
        btn_ad = findViewById(R.id.btn_ad);
        btn_money = findViewById(R.id.btn_money);
        btn_notice = findViewById(R.id.btn_notice);
        btn_home = findViewById(R.id.btn_home);

        // 통신 -> notice 화면
        String url ="http://222.102.104.230:8081/threego/notice.do";
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jarr = new JSONArray(response);
                    nn = new String[jarr.length()];
                    for (int i=0,j=jarr.length();i<j;i++){
                        JSONObject jobj = jarr.getJSONObject(i);

                        notice.add("\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+jobj.getString("n_postnum")+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+jobj.getString("n_title")+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+jobj.getString("n_date"));

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.notice_list, notice);
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        NoticeVO noticeVO = new NoticeVO();
                        noticeVO.setN_content(jobj.getString("n_content"));
                        noticeVO.setN_title(jobj.getString("n_title"));
                        noticeVO.setN_date(jobj.getString("n_date"));
                        noticeVO.setN_postnum(jobj.getInt("n_postnum"));

                        tv_n_title.setText(jobj.getString("n_title"));
                        tv_contents.setText(jobj.getString("n_content"));
                        tv_n_date.setText(jobj.getString("n_date"));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> temp = new HashMap<>();
                temp.put("r_id",tv_admin.getText().toString());
                return temp;
            }
        };
        requestQueue.add(stringRequest);


        // DB 연결 필요 -> notice 내용 화면
        String URL = "http://222.102.104.230:8081/threego/noticeDetail.do";
        requestQueue2 = Volley.newRequestQueue(getApplicationContext());

        stringRequest2 = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("changsoo","통신된다.");
                try {
                    jarr = new JSONArray(response);
                    for (int i=0,j=jarr.length();i<j; i++){
                        JSONObject jobj = jarr.getJSONObject(i);
                        NoticeVO noticeVO = new NoticeVO();

                        noticeVO.setN_date(jobj.getString("n_date"));
                        noticeVO.setN_title(jobj.getString("n_title"));
                        noticeVO.setN_content(jobj.getString("n_content"));

                        tv_n_title.setText(noticeVO.getN_title());
                        tv_n_date.setText(noticeVO.getN_date());
                        tv_contents.setText(noticeVO.getN_content());
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> temp = new HashMap<>();

                    temp.put("n_postnum", tv_hidden.getText().toString());

                return temp;
            }
        };



        // listview 선택할때 이동하기, 배경 없애기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setVisibility(View.INVISIBLE);
                tv_1.setVisibility(View.INVISIBLE);
                tv_2.setVisibility(View.INVISIBLE);
                tv_3.setVisibility(View.INVISIBLE);
                tv_4.setVisibility(View.INVISIBLE);

                tv_n_title.setVisibility(View.VISIBLE);
                tv_n_date.setVisibility(View.VISIBLE);
                tv_contents.setVisibility(View.VISIBLE);
                tv_date_n.setVisibility(View.VISIBLE);
                tv_title_n.setVisibility(View.VISIBLE);
                tv_content.setVisibility(View.VISIBLE);
                iv_close.setVisibility(View.VISIBLE);

                // 파라미터 보낼 값 미리 저장
                tv_hidden.setText(position+1+"");
                requestQueue2.add(stringRequest2);
            }
        });
            
        // 버튼 클릭시 listview 등장, 내용 없애기
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.VISIBLE);
                tv_1.setVisibility(View.VISIBLE);
                tv_2.setVisibility(View.VISIBLE);
                tv_3.setVisibility(View.VISIBLE);
                tv_4.setVisibility(View.VISIBLE);

                tv_n_title.setVisibility(View.INVISIBLE);
                tv_n_date.setVisibility(View.INVISIBLE);
                tv_contents.setVisibility(View.INVISIBLE);
                tv_date_n.setVisibility(View.INVISIBLE);
                tv_title_n.setVisibility(View.INVISIBLE);
                tv_content.setVisibility(View.INVISIBLE);
                iv_close.setVisibility(View.INVISIBLE);
            }
        });


        // 네비게이션 바 이동
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });

        btn_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, AdActivity.class);
                startActivity(intent);
            }
        });

        btn_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, MoneyActivity.class);
                startActivity(intent);
            }
        });
        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });

    // 네비게이션 열고 닫기
        iv_menu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawerLayout.openDrawer(START);
        }
    });

        ibtn_close.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawerLayout.closeDrawer(START);
        }
    });

}

    // 네비게이션 설정
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(START)){
            drawerLayout.closeDrawer(START);
        }else {
            super.onBackPressed();
        }
    }
}