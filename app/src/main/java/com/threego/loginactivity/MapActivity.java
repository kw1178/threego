package com.threego.loginactivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.service.restrictions.RestrictionsReceiver;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.skt.Tmap.TMapCircle;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapTapi;
import com.skt.Tmap.TMapView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapActivity extends AppCompatActivity {
    Button btn_cancel, btn_tmap, btn_select;
    BottomNavigationView bv, bv2;
    TextView tv_new, tv_address2, tv_map_shop, tv_map_food, tv_map_foodfinish, tv_map_call, tv_distoadd, tv_distoshop
            , textView24;

    StringRequest stringRequest, stringRequest2;
    RequestQueue requestQueue, requestQueue2;

    JSONArray jarr;
    LinearLayout linearLayoutTmap;
    TMapView tMapView;
    TMapMarkerItem mapMarkerItem1, mapMarkerItem2, mapMarkerItem3;
    Bitmap bitmap1, bitmap2, bitmap3;
    TMapPoint tMapPoint1, tMapPoint2, tMapPoint3;
    ArrayList<TMapPoint> alTMapPoint = new ArrayList<TMapPoint>();
    TMapCircle tMapCircle;
    Fragment_choice fragment_choice;
    FrameLayout list;

    String dl_r_longi, dl_r_lati, dl_c_longi, dl_c_lati, dl_s_longi, dl_s_lati;

    //ArrayList<String> dialog = new ArrayList<>();
    ListView listView;

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        fragment_choice = new Fragment_choice();
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_tmap = findViewById(R.id.btn_tmap);
        btn_select = findViewById(R.id.btn_select);

        list = findViewById(R.id.list);

        tv_new = findViewById(R.id.tv_new);
        tv_address2 = findViewById(R.id.tv_c_address);

        tv_map_shop = findViewById(R.id.tv_map_shop);
        tv_map_foodfinish = findViewById(R.id.tv_map_foodfinish);
        tv_map_food = findViewById(R.id.tv_map_food);
        tv_map_call = findViewById(R.id.tv_map_call);
        tv_distoadd = findViewById(R.id.tv_distoadd);
        tv_distoshop = findViewById(R.id.tv_distoshop);
        textView24 = findViewById(R.id.textView24);

        bv = (BottomNavigationView) findViewById(R.id.menu_new);
        bv2 = (BottomNavigationView) findViewById(R.id.menu_choice);
        linearLayoutTmap = findViewById(R.id.linearLayoutTmap);

        listView = findViewById(R.id.listview);
        listView.setVisibility(View.INVISIBLE);


        // 통신
        String url = "http://222.102.104.230:8081/threego/location.do";

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("soo",response+"");
                try {
                    jarr = new JSONArray(response);
                for (int i=0,j=jarr.length(); i<j; i++){
                    JSONObject jobj = jarr.getJSONObject(i);
                  DeliveryVO  deliveryVO = new DeliveryVO();

                    deliveryVO.setDl_r_lati(jobj.getString("dl_r_lati"));
                    deliveryVO.setDl_r_longi(jobj.getString("dl_r_longi"));
                    deliveryVO.setDl_c_lati(jobj.getString("dl_c_lati"));
                    deliveryVO.setDl_c_longi(jobj.getString("dl_c_longi"));
                    deliveryVO.setDl_s_lati(jobj.getString("dl_s_lati"));
                    deliveryVO.setDl_s_longi(jobj.getString("dl_s_longi"));
                    deliveryVO.setDl_address(jobj.getString("dl_address"));
                    deliveryVO.setDl_shop(jobj.getString("dl_shop"));
                    deliveryVO.setDl_location(jobj.getString("dl_location"));
                    deliveryVO.setDl_call(jobj.getInt("dl_call"));
                    deliveryVO.setDl_shop(jobj.getString("dl_shop"));
                    deliveryVO.setDl_food(jobj.getString("dl_food"));
                    deliveryVO.setDl_cooktime(jobj.getString("dl_cooktime"));
                    deliveryVO.setDl_distoadd(jobj.getString("dl_distoadd"));
                    deliveryVO.setDl_distoshop(jobj.getString("dl_distoshop"));

                    Log.v("soo",deliveryVO.getDl_c_longi()+"");
                    tv_map_call.setText(deliveryVO.getDl_call()+"");
                    Log.v("soo",deliveryVO.getDl_call()+"");
                    tv_map_food.setText(deliveryVO.getDl_food());
                    tv_map_foodfinish.setText(deliveryVO.getDl_cooktime());
                    tv_map_shop.setText(deliveryVO.getDl_shop());
                    tv_distoadd.setText(deliveryVO.getDl_distoadd());
                    tv_distoshop.setText(deliveryVO.getDl_distoshop());

                    dl_c_lati = deliveryVO.getDl_c_lati();
                    dl_c_longi = deliveryVO.getDl_c_longi();
                    dl_s_longi=deliveryVO.getDl_s_longi();
                    dl_s_lati=deliveryVO.getDl_s_lati();
                    dl_r_longi=deliveryVO.getDl_r_longi();
                    dl_r_lati=deliveryVO.getDl_r_lati();
                    Log.v("soo2",dl_c_longi+"");

                    tv_address2.setText(deliveryVO.getDl_address());

                    tMapView = new TMapView(MapActivity.this);
                    tMapView.setSKTMapApiKey("l7xxa613be7f03824d6db3a06668a8760374");

                    mapMarkerItem1 = new TMapMarkerItem();
                    mapMarkerItem2 = new TMapMarkerItem();
                    mapMarkerItem3 = new TMapMarkerItem();

                    tMapPoint1 = new TMapPoint(Double.parseDouble(dl_r_lati),Double.parseDouble(dl_r_longi));   // 현위치 ~~ 경도/위도 longi lati로 찍어야 됨
                    tMapPoint2 = new TMapPoint(Double.parseDouble(dl_c_lati),Double.parseDouble(dl_c_longi)); // 조대
                    tMapPoint3 = new TMapPoint(Double.parseDouble(dl_s_lati),Double.parseDouble(dl_s_longi)); // 충장로

                    Log.v("soo2",dl_c_longi+"");
                    Log.v("soo3",tMapPoint1+"");
                    Log.v("soo3",tMapPoint2+"");
                    Log.v("soo3",tMapPoint3+"");

                    bitmap1 = BitmapFactory.decodeResource(MapActivity.this.getResources(),R.drawable.poi_star);
                    bitmap2 = BitmapFactory.decodeResource(MapActivity.this.getResources(),R.drawable.poi_star1);
                    bitmap3 = BitmapFactory.decodeResource(MapActivity.this.getResources(),R.drawable.poi_star2);

                    mapMarkerItem1.setIcon(bitmap1);
                    mapMarkerItem1.setPosition(0.5f,1.0f);
                    mapMarkerItem1.setTMapPoint(tMapPoint1);
                    mapMarkerItem1.setCanShowCallout(true);
                    mapMarkerItem1.setCalloutTitle(deliveryVO.getDl_location()); // 출발지
                    tMapView.addMarkerItem("mapMarkerItem1",mapMarkerItem1);

                    mapMarkerItem2.setIcon(bitmap2);
                    mapMarkerItem2.setPosition(0.5f,1.0f);
                    mapMarkerItem2.setTMapPoint(tMapPoint2);
                    mapMarkerItem2.setCanShowCallout(true);
                    mapMarkerItem2.setCalloutTitle(deliveryVO.getDl_address());
                    tMapView.addMarkerItem("mapMarkerItem2",mapMarkerItem2);
                    Log.v("soo4",deliveryVO.getDl_address()+"");

                    mapMarkerItem3.setIcon(bitmap3);
                    mapMarkerItem3.setPosition(0.5f,1.0f);
                    mapMarkerItem3.setTMapPoint(tMapPoint3);
                    mapMarkerItem3.setCanShowCallout(true);
                    mapMarkerItem3.setCalloutTitle(deliveryVO.getDl_shop());
                    tMapView.addMarkerItem("mapMarkerItem3",mapMarkerItem3);
                    Log.v("soo4",deliveryVO.getDl_shop()+"");

                    alTMapPoint.add(tMapPoint1);
                    alTMapPoint.add(tMapPoint2);
                    alTMapPoint.add(tMapPoint3);

                    tMapCircle = new TMapCircle();
                    tMapCircle.setCenterPoint(tMapPoint1);
                    tMapCircle.setLineColor(Color.BLUE);
                    tMapCircle.setCircleWidth(2);
                    tMapCircle.setAreaColor(Color.GRAY);
                    tMapCircle.setAreaAlpha(100);
                    tMapCircle.setRadius(1500); // 원 크기
                    tMapView.addTMapCircle("Area1", tMapCircle);

                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                TMapPoint tStart =new TMapPoint(Double.parseDouble(dl_r_lati),Double.parseDouble(dl_r_longi));
                                TMapPoint tAnd = new TMapPoint(Double.parseDouble(dl_s_lati),Double.parseDouble(dl_s_longi));
                                TMapPoint tEnd = new TMapPoint(Double.parseDouble(dl_c_lati),Double.parseDouble(dl_c_longi));


                                TMapPolyLine tMapPolyLine1 = new TMapData().findPathData(tStart,tAnd);
                                tMapPolyLine1.setLineWidth(2);
                                tMapPolyLine1.setLineColor(Color.RED);
                                tMapPolyLine1.setOutLineColor(Color.RED);
                                tMapView.addTMapPolyLine("And",tMapPolyLine1);


                                TMapPolyLine tMapPolyLine2 = new TMapData().findPathData(tAnd,tEnd);
                                tMapPolyLine2.setLineWidth(2);
                                tMapPolyLine2.setLineColor(Color.GREEN);
                                tMapPolyLine2.setOutLineColor(Color.GREEN);
                                tMapView.addTMapPolyLine("End",tMapPolyLine2);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }.start();

                    tMapView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mapMarkerItem1.getName();
                            mapMarkerItem2.getName();
                            mapMarkerItem3.getName();
                        }
                    });
                    tMapView.MapZoomOut();



                    btn_tmap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new AlertDialog.Builder(MapActivity.this)
                                    .setTitle("경로안내").setMessage("경로안내를 시작하시겠습니까?").setIcon(R.drawable.logo2).setPositiveButton("안내시작", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    TMapTapi tMapTapi = new TMapTapi(MapActivity.this);
                                    HashMap pathInfo = new HashMap();

                                    // 목적지
                                    pathInfo.put("rGoName", tv_address2.getText().toString());
                                    pathInfo.put("rGoX",dl_c_longi);
                                    pathInfo.put("rGoY", dl_c_lati);

                                    // 출발지 (현위치)
                                    pathInfo.put("rStName", deliveryVO.getDl_location());
                                    pathInfo.put("rStX", dl_r_longi);
                                    pathInfo.put("rStY", dl_r_lati);

                                    // 경유지
                                    pathInfo.put("rV1Name", deliveryVO.getDl_shop());
                                    pathInfo.put("rV1X", dl_s_longi);
                                    pathInfo.put("rV1Y", dl_s_lati);

                                    Log.v("soo5",dl_c_longi+"");

                                    tMapTapi.invokeRoute(pathInfo);
                                }
                            }).setNegativeButton("취소",null).show();

                        }
                    });

                    tMapView.setCenterPoint(Double.parseDouble(dl_r_longi),Double.parseDouble(dl_r_lati));
                    tMapView.setZoomLevel(14);
                    linearLayoutTmap.addView(tMapView);

                    Log.v("soo5",dl_c_longi+"");


                } // end for
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"된다",Toast.LENGTH_SHORT).show();
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
                Intent intent1 = getIntent();
                int dl_number1 = intent1.getExtras().getInt("dl_number");

                temp.put("dl_number", dl_number1+"");
                Log.v("number",dl_number1+"");
                return temp;
            }
        }; // end request
        requestQueue.add(stringRequest);






        // 버튼 누르면 각각 이동하기
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 신규상태 -> 배정상태로 업데이트 통신
        String statusUrl = "http://222.102.104.230:8081/threego/statusUpdate.do";
        requestQueue2 = Volley.newRequestQueue(getApplicationContext());

        stringRequest2 = new StringRequest(Request.Method.POST, statusUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {




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
                Intent intent1 = getIntent();
                int dl_number1 = intent1.getExtras().getInt("dl_number");

                temp.put("dl_number", dl_number1+"");
                return temp;
            }
        };


        // 가게에 보낼 메시지 선택 listView
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MapActivity.this).setTitle("배정선택").setMessage("배정 하시겠습니까?").setIcon(R.drawable.logo2).setPositiveButton("픽업시작", new DialogInterface.OnClickListener() {
                    @SuppressLint("IntentReset")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ArrayList<String> dialog1 = new ArrayList<>();
                        dialog1.add("10분 뒤 도착예정입니다.");
                        dialog1.add("가는 중");
                        dialog1.add("준비해라");
                        dialog1.add("배달잡았다.");
                        dialog1.add("내가 1등임 ㅇㅇ");

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.dialog_list, dialog1);
                        listView.setAdapter(adapter);

                        try {
                            tv_map_call.setVisibility(View.INVISIBLE);
                            tv_map_food.setVisibility(View.INVISIBLE);
                            tv_map_foodfinish.setVisibility(View.INVISIBLE);
                            tv_distoadd.setVisibility(View.INVISIBLE);
                            tv_distoshop.setVisibility(View.INVISIBLE);
                            tv_map_shop.setVisibility(View.INVISIBLE);
                            textView24.setVisibility(View.INVISIBLE);

                            listView.setVisibility(View.VISIBLE);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    requestQueue2.add(stringRequest2);

                                    Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("sms:010-4200-5974")); // 고객 전화번호 DB값 필요!
                                    intent.putExtra("sms_body",adapter.getItem(position)+"");
                                    startActivity(intent);
                                    finish();
                                }
                            });

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).setNegativeButton("취소",null).show();
            }
        });
    }

}
