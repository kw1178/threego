package com.threego.loginactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dinuscxj.progressbar.CircleProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static androidx.core.view.GravityCompat.START;

public class AdActivity extends AppCompatActivity {

    //adRequestQueue, adStringRequest 일반광고
    // vidRequestQueue, vidStringRequest 영상광고
    RequestQueue adReq, vidReq, moneyReq;
    StringRequest adStrReq, vidStrReq, moneyStrReq;

    ImageView iv_menu, iv_gif;
    ImageButton ibtn_close;
    TextView tv_money, tv_ad_name, tv_time, tv_rider, textView6;
    ProgressBar pro;
    CircleProgressBar circle;
    VideoView videoView;
    MediaController mediaController;
    //String videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"; // 경로, 확장자까지 필요
    DrawerLayout drawerLayout;
    Button btn_delivery, btn_mypage, btn_ad, btn_money, btn_notice, btn_change, btn_home;

    // 하단 광고 리스트뷰
    ListView lv;
    AdAdapter adapter;
    ArrayList<AdVO> list = new ArrayList<>();

    JSONObject obj;
    String r_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        tv_ad_name = findViewById(R.id.tv_ad_name);
        tv_money = findViewById(R.id.tv_money);
        tv_time = findViewById(R.id.tv_time);

        //pro = findViewById(R.id.pro);
        circle = findViewById(R.id.circle);

        videoView = (VideoView) findViewById(R.id.videoView);
        //iv_gif = findViewById(R.id.imageView4);
        //Glide.with(this).load("https://media4.giphy.com/media/K4x1ZL36xWCf6/giphy.gif").into(iv_gif);

        //btn_change = findViewById(R.id.btn_change);

        iv_menu = findViewById(R.id.iv_menu);
        ibtn_close = findViewById(R.id.ibtn_close);
        tv_rider = findViewById(R.id.tv_rider);

        drawerLayout = findViewById(R.id.drawer_layout);

        btn_delivery = findViewById(R.id.btn_delivery);
        btn_mypage = findViewById(R.id.btn_mypage);
        btn_ad = findViewById(R.id.btn_ad);
        btn_money = findViewById(R.id.btn_money);
        btn_notice = findViewById(R.id.btn_notice);
        btn_home = findViewById(R.id.btn_home);

        // 리스트뷰 adVO, adAdapter, adlist
        lv = findViewById(R.id.listView);
        //list.add(new AdVO("https://i.imgur.com/NYJ9bZr.jpg", "고양이", "노르웨이숲", "배달 중"));
        //list.add(new AdVO("https://i.imgur.com/NYJ9bZr.jpg", "고양이", "코숏 기여워", ""));
        //list.add(new AdVO("https://i.imgur.com/NYJ9bZr.jpg", "고양이", "애교쟁이 샴", ""));
        //list.add(new AdVO("https://i.imgur.com/NYJ9bZr.jpg", "고양이", "꾹꾹이 얍", ""));


        Intent intent = getIntent();
        r_id = intent.getExtras().getString("r_id");

