package com.threego.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

import static androidx.core.view.GravityCompat.START;

public class AdActivity extends AppCompatActivity {
    ImageView iv_menu;
    ImageButton ibtn_close;
    TextView tv_money, tv_ad_name;
    ProgressBar pro;
    //VideoView videoView;
    MediaController mediaController;
    //String videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"; // 경로, 확장자까지 필요
    DrawerLayout drawerLayout;
    Button btn_delivery, btn_mypage, btn_ad, btn_money, btn_notice, btn_change, btn_home;

    // 하단 광고 리스트뷰
    ListView lv;
    AdAdapter adapter;
    ArrayList<AdVO> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        tv_ad_name = findViewById(R.id.tv_ad_name);
        tv_money = findViewById(R.id.tv_money);

        pro = findViewById(R.id.pro);

        //videoView = (VideoView) findViewById(R.id.videoView3);

        btn_change = findViewById(R.id.btn_change);

        iv_menu = findViewById(R.id.iv_menu);
        ibtn_close = findViewById(R.id.ibtn_close);

        drawerLayout = findViewById(R.id.drawer_layout);

        btn_delivery = findViewById(R.id.btn_delivery);
        btn_mypage = findViewById(R.id.btn_mypage);
        btn_ad = findViewById(R.id.btn_ad);
        btn_money = findViewById(R.id.btn_money);
        btn_notice = findViewById(R.id.btn_notice);
        btn_home = findViewById(R.id.btn_home);

        // 리스트뷰 adVO, adAdapter, adlist
        lv = findViewById(R.id.listView);
        list.add(new AdVO("https://i.imgur.com/NYJ9bZr.jpg", "고양이", "노르웨이숲", "배달 중"));
        list.add(new AdVO("https://i.imgur.com/NYJ9bZr.jpg", "고양이", "코숏 기여워", ""));
        list.add(new AdVO("https://i.imgur.com/NYJ9bZr.jpg", "고양이", "애교쟁이 샴", ""));
        list.add(new AdVO("https://i.imgur.com/NYJ9bZr.jpg", "고양이", "꾹꾹이 얍",  ""));

        adapter = new AdAdapter(getApplication(), R.layout.ad_list, list);
        lv.setAdapter(adapter);

        // 네비게이션 바 이동
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });

        btn_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdActivity.this, AdActivity.class);
                startActivity(intent);
            }
        });

        btn_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdActivity.this, MoneyActivity.class);
                startActivity(intent);
            }
        });
        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });
        // DB 연동 필요
        tv_ad_name.setText("BURGER KING 기네스 와퍼");
        tv_money.setText("2350");

        // progress bar 바꾸기, 알고리즘 필요
        pro.setProgress(35);

        // videoView 사용하기
//        mediaController = new MediaController(this); // 미디어 제어 부분
//        mediaController.setAnchorView(videoView);   // videoView에 연결
//        Uri uri = Uri.parse(videoUrl);
//        videoView.setMediaController(mediaController);  // 미디어 제어 부분 세팅
//        videoView.setVideoURI(uri); // url 연결
//        videoView.start();

        // 이미지뷰



        // 예치금 전환 버튼 클릭시 Toast 메시지 띄우기
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"예치금이 전환되었습니다. ^^",Toast.LENGTH_SHORT).show();
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