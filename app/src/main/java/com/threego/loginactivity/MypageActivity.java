package com.threego.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import static androidx.core.view.GravityCompat.START;

public class MypageActivity extends AppCompatActivity {
    ImageView iv_menu;
    ImageButton ibtn_close;
    Button btn_review;
    RatingBar ratingBar;
    ArrayList<ReviewVO> review = new ArrayList<>();
    TextView tv_r_name, tv_r_phone, tv_r_gender, tv_r_age, tv_r_box;
    DrawerLayout drawerLayout;
    Button btn_delivery, btn_mypage, btn_ad, btn_money, btn_notice, btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        btn_review = findViewById(R.id.btn_review);

        ratingBar = findViewById(R.id.ratingBar);

        tv_r_name = findViewById(R.id.tv_r_name);
        tv_r_gender = findViewById(R.id.tv_r_gender);
        tv_r_age = findViewById(R.id.tv_r_age);
        tv_r_box = findViewById(R.id.tv_r_box);
        tv_r_phone = findViewById(R.id.tv_r_phone);

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
                Intent intent = new Intent(MypageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypageActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypageActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });

        btn_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypageActivity.this, AdActivity.class);
                startActivity(intent);
            }
        });

        btn_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypageActivity.this, MoneyActivity.class);
                startActivity(intent);
            }
        });
        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypageActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });

        // 버튼 클릭 시 ReviewActivity로 이동
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypageActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        // 별점 변경
        ratingBar.setNumStars(5);   // 보여줄 별 갯수
        ratingBar.setRating(3.5f); // 점수

        // DB 연동 필요!
        review.add(new ReviewVO("더시티", 27, "010-4200-5974", "남자", 1112));

        tv_r_name.setText(review.get(0).getR_name());
        tv_r_gender.setText(review.get(0).getR_gender());
        tv_r_age.setText(review.get(0).getR_age() + "");
        tv_r_box.setText(review.get(0).getR_box() + "");
        tv_r_phone.setText(review.get(0).getR_phone());



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