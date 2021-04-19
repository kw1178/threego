package com.threego.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static androidx.core.view.GravityCompat.START;

public class MypageActivity extends AppCompatActivity {
    ImageView iv_menu;
    ImageButton ibtn_close, ibtn_back;
    Button btn_review;
    RatingBar ratingBar;

    TextView tv_r_name, tv_r_phone, tv_r_gender, tv_r_age, tv_r_box, tv_1, tv_list1, tv_list2, tv_list3, tv_list4, tv_bg;
    DrawerLayout drawerLayout;
    Button btn_delivery, btn_mypage, btn_ad, btn_money, btn_notice, btn_home;
    JSONArray jarr, r_jarr;
    ArrayList<String> review = new ArrayList<>();
    ListView list_review;

    //통신부분
    // Volley Library 사용
    // 1. Volley Library 임포트 하기!
    // 2. RequestQueue, StringRequest
    RequestQueue requestQueue, requestQueue2; // Server와 통신할 통로
    StringRequest stringRequest, stringRequest2; // 내가 전송할 데이터!


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        
        // id 값 찾기
        btn_review = findViewById(R.id.btn_review);
        ratingBar = findViewById(R.id.ratingBar);
        tv_r_name = findViewById(R.id.tv_r_name);
        tv_r_gender = findViewById(R.id.tv_r_gender);
        tv_r_age = findViewById(R.id.tv_r_age);
        tv_r_box = findViewById(R.id.tv_r_box);
        tv_r_phone = findViewById(R.id.tv_r_phone);
        tv_1 = findViewById(R.id.tv_1);

        // 네비게이션 id값 찾기
        iv_menu = findViewById(R.id.iv_menu);
        ibtn_close = findViewById(R.id.ibtn_close);
        drawerLayout = findViewById(R.id.drawer_layout);
        btn_delivery = findViewById(R.id.btn_delivery);
        btn_mypage = findViewById(R.id.btn_mypage);
        btn_ad = findViewById(R.id.btn_ad);
        btn_money = findViewById(R.id.btn_money);
        btn_notice = findViewById(R.id.btn_notice);
        btn_home = findViewById(R.id.btn_home);
        Log.v("changsoo", "ok"); // debug 용도

        // listView에 사용되는 애들 id값 & invisible 설정
        list_review = findViewById(R.id.list_review);
        list_review.setVisibility(View.INVISIBLE);
        tv_list1 = findViewById(R.id.tv_list1);
        tv_list1.setVisibility(View.INVISIBLE);
        tv_list2 = findViewById(R.id.tv_list2);
        tv_list2.setVisibility(View.INVISIBLE);
        tv_list3 = findViewById(R.id.tv_list3);
        tv_list3.setVisibility(View.INVISIBLE);
        tv_list4 = findViewById(R.id.tv_list4);
        tv_list4.setVisibility(View.INVISIBLE);
        tv_bg = findViewById(R.id.tv_bg);
        tv_bg.setVisibility(View.INVISIBLE);
        ibtn_back = findViewById(R.id.ibtn_back);
        ibtn_back.setVisibility(View.INVISIBLE);

        // requestQueue 생성! Spring이랑 연결하기!
        String url = "http://222.102.104.230:8081/threego/mypage.do";

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 서버에서 돌려준 응답을 처리!
                try {
                    jarr = new JSONArray(response);
                    Log.v("changsoo", jarr + "");
                    JSONObject jobj = jarr.getJSONObject(0);
                    RiderVO riderVO = new RiderVO();

                    riderVO.setR_id(jobj.getString("r_id"));
                    riderVO.setR_name(jobj.getString("r_name"));
                    riderVO.setR_gender(jobj.getString("r_gender"));
                    riderVO.setR_box((jobj.getInt("r_box")));
                    riderVO.setR_age((jobj.getInt("r_age")));
                    riderVO.setR_phone(jobj.getString("r_phone"));

                    tv_r_phone.setText(riderVO.getR_phone());
                    tv_r_name.setText(riderVO.getR_name());
                    tv_r_gender.setText(riderVO.getR_gender());
                    tv_r_age.setText(String.valueOf(riderVO.getR_age()));
                    tv_r_box.setText(String.valueOf(riderVO.getR_box()));
                    tv_1.setText(riderVO.getR_id());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();   // 값 넘어오는지 확인용
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });

        // 버튼 클릭 없어도 통신 전송 가능
        requestQueue.add(stringRequest);



        // review list 보여주기!
        String URL = "http://222.102.104.230:8081/threego/review.do";
        requestQueue2 = Volley.newRequestQueue(getApplicationContext());
        stringRequest2 = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    float avg = 0f;
                    int [] result = new int [4];
                    int sum = 0;
                    r_jarr = new JSONArray(response);
                    for (int i=0, j= r_jarr.length(); i<j; i++){

                        JSONObject jobj = r_jarr.getJSONObject(i);

                        RatingsVO ratingsVO = new RatingsVO();

                        // ArrayAdapter 를 한줄에 보여주기 위한 것
                        review.add("\t"+"\t"+"\t"+"\t"+jobj.getString("ra_reviewnum")+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+jobj.getString("dl_number")+"\t"+"\t"+"\t"+"\t"+jobj.getString("ra_evals"));

                        Log.v("changsoo", review+"");

                        // 별점 평균 구하기  result[3] = ra_rating
                        ratingsVO.setRa_rating(jobj.getInt("ra_rating"));
                        result[3] = ratingsVO.getRa_rating();
                        sum += result[3];
                        Log.v("changsoo", sum+""); // 확인용
                        avg = (float) sum/j;
                        Log.v("changsoo", avg+""); // 확인용용
                       ratingBar.setRating(avg);

                        // list-up 작업, custom 할 수 없기 때문에 ArrayAdapter 사용
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(),R.layout.review_list,review);
                        list_review.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show(); // DB값 확인용
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue2.add(stringRequest2);
//        {
//
//        // request
//        @Nullable
//        @Override   // 리턴타입         메소드이름(매개변수 X) 예외처리
//        protected Map<String, String> getParams() throws AuthFailureError {
//            // 전송할 데이터 key, value로 셋팅하기!
//            Map<String,String> temp = new HashMap<>();
//            temp.put("r_id",tv_1.getText().toString());
//
//            return temp;
//        }
//    };

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

        // 버튼 클릭 시 listview 보이게 하고 첫 번째 화면은 안보이게 하기! -> 다른 페이지 전환 필요 X
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_review.setVisibility(View.VISIBLE);
                tv_list1.setVisibility(View.VISIBLE);
                tv_list2.setVisibility(View.VISIBLE);
                tv_list3.setVisibility(View.VISIBLE);
                tv_list4.setVisibility(View.VISIBLE);
                tv_bg.setVisibility(View.VISIBLE);
                ibtn_back.setVisibility(View.VISIBLE);

                btn_review.setVisibility(View.INVISIBLE);
            }
        });

        // 뒤로가기 버튼 눌렀을 때 첫 번째 화면 visible
        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_review.setVisibility(View.INVISIBLE);
                tv_list1.setVisibility(View.INVISIBLE);
                tv_list2.setVisibility(View.INVISIBLE);
                tv_list3.setVisibility(View.INVISIBLE);
                tv_list4.setVisibility(View.INVISIBLE);
                tv_bg.setVisibility(View.INVISIBLE);
                ibtn_back.setVisibility(View.INVISIBLE);

                btn_review.setVisibility(View.VISIBLE);
            }
        });

        // 별점 변경
        ratingBar.setNumStars(5);   // 보여줄 별 갯수
        // ratingBar.setRating(3.5f); // 점수


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