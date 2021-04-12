package com.threego.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static androidx.core.view.GravityCompat.START;

public class NoticeActivity extends AppCompatActivity {
    ImageView iv_menu;
    ImageButton ibtn_close;
    ArrayList<NoticeVO> notice = new ArrayList<>();
    ListView listView;
    Fragment_notice fragment_notice;
    TextView tv_1, tv_2, tv_3, tv_4;
    DrawerLayout drawerLayout;
    Button btn_delivery, btn_mypage, btn_ad, btn_money, btn_notice, btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        listView = findViewById(R.id.list);

        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);
        tv_4 = findViewById(R.id.tv_4);

        fragment_notice = new Fragment_notice();
        
        // DB 연결 필요
        notice.add(new NoticeVO(1, "안녕하세요.", "2021-04-09"));
        notice.add(new NoticeVO(2, "비밀번호 변경안내", "2021-04-10"));
        notice.add(new NoticeVO(3, "ㅎㅎ", "2021-04-11"));

        NoticeAdapter adapter = new NoticeAdapter(getApplicationContext(), R.layout.notice_list, notice);
        listView.setAdapter(adapter);

        // listview 선택할때 이동하기, 배경 없애기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getSupportFragmentManager().beginTransaction().replace(R.id.list_notice, fragment_notice).commit();
                listView.setVisibility(View.INVISIBLE);
                tv_1.setVisibility(View.INVISIBLE);
                tv_2.setVisibility(View.INVISIBLE);
                tv_3.setVisibility(View.INVISIBLE);
                tv_4.setVisibility(View.INVISIBLE);
            }
        });

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