package com.threego.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    ImageButton ibtn_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        ibtn_review = findViewById(R.id.ibtn_review);

        // DB 연동 필요!




        // 뒤로가기 버튼 클릭 시 MypageActivity로 이동
        ibtn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });
    }
}