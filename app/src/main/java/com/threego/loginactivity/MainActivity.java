package com.threego.loginactivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import static androidx.core.view.GravityCompat.START;

public class MainActivity extends AppCompatActivity {
    ImageView iv_menu;
    ImageButton ibtn_close;
    Fragment_new fragment_new;
    Fragment_choice fragment_choice;
    Fragment_ok fragment_ok;
    BottomNavigationView bv;
    DrawerLayout drawerLayout;
    Button btn_delivery, btn_mypage, btn_ad, btn_money, btn_notice, btn_home;
    TextView tv_count_new, tv_count_choice, tv_count_ok, tv_new, tv_choice, tv_ok, tv_rider;
    RequestQueue requestQueue,requestQueue2, requestQueue3;
    StringRequest stringRequest, stringRequest2, stringRequest3;
    JSONArray jarr;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    String r_id;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        fragment_new = new Fragment_new();
        fragment_choice = new Fragment_choice();
        fragment_ok = new Fragment_ok();
        iv_menu = findViewById(R.id.iv_menu);

        ibtn_close = findViewById(R.id.ibtn_close);

        drawerLayout = findViewById(R.id.drawer_layout);

        btn_delivery = findViewById(R.id.btn_delivery);
        btn_mypage = findViewById(R.id.btn_mypage);
        btn_ad = findViewById(R.id.btn_ad);
        btn_money = findViewById(R.id.btn_money);
        btn_notice = findViewById(R.id.btn_notice);
        btn_home = findViewById(R.id.btn_home);

        tv_count_new = findViewById(R.id.tv_count_new);
        tv_count_choice = findViewById(R.id.tv_count_choice);
        tv_count_ok = findViewById(R.id.tv_count_ok);

        tv_new = findViewById(R.id.tv_new2);
        tv_choice = findViewById(R.id.tv_choice2);
        tv_ok = findViewById(R.id.tv_ok2);

        tv_rider = findViewById(R.id.tv_rider);

        // ??????????????? ?????????
        String url ="http://222.102.104.230:8081/threego/deliveryAll.do";
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    jarr = new JSONArray(response);
                    for (int i=0,j=jarr.length();i<j;i++){
                        tv_count_new.setText(jarr.length()+"");
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
                Intent intent = getIntent();
                r_id = intent.getExtras().getString("r_id");
                temp.put("dl_status",tv_new.getText().toString());
                temp.put("r_id",r_id);
                editor.putString("ID", r_id);   // fragment??? ??? ????????????
                editor.commit();
                return temp;
            }
        };



        requestQueue2 = Volley.newRequestQueue(getApplicationContext());
        stringRequest2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jarr = new JSONArray(response);
                    for (int i=0,j=jarr.length();i<j;i++){

                        tv_count_choice.setText(jarr.length()+"");
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
                Intent intent = getIntent();
                r_id = intent.getExtras().getString("r_id");
                temp.put("dl_status",tv_choice.getText().toString());
                temp.put("r_id",r_id);
                return temp;
            }
        };



        requestQueue3 = Volley.newRequestQueue(getApplicationContext());
        stringRequest3 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jarr = new JSONArray(response);
                    for (int i=0,j=jarr.length();i<j;i++){

                        tv_count_ok.setText(jarr.length()+"");
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
                Intent intent = getIntent();
                r_id = intent.getExtras().getString("r_id");
                temp.put("dl_status",tv_ok.getText().toString());
                temp.put("r_id",r_id);
                return temp;
            }
        };




        // ??????????????? ??? ??????
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });
        btn_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MypageActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        btn_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        btn_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoneyActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });
        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        // fragment ???????????? ????????????
        getSupportFragmentManager().beginTransaction().replace(R.id.list, fragment_new).commit();

        bv = findViewById(R.id.bv);

        bv.setItemIconTintList(null);

        bv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                tv_count_new.setText("");
                tv_count_choice.setText("");
                tv_count_ok.setText("");
                switch (item.getItemId()){
                    case R.id.menu_new:
                        requestQueue.add(stringRequest);
                        getSupportFragmentManager().beginTransaction().replace(R.id.list, fragment_new).commit();
                        break;
                    case R.id.menu_choice:
                        requestQueue2.add(stringRequest2);
                        getSupportFragmentManager().beginTransaction().replace(R.id.list, fragment_choice).commit();
                        break;
                    case R.id.menu_ok:
                        requestQueue3.add(stringRequest3);
                        getSupportFragmentManager().beginTransaction().replace(R.id.list, fragment_ok).commit();
                        break;
                }
              return true;
            }
        });



        // ??????????????? ?????? ??????
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(START);
                Intent intent = getIntent();
                r_id = intent.getExtras().getString("r_id");
                tv_rider.setText(r_id+"??? ???????????????!");
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
        if (drawerLayout.isDrawerOpen(START)){
            drawerLayout.closeDrawer(START);
        }else {
            super.onBackPressed();
        }
    }
}
