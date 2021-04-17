package com.threego.loginactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.skt.Tmap.TMapPolygon;
import com.skt.Tmap.TMapTapi;
import com.skt.Tmap.TMapView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MapActivity extends AppCompatActivity {
    Button btn_cancel, btn_tmap;
    BottomNavigationView bv, bv2;
    TextView tv_new;

    StringRequest stringRequest;
    RequestQueue requestQueue;

    JSONArray jarr;
    LinearLayout linearLayoutTmap;
    TMapView tMapView;
    TMapMarkerItem mapMarkerItem1, mapMarkerItem2, mapMarkerItem3;
    Bitmap bitmap1, bitmap2, bitmap3;
    TMapPoint tMapPoint1, tMapPoint2, tMapPoint3, tStart, tEnd;
    ArrayList<TMapPoint> alTMapPoint = new ArrayList<TMapPoint>();
    TMapCircle tMapCircle;

    Double dl_r_longi, dl_r_lati, dl_c_longi, dl_c_lati, dl_s_longi, dl_s_lati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_tmap = findViewById(R.id.btn_tmap);

        tv_new = findViewById(R.id.tv_new);

        bv = (BottomNavigationView) findViewById(R.id.menu_new);
        bv2 = (BottomNavigationView) findViewById(R.id.menu_choice);
        linearLayoutTmap = findViewById(R.id.linearLayoutTmap);

        // 통신
        String url = "http://222.102.104.230:8081/threego/deliveryAll.do";

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

                    Log.v("soo",deliveryVO.getDl_c_longi()+"");

                    dl_c_lati = Double.parseDouble(deliveryVO.getDl_c_lati());
                    dl_c_longi = Double.parseDouble(deliveryVO.getDl_c_longi());
                    dl_s_longi=Double.parseDouble(deliveryVO.getDl_s_longi());
                    dl_s_lati=Double.parseDouble(deliveryVO.getDl_s_lati());
                    dl_r_longi=Double.parseDouble(deliveryVO.getDl_r_longi());
                    dl_r_lati=Double.parseDouble(deliveryVO.getDl_r_lati());
                    Log.v("soo2",dl_c_longi+"");

                    tMapView = new TMapView(MapActivity.this);
                    tMapView.setSKTMapApiKey("l7xxa613be7f03824d6db3a06668a8760374");

                    mapMarkerItem1 = new TMapMarkerItem();
                    mapMarkerItem2 = new TMapMarkerItem();
                    mapMarkerItem3 = new TMapMarkerItem();

                    tMapPoint1 = new TMapPoint(dl_r_longi,dl_r_lati);   // 현위치 ~~ 경도/위도 longi lati로 찍어야 됨
                    tMapPoint2 = new TMapPoint(dl_c_longi,dl_c_lati); // 조대
                    tMapPoint3 = new TMapPoint(dl_s_longi,dl_s_lati); // 충장로

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
                    mapMarkerItem1.setCalloutTitle("스마트인재개발원");
                    tMapView.addMarkerItem("mapMarkerItem1",mapMarkerItem1);

                    mapMarkerItem2.setIcon(bitmap2);
                    mapMarkerItem2.setPosition(0.5f,1.0f);
                    mapMarkerItem2.setTMapPoint(tMapPoint2);
                    mapMarkerItem2.setCanShowCallout(true);
                    mapMarkerItem2.setCalloutTitle(deliveryVO.getDl_address());
                    tMapView.addMarkerItem("mapMarkerItem2",mapMarkerItem2);

                    mapMarkerItem3.setIcon(bitmap3);
                    mapMarkerItem3.setPosition(0.5f,1.0f);
                    mapMarkerItem3.setTMapPoint(tMapPoint3);
                    mapMarkerItem3.setCanShowCallout(true);
                    mapMarkerItem3.setCalloutTitle(deliveryVO.getDl_shop());
                    tMapView.addMarkerItem("mapMarkerItem3",mapMarkerItem3);

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
                                TMapPoint tStart =tMapPoint1;
                                TMapPoint tAnd = tMapPoint3;
                                TMapPoint tEnd = tMapPoint2;

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
                                    pathInfo.put("rGoName", deliveryVO.getDl_address());
                                    pathInfo.put("rGoX",dl_c_longi+"");
                                    pathInfo.put("rGoY", dl_c_lati+"");

                                    // 출발지 (현위치)
                                    pathInfo.put("rStName", "출발지");
                                    pathInfo.put("rStX", dl_r_longi+"");
                                    pathInfo.put("rStY", dl_r_lati+"");

                                    // 경유지
                                    pathInfo.put("rV1Name", deliveryVO.getDl_shop());
                                    pathInfo.put("rV1X", dl_s_longi+"");
                                    pathInfo.put("rV1Y", dl_s_lati+"");

                                    Log.v("soo5",dl_c_longi+"");

                                    tMapTapi.invokeRoute(pathInfo);
                                }
                            }).setNegativeButton("취소",null).show();

                        }
                    });

                    tMapView.setCenterPoint(dl_r_longi,dl_r_lati);
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
                temp.put("dl_status", tv_new.getText().toString());
                return temp;
            }
        }; // end request
        requestQueue.add(stringRequest);

        // 버튼 누르면 각각 이동하기
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(bv+""));
                startActivity(intent);
            }
        });

    }

}
