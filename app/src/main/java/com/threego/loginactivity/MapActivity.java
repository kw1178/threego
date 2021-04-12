package com.threego.loginactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap gMap;
    Button btn_cancle, btn_ok;
    BottomNavigationView bv, bv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        btn_cancle = findViewById(R.id.btn_cancle);
        btn_ok = findViewById(R.id.btn_ok);
        bv = (BottomNavigationView) findViewById(R.id.menu_new);
        bv2 = (BottomNavigationView) findViewById(R.id.menu_choice);

        // 버튼 누르면 각각 이동하기
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(bv+""));
                startActivity(intent);
            }
        });

        // 이동 안됨... 선생님 도움 필요..ㅠㅠ
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(bv2+""));
                startActivity(intent);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // 구글 지도 API 띄우기
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        LatLng seoul = new LatLng(37.56, 126.97);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(seoul);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        gMap.addMarker(markerOptions);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 10));
    }


}
