package com.threego.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static androidx.core.view.GravityCompat.START;

public class MoneyActivity extends AppCompatActivity {
    ImageView iv_menu;
    ImageButton ibtn_close;
    ArrayList<MoneyVO> money = new ArrayList<>();
    ListView list_money;
    TextView tv_m_date,tv_m_money,tv_m_status;
    DrawerLayout drawerLayout;
    Button btn_delivery, btn_mypage, btn_ad, btn_money, btn_notice, btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        list_money = findViewById(R.id.list_review);

        tv_m_date = (TextView) findViewById(R.id.tv_m_date);
        tv_m_money = (TextView)findViewById(R.id.tv_m_money);
        tv_m_status = (TextView)findViewById(R.id.tv_m_status);

        // DB 연동 필요!
        money.add(new MoneyVO("2021-04-09", -2000, "출금"));
        money.add(new MoneyVO("2021-04-10", 2500, "입금"));
        money.add(new MoneyVO("2021-04-11", 2500, "입금"));
        money.add(new MoneyVO("2021-04-12", -1500, "출금"));
        money.add(new MoneyVO("2021-04-13", 5000, "입금"));
        
        // listView 연결
        MoneyAdapter adapter = new MoneyAdapter(getApplicationContext(), R.layout.money_list, money);
        list_money.setAdapter(adapter);

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
                Intent intent = new Intent(MoneyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoneyActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoneyActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });

        btn_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoneyActivity.this, AdActivity.class);
                startActivity(intent);
            }
        });

        btn_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoneyActivity.this, MoneyActivity.class);
                startActivity(intent);
            }
        });
        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoneyActivity.this, NoticeActivity.class);
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