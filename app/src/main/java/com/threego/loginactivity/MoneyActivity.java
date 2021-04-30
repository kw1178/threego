package com.threego.loginactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MoneyActivity extends AppCompatActivity {
    ImageView iv_menu;
    ImageButton ibtn_close;
    ArrayList<DeliveryVO> money = new ArrayList<>();
    ListView list_money;
    TextView tv_m_date,tv_m_money,tv_m_status, tv_allmoney, tv_rider;
    DrawerLayout drawerLayout;
    Button btn_delivery, btn_mypage, btn_ad, btn_money, btn_notice, btn_home, btn_m_change;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String r_id;
    JSONArray jarr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        list_money = findViewById(R.id.list_review);

        tv_m_date = (TextView) findViewById(R.id.tv_m_date);
        tv_m_money = (TextView)findViewById(R.id.tv_m_money);
        tv_m_status = (TextView)findViewById(R.id.tv_m_status);
        tv_allmoney = findViewById(R.id.tv_allmoney);
        tv_rider = findViewById(R.id.tv_rider);

        iv_menu = findViewById(R.id.iv_menu);
        ibtn_close = findViewById(R.id.ibtn_close);

        drawerLayout = findViewById(R.id.drawer_layout);

        btn_delivery = findViewById(R.id.btn_delivery);
        btn_mypage = findViewById(R.id.btn_mypage);
        btn_ad = findViewById(R.id.btn_ad);
        btn_money = findViewById(R.id.btn_money);
        btn_notice = findViewById(R.id.btn_notice);
        btn_home = findViewById(R.id.btn_home);
        btn_m_change = findViewById(R.id.btn_m_change);

        Intent intent = getIntent();
        r_id = intent.getExtras().getString("r_id");

        // 콜비 받아오는 통신
        String url = "http://222.102.104.230:8081/threego/call.do";
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jarr = new JSONArray(response);
                    int sum = 0;
                    for (int i=0, j=jarr.length(); i<j; i++){
                        JSONObject jobj = jarr.getJSONObject(i);

                        DeliveryVO deliveryVO = new DeliveryVO();
                        deliveryVO.setDl_date(jobj.getString("dl_date"));
                        deliveryVO.setDl_call(jobj.getInt("dl_call"));
                        sum += deliveryVO.getDl_call();

                        // listView 연결
                        money.add(deliveryVO);
                        MoneyAdapter adapter = new MoneyAdapter(getApplicationContext(), R.layout.money_list, money);
                        list_money.setAdapter(adapter);
                    }
                    tv_allmoney.setText(sum+"");
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
                temp.put("r_id",r_id);
                return temp;
            }
        };

        requestQueue.add(stringRequest);

        // 버튼 클릭시 출금안내 및 페이지 이동
        btn_m_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(tv_allmoney.getText().toString()) >=30000){
                    Toast.makeText(getApplicationContext(),"출금페이지로 이동합니다.",Toast.LENGTH_SHORT).show();

                    Intent intent1 = new Intent(MoneyActivity.this,OutputActivity.class);
                    intent1.putExtra("allmoney",tv_allmoney.getText().toString());
                    intent1.putExtra("r_id",r_id);
                    startActivity(intent1);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"30,000원 이상부터 출금가능합니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });




        // 네비게이션 바 이동
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoneyActivity.this, MainActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        btn_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoneyActivity.this, ListActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoneyActivity.this, MypageActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        btn_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoneyActivity.this, AdActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        btn_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoneyActivity.this, MoneyActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });
        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoneyActivity.this, NoticeActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

    // 네비게이션 열고 닫기
        iv_menu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawerLayout.openDrawer(START);
            Intent intent = getIntent();
            r_id = intent.getExtras().getString("r_id");
            tv_rider.setText(r_id+"님 환영합니다.");
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