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
    ArrayList<ReviewVO> review = new ArrayList<>();
    ListView list_review;
    ImageButton ibtn_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        list_review = findViewById(R.id.list_review);
        ibtn_review = findViewById(R.id.ibtn_review);

        // DB 연동 필요!
        review.add(new ReviewVO(1, 2342, "배송이 빨라서 좋았어요."));
        review.add(new ReviewVO(2, 2243, "친절하셨습니다."));
        review.add(new ReviewVO(3, 2532, "안내문자 갑사합니다."));
        review.add(new ReviewVO(4, 1423, "약간 느린거 같아요."));
        review.add(new ReviewVO(5, 6342, "음식이 식었어요 ㅠㅠ"));

        ReviewAdapter adapter = new ReviewAdapter(getApplicationContext(), R.layout.review_list, review);
        list_review.setAdapter(adapter);

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