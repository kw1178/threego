package com.threego.loginactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

import static androidx.core.view.GravityCompat.START;

public class ListActivity extends AppCompatActivity {
    ImageView iv_menu;
    ImageButton ibtn_close;
    CalendarView calendarView;
    TextView textView, tv_all, tv_choice, tv_c_date, tv_rider;
    DrawerLayout drawerLayout;
    Button btn_delivery, btn_mypage, btn_ad, btn_money, btn_notice, btn_home, btn_push;
    RequestQueue requestQueue, requestQueue2;
    StringRequest stringRequest, stringRequest2;
    JSONArray jarr;
    DeliveryVO deliveryVO;
    String r_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        tv_rider = findViewById(R.id.tv_rider);
        textView = findViewById(R.id.textView);
        tv_all = findViewById(R.id.tv_all);
        textView.setVisibility(View.INVISIBLE);

        tv_c_date = findViewById(R.id.tv_c_date);
        tv_c_date.setVisibility(View.INVISIBLE);

        tv_choice=findViewById(R.id.tv_choice2);

        iv_menu = findViewById(R.id.iv_menu);
        ibtn_close = findViewById(R.id.ibtn_close);

        drawerLayout = findViewById(R.id.drawer_layout);

        btn_delivery = findViewById(R.id.btn_delivery);
        btn_mypage = findViewById(R.id.btn_mypage);
        btn_ad = findViewById(R.id.btn_ad);
        btn_money = findViewById(R.id.btn_money);
        btn_notice = findViewById(R.id.btn_notice);
        btn_home = findViewById(R.id.btn_home);
        btn_push = findViewById(R.id.btn_push);

        Intent intent = getIntent();
        r_id = intent.getExtras().getString("r_id");
        textView.setText(r_id);

        // ??????????????? ??? ??????
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        btn_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, ListActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MypageActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        btn_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, AdActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        btn_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MoneyActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });
        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, NoticeActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        // ?????? ????????? ?????? ?????? ?????? ??????
        String url = "http://222.102.104.230:8081/threego/delivery.do";

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    int sum = 0;
                        jarr = new JSONArray(response);

                    for (int i = 0, j = jarr.length(); i < j; i++) {
                            JSONObject jobj = jarr.getJSONObject(i);
                            deliveryVO = new DeliveryVO();

                            deliveryVO.setDl_date(jobj.getString("dl_date"));
                            deliveryVO.setDl_call(Integer.parseInt(jobj.getString("dl_call")));

                            sum += deliveryVO.getDl_call();

                    }
                    tv_choice.setText(sum + "???");


                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show(); // ?????????
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        // request ??????
        @Nullable
        @Override   // ????????????         ???????????????(???????????? X) ????????????
        protected Map<String, String> getParams() throws AuthFailureError {
            // ????????? ????????? key, value??? ????????????!
            Map<String,String> temp = new HashMap<>();
            temp.put("r_id",r_id); // ???????????? ??? ???????????? ???????????? ??????.
            temp.put("dl_date",tv_c_date.getText().toString());
            return temp;
        }
    };

        // calendarView ????????????, calendarView ????????? ?????? ?????????.
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String nowDay = year+"-"+(month+1)+"-"+dayOfMonth;
                tv_c_date.setText(nowDay);

                requestQueue.add(stringRequest);    // ????????? ????????? Parameter ??? ?????????.

            }
        });

        // ??? ????????? ?????? ?????? ??????
        String URL = "http://222.102.104.230:8081/threego/call.do";
        requestQueue2 = Volley.newRequestQueue(getApplicationContext());
        stringRequest2 = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    int sum = 0;
                    jarr = new JSONArray(response);
                    for (int i=0, j=jarr.length(); i<j; i++){
                        JSONObject jobj = jarr.getJSONObject(i);

                        DeliveryVO deliveryVO = new DeliveryVO();
                        deliveryVO.setDl_call(jobj.getInt("dl_call"));

                        sum += deliveryVO.getDl_call();
                    }
                    tv_all.setText(sum+"???");
                }catch (Exception e){

                }
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
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
                temp.put("r_id",r_id); // ????????? ?????? ???????????? ??????????????????.

                return temp;
            }
        };

        // ?????? ????????? ?????? ?????? ??? ??????
        btn_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue2.add(stringRequest2);
            }
        });


        // ??????????????? ?????? ??????
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(START);
                Intent intent = getIntent();
                r_id = intent.getExtras().getString("r_id");
                tv_rider.setText(r_id+"??? ???????????????.");
            }
        });

        ibtn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(START);
            }
        });

    }

    // ??????????????? ??????
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(START)) {
            drawerLayout.closeDrawer(START);
        } else {
            super.onBackPressed();
        }
    }
}