        // 네비게이션 바 이동
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdActivity.this, MainActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });

        btn_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdActivity.this, ListActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
            }
        });

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdActivity.this, MypageActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
            }
        });

        btn_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdActivity.this, AdActivity.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
            }
        });

        btn_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdActivity.this, MoneyActivity.class);
                intent.putExtra("r_id",r_id);
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

        // 영상광고 연동
        //tv_ad_name.setText("BURGER KING 기네스 와퍼");
        //tv_money.setText("2350");

        vidReq = Volley.newRequestQueue(getApplicationContext());
        String vidUrl = "http://222.102.104.235:8081/threego/appvid.do";

        vidStrReq = new StringRequest(Request.Method.POST, vidUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d("test", response.toString());
                try {
                    obj = new JSONObject(response);
                    tv_ad_name.setText(obj.getString("a_contents")+" : "+obj.getString("a_name"));
                    tv_time.setText(obj.getInt("a_time")+" / 100회");
                    tv_money.setText(String.valueOf((100-obj.getInt("a_time"))*60)+"원");
                    circle.setProgress(100-obj.getInt("a_time"));
                    //String rider = obj.getString("r_id");
                    String adnum = obj.getString("a_adnum");

                    if(obj.getInt("a_time")>0){
                        int id = getRawResIdByName("alssa");
                        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+id);
                        videoView.setVideoURI(uri); // url 연결
                        videoView.start();

                    }else if(obj.getInt("a_time")==0){
                        // 광고 횟수 0이 되면 예치금으로 업데이트되는 통신
                        moneyReq = Volley.newRequestQueue(getApplicationContext());
                        String moneyUrl = "http://222.102.104.235:8081/threego/moneyUpdate.do";

                        moneyStrReq = new StringRequest(Request.Method.POST, moneyUrl, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Log.d("test", response.toString());
                                if(response != null){
                                    Toast.makeText(getApplicationContext(), "예치금으로 전환되었습니다!", Toast.LENGTH_SHORT).show();
                                    tv_money.setText("0");
                                }else{
                                    Toast.makeText(getApplicationContext(), "예치금 전환 실패!", Toast.LENGTH_SHORT).show();
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
                                temp.put("a_money", tv_money.getText().toString());
                                temp.put("a_adnum", adnum);
                                //Intent intent = getIntent();
                                r_id = intent.getExtras().getString("r_id");
                                temp.put("r_id", r_id);
                                return temp;
                            }
                        };
                        moneyReq.add(moneyStrReq);

                    } //_moneyReq

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
        vidReq.add(vidStrReq);

        // videoView 사용하기
        mediaController = new MediaController(this); // 미디어 제어 부분
        mediaController.setAnchorView(videoView);   // videoView에 연결
        videoView.setMediaController(mediaController);  // 미디어 제어 부분 세팅


        // progress bar 바꾸기, 알고리즘 필요
        //pro.setProgress(getPlayTime("android.resource://"+getPackageName()+"/"+id));

        // 이미지뷰 연동
        adReq = Volley.newRequestQueue(getApplicationContext());
        String adUrl = "http://222.102.104.235:8081/threego/appads.do";

        adStrReq = new StringRequest(Request.Method.GET, adUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d("test", response.toString()); // 성공

                try {
                    JSONArray arr = new JSONArray(response);
                    for(int i=0; i<arr.length(); i++){
                        JSONObject obj = arr.getJSONObject(i);
                        //if(obj.getString("a_type")=="pic") {
                            AdVO vo = new AdVO();
                            vo.setAdTitle(obj.getString("a_name"));
                            vo.setSrc(obj.getString("a_src"));
                            vo.setAdContent(obj.getString("a_contents"));
                            vo.setAdType(obj.getString("a_type"));
                            list.add(vo);
                            //Log.d("test", vo.toString());

                            adapter = new AdAdapter(getApplication(), R.layout.ad_list, list);
                            lv.setAdapter(adapter);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("test", error.toString(), error);
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> temp = new HashMap<>();
                //Intent intent = getIntent();
                r_id = intent.getExtras().getString("r_id");
                temp.put("r_id", r_id);
                return temp;
            }
        };
        adStrReq.setShouldCache(false);
        adReq.add(adStrReq);


        // 이 버튼 일단 봉인...
        // 예치금 전환 버튼 클릭시 Toast 메시지 띄우기
//        btn_change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "예치금이 전환되었습니다. ^^", Toast.LENGTH_SHORT).show();
//            }
//        });

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

    } //_onCreate

    // 네비게이션 설정
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(START)) {
            drawerLayout.closeDrawer(START);
        } else {
            super.onBackPressed();
        }
    }

    // 영상 길이 구하기
    private int getPlayTime(String path){
        MediaMetadataRetriever ret = new MediaMetadataRetriever();
        ret.setDataSource(path);
        String time = ret.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

        long millisec = Long.parseLong(time);
        long second = millisec/1000;
//        long hours = duration/3600;
//        long minutes = (duration - hours * 3600) / 60;
//        long seconds = duration - (hours*3600+minutes*60);
        return (int)second;
    }

    // raw
    public int getRawResIdByName(String resName) {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
        Log.i("AndroidVideoView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }
}