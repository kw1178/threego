package com.threego.loginactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static androidx.core.view.GravityCompat.START;

public class ListActivity extends AppCompatActivity {
    ImageView iv_menu;
    ImageButton ibtn_close;
    CalendarView calendarView;
    TextView textView, tv_all;
    DrawerLayout drawerLayout;
    Button btn_delivery, btn_mypage, btn_ad, btn_money, btn_notice, btn_home;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    JSONArray jarr;
    DeliveryVO deliveryVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

        textView = findViewById(R.id.textView);
        tv_all = findViewById(R.id.tv_all);
        textView.setVisibility(View.INVISIBLE);

        iv_menu = findViewById(R.id.iv_menu);
        ibtn_close = findViewById(R.id.ibtn_close);

        drawerLayout = findViewById(R.id.drawer_layout);

        btn_delivery = findViewById(R.id.btn_delivery);
        btn_mypage = findViewById(R.id.btn_mypage);
        btn_ad = findViewById(R.id.btn_ad);
        btn_money = findViewById(R.id.btn_money);
        btn_notice = findViewById(R.id.btn_notice);
        btn_home = findViewById(R.id.btn_home);

        // 네비게이션 바 이동
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });

        btn_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, AdActivity.class);
                startActivity(intent);
            }
        });

        btn_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MoneyActivity.class);
                startActivity(intent);
            }
        });
        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });

        // 통신
        String url = "http://222.102.104.230:8081/threego/delivery.do";

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    try {
                        int sum = 0;
                        jarr = new JSONArray(response);
                        int [] call = new int[jarr.length()];

                        for (int i = 0, j = jarr.length(); i < j; i++) {
                            JSONObject jobj = jarr.getJSONObject(i);
                            deliveryVO = new DeliveryVO();

                            deliveryVO.setDl_date(jobj.getString("dl_date"));
                            deliveryVO.setDl_call(jobj.getInt("dl_call"));
                            deliveryVO.setDl_cooktime(jobj.getString("dl_cooktime"));

                            sum += deliveryVO.getDl_call();



                            String [] all = new String[jarr.length()];

                                all[i] = deliveryVO.getDl_date().substring(3,4);

                            // calendarView 사용하기, calendarView 선택한 경우 반응함.
                        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                                String[] dl_date = new String[jarr.length()];
                                int i = 0;
                                int sum =0;

                                String nowDay = month + 1 + "월 " + dayOfMonth + ", " + year;
                                textView.setVisibility(View.VISIBLE);
                                textView.setText(nowDay);
                            }

                        });
                        Log.v("changsoo", deliveryVO.getDl_call() + "");
                    }
                        tv_all.setText(sum + "원");

                } catch (Exception e) {
                    e.printStackTrace();
                    }
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show(); // 확인용
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        // request 부분
        @Nullable
        @Override   // 리턴타입         메소드이름(매개변수 X) 예외처리
        protected Map<String, String> getParams() throws AuthFailureError {
            // 전송할 데이터 key, value로 셋팅하기!
            Map<String,String> temp = new HashMap<>();
            temp.put("r_id",textView.getText().toString()); // 로그인할 때 아이디로 수정하면 된다.
            return temp;
        }
    };

        requestQueue.add(stringRequest);




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
        if (drawerLayout.isDrawerOpen(START)) {
            drawerLayout.closeDrawer(START);
        } else {
            super.onBackPressed();
        }
    }
